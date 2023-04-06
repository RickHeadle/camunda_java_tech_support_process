package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestEntityCreator implements JavaDelegate {

  private final RequestServiceImpl requestService;

  @Autowired
  public RequestEntityCreator(RequestServiceImpl requestService) {
    this.requestService = requestService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug("Adding new request to the database...");
    String requestText = execution.getVariable("requestText").toString();
    Long entityId = requestService.addNewRequest(requestText);
    log.debug("New request added to the database. EntityId = " + entityId);
  }
}
