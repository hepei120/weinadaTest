package com.comverse.timesheet.web.bean;


import java.util.Date;

public class SmUser extends BaseModel {
    private Integer id;

    private String createdBy;

    private Date createdDate;

    private String lastUpdBy;

    private Date lastUpdDate;

    private Integer modificationNum;

    private String userName;

    private String userCode;

    private String password;

    private Integer userGroupId;

    private Integer userGroupStatusFlag;

    private Integer taxisNo;

    private Integer userInformationId;

    private Integer userSecurityId;

    private String userHomeBlock;

    private Integer isDelete;

    private Integer activeFlag;

    private String remark;

    private Integer forwardnext;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getUserGroupStatusFlag() {
        return userGroupStatusFlag;
    }

    public void setUserGroupStatusFlag(Integer userGroupStatusFlag) {
        this.userGroupStatusFlag = userGroupStatusFlag;
    }

    public Integer getTaxisNo() {
        return taxisNo;
    }

    public void setTaxisNo(Integer taxisNo) {
        this.taxisNo = taxisNo;
    }

    public Integer getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(Integer userInformationId) {
        this.userInformationId = userInformationId;
    }

    public Integer getUserSecurityId() {
        return userSecurityId;
    }

    public void setUserSecurityId(Integer userSecurityId) {
        this.userSecurityId = userSecurityId;
    }

    public String getUserHomeBlock() {
        return userHomeBlock;
    }

    public void setUserHomeBlock(String userHomeBlock) {
        this.userHomeBlock = userHomeBlock == null ? null : userHomeBlock.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getForwardnext() {
        return forwardnext;
    }

    public void setForwardnext(Integer forwardnext) {
        this.forwardnext = forwardnext;
    }
}