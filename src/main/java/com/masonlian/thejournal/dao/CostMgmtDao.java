package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.Material;
import org.springframework.web.bind.annotation.RequestBody;

public interface CostMgmtDao {
    Integer createMaterial(MaterialRequest materialRequest);
    Material getMaterialById(Integer materialId);
    void updateMaterialById(Integer materialId, MaterialRequest materialRequest);
    void deleteMaterialById(Integer materialId);
}
