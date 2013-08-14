package com.comverse.timesheet.web.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comverse.timesheet.web.bean.BaseModel;
import com.comverse.timesheet.web.bean.SmMenu;

@Service
@Transactional
public class SmMenuDaoImpl extends BaseDao<SmMenu> {
	@Override
	public List<SmMenu> queryByList(BaseModel model) throws Exception {
		return this.session.selectList(this.getClass().getName()
				+ ".queryByList", model);
	}

	public Map<String, Object> select(int page, int rows, SmMenu model)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>(2);
		List<SmMenu> list = new ArrayList<SmMenu>();
		Integer offset = (page - 1) * rows;
		Integer limit = rows;
		model.setOffset(offset);
		model.setLimit(limit);
		list = this.session.selectList(this.getClass().getName() + ".select",
				model, new RowBounds(offset, limit));
		result.put("total", queryByCount(model));
		result.put("rows", list);
		return result;
	}

	public int queryByCount(SmMenu model) throws Exception {
		return this.session.selectOne(this.getClass().getName()
				+ ".queryByCount", model);
	}
}
