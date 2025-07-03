package com.masonlian.thejournal.controller;


import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.Material;
import com.masonlian.thejournal.service.CostMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CostMgmtController {

    @Autowired
    private CostMgmtService costMgmtService;

    @PostMapping("material")
    public ResponseEntity<Material>  createMaterial(@RequestBody MaterialRequest materialRequest ){


        Integer materialId = costMgmtService.createMaterial(materialRequest);
        Material material = costMgmtService.getMaterialById(materialId);
        return ResponseEntity.status(HttpStatus.CREATED).body(material);
    }

    @DeleteMapping("/material/{materialId}")
    public ResponseEntity<Material>  deleteMaterialById(@PathVariable("materialId") Integer materialId){

        costMgmtService.deleteMaterialById(materialId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PostMapping("/material/{materialId}")
    public ResponseEntity<Material>  updateMaterialById(@PathVariable Integer materialId,
                                                    @RequestBody MaterialRequest materialRequest ){

        costMgmtService.updateMaterialById(materialId,materialRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }





}
