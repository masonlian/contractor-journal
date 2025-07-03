package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.Material;
import com.masonlian.thejournal.service.CostMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CostMgmtServiceImpl implements CostMgmtService {


    @Autowired
    private CostMgmtDao costMgmtDao;

    public Integer createMaterial(MaterialRequest materialRequest) {

        return costMgmtDao.createMaterial(materialRequest);

    }

    public Material getMaterialById(Integer materialId) {

        return costMgmtDao.getMaterialById(materialId);
    }

    public void deleteMaterialById(Integer materialId) {
        costMgmtDao.deleteMaterialById(materialId);
    }

    public void updateMaterialById(Integer materialId, MaterialRequest materialRequest) {

        costMgmtDao.updateMaterialById(materialId,materialRequest);
    }



}
