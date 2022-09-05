package com.project.CQRS.hundler;

import com.project.CQRS.domain.ProjectQuery;

public interface IProjectQueryHandler {

    public  void createProject(ProjectQuery projectQuery);
    public void updateProject(ProjectQuery projectQuery);
    public  void deleteProject(ProjectQuery projectQueryDto);

}
