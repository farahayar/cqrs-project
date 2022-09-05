package com.project.CQRS.query.service;

import com.project.CQRS.domain.ProjectQuery;
import com.project.CQRS.query.repo.ProjectQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectQueryServiceImp implements IProjectQueryService{

    //injection des methode de ProjectQueryRepository
    @Autowired
    private ProjectQueryRepository projectQueryRepository;


    //service de récupération d'un projet by id
    @Override
    public ProjectQuery findById(long id) {
        return projectQueryRepository.findById(id).orElse(null);
    }

    //service de récupération des projets
    @Override
    public List<ProjectQuery> getProjects() {
        return projectQueryRepository.findAll();
    }

    //service de récupération d'un projet par titre
    @Override
    public ProjectQuery findByTitle(String s) {
        return projectQueryRepository.findByTitle(s);
    }

    //service de récupération d'un projet par budget >
    @Override
    public List<ProjectQuery> findByBudgetGreaterThan(double b) {
        return projectQueryRepository.findByBudgetGreaterThan(b);
    }

    //service de récupération d'un projet par budget <
    @Override
    public List<ProjectQuery> findByBudgetLessThan(double b) {
        return projectQueryRepository.findByBudgetLessThan(b);
    }

    //service de récupération d'un projet par son créateur
    @Override
    public List<ProjectQuery> findByCreatedBy(String user) {
        return projectQueryRepository.findByCreatedBy(user);
    }


}
