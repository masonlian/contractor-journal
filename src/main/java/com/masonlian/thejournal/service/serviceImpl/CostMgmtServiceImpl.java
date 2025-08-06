package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CostMgmtService;
import com.masonlian.thejournal.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import java.math.BigDecimal;

import java.util.ArrayList;

import java.util.List;


@Component
public class CostMgmtServiceImpl implements CostMgmtService {


    @Autowired
    private CostMgmtDao costMgmtDao;
    @Autowired
    private ProjectsService projectsService;

    @Autowired
    private FinancialServiceImpl financialService;

    @Override
    public void createMaterial(CreateMaterialRequest createMaterialRequest) {
        List<Material> materialList = new ArrayList<>();

        for (MaterialRequest materialRequest: createMaterialRequest.getCreateMaterialRequests()) {
            Material material = new Material();

            material.setImageUrl(materialRequest.getImageUrl());
            material.setUnitPrice(materialRequest.getUnitPrice());
            material.setMaterialName(materialRequest.getMaterialName());
            material.setSupplier(materialRequest.getSupplier());
            material.setConstructionCategory(materialRequest.getConstructionCategory());
            material.setSpecification(materialRequest.getSpecification());


            materialList.add(material);

        }

        costMgmtDao.createMaterial(materialList);

    }

    @Override
    public Material getMaterialById(Integer materialId) {

        return costMgmtDao.getMaterialById(materialId);
    }

    @Override
    public void deleteMaterialById(Integer materialId) {
        costMgmtDao.deleteMaterialById(materialId);
    }

    @Override
    public void updateMaterialById(Integer materialId, MaterialRequest materialRequest) {

        costMgmtDao.updateMaterialById(materialId,materialRequest);
    }

    @Override
    public List<Construction>  createConstruction(CreateConstructionRequest createConstructionRequest) {

        List<Construction> constructionList = new ArrayList<>();

        for( ConstructionRequest constructionRequest: createConstructionRequest.getCreateConstructionRequests()){

            Construction construction = new Construction();

            construction.setConstructionEstimate(constructionRequest.getConstructionEstimate());
            construction.setConstructionItem(constructionRequest.getConstructionItem());
            construction.setConstructionSpec(constructionRequest.getConstructionSpec());
            construction.setConstructionCategory(constructionRequest.getConstructionCategory());

            constructionList.add(construction);

        }


         int [] result = costMgmtDao.createConstruction(constructionList);
        if( Arrays.stream(result).allMatch(i -> i>0 )){
            return  constructionList;
        }  else  {
            return  null;
        }


    }

    @Override
    public Construction getConstructionById(Integer constructionId){

        return costMgmtDao.getConstructionById(constructionId);
    }

    @Override
    public void deleteConstructionById(Integer constructionId) {
        costMgmtDao.deleteConstructionById(constructionId);
    }

    @Override
    public void updateConstructionBydId (Integer constructionId,ConstructionRequest constructionRequest){

        costMgmtDao.updateConstructionBydId(constructionId,constructionRequest);
    }
    @Override
    public List<Material> getMaterials(QueryPara queryPara){

        return costMgmtDao.getMaterials(queryPara);

    }
    @Override
    public List<Construction> getConstructionItems(QueryPara queryPara){
        return costMgmtDao.getConstructionItems(queryPara);

    }

    @Transactional
    @Override
    public Integer createMaterialEvent(CreateMaterialEventRequest createMaterialEventRequest){


        BigDecimal totalAmount = BigDecimal.ZERO;

        List<MaterialUsed> materialUsedList = new ArrayList<>();

        for(MaterialItem materialItem :createMaterialEventRequest.getUsedList()){

            Material material = costMgmtDao.getMaterialByName( materialItem.getMaterialName());

            System.out.println("被找出的建材為:"+material.getMaterialName());
            System.out.println("建材名為:"+material.getMaterialName());

            MaterialUsed materialUsed = new MaterialUsed();

            materialUsed.setMaterialName(materialItem.getMaterialName());
            materialUsed.setUnit(materialItem.getUnit());
            BigDecimal unitPrice =  material.getUnitPrice();

            System.out.println("建材單價為:"+unitPrice);
            System.out.println("建材用量為:"+materialUsed.getUnit());

            materialUsed.setAmount(unitPrice.multiply(materialUsed.getUnit()));

            BigDecimal amount = materialUsed.getAmount();
            totalAmount = totalAmount.add(amount);

            System.out.println("被使用的物料為:"+materialUsed.getMaterialName()+"金額是:"+amount);


            materialUsedList.add(materialUsed);
        }

        System.out.println("建材總金額為:"+totalAmount);

        financialService.updateMaterialPayable(totalAmount);  //2025,08,03

        Integer materialEventId = costMgmtDao.createMaterialEvent(totalAmount,createMaterialEventRequest.getProjectId());

        projectsService.updateBalanceByMaterialCost(createMaterialEventRequest.getProjectId(), totalAmount);

        costMgmtDao.createMaterialUsed( materialEventId,materialUsedList);

        createAccountPayable(materialEventId, materialUsedList);


        return materialEventId;

    }

    @Override
    public void createAccountPayable(Integer materialEventId ,List<MaterialUsed> materialUsedList){

        for (MaterialUsed materialUsed : materialUsedList) {



            costMgmtDao.createAccountPayable(materialEventId,materialUsed);

        }


    }


    @Override
    public MaterialEvent getMaterialEventById(Integer materialEventId){


        return costMgmtDao.getMaterialEventById(materialEventId);
    }


    @Override
    public void  payToSupplier(Integer payableId, Boolean alreadyPaid){

        costMgmtDao.payToSupplier(payableId,alreadyPaid);


    }
    @Override
    public AccountPayable getPayableById(Integer payableId){

        return costMgmtDao.getPayableById(payableId);


    }
    @Override
    public List<AccountPayable> getPayable(QueryPara queryPara){

        return costMgmtDao.getPayable(queryPara);
    }

    @Override
    public List<MaterialEvent> getMaterialEventByProject(Integer projectId){

        return costMgmtDao.getMaterialEventByProject(projectId);
    }



}
