package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstLineAnalysisDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    boolean result = (boolean) execution.getVariable("isRequestForFirstLineOfSupport");
    log.debug("Request has been analyzed for the first line. Result: " + result);
  }
}
