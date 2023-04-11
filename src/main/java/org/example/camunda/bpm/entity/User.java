package org.example.camunda.bpm.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.springframework.lang.NonNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

  @Id
  @NonNull
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Имя пользователя
   */
  @NonNull
  @Basic(optional = false)
  @Column(name = "FIRST_NAME", nullable = false)
  private String firstName;

  /**
   * Фамилия пользователя
   */
  @NonNull
  @Basic(optional = false)
  @Column(name = "LAST_NAME", nullable = false)
  private String lastName;

  /**
   * Роль пользователя
   */
  @NonNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "ROLE_ID", nullable = false)
  private Role role;
}
