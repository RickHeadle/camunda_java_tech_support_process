package org.example.camunda.bpm.delegate;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.entity.User;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.example.camunda.bpm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExecutorSetter implements JavaDelegate {

  private final RequestServiceImpl requestService;
  private final UserServiceImpl userService;

  @Autowired
  public ExecutorSetter(RequestServiceImpl requestService, UserServiceImpl userService) {
    this.requestService = requestService;
    this.userService = userService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug("Setting up an executor for a new task...");
    Long entityId = (Long) execution.getVariableLocal("requestEntityId");
    Request request = getEntityFromDB(entityId);
    User executor = userService.getExecutorWithLeastRequests();
    request.setExecutor(executor);
    execution.setVariableLocal("executor", executor);
    log.debug(String.format("Request (ID = %d) got an executor (ID = %d)!",
        entityId, executor.getId()));
  }

  private Request getEntityFromDB(Long entityId) {
    Optional<Request> request = requestService.findById(entityId);
    if (!request.isPresent()) {
      throw new IllegalArgumentException(
          String.format("Запрос с entityId = %d отсутствует в БД!", entityId));
    }
    return request.get();
  }
}
