package org.example.camunda.bpm.service;

import org.example.camunda.bpm.entity.User;
import org.example.camunda.bpm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  @Deprecated
  @Override
  public User getExecutorWithLeastRequests() {
    return userRepository.getExecutorWithLeastRequests();
  }

  @Override
  public User findById(@NonNull Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User not found by id: " + id));
  }

  @Override
  public User findByCamundaId(@NonNull String camundaId) {
    return userRepository.findByCamundaId(camundaId)
        .orElseThrow(
            () -> new IllegalArgumentException("User not found by camundaId: " + camundaId));
  }
}
