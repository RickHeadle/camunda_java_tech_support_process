package org.example.camunda.bpm;

import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase
public class RequestServiceTest {

  @Autowired
  private RequestServiceImpl requestService;

  @Test
  public void createdRequestDoesNotChangeAfterSave() {
    final String requestMessage = "Test Request #0";

    Assertions.assertEquals(1L, requestService.addNewRequest(requestMessage));
    Request request = requestService.findById(1L).orElse(new Request());

    Assertions.assertEquals(requestMessage, request.getMessage());
    Assertions.assertEquals(RequestStatus.NEW, request.getStatus());
  }


}
