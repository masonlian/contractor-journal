package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.service.ProjectsService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
public class ProjecetsController {

    @Autowired
    private ProjectsService projectsService;


    @PostMapping("/projects")
    public ResponseEntity<Project> createProjects(@RequestBody ProjectRequest projectRequest


    ) {
        Integer projectId = projectsService.createProject(projectRequest);  // creat project後 還傳奇ID讓其他羅際去取得比較方便
        Project project = projectsService.getProjectById(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(project);

    }

//    @GetMapping("/projects/{projectId}")
//    public ResponseEntity<Project> getProjectById(@PathVariable Integer projectId) {
//
//        if (projectId == null)
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        Project project = projectsService.getProjectById(projectId);
//        return ResponseEntity.status(HttpStatus.OK).body(project);
//    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Project> deleteProjectById(@PathVariable Integer projectId) {

        if (projectId == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        projectsService.deleteProjectById(projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer projectId,
                                                 @RequestBody ProjectRequest projectRequest) {
        if (projectId == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        projectsService.updateProjectById(projectId, projectRequest);
        Project updatedProject = projectsService.getProjectById(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProject);
    }




    @Valid
    @GetMapping("allprojects") //set
    public ResponseEntity<Page<Project>> getProjects(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "budget") String orderBy,
            @RequestParam(defaultValue = "1") @Min(0) int offset,
            @RequestParam(name= "limit",defaultValue= " 5") @Max(5) @Min(0) int limit,
            @RequestParam(defaultValue = "desc") String sort
    ) {

            QueryPara queryPara = new QueryPara();
            queryPara.setSearch(search);
            queryPara.setOrderBy(orderBy);
            queryPara.setLimit(limit);
            queryPara.setOffset(offset);
            queryPara.setSort(sort);

            List <Project> projectList =  projectsService.getProjects(queryPara);

            Page<Project> projectPage = new Page<>();
            projectPage.setTotal(projectList.size());
            projectPage.setOffset(offset);
            projectPage.setLimit(limit);
            projectPage.setResult(projectList);

            return ResponseEntity.status(HttpStatus.OK).body(projectPage);


    }
}







