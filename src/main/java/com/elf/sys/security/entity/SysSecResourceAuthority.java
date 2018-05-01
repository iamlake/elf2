package com.elf.sys.security.entity;

import java.util.Date;

public class SysSecResourceAuthority {
    private String id;

    private String description;

    private String roleId;

    private String roleType;

    private String resourceId;

    private String resourceType;

    private String authorityType;

    private String dimensionConstraint;

    private String circumstanceId;

    private String circumstanceType;

    private String createdBy;

    private Date creationDate;

    private String lastUpdatedBy;

    private Date lastUpdateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(String authorityType) {
        this.authorityType = authorityType == null ? null : authorityType.trim();
    }

    public String getDimensionConstraint() {
        return dimensionConstraint;
    }

    public void setDimensionConstraint(String dimensionConstraint) {
        this.dimensionConstraint = dimensionConstraint == null ? null : dimensionConstraint.trim();
    }

    public String getCircumstanceId() {
        return circumstanceId;
    }

    public void setCircumstanceId(String circumstanceId) {
        this.circumstanceId = circumstanceId == null ? null : circumstanceId.trim();
    }

    public String getCircumstanceType() {
        return circumstanceType;
    }

    public void setCircumstanceType(String circumstanceType) {
        this.circumstanceType = circumstanceType == null ? null : circumstanceType.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy == null ? null : lastUpdatedBy.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}