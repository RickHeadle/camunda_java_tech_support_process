package org.example.camunda.bpm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.camunda.bpm.RequestPriority;
import org.example.camunda.bpm.RequestStatus;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REQUESTS")
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @NonNull
  @Column(name = "ID")
  private Long id;

  /**
   * Текст запроса. <br> Краткое описание поставленной задачи.
   */
  @NonNull
  @Column(name = "REQUEST_TEXT", nullable = false)
  private String message;

  /**
   * Приоритет запроса. <br>
   *
   * @see RequestPriority
   */
  @NonNull
  @Enumerated(EnumType.STRING)
  @Column(name = "PRIORITY", nullable = false)
  private RequestPriority priority;

  @NonNull
  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false)
  private RequestStatus status;

  @Column(name = "EXECUTOR")
  private String executor;

  public Request(@NonNull String requestText, @NonNull RequestPriority priority,
      @NonNull RequestStatus status) {
    this.message = requestText;
    this.priority = priority;
    this.status = status;
  }
}
