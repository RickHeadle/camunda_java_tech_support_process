package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.RequestStatus;
import org.example.camunda.bpm.entity.User;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.example.camunda.bpm.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestProcessing implements JavaDelegate {

  private final UserServiceImpl userService;
  private final RequestServiceImpl requestService;

  @Autowired
  public RequestProcessing(UserServiceImpl userService, RequestServiceImpl requestService) {
    this.userService = userService;
    this.requestService = requestService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    final String debugInitMessage = "Executor %d is now processing request with EntityId = %d";
    final String debugEndMessage = "Request with EntityId = %d has been processed by Executor %d";
    Long entityId = (Long) execution.getVariableLocal("requestEntityId");
    User executor = (User) execution.getVariableLocal("executor");
    log.debug(String.format(debugInitMessage, executor.getId(), entityId));
    requestService.setStatus(entityId, RequestStatus.PROCESSING);
    //Ожидание - 1 минута
    Thread.sleep(60000);
    requestService.setStatus(entityId, RequestStatus.DONE);
    executor.setAvailable(true);
    log.debug(String.format(debugEndMessage, entityId, executor.getId()));
  }
}
