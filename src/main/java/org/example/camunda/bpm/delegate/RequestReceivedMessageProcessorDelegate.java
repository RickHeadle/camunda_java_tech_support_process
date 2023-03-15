package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.camunda.bpm.ProcessConstants;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Получен запрос"
 * @deprecated использовался в устаревшей версии схемы
 * @version 2
 */
@Slf4j
@Deprecated
@Component(ProcessConstants.EVENT_REQUEST_RECEIVED_KEY)
public class RequestReceivedMessageProcessorDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug(RequestReceivedMessageProcessorDelegate.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(ProcessConstants.EVENT_REQUEST_RECEIVED_KEY)
        .correlate();
  }
}
