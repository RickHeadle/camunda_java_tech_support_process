package org.example.camunda.bpm.controller;

import java.util.Optional;
import org.example.camunda.bpm.assembler.RequestModelAssembler;
import org.example.camunda.bpm.model.RequestModel;
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
        .flatMap(identifier -> requestService.findById(identifier))
        .map(request -> modelAssembler.toModel(request))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.noContent().build());
  }
}
