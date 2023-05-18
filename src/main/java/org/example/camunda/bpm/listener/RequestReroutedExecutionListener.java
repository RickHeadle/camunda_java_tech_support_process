package org.example.camunda.bpm.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestReroutedExecutionListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    log.debug("Increasing priority request...");
  }
}
