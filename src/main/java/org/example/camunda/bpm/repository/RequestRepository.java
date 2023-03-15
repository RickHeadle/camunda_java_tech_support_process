package org.example.camunda.bpm.repository;

import org.example.camunda.bpm.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
