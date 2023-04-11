package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Запросу присвоен исполнитель"
 */
@Slf4j
@Component
public class RequestExecutorSet implements JavaDelegate {

  public static final String EVENT_REQUEST_EXECUTOR_SET = "requestExecutorSet";

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug(RequestExecutorSet.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(EVENT_REQUEST_EXECUTOR_SET).correlate();
  }
}
