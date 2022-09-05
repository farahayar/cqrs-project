package com.project.CQRS.hundler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.CQRS.domain.ProjectQuery;
import com.project.CQRS.query.repo.ProjectQueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProjectEventHandlerImp implements  IProjectQueryHandler{

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private ProjectQueryRepository projectQueryRepository;

    @Override
    public void createProject(ProjectQuery projectQuery) {
        projectQueryRepository.save(projectQuery);
    }

    @Override
    public void updateProject(ProjectQuery projectQuery) {
        projectQueryRepository.save(projectQuery);
    }

    @Override
    public void deleteProject(ProjectQuery projectQuery) {
        projectQueryRepository.deleteById(projectQuery.getId());
    }

    //lister on the topic sended when creating one project in mysql database to create in mongo database
    @KafkaListener(topics = "project-event-create")
    public void consumeCreate(String userStr) {
        System.out.println("in");
        try {
            ProjectQuery projectQuery = OBJECT_MAPPER.readValue(userStr, ProjectQuery.class);
            log.info(userStr);
            this.createProject(projectQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //lister on the topic sended when updating one project in mysql database to update in mongo database
    @KafkaListener(topics = "project-event-update")
    public void consumeUpdate(String userStr) {
        try {
            ProjectQuery projectQuery = OBJECT_MAPPER.readValue(userStr, ProjectQuery.class);
            this.updateProject(projectQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //lister on the topic sended when deleting one project in mysql database to delete in mongo database
    @KafkaListener(topics = "project-event-delete")
    public void consumeDelete(String userStr) {
        try {
            //System.out.println(purchaseOrderStr);
            ProjectQuery projectQuery = OBJECT_MAPPER.readValue(userStr, ProjectQuery.class);
            this.deleteProject(projectQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
