package org.example.camunda.bpm.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@ComponentScan(basePackages = "org.example.camunda.bpm")
@EntityScan(basePackages = "org.example.camunda.bpm.entity")
@EnableJpaRepositories(basePackages = "org.example.camunda.bpm.repository")
public class JpaRepositoryConfig {

}
