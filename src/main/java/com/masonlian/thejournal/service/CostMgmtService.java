package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.Material;

public interface CostMgmtService {

    Integer createMaterial(MaterialRequest materialRequest );
    Material getMaterialById(Integer materialId);
    void updateMaterialById(Integer materialId, MaterialRequest materialRequest);
    void deleteMaterialById(Integer materialId);

}
