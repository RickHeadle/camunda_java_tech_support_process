package org.example.camunda.bpm.service;

import java.util.Optional;
import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

  private final RequestRepository requestRepository;

  @Autowired
  public RequestServiceImpl(RequestRepository requestRepository) {
    this.requestRepository = requestRepository;
  }

  @Override
  public Optional<Request> findById(@NonNull Long id) {
    return requestRepository.findById(id);
  }
}
