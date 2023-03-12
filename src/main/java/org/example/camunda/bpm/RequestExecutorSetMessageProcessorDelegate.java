package org.example.camunda.bpm;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Запросу присвоен исполнитель"
 * @version 4
 */
@Slf4j
@Component(ProcessConstants.EVENT_REQUEST_EXECUTOR_SET)
public class RequestExecutorSetMessageProcessorDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug(RequestExecutorSetMessageProcessorDelegate.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(ProcessConstants.EVENT_REQUEST_EXECUTOR_SET)
        .correlateStartMessage();
  }
}
