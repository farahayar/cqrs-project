package com.project.CQRS.command.repository;

import com.project.CQRS.domain.ProjectCommand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectCommandRepository extends JpaRepository<ProjectCommand, Long> {
    ProjectCommand findByTitle(String s);
    List<ProjectCommand> findByBudgetGreaterThan(double b);
    List<ProjectCommand> findByBudgetLessThan(double b);
}
