package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Сохранение новых поступивших запросов в БД.
 */
@Slf4j
@Component
public class EntityCreator implements JavaDelegate {

  private final RequestServiceImpl requestService;

  @Autowired
  public EntityCreator(RequestServiceImpl requestService) {
    this.requestService = requestService;
  }

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug("Adding new request to the database...");
    String requestText = execution.getVariableLocal("requestMessage").toString();
    Long entityId = requestService.addNewRequest(requestText);
    execution.setVariableLocal("requestEntityId", entityId);
    log.debug("New request added to the database. EntityId = " + entityId);
  }
}
