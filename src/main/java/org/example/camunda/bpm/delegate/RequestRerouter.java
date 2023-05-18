package org.example.camunda.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Обработка события "Получен запрос с повышенным приоритетом"
 */

public class RequestRerouter implements JavaDelegate {

  private static final Logger log = LoggerFactory.getLogger(
      RequestRerouter.class);
  private FixedValue messageCorrelation;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String businessKey = execution.getBusinessKey();
    log.debug(RequestRerouter.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(messageCorrelation.getExpressionText())
        .processInstanceBusinessKey(businessKey)
        .correlate();
  }

  public void setMessageCorrelation(FixedValue messageCorrelation) {
    this.messageCorrelation = messageCorrelation;
  }
}
