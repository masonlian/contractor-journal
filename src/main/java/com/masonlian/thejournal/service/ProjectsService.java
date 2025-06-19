package com.masonlian.thejournal.service;

import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.request.ProjectRequest;

public interface ProjectsService {
    Integer createProject(ProjectRequest projectRequest);
    Project getProjectById(Integer projectId);
}
