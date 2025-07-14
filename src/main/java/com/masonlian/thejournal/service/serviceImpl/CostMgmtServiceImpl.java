package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.ConstructionRequest;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.Construction;
import com.masonlian.thejournal.model.Material;
import com.masonlian.thejournal.service.CostMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostMgmtServiceImpl implements CostMgmtService {


    @Autowired
    private CostMgmtDao costMgmtDao;

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


}
