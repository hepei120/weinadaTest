package com.comverse.timesheet.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.comverse.timesheet.web.bean.SmOrganization;
import com.comverse.timesheet.web.impl.SmOrganizationDaoImpl;

@Controller
@RequestMapping("/orgmanager")
public class SmOrganizationController extends BaseController {

	@Autowired
	private SmOrganizationDaoImpl smOrganizationDaoImpl;

	@RequestMapping(value = "/ajaxlist.do")
	public @ResponseBody
	Map<String, Object> listbySelect(HttpServletRequest request,SmOrganization so,
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
			result = smOrganizationDaoImpl.select(page,row,so);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	@RequestMapping(value = "/add.do")
	public @ResponseBody
	Map<String, Object> add(SmOrganization org) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			org.setCreatedBy("admin");
			org.setActiveFlag(0);
			boolean r = (smOrganizationDaoImpl.add(org));
			result.put("success", r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> update(SmOrganization org) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			boolean r = false;
			r = (smOrganizationDaoImpl.update(org));
		    result.put("success", r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/queryByList.do", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Object> queryByList() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<SmOrganization> r = smOrganizationDaoImpl.queryByList(null);
		result.put("data", r);
		return result;
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> delete(@RequestParam Long id) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean r = false;
		r = smOrganizationDaoImpl.delete(id);
		if (r)
			result.put("success", r);

		return result;
	}
	

	@RequestMapping("/list.do")
	public String listresult(HttpServletRequest req) {

		return "orgmanager/list";
	}

}
