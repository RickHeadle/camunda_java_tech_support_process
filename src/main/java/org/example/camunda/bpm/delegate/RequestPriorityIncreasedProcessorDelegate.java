package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.ProcessConstants;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Получен запрос с повышенным приоритетом"
 * @version 5
 */
@Slf4j
@Component(ProcessConstants.EVENT_REQUEST_PRIORITY_INCREASED)
public class RequestPriorityIncreasedProcessorDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug(RequestPriorityIncreasedProcessorDelegate.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(ProcessConstants.EVENT_REQUEST_PRIORITY_INCREASED)
        .correlateStartMessage();
  }
}
