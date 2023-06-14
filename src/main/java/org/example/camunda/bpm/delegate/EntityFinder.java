package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityFinder implements JavaDelegate {

  private final RequestServiceImpl requestService;

  @Autowired
  public EntityFinder(RequestServiceImpl requestService) {
    this.requestService = requestService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug("Searching for an entity...");
    Long entityId = Long.valueOf(
        execution.getVariableLocal("requestEntityId").toString());
    Request request = requestService.findById(entityId)
        .orElseThrow(() -> new IllegalArgumentException("Entity not found by id: " + entityId));
    execution.setVariableLocal("requestMessage", request.getMessage());
    log.debug("Entity found! Setting up a new requestMessage variable.");
  }
}
