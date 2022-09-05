package com.project.CQRS.controller;

import com.project.CQRS.domain.ProjectQuery;
import com.project.CQRS.query.repo.ProjectQueryRepository;
import com.project.CQRS.query.service.IProjectQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/project-query")
@CrossOrigin("*")
public class ProjectQueryController {
    @Autowired
    IProjectQueryService projectQueryServiceImp;

    //api de récupération du projet par id
    @GetMapping("/projectbyid/{id}")
    public ProjectQuery getProjectById(@PathVariable long id){

        return this.projectQueryServiceImp.findById(id);
    }

    //api de récupération de tous les projets
    @GetMapping("/all")
    public List<ProjectQuery> getProjects(){

        return this.projectQueryServiceImp.getProjects();
    }

    //api de récupération du projet par son créateur
    @GetMapping("/project-CreatedBy/{username}")
    public List<ProjectQuery> getProjectsCreatedBy(@PathVariable("username") String username)
    {

        return  this.projectQueryServiceImp.findByCreatedBy(username);
    }

    //api de récupération du projet son titre
    @GetMapping("/projectbyTitle/{title}")
    public ProjectQuery getProjectTitle(@PathVariable String title){
        return this.projectQueryServiceImp.findByTitle(title);
    }

    //api de récupération du projet par budjet >
    @GetMapping("/withBudgetGreaterThan/{budget}")
    public List<ProjectQuery> getProjectwithBudgetGreaterThan(@PathVariable double budget){
        return this.projectQueryServiceImp.findByBudgetGreaterThan(budget);
    }

    //api de récupération du projet par budget <
    @GetMapping("/withBudgetLesserThan/{budget}")
    public List<ProjectQuery> getProjectwithBudgetLesserThan(@PathVariable double budget){
        return this.projectQueryServiceImp.findByBudgetLessThan(budget);
    }



}
