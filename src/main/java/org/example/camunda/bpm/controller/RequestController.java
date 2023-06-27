package org.example.camunda.bpm.controller;

import java.util.List;
import java.util.Optional;
import org.example.camunda.bpm.assembler.RequestModelAssembler;
import org.example.camunda.bpm.model.RequestModel;
import org.example.camunda.bpm.projection.RequestProjection;
import org.example.camunda.bpm.service.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

  private final RequestServiceImpl requestService;
  private final RequestModelAssembler modelAssembler;

  @Autowired
  public RequestController(RequestServiceImpl requestService,
      RequestModelAssembler modelAssembler) {
    this.requestService = requestService;
    this.modelAssembler = modelAssembler;
  }

  @GetMapping("/request/{id}")
  public ResponseEntity<RequestModel> findById(@PathVariable Long id) {
    return Optional.of(id)
        .flatMap(requestService::findById)
        .map(modelAssembler::toModel)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());
  }

  @GetMapping("/request/proj")
  public List<RequestProjection> findByProjection() {
    return requestService.getByProj();
  }
}
