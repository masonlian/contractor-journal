package com.masonlian.thejournal.controller;


import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CostMgmtService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//materialEvent不應該跟日曆綁在一起，不貼合現實。
@Validated
@RestController
public class CostMgmtController {

    @Autowired
    private CostMgmtService costMgmtService;

    //@PreAuthorize("hasAnyAuthority('L0','L1','L2')")
    @PostMapping("/cost/material")
    public ResponseEntity<String>  createMaterial(@Valid @RequestBody
                                             CreateMaterialRequest createMaterialRequest){


        costMgmtService.createMaterial(createMaterialRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body("建材資料創建成功！");
    }

    @PreAuthorize("hasAnyAuthority('L0','L1','L2')")
    @DeleteMapping("/cost/material/{materialId}")
    public ResponseEntity<Material>  deleteMaterialById(@PathVariable("materialId") Integer materialId){

        costMgmtService.deleteMaterialById(materialId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/cost/material/{materialId}")
    public ResponseEntity<Material>  updateMaterialById(@PathVariable Integer materialId,
                                                    @RequestBody MaterialRequest materialRequest ){

        costMgmtService.updateMaterialById(materialId,materialRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PreAuthorize("hasAnyAuthority('L0','L1','L2')")
    @PostMapping("/cost/construction")
    public ResponseEntity<List<Construction>>  createConstruction(@Valid @RequestBody CreateConstructionRequest createConstructionRequest ){

        List<Construction>  constructionList =  costMgmtService.createConstruction( createConstructionRequest) ;


        return ResponseEntity.status(HttpStatus.CREATED).body(constructionList);


    }

    //@PreAuthorize("hasAnyAuthority('L0','L1','L2','L3')")
   @GetMapping ("/cost/material")
   public ResponseEntity<Page<Material>> getMaterial (@RequestParam  (defaultValue = "0")@Max(5) @Min(0) Integer offset,
                                                      @RequestParam (name = "limit", defaultValue="100") @Max(100) Integer limit,

                                                      @RequestParam (required = false)String search,
                                                      @RequestParam (required = false)ConstructionCategory category,

                                                      @RequestParam (defaultValue = "unit_price")String orderBy,
                                                      @RequestParam (defaultValue = "desc")String sort

                                                      ){
       QueryPara queryPara = new QueryPara();

       queryPara.setOffset(offset);
       queryPara.setLimit(limit);
       queryPara.setSearch(search);
       queryPara.setOrderBy(orderBy);
       queryPara.setSort(sort);
       queryPara.setConstructionCategory(category);

       List<Material> materialList = costMgmtService.getMaterials(queryPara);

       Page<Material> materialPage = new Page<>();
       materialPage.setTotal(materialList.size());
       materialPage.setLimit(limit);
       materialPage.setOffset(offset);
       materialPage.setTotal(materialList.size());
       materialPage.setResult(materialList);

       return ResponseEntity.status(HttpStatus.OK).body(materialPage);
   }








    @PreAuthorize("hasAnyAuthority('L0','L1','L2')")
    @DeleteMapping("/cost/construction/{constructionId}")
    public ResponseEntity<Construction>  deleteConstructionById(@PathVariable Integer constructionId ){

        costMgmtService.deleteConstructionById(constructionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PreAuthorize("hasAnyAuthority('L0','L1','L2')")
    @PostMapping("/cost/construction/{constructionId}")
    public ResponseEntity <Construction> updateConstructionById(@PathVariable Integer constructionId ,@RequestBody ConstructionRequest constructionRequest ){

        costMgmtService.updateConstructionBydId(constructionId,constructionRequest);
        Construction  construction  =  costMgmtService.getConstructionById(constructionId);
        return ResponseEntity.status(HttpStatus.OK).body(construction);

    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @GetMapping("/cost/construction")
    public ResponseEntity<Page<Construction>> getConstructionItems(@RequestParam  (defaultValue = "0")@Max(5) @Min(0) Integer offset,
                                                             @RequestParam (name = "limit", defaultValue="100") @Max(100) Integer limit,

                                                             @RequestParam (required = false)String search, //只有不需要預設值的參數到了Dao層才需要判斷式

                                                             @RequestParam (defaultValue = "construction_estimate")String orderBy,
                                                             @RequestParam (defaultValue = "desc")String sort){
        QueryPara queryPara = new QueryPara();
        queryPara.setOffset(offset);
        queryPara.setLimit(limit);
        queryPara.setSearch(search);
        queryPara.setOrderBy(orderBy);
        queryPara.setSort(sort);

        List<Construction> constructionList =  costMgmtService.getConstructionItems(queryPara);

        Page<Construction> constructionPage = new Page<>();
        constructionPage.setTotal(constructionList.size());
        constructionPage.setLimit(limit);
        constructionPage.setOffset(offset);
        constructionPage.setResult(constructionList);

        return ResponseEntity.status(HttpStatus.OK).body(constructionPage);

    }

    //@PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/material/event")
    public ResponseEntity<MaterialEvent>  createMaterialEvent(@Valid @RequestBody CreateMaterialEventRequest createMaterialEventRequest){

        Integer materialEventId  =  costMgmtService.createMaterialEvent(createMaterialEventRequest);
        MaterialEvent materialEvent = costMgmtService.getMaterialEventById(materialEventId);


        return ResponseEntity.status(HttpStatus.CREATED).body(materialEvent);


    }

    //@PreAuthorize("hasAnyAuthority('L0','L1')")
    @PutMapping("/payable/{payableId}") //
    public ResponseEntity<AccountPayable> payToSupplier( @PathVariable Integer payableId ,  @RequestBody PaySupplierRequest paySupplierRequest  ){

        costMgmtService.payToSupplier(payableId, paySupplierRequest.getAlreadyPaid() );
        AccountPayable accountPayable =  costMgmtService.getPayableById(payableId);
        return ResponseEntity.status(HttpStatus.OK).body(accountPayable);

    } //

    //@PreAuthorize("hasAnyAuthority('L0','L1')")
    @GetMapping("/payable" )
    public ResponseEntity<List<AccountPayable>> getPayable(@RequestParam (required = false)  String search ,
                                                    @RequestParam (defaultValue = "last_modified_date" ) String orderBy,
                                                     @RequestParam (defaultValue = "desc" )String sort,
                                                     @RequestParam  (name= "limit",defaultValue = "10")  @Max(100)  Integer limit,
                                                     @RequestParam  (defaultValue = "0" )@Max(100)@Min(0) Integer offset ){

        QueryPara queryPara = new QueryPara();
        queryPara.setSearch(search);
        queryPara.setOrderBy(orderBy);
        queryPara.setSort(sort);
        queryPara.setLimit(limit);
        queryPara.setOffset(offset);

        List<AccountPayable> payableList = costMgmtService.getPayable(queryPara);
        return ResponseEntity.status(HttpStatus.OK).body(payableList);

    }


    @GetMapping("/cost/{projectId}")
    public ResponseEntity<List<MaterialEvent>> getMaterialEventByProject(@PathVariable Integer projectId){


        List<MaterialEvent> materialEventList =  costMgmtService.getMaterialEventByProject(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(materialEventList);


    }






}
