package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestMessageLoader implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug("Writing down requestMessage value into variable...");
    String requestMessage = execution.getProcessEngine().getRuntimeService()
        .getVariableLocal(execution.getId(), "requestMessage").toString();
    execution.setVariable("requestMessage", requestMessage);
  }
}
