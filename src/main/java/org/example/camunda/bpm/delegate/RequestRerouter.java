package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Получен запрос с повышенным приоритетом"
 */
@Slf4j
@Component
public class RequestRerouter implements JavaDelegate {

  private FixedValue messageCorrelation;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String businessKey = execution.getBusinessKey();
    String requestMessage = execution.getVariable("requestMessage").toString();
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(messageCorrelation.getExpressionText())
        .processInstanceBusinessKey(businessKey)
        .setVariable("requestMessage", requestMessage)
        .correlate();
  }

  public void setMessageCorrelation(FixedValue messageCorrelation) {
    this.messageCorrelation = messageCorrelation;
  }
}
