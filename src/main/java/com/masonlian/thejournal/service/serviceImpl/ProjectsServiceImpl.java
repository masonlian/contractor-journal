package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.request.ProjectRequest;
import com.masonlian.thejournal.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProjectsServiceImpl implements ProjectsService {

    @Autowired
    ProjectsDao projectsDao;

    @Override
    public Integer createProject(ProjectRequest projectRequest){

        return projectsDao.createProject(projectRequest);
    }

    @Override
    public Project getProjectById(Integer projectId){

        return projectsDao.getProjectById(projectId);

    }

}
