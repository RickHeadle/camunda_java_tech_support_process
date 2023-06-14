package org.example.camunda.bpm.listener;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OnCompleteTaskListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    log.debug(String.format("Task %s has been completed by: %s", delegateTask.getName(),
        delegateTask.getAssignee()));
    delegateTask.setVariable("taskCompletedBy", delegateTask.getAssignee());
  }
}
