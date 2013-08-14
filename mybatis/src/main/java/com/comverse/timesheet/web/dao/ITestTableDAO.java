package com.comverse.timesheet.web.dao;

import com.comverse.timesheet.web.bean.TestTable;

public interface ITestTableDAO {
	public boolean add(TestTable test) throws Exception;
	
	public boolean update(TestTable test) throws Exception;
	
	public boolean delete(Long id)throws Exception;
}
