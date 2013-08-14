package com.comverse.timesheet.web.dao;


import com.comverse.timesheet.web.bean.SmUser;

public interface SmUserMapper {

	public boolean  insert(SmUser record);


	public boolean add(SmUser smUser) throws Exception;
	
	public boolean update(SmUser smUser) throws Exception;
	
	public boolean delete(Long id)throws Exception;
  
}