package org.example.camunda.bpm.delegate;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestWaitingForProcessing implements JavaDelegate {

  private static final Random random = new Random();
  @Autowired
  private ExecutorSetter executorSetter;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    Long entityId = (Long) execution.getVariableLocal("requestEntityId");
    log.debug("Request (ID = " + entityId + " is now waiting for processing...");
    boolean isExecutorAvailable;
    for (int iteration = 0; iteration < 3; iteration++) {
      isExecutorAvailable = random.nextBoolean();
      if (!isExecutorAvailable) {
        //Сон на 1 минуту
        Thread.sleep(60000);
      } else {
        executorSetter.execute(execution);
      }
    }
  }
}
