package org.example.camunda.bpm.service;

import org.example.camunda.bpm.entity.User;

public interface UserService {

  User getExecutorWithLeastRequests();
}
