package org.example.camunda.bpm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.camunda.bpm.RequestPriority;
import org.example.camunda.bpm.RequestStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_REQUESTS")
public class Request {

  @Id
  private Long id;
  private String message;
  private RequestPriority priority;
  private RequestStatus status;

}
