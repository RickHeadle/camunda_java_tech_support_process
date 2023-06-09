package org.example.camunda.bpm.repository;

import java.util.Optional;
import org.example.camunda.bpm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "from User where camunda_id = :camundaId")
  Optional<User> findByCamundaId(@Param(value = "camundaId") String camundaId);

  /**
   * @return Пользователя с ролью "Исполнитель" с минимальным количеством присвоенных запросов в
   * не-терминальном статусе
   * @implNote Вынужден разместить скрипт здесь, т.к. в H2 нет Stored Procedures. <br> В дальнейшем
   * потребуется найти решение получше.
   */
  @Query(nativeQuery = true, value = "select id, first_name, last_name, role_id\n"
      + "from (select users.*, counter\n"
      + "      from (select count(*) as counter, requests.executor_id\n"
      + "            from requests\n"
      + "            where status <> 'DONE'\n"
      + "            group by requests.executor_id\n"
      + "            order by counter) as reqs\n"
      + "               left join users on users.id = reqs.executor_id\n"
      + "               left join roles on users.role_id = roles.id\n"
      + "      where roles.code = 'EXPERT'\n"
      + "        and counter is not null\n"
      + "      UNION ALL\n"
      + "      select users.*, counter\n"
      + "      from (select count(*) as counter, requests.executor_id\n"
      + "            from requests\n"
      + "            where status <> 'DONE'\n"
      + "            group by requests.executor_id\n"
      + "            order by counter) as reqs\n"
      + "               right join users on users.id = reqs.executor_id\n"
      + "               left join roles on users.role_id = roles.id\n"
      + "      where roles.code = 'EXPERT'\n"
      + "        and reqs.counter is null) as united\n"
      + "order by counter\n"
      + "LIMIT 1;")
  User getExecutorWithLeastRequests();
}
