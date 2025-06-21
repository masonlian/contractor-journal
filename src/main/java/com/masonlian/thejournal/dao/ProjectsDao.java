package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;

import java.util.List;

public interface ProjectsDao {
    Integer createProject(ProjectRequest projectRequest);
    Project getProjectById(Integer projectId);
    void deleteProjectById(Integer projectId);
    void updateProjectById(Integer projectId,ProjectRequest projectRequest);
    List<Project> getProjects(QueryPara queryPara);


}
