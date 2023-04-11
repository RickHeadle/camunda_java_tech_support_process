package org.example.camunda.bpm.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
public class Role {

  @Id
  @NonNull
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Код роли. <br> Представляет собой кодовое обозначение, через которое можно ссылаться на данную
   * роль внутри системы. <br> Примеры: DEPARTMENT_HEAD, TEAM_LEADER, etc.
   */
  @NonNull
  @Basic(optional = false)
  @Column(name = "CODE", nullable = false)
  private String code;

  /**
   * Наименование роли. <br> Представляет собой кодовое обозначение, через которое принято ссылаться
   * на данную роль вне системы. <br> Примеры: Руководитель отдела, Руководитель группы, etc.
   */
  @NonNull
  @Basic(optional = false)
  @Column(name = "NAME", nullable = false)
  private String name;

  /**
   * Описание роли. <br> Поле, содержащее дополнительную информацию касательно роли.
   */
  @Nullable
  @Basic
  @Column(name = "DESCRIPTION")
  private String description;

}
