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
/*    Set<Entry<String, Object>> entries = execution.getVariablesLocal().entrySet();
    for (Entry<String, Object> entry : entries) {
      switch (entry.getKey()) {
        case ""
      }
    }*/
    ObjectMapper objectMapper = new ObjectMapper();
    Long entityId = (long) execution.getVariable("requestEntityId");
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
        case "executor":
          request.setExecutor(userService.findById(Long.valueOf(value)));
      }
    }
  }
}
