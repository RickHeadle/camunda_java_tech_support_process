package org.example.camunda.bpm.repository;

import java.util.List;
import org.example.camunda.bpm.entity.Request;
import org.example.camunda.bpm.projection.RequestProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestProjectionRepository extends JpaRepository<Request, Long> {
  List<RequestProjection> findAllByStatusIsNotNull();
}
