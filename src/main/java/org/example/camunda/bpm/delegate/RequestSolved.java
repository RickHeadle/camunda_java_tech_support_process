package org.example.camunda.bpm.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Запрос успешно обработан"
 */
@Slf4j
@Component
public class RequestSolved implements JavaDelegate {

  public static final String EVENT_REQUEST_SOLVED_KEY = "requestSolvedByWatchGroup";

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug(RequestSolved.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(EVENT_REQUEST_SOLVED_KEY).correlateStartMessage();
  }
}
