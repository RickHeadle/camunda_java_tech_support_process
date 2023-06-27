package org.example.camunda.bpm.service;

import java.util.List;
import java.util.Optional;
import org.example.camunda.bpm.RequestStatus;
import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.projection.RequestProjection;
import org.example.camunda.bpm.repository.RequestProjectionRepository;
import org.example.camunda.bpm.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

  private final RequestRepository requestRepository;
  private final RequestProjectionRepository requestProjectionRepository;

  @Autowired
  public RequestServiceImpl(RequestRepository requestRepository,
      RequestProjectionRepository requestProjectionRepository) {
    this.requestRepository = requestRepository;
    this.requestProjectionRepository = requestProjectionRepository;
  }

  @Override
  public Optional<Request> findById(@NonNull Long id) {
    return requestRepository.findById(id);
  }

  @Override
  public Long addNewRequest(String requestText) {
    Request savedRequest = requestRepository.save(
        new Request(requestText, RequestStatus.NEW));
    return savedRequest.getId();
  }

  @Override
  public void setStatus(@NonNull Long requestEntityId, @NonNull RequestStatus status) {
    Optional<Request> request = findById(requestEntityId);
    if (!request.isPresent()) {
      throw new IllegalArgumentException(
          String.format("Запрос с entityId = %d отсутствует в БД!", requestEntityId));
    }
    Request modifiedRequest = request.get();
    modifiedRequest.setStatus(status);
    requestRepository.save(modifiedRequest);
  }

  @Override
  public List<RequestProjection> getByProj() {
    return requestProjectionRepository.findAllByStatusIsNotNull();
  }
}
