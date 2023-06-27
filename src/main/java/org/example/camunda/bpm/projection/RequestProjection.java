package org.example.camunda.bpm.projection;

import org.example.camunda.bpm.entity.User;
import org.springframework.beans.factory.annotation.Value;

public interface RequestProjection {

  Long getId();

  String getMessage();

  @Value(value = "#{target.status.name}")
  String getStatus();

  @Value(value = "#{target.solver}")
  User getSolver();

  String getSolution();
}
