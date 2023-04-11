package org.example.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Обработка события "Получен запрос с повышенным приоритетом"
 */

public class RequestPriorityIncreased implements JavaDelegate {

  private static final Logger log = LoggerFactory.getLogger(
      RequestPriorityIncreased.class);
  private FixedValue messageCorrelation;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String businessKey = execution.getBusinessKey();
    log.debug(RequestPriorityIncreased.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(messageCorrelation.getExpressionText())
        .processInstanceBusinessKey(businessKey)
        .correlate();
  }

  public void setMessageCorrelation(FixedValue messageCorrelation) {
    this.messageCorrelation = messageCorrelation;
  }
}
