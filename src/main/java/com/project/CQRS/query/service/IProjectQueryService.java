package com.project.CQRS.query.service;

import com.project.CQRS.domain.ProjectQuery;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IProjectQueryService {
    ProjectQuery findById(long Id);

    List<ProjectQuery> getProjects();
    ProjectQuery findByTitle(String s);
    List<ProjectQuery> findByBudgetGreaterThan(double b);
    List<ProjectQuery> findByBudgetLessThan(double b);
    List<ProjectQuery> findByCreatedBy(String user);
}
