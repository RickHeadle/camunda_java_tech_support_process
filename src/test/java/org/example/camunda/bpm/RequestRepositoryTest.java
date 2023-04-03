package org.example.camunda.bpm;

import org.example.camunda.bpm.service.RequestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@AutoConfigureTestDatabase
@SpringBootTest
public class RequestRepositoryTest {

  @SpyBean
  private RequestServiceImpl requestService;

  @Test
  public void createRequestWillSucceed() {
    Assertions.assertEquals(1L, requestService.addNewRequest("Test Request #0"));
  }
}
