package com.comverse.timesheet.web.bean;

import java.util.List;

public class SmMenu extends BaseModel{
	private Integer id;

	private String createdBy;

	private String lastUpdBy;

	private Integer modificationNum;

	private String menuCode;

	private String text;

	private Integer menuType;

	private Integer menuLevel;

	private Integer parentId;

	private String hrefTarget;

	private Integer taxisNo;

	private Integer activeFlag;

	private String remark;

	private String leaf;

	private SmMenu parentMenu;

	private List<SmMenu> children;

	public String getLeaf() {
		if (this.getChildren().isEmpty()) {
			this.leaf = String.valueOf(true);
		} else {
			this.leaf = String.valueOf(false);
		}
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

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

	public String getLastUpdBy() {
		return lastUpdBy;
	}

	public void setLastUpdBy(String lastUpdBy) {
		this.lastUpdBy = lastUpdBy == null ? null : lastUpdBy.trim();
	}

	public Integer getModificationNum() {
		return modificationNum;
	}

	public void setModificationNum(Integer modificationNum) {
		this.modificationNum = modificationNum;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode == null ? null : menuCode.trim();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	
	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public SmMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(SmMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public List<SmMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SmMenu> children) {
		this.children = children;
	}


}