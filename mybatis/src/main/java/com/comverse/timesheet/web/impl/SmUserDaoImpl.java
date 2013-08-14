package com.comverse.timesheet.web.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comverse.timesheet.web.bean.SmUser;

@Service
@Transactional
public class SmUserDaoImpl extends BaseDao<SmUser> {

	public Map<String, Object> select(int page, int rows, SmUser model)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>(2);
		List<SmUser> list = new ArrayList<SmUser>();
		Integer offset = (page - 1) * rows;
		Integer limit = rows;
		model.setOffset(offset);
		model.setLimit(limit);

		list = this.session.selectList(this.getClass().getName() + ".select",
				model);
		result.put("total", queryByCount(model));
		result.put("rows", list);
		return result;
	}

	public int queryByCount(SmUser model) throws Exception {
		return this.session.selectOne(this.getClass().getName()
				+ ".queryByCount", model);
	}
}
