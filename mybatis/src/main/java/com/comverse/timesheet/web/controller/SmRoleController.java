package com.comverse.timesheet.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comverse.timesheet.web.bean.SmRole;
import com.comverse.timesheet.web.impl.SmRoleDaoImpl;

@Controller
@RequestMapping("/rolemanager")
public class SmRoleController extends BaseController {

	@Autowired
	private SmRoleDaoImpl smRoleDaoImpl;

	@RequestMapping(value = "/ajaxlist.do")
	public @ResponseBody
	Map<String, Object> listbySelect(HttpServletRequest request,SmRole sr,
			ModelMap modelMap, HttpServletResponse response) throws IOException {
		String spage="";
		if(null==request.getParameter("page")||request.getParameter("page").equals("")){
			spage="0";
		}else {
			spage=request.getParameter("page");
		}
		int page = Integer.parseInt(spage);
		String srow="";
		if(null==request.getParameter("rows")||request.getParameter("rows").equals("")){
			srow="0";
		}else {
			srow=request.getParameter("rows");
		}
		
		int row = Integer.parseInt(srow);// 接受参数page和rows
		Map<String, Object> result = new HashMap<String, Object>(2);
		try {
			result = smRoleDaoImpl.select(page,row,sr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	@RequestMapping(value = "/add.do")
	public @ResponseBody
	Map<String, Object> add(SmRole smRole) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			smRole.setCreatedBy("admin");
			smRole.setActiveFlag(0);
			smRole.setCreatedDate(new Date());
			boolean r = (smRoleDaoImpl.add(smRole));
			result.put("success", r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> update(SmRole smRole) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			boolean r = false;
			r = (smRoleDaoImpl.update(smRole));
		    result.put("success", r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> delete(@RequestParam Long id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean r = false;
		r = smRoleDaoImpl.delete(id);
		if (r)
			result.put("success", r);

		return result;
	}

	

	@RequestMapping("/list.do")
	public String listresult(HttpServletRequest req) {

		return "rolemanager/list";
	}

}
