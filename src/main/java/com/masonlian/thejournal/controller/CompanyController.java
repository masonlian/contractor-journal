package com.masonlian.thejournal.controller;


import com.masonlian.thejournal.model.FinancialStatement;
import com.masonlian.thejournal.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


//
@RestController
public class CompanyController {

     @Autowired
     FinancialService financialService;


     //
     //@PreAuthorize("hasAnyAuthority('L0')")
     @GetMapping("/financial/{quarter}")
     public ResponseEntity <FinancialStatement> getFinancialStatement(@PathVariable Integer quarter){

        FinancialStatement financialStatement =  financialService.getStatementByQuarter(quarter);

        return ResponseEntity.status(HttpStatus.OK).body(financialStatement);



     }







}
