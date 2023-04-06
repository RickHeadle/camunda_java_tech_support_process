package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Получен запрос"
 */
@Slf4j
@Component
public class RequestReceivedMessageProcessorDelegate implements JavaDelegate {

  public static final String EVENT_REQUEST_RECEIVED_KEY = "requestReceived";

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug(RequestReceivedMessageProcessorDelegate.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(EVENT_REQUEST_RECEIVED_KEY).correlate();
  }
}
