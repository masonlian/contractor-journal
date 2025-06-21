package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


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

    @Override
    public void deleteProjectById(Integer projectId){
      projectsDao.deleteProjectById(projectId);
    }

    @Override
    public void updateProjectById(Integer projectId,ProjectRequest projectRequest){
        projectsDao.updateProjectById(projectId ,projectRequest);
    }
    @Override
    public List<Project> getProjects(QueryPara queryPara){
        return projectsDao.getProjects(queryPara);
    }


}
