package org.example.camunda.bpm;

import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
@AutoConfigureTestDatabase
public class RequestRepositoryTest {

  @SpyBean
  private RequestServiceImpl requestService;

  @Test
/*  @Sql(value = "classpath:scripts/h2/schema-h2.sql",
      executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)*/
  public void createdRequestDoesNotChangeAfterSave() {
    final String requestMessage = "Test Request #0";

    Assertions.assertEquals(1L, requestService.addNewRequest(requestMessage));
    Request request = requestService.findById(1L).orElse(new Request());

    Assertions.assertEquals(requestMessage, request.getMessage());
    Assertions.assertEquals(RequestStatus.NEW, request.getStatus());
  }
}
