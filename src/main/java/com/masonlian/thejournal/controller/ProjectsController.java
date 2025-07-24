package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.config.CustomUserDetails;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.NewReceived;
import com.masonlian.thejournal.dto.request.QuotationItemRequest;
import com.masonlian.thejournal.dto.request.QuotationRequest;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.model.Quotation;
import com.masonlian.thejournal.model.Received;
import com.masonlian.thejournal.service.ProjectsService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//時間關係先CREATE跟READ功能，UPDATE跟DELETE再說。
@Validated
@RestController
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;


    //創建專案
    @PreAuthorize("hasAnyAuthority('L0','L1')")
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



//刪除專案
@PreAuthorize( "!hasAnyAuthority('L3','L2')")
    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Project> deleteProjectById(@PathVariable Integer projectId) {

        if (projectId == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        projectsService.deleteProjectById(projectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //更新專案狀態
    @PreAuthorize( "!hasAnyAuthority('L3')")
    @PostMapping("projects/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable Integer projectId,
                                                 @RequestBody ProjectRequest projectRequest) {
        if (projectId == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        projectsService.updateProjectById(projectId, projectRequest);
        Project updatedProject = projectsService.getProjectById(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProject);
    }




    //調閱專案內容以及分頁功能
    @PreAuthorize("hasAnyAuthority('L0','L1','L2')")
    @Valid
    @GetMapping("projects") //set
    public ResponseEntity<Page<Project>> getProjects(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "budget") String orderBy,
            @RequestParam(defaultValue = "1") @Min(0) int offset,
            @RequestParam(name= "limit",defaultValue= " 5") @Max(5) @Min(0) int limit,
            @RequestParam(defaultValue = "desc") String sort,
            @AuthenticationPrincipal CustomUserDetails customUserDetails // 增加權限範圍
    ) {

            QueryPara queryPara = new QueryPara();
            queryPara.setSearch(search);
            queryPara.setOrderBy(orderBy);
            queryPara.setLimit(limit);
            queryPara.setOffset(offset);
            queryPara.setSort(sort);

            List <Project> projectList =  projectsService.getProjects(queryPara,customUserDetails);

            Page<Project> projectPage = new Page<>();
            projectPage.setTotal(projectList.size());
            projectPage.setOffset(offset);
            projectPage.setLimit(limit);
            projectPage.setResult(projectList);

            return ResponseEntity.status(HttpStatus.OK).body(projectPage);


    }


    //透過報價單的設計除了能夠建立預算進入案件之外，還能調整成本資料庫。

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/projects/{projectId}/quotation")
    public ResponseEntity<Quotation> createQuotation(@PathVariable Integer projectId , @RequestBody QuotationRequest quotationRequest) {

        Integer quotationId = projectsService.createQuotation(projectId, quotationRequest);
        Quotation quotation = projectsService.getQuotationById(quotationId);
        return ResponseEntity.status(HttpStatus.CREATED).body(quotation);



    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/projects/{quotationId}/item")
    public ResponseEntity<?> createQuotationItem(@PathVariable Integer quotationId, @RequestBody QuotationItemRequest quotationItemRequest) {

        projectsService.createQuotationItem(quotationId, quotationItemRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(quotationItemRequest);


    }


    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @GetMapping("/projects/{projectId}/quotation/")
    public ResponseEntity<List<QuotationWithItemDto>> getQuotations(@PathVariable Integer projectId) {

        List<QuotationWithItemDto> quotationList  = projectsService.getQuotations(projectId);

        return ResponseEntity.status(HttpStatus.OK).body(quotationList);


    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/projects/received ")
    public ResponseEntity<Received> createReceived(@RequestBody NewReceived newReceived) {

        Integer createReceived = projectsService.createReceived(newReceived);
        Received received  = projectsService.getReceivedById(createReceived);

        return ResponseEntity.status(HttpStatus.CREATED).body(received);
    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @GetMapping("/projects/{projectId}/received ")
    public ResponseEntity<List<Received>> getReceived(@PathVariable Integer projectId) {

        List<Received> receivedList = projectsService.getReceivedByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(receivedList);


    }



    // Update與Delete先省略

   //建立一個收付款項的table  去增加專案的balance







}







