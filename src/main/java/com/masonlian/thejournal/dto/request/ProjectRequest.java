package com.masonlian.thejournal.dto.request;

public class ProjectRequest {

    private String projectName;
    private String description;
    private String owner;
    private String address;
    private String projectManager;
    private Integer constructionPeriod;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public Integer getConstructionPeriod() {
        return constructionPeriod;
    }

    public void setConstructionPeriod(Integer constructionPeriod) {
        this.constructionPeriod = constructionPeriod;
    }
}
