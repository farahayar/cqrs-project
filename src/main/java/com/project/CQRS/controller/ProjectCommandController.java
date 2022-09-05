package com.project.CQRS.controller;

import com.project.CQRS.command.service.IProjectCommandService;
import com.project.CQRS.domain.ProjectCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/project-command")
@CrossOrigin(origins = "*")
public class ProjectCommandController {

    @Autowired
    IProjectCommandService projectCommandServiceImp;

    // api de czration du proejt
    @PostMapping("/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectCommand p){
        //modifier la date de dérniere modification
        p.setCreationDate(new Date());
        if(this.projectCommandServiceImp.createProject(p)==1){
            return new ResponseEntity<>(HttpStatus.CREATED);
        };
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //api de récupération de tous les projet pour des fins de tests
    @GetMapping("all")
    public List<ProjectCommand> getAll(){
        return this.projectCommandServiceImp.getAll();
    }

    //api de suppression du projet
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable long id){

        try{
            this.projectCommandServiceImp.deleteProject(id);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (Exception e) {
            return  new ResponseEntity<>(HttpStatus.CONFLICT);

        }


    }

    //api de modification du projt
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProjectCommand p){
        //p.setLastModifiedDate(new Date());
        if(this.projectCommandServiceImp.updateProject(p)==1){
            return new ResponseEntity<>(HttpStatus.CREATED);
        };
        return  new ResponseEntity<>(HttpStatus.CONFLICT);

    }
}
