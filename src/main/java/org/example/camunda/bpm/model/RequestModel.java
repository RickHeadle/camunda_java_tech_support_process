package org.example.camunda.bpm.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.camunda.bpm.RequestPriority;
import org.example.camunda.bpm.RequestStatus;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
public class RequestModel extends RepresentationModel<RequestModel> {
  private Long id;
  private String message;
  private RequestPriority priority;
  private RequestStatus status;
}
