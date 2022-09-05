package com.project.CQRS.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CQRS.command.repository.ProjectCommandRepository;
import com.project.CQRS.domain.CategoryP;
import com.project.CQRS.domain.ProjectCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProjectCommandServiceImp implements IProjectCommandService{

    //injection des methode de ProjectCommandRepository
    @Autowired
    ProjectCommandRepository projectCommandRepository;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    private void raiseEventToQueryProject(ProjectCommand p, String topic){
        try{
            String value = OBJECT_MAPPER.writeValueAsString(p);
            this.kafkaTemplate.send(topic,value);
            System.out.println("sended");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //service de creation du projet
    @Override
    public int createProject(ProjectCommand projectCommand) {
        log.info("ProjectCommand"+projectCommand);
        //récupération du projet crée
        ProjectCommand p=projectCommandRepository.save(projectCommand);
        //appel de la méthode qui va lever un evenment pour envoiyer un topic à kafka pour la creation du meme projet dans la base mongo
        this.raiseEventToQueryProject(p,"project-event-create");
        return 1;

    }

    //service de modification du projet
    @Override
    public int updateProject(ProjectCommand projectCommand) {
        //verrification de la présence du projet à modifier, si il est présent on le modifie
        this.projectCommandRepository.findById(projectCommand.getId()).ifPresent(projectCommand1 -> {
            projectCommand1.setId(projectCommand.getId());
            projectCommand1.setLastModifiedDate(new Date());
            projectCommand1.setTitle(projectCommand.getTitle());
            projectCommand1.setBudget(projectCommand.getBudget());
            projectCommand1.setCategoryP(projectCommand.getCategoryP());
            projectCommand1.setStartDate(projectCommand.getStartDate());
            projectCommand1.setCreationDate(projectCommand.getCreationDate());
            projectCommand1.setClosed(projectCommand.isClosed());
            projectCommand1.setDescription(projectCommand.getDescription());
            projectCommand1.setEndDate(projectCommand.getEndDate());
            projectCommand1.setMembers(projectCommand.getMembers());
            projectCommand1.setCreatedBy(projectCommand.getCreatedBy());
            projectCommandRepository.save(projectCommand1);
            //appel de la méthode qui va lever un evenment pour envoiyer un topic à kafka pour la modification du meme projet dans la base mongo
            this.raiseEventToQueryProject(projectCommand1,"project-event-update");
        });
        return 1;
    }

    //service de suppression du projet
    @Override
    public void deleteProject(Long id) {
        ProjectCommand projectCommand= new ProjectCommand();
        projectCommand.setId(id);
        projectCommandRepository.deleteById(id);
        //appel de la méthode qui va lever un evenment pour envoiyer un topic à kafka pour la suppression du meme projet dans la base mongo
        this.raiseEventToQueryProject(projectCommand,"project-event-delete");
    }


    //service de récupération des projet juste pour des raisons de test
    @Override
    public List<ProjectCommand> getAll() {
        return this.projectCommandRepository.findAll();
    }

}
