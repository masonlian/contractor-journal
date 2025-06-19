package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.request.ProjectRequest;

public interface ProjectsDao {
    Integer createProject(ProjectRequest projectRequest);
    Project getProjectById(Integer projectId);
}
