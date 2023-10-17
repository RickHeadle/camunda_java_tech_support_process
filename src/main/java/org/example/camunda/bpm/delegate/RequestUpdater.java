package org.example.camunda.bpm.delegate;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Optional;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.RequestStatus;
import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.example.camunda.bpm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestUpdater implements JavaDelegate {

  private final RequestServiceImpl requestService;
  private final UserServiceImpl userService;

  @Autowired
  public RequestUpdater(RequestServiceImpl requestService, UserServiceImpl userService) {
    this.requestService = requestService;
    this.userService = userService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    Long entityId = Long.valueOf(
        execution.getVariable("requestEntityId").toString());
    Optional<Request> entity = requestService.findById(entityId);
    if (!entity.isPresent()) {
      throw new IllegalArgumentException("Entity not found by id: " + entityId);
    }
    Request request = entity.get();
    Map<String, Object> updatableValues = objectMapper.convertValue(
        execution.getVariableLocal("updatableValues"), Map.class);
    for (String key : updatableValues.keySet()) {
      String value = String.valueOf(updatableValues.get(key));
      switch (key) {
        case "solutionDescription":
          request.setSolution(value);
          break;
        case "status":
          request.setStatus(RequestStatus.valueOf(value.toUpperCase()));
          break;
        case "solver":
          //Если запрос отбракован системой - исполнитель не заполняется.
          if (!isNull(value)) {
            request.setSolver(userService.findByCamundaId(value));;
          }
          break;
      }
    }
  }

  /**
   * Camunda возвращает строку "null" вместо традиционного null-значения
   * в случае, когда производится попытка получить значение несуществующей переменной. <br>
   * В данном случае, идёт попытка получения идентификатора пользователя в системе Camunda,
   * который <b>не будет заполнен</b>, если запрос отбракован.
   * @param value значение из набора updatableValues
   * @return true if equals to "null" string
   */
  private boolean isNull(String value) {
    return value.equals("null");
  }
}
