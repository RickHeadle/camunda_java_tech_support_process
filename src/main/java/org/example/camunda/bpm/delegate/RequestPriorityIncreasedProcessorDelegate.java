package org.example.camunda.bpm.delegate;

import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.example.camunda.bpm.ProcessConstants;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Получен запрос с повышенным приоритетом"
 */
@Slf4j
@Component(ProcessConstants.EVENT_REQUEST_PRIORITY_INCREASED)
public class RequestPriorityIncreasedProcessorDelegate implements JavaDelegate {

  private FixedValue messageCorrelation;
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String businessKey = execution.getBusinessKey();
    log.debug(RequestPriorityIncreasedProcessorDelegate.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(messageCorrelation.getExpressionText())
        .processInstanceBusinessKey(businessKey)
        .correlateStartMessage();
  }

  public void setMessageCorrelation(FixedValue messageCorrelation) {
    this.messageCorrelation = messageCorrelation;
  }
}
