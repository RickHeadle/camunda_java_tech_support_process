package org.example.camunda.bpm.service;

import java.util.Optional;
import org.example.camunda.bpm.RequestStatus;
import org.example.camunda.bpm.entity.Request;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface RequestService {

  Optional<Request> findById(@NonNull Long id);

  Long addNewRequest(@Nullable String requestText);

  void setStatus(@NonNull Long requestEntityId, @NonNull RequestStatus status);

}
