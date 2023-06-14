package org.example.camunda.bpm.listener;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
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
        Optional.ofNullable(execution.getVariableLocal("isRequestDone"));
    if (!isRequestDone.isPresent()) {
      execution.setVariableLocal("isRequestDone", false);
    }
    Optional<Object> solutionDescription =
        Optional.ofNullable(execution.getVariableLocal("solutionDescription"));
    if (!solutionDescription.isPresent()) {
      String requestDeniedDescription = "Отклонено.";
      execution.setVariable("solutionDescription", requestDeniedDescription);
    }
  }
}
