package org.example.camunda.bpm;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * Обработка события "Запрос успешно обработан"
 * @deprecated использовался в устаревшей версии схемы
 * @version 2
 */
@Slf4j
@Deprecated
@Component(ProcessConstants.EVENT_REQUEST_SOLVED_KEY)
public class RequestSolvedMessageProcessorDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    log.debug(RequestSolvedMessageProcessorDelegate.log.getName());
    execution.getProcessEngine().getRuntimeService()
        .createMessageCorrelation(ProcessConstants.EVENT_REQUEST_SOLVED_KEY)
        .correlateStartMessage();
  }
}
