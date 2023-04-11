package org.example.camunda.bpm.service;

import org.example.camunda.bpm.entity.User;
import org.example.camunda.bpm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Override
  public User getExecutorWithLeastRequests() {
    return userRepository.getExecutorWithLeastRequests();
  }
}
