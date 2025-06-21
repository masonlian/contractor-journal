package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.EmployeeRequest;
import com.masonlian.thejournal.model.Employee;
import com.masonlian.thejournal.service.HumanResourceService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HumanResourceController {

    @Autowired
    HumanResourceService humanResourceService;

    @PostMapping("h-resource")
    public ResponseEntity<Employee>  createProfile(@RequestBody EmployeeRequest employeeRequest) {

        Integer employeeId= humanResourceService.createProfile(employeeRequest);
        Employee employee = humanResourceService.getEmployeeById(employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @GetMapping("profiles")
    public ResponseEntity<Page<Employee>> getEmployees(@RequestParam (required = false) String search,
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

       List< Employee> employeeList = humanResourceService.getEmployees(employeeQuery);

       Page<Employee> employeePage = new Page<>();
       employeePage.setTotal(employeeList.size());
       employeePage.setOffset(offset);
       employeePage.setLimit(limit);
       employeePage.setResult(employeeList);

       return ResponseEntity.status(HttpStatus.OK).body(employeePage);


    }

    @DeleteMapping("/layoff/{employeeId}")
    public ResponseEntity<Employee> deleteProfile(@PathVariable Integer employeeId) {

        humanResourceService.deleteProfileById(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PostMapping("/update/{employeeId}")
    public ResponseEntity<Employee> updateProfileById(@PathVariable Integer employeeId,
                                                      @RequestBody EmployeeRequest employeeRequest) {

        humanResourceService.updateProfileById(employeeId,employeeRequest);
        return ResponseEntity.status(HttpStatus.OK).build();


    }



}



