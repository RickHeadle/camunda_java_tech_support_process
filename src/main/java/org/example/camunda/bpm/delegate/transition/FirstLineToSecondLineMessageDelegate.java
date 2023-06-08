package org.example.camunda.bpm.delegate.transition;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstLineToSecondLineMessageDelegate implements JavaDelegate {

  private FixedValue messageCorrelation;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String businessKey = execution.getBusinessKey();
    String requestMessage = execution.getVariable("requestEntityId").toString();
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(messageCorrelation.getExpressionText())
        .processInstanceBusinessKey(businessKey)
        .setVariable("requestEntityId", requestMessage)
        .correlate();
  }

}
