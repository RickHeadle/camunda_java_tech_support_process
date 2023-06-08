package org.example.camunda.bpm.service;

import org.example.camunda.bpm.entity.User;
import org.springframework.lang.NonNull;

public interface UserService {

  User getExecutorWithLeastRequests();
  User findById(@NonNull Long id);
}
