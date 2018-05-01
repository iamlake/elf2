package com.elf.sys.org.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SysOrgDimensionUnit {
    private String dimensionUnitId;

    private String dimensionId;

    private String unitId;

    private String parentDimensionUnitId;

    private String aliasName;

    private String unitPath;

    private BigDecimal depth;

    private BigDecimal sort;

    private Date timeBegin;

    private Date timeEnd;

    private String description;

    private String isEnabled;

    private String remark;

    private String activeFlag;

    private String createdBy;

    private Date creationTime;

    private String modifiedBy;

    private Date modificationTime;

    private String ext1;

    private String ext2;

    private String ext3;
    
    private SysOrgUnit unit;
    
    private SysOrgDimension dimension;

    public String getDimensionUnitId() {
        return dimensionUnitId;
    }

    public void setDimensionUnitId(String dimensionUnitId) {
        this.dimensionUnitId = dimensionUnitId == null ? null : dimensionUnitId.trim();
    }

    public String getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(String dimensionId) {
        this.dimensionId = dimensionId == null ? null : dimensionId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getParentDimensionUnitId() {
        return parentDimensionUnitId;
    }

    public void setParentDimensionUnitId(String parentDimensionUnitId) {
        this.parentDimensionUnitId = parentDimensionUnitId == null ? null : parentDimensionUnitId.trim();
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName == null ? null : aliasName.trim();
    }

    public String getUnitPath() {
        return unitPath;
    }

    public void setUnitPath(String unitPath) {
        this.unitPath = unitPath == null ? null : unitPath.trim();
    }

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled == null ? null : isEnabled.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(String activeFlag) {
        this.activeFlag = activeFlag == null ? null : activeFlag.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }

	public SysOrgUnit getUnit() {
		return unit;
	}

	public void setUnit(SysOrgUnit unit) {
		this.unit = unit;
	}

	public SysOrgDimension getDimension() {
		return dimension;
	}

	public void setDimension(SysOrgDimension dimension) {
		this.dimension = dimension;
	}

}