package com.comverse.timesheet.web.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comverse.timesheet.web.bean.TestTable;
import com.comverse.timesheet.web.dao.ITestTableDAO;
import com.comverse.timesheet.web.util.BasicSqlSupport;

@Service
@Transactional
public class ITestTableDAOImpl extends BasicSqlSupport implements ITestTableDAO {
	public boolean add(TestTable test) throws Exception {
		boolean flag = false;
		int count = this.session.insert(
				"com.comverse.timesheet.web.mapper.Test.add", test);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	public List<TestTable> list() throws Exception {
		List<TestTable> list = new ArrayList<TestTable>();
		list = this.session
				.selectList("com.comverse.timesheet.web.mapper.Test.select");

		return list;
	}

	public Map<String, Object> listbySelect() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>(2);
		List<TestTable> list = new ArrayList<TestTable>();
		list = this.session
				.selectList("com.comverse.timesheet.web.mapper.Test.select");
		result.put("total", list == null ? 0 : list.size());
		result.put("rows", list);
		return result;
	}

	/**
 * 
 */
	public boolean update(TestTable t) throws Exception {
		boolean flag = false;
		int count = this.session.update(
				"com.comverse.timesheet.web.mapper.Test.update", t);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean delete(Long id) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int count = this.session.delete(
				"com.comverse.timesheet.web.mapper.Test.delete", id);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

}
