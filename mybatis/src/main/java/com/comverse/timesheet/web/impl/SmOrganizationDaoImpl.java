package com.comverse.timesheet.web.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comverse.timesheet.web.bean.BaseModel;
import com.comverse.timesheet.web.bean.SmOrganization;
@Service
@Transactional
public class SmOrganizationDaoImpl extends BaseDao<SmOrganization>  {
	
	@Override
	public List<SmOrganization> queryByList(BaseModel model) throws Exception {
		return this.session.selectList(this.getClass().getName()
				+ ".queryByList", model);
	}
}
