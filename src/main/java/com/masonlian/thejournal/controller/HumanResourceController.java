package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CreateLaborRoleRequest;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.LaborRole;
import com.masonlian.thejournal.service.HumanResourceService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HumanResourceController {

    @Autowired
    HumanResourceService humanResourceService;


    //主管創建人事檔案
    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("h-resource")
    public ResponseEntity<LaborRole>  createProfile(@RequestBody CreateLaborRoleRequest createLaborRoleRequest) {

        Integer employeeId= humanResourceService.createProfile(createLaborRoleRequest);
        LaborRole laborRole = humanResourceService.getEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(laborRole);
    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @GetMapping("profiles")
    public ResponseEntity<Page<LaborRole>> getEmployees(@RequestParam (required = false) String search,
                                                        @RequestParam  (required = false) Level level,

                                                        @RequestParam (defaultValue = "0") @Min(0) int offset,
                                                        @RequestParam (name="limit",defaultValue = "5") @Max(100)@Min(0) int limit,

                                                        @RequestParam (defaultValue = " wage ") String orderBy,
                                                        @RequestParam(defaultValue = " desc") String sort
    ) {

       QueryPara employeeQuery =  new QueryPara();

       employeeQuery.setSearch(search);

       employeeQuery.setJoblevel(level);
       employeeQuery.setOffset(offset);
       employeeQuery.setLimit(limit);
       employeeQuery.setOrderBy(orderBy);
       employeeQuery.setSort(sort);

       List<LaborRole> laborRoleList = humanResourceService.getEmployees(employeeQuery);

       Page<LaborRole> employeePage = new Page<>();
       employeePage.setTotal(laborRoleList.size());
       employeePage.setOffset(offset);
       employeePage.setLimit(limit);
       employeePage.setResult(laborRoleList);

       return ResponseEntity.status(HttpStatus.OK).body(employeePage);


    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @DeleteMapping("/layoff/{employeeId}")
    public ResponseEntity<LaborRole> deleteProfile(@PathVariable Integer employeeId) {

        humanResourceService.deleteProfileById(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/update/{employeeId}")
    public ResponseEntity<LaborRole> updateProfileById(@PathVariable Integer employeeId,
                                                       @RequestBody LaborEventQueryRequest laborEventQueryRequest) {

        humanResourceService.updateProfileById(employeeId, laborEventQueryRequest);
        return ResponseEntity.status(HttpStatus.OK).build();


    }



}



