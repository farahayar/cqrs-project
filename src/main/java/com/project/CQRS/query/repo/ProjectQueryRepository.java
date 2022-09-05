package com.project.CQRS.query.repo;

import com.project.CQRS.domain.ProjectQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectQueryRepository extends MongoRepository<ProjectQuery, Long> {
    ProjectQuery findByTitle(String s);
    List<ProjectQuery> findByBudgetGreaterThan(double b);
    List<ProjectQuery> findByBudgetLessThan(double b);
    List<ProjectQuery> findByCreatedBy(String user);
}
