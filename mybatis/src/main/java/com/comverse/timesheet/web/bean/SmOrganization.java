package com.comverse.timesheet.web.bean;

import java.util.Date;
import java.util.List;

public class SmOrganization {
    private Integer id;

    private String createdBy;

    private Date createdDate;

    private String lastUpdBy;

    private Date lastUpdDate;

    private Integer modificationNum;

    private String userGroupName;

    private Integer parentUserGroupId;

    private Integer typeFlag;

    private String groupLevel;

    private String namePath;

    private String idPath;

    private Integer taxisNo;

    private Integer activeFlag;

    private Integer isroot;

    private String remark;
    
    private SmOrganization parentOrg;
    
    private List<SmOrganization> smOrganizationList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(String lastUpdBy) {
        this.lastUpdBy = lastUpdBy == null ? null : lastUpdBy.trim();
    }

    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    public Integer getModificationNum() {
        return modificationNum;
    }

    public void setModificationNum(Integer modificationNum) {
        this.modificationNum = modificationNum;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName == null ? null : userGroupName.trim();
    }

    public Integer getParentUserGroupId() {
        return parentUserGroupId;
    }

    public void setParentUserGroupId(Integer parentUserGroupId) {
        this.parentUserGroupId = parentUserGroupId;
    }

    public Integer getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(Integer typeFlag) {
        this.typeFlag = typeFlag;
    }

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel == null ? null : groupLevel.trim();
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath == null ? null : namePath.trim();
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath == null ? null : idPath.trim();
    }

    public Integer getTaxisNo() {
        return taxisNo;
    }

    public void setTaxisNo(Integer taxisNo) {
        this.taxisNo = taxisNo;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Integer getIsroot() {
        return isroot;
    }

    public void setIsroot(Integer isroot) {
        this.isroot = isroot;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    
    

	public SmOrganization getParentOrg() {
		return parentOrg;
	}

	public void setParentOrg(SmOrganization parentOrg) {
		this.parentOrg = parentOrg;
	}

	public List<SmOrganization> getSmOrganizationList() {
		return smOrganizationList;
	}

	public void setSmOrganizationList(List<SmOrganization> smOrganizationList) {
		this.smOrganizationList = smOrganizationList;
	}
    
}