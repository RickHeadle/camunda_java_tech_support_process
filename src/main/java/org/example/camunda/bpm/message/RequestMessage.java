package org.example.camunda.bpm.message;

import java.io.Serializable;
import lombok.Data;

@Data
public class RequestMessage implements Serializable {
  private String requestText;

}
