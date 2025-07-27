package com.masonlian.thejournal.model;

import java.math.BigDecimal;

public class Material{



    private Integer materialId;
    private String constructionCategory;
    private String materialName;
    private String specification;
    private String imageUrl;
    private BigDecimal unitPrice;
    private String supplier;


    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getConstructionCategory() {
        return constructionCategory;
    }

    public void setConstructionCategory(String constructionCategory) {
        this.constructionCategory = constructionCategory;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
