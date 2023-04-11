package org.example.camunda.bpm.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.camunda.bpm.RequestPriority;
import org.example.camunda.bpm.RequestStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REQUESTS")
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @NonNull
  @Column(name = "ID")
  private Long id;

  /**
   * Текст запроса. <br> Краткое описание поставленной задачи.
   */
  @NonNull
  @Basic(optional = false)
  @Column(name = "REQUEST_TEXT", nullable = false)
  private String message;

  /**
   * Приоритет запроса.
   * @see RequestPriority
   */
  @NonNull
  @Enumerated(EnumType.STRING)
  @Column(name = "PRIORITY", nullable = false)
  private RequestPriority priority;

  /**
   * Статус запроса.
   *
   * @see RequestStatus
   */
  @NonNull
  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false)
  private RequestStatus status;

  /**
   * Текущий исполнитель запроса. <br> По умолчанию не заполнен для новых запросов.
   */
  @Nullable
  @ManyToOne
  @JoinColumn(name = "EXECUTOR_ID")
  private User executor;

  public Request(@NonNull String requestText, @NonNull RequestPriority priority,
      @NonNull RequestStatus status) {
    this.message = requestText;
    this.priority = priority;
    this.status = status;
  }
}
