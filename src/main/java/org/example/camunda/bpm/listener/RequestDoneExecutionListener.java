package org.example.camunda.bpm.listener;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.example.camunda.bpm.RequestStatus;
import org.springframework.stereotype.Component;

/**
 * Заполнение переменных значениями в случае, если запрос был отклонен на второй линии.
 */
@Slf4j
@Component
public class RequestDoneExecutionListener implements ExecutionListener {

  @Override
  public void notify(DelegateExecution execution) throws Exception {
    Optional<Object> isRequestDone =
        Optional.ofNullable(execution.getVariable("isRequestDone"));
    if (!isRequestDone.isPresent()) {
      execution.setVariable("isRequestDone", false);
    }
    Optional<Object> solutionDescription =
        Optional.ofNullable(execution.getVariable("solutionDescription"));
    if (!solutionDescription.isPresent()) {
      String requestDeniedDescription = "Отклонено.";
      execution.setVariable("solutionDescription", requestDeniedDescription);
      execution.setVariable("status", RequestStatus.DENIED);
    }
  }
}
