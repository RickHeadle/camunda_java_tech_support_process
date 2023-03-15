package org.example.camunda.bpm.service;

import java.util.Optional;
import org.example.camunda.bpm.entity.Request;
import org.springframework.lang.NonNull;

public interface RequestService {

  Optional<Request> findById(@NonNull Long id);

}
