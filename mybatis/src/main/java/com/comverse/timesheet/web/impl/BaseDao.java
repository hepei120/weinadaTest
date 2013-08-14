package com.comverse.timesheet.web.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.comverse.timesheet.web.bean.BaseModel;
import com.comverse.timesheet.web.util.BasicSqlSupport;

/**
 * 父类
 * 
 * @author Administrator
 * 
 * @param <T>
 */
public class BaseDao<T> extends BasicSqlSupport {

	public boolean add(T t) throws Exception {
		boolean flag = false;
		int count = this.session.update(this.getClass().getName() + ".add", t);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean update(T t) throws Exception {
		boolean flag = false;
		int count = this.session
				.update(this.getClass().getName() + ".update", t);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean delete(Long id) throws Exception {
		boolean flag = false;
		int count = this.session.delete(this.getClass().getName() + ".delete",
				id);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}
	


	public Map<String, Object> select(int page,int rows,T model) throws Exception {
		
		Map<String,Object> result = new HashMap<String, Object>(2);
		List<T> list = new ArrayList<T>();
		Integer offset=(page-1)*rows;
		Integer limit=rows;
		list = this.session.selectList(this.getClass().getName()+".select", new RowBounds((page-1)*rows,rows) );
		result.put("total", this.queryByCount(null));
		result.put("rows", list);
		return result;
	}

	public T selectOne(Long id) throws Exception {
		T t = this.session.selectOne(this.getClass().getName() + ".selectOne",
				id);

		return t;
	}

	public int queryByCount(BaseModel model) throws Exception {
		return this.session.selectOne(this.getClass().getName()
				+ ".queryByCount", model);
	}

	public List<T> queryByList(BaseModel model) throws Exception {
		Integer rowCount = queryByCount(model);
		model.getPager().setRowCount(rowCount);
		return this.session.selectList(this.getClass().getName()
				+ ".queryByList", model);
	}
}
