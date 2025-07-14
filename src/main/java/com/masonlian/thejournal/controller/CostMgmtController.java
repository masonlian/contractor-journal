package com.masonlian.thejournal.controller;


import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.ConstructionRequest;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.Construction;
import com.masonlian.thejournal.model.Material;
import com.masonlian.thejournal.service.CostMgmtService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class CostMgmtController {

    @Autowired
    private CostMgmtService costMgmtService;

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/costmgmt/material")
    public ResponseEntity<Material>  createMaterial(@RequestBody MaterialRequest materialRequest ){


        Integer materialId = costMgmtService.createMaterial(materialRequest);
        Material material = costMgmtService.getMaterialById(materialId);
        return ResponseEntity.status(HttpStatus.CREATED).body(material);
    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @DeleteMapping("/costmgmt/material/{materialId}")
    public ResponseEntity<Material>  deleteMaterialById(@PathVariable("materialId") Integer materialId){

        costMgmtService.deleteMaterialById(materialId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/costmgmt/material/{materialId}")
    public ResponseEntity<Material>  updateMaterialById(@PathVariable Integer materialId,
                                                    @RequestBody MaterialRequest materialRequest ){

        costMgmtService.updateMaterialById(materialId,materialRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/costmgmt/construnction")
    public ResponseEntity<Construction>  createConstruction(@RequestBody ConstructionRequest constructionRequest ){

        Integer constructionId =  costMgmtService.createConstruction(constructionRequest);
        Construction construction = costMgmtService.getConstructionById(constructionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(construction);


    }

   @GetMapping ("/costmgmt/material/")
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








    @DeleteMapping("/costmgmt/construction/{constructionId}")
    public ResponseEntity<Construction>  deleteConstructionById(@PathVariable Integer constructionId ){

        costMgmtService.deleteConstructionById(constructionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PostMapping("/costmgmt/construction/{constructionId}")
    public ResponseEntity <Construction> updateConstructionById(@PathVariable Integer constructionId ,@RequestBody ConstructionRequest constructionRequest ){

        costMgmtService.updateConstructionBydId(constructionId,constructionRequest);
        Construction  construction  =  costMgmtService.getConstructionById(constructionId);
        return ResponseEntity.status(HttpStatus.OK).body(construction);

    }

    @GetMapping("/costmgmt/construction")
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







}
