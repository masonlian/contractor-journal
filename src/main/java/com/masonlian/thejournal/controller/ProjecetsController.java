package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.request.ProjectRequest;
import com.masonlian.thejournal.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjecetsController {

    @Autowired
    private ProjectsService projectsService;


    @PostMapping("/projects")
    public  ResponseEntity<Project> createProjects(@RequestBody ProjectRequest projectRequest) {
          Integer projectId = projectsService.createProject(projectRequest);  // creat project後 還傳奇ID讓其他羅際去取得比較方便
          Project  project = projectsService.getProjectById(projectId);
          return ResponseEntity.status(HttpStatus.OK).body(project);

    }


}
