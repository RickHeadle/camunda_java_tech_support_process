package org.example.camunda.bpm.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.example.camunda.bpm.controller.RequestController;
import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.model.RequestModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RequestModelAssembler extends
    RepresentationModelAssemblerSupport<Request, RequestModel> {

  public RequestModelAssembler() {
    super(RequestController.class, RequestModel.class);
  }

  @Override
  public @NonNull RequestModel toModel(@NonNull Request entity) {
    return assemble(new RequestModel(), entity);
  }

  private RequestModel assemble(RequestModel model, Request entity) {
    model.setId(entity.getId());
    model.setMessage(entity.getMessage());
    model.setStatus(entity.getStatus());

    model.add(getSelfLink(entity.getId()));

    return model;
  }

  private Link getSelfLink(Long entityId) {
    return linkTo(methodOn(RequestController.class).findById(entityId)).withSelfRel();
  }
}
