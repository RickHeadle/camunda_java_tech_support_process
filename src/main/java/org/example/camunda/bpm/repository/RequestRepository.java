package org.example.camunda.bpm.repository;

import org.example.camunda.bpm.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

  @Query(nativeQuery = true, value =
      "select count(*) > 0 from requests where EXECUTOR_ID = :userId")
  boolean isExecutorAvailable(@Param(value = "userId") Long userId);
}
