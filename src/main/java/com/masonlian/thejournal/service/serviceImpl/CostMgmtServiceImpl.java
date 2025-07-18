package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.ConstructionRequest;
import com.masonlian.thejournal.dto.request.CreateMaterialEventRequest;
import com.masonlian.thejournal.dto.request.MaterialItem;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CostMgmtService;
import com.masonlian.thejournal.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Component
public class CostMgmtServiceImpl implements CostMgmtService {


    @Autowired
    private CostMgmtDao costMgmtDao;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private CostMgmtService costMgmtService;
    @Autowired
    private FinancialServiceImpl financialService;

    @Override
    public Integer createMaterial(MaterialRequest materialRequest) {

        return costMgmtDao.createMaterial(materialRequest);

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
    public Integer createConstruction(ConstructionRequest constructionRequest){
        return costMgmtDao.createConstruction(constructionRequest);
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

    @Override
    public Integer createMaterialEvent(CreateMaterialEventRequest createMaterialEventRequest){


        BigDecimal totalAmount = BigDecimal.ZERO;

        List<MaterialUsed> materialUsedList = new ArrayList<>();

        for(MaterialItem materialItem :createMaterialEventRequest.getUsedList() ){

            Material material = costMgmtDao.getMaterialByName( materialItem.getMaterialName());

            MaterialUsed materialUsed = new MaterialUsed();

            materialUsed.setMaterialName(materialItem.getMaterialName());
            materialUsed.setUnit(materialItem.getUnit());
            BigDecimal unitPrice =  material.getUnitPrice();

            materialUsed.setAmount(unitPrice.multiply(materialUsed.getUnit()));

            BigDecimal amount = materialUsed.getAmount();
            totalAmount = totalAmount.add(amount);

            costMgmtService.createAccountPayable(materialUsed);

            materialUsedList.add(materialUsed);


        }


        financialService.updateMaterialPayable(totalAmount);

        Integer materialEventId = costMgmtDao.createMaterialEvent(totalAmount,createMaterialEventRequest.getProjectId());

        projectsService.updateBalanceByMaterialCost(createMaterialEventRequest.getProjectId(), totalAmount);

        costMgmtDao.createMaterialUsed( materialEventId,materialUsedList);


        return materialEventId;

    }

    @Override
    public void createAccountPayable(MaterialUsed materialUsed){

         costMgmtDao.createAccountPayable(materialUsed);

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

}
