package com.project.CQRS.command.service;

import com.project.CQRS.domain.ProjectCommand;

import java.util.List;

public interface IProjectCommandService {
    int createProject(ProjectCommand projectCommand);
    int updateProject(ProjectCommand projectCommand);
    void deleteProject(Long id );
    List<ProjectCommand> getAll();
}
