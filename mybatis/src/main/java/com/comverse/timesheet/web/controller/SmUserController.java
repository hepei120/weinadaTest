package com.comverse.timesheet.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comverse.timesheet.web.bean.SmUser;
import com.comverse.timesheet.web.impl.SmUserDaoImpl;

@Controller
@RequestMapping("/usermanager")
public class SmUserController extends BaseController {

	@Autowired
	private SmUserDaoImpl smUserDaoImpl;

	public String smUserStr;
	
	public String userName;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSmUserStr() {
		return smUserStr;
	}

	public void setSmUserStr(String smUserStr) {
		this.smUserStr = smUserStr;
	}

	@RequestMapping(value = "/listbySelect.do")
	public @ResponseBody
	Map<String, Object> listbySelect(HttpServletRequest request,SmUser sm,
			ModelMap modelMap, HttpServletResponse response) throws IOException {
		String spage="";
		if(null==request.getParameter("page")||request.getParameter("page").equals("")){
			spage="0";
		}else {
			spage=request.getParameter("page");
		}
		int page = Integer.parseInt(spage);
		String srow="";
		if(null==request.getParameter("limit")||request.getParameter("limit").equals("")){
			srow="0";
		}else {
			srow=request.getParameter("limit");
		}
		int row = Integer.parseInt(srow);// 接受参数page和rows
		Map<String, Object> result = new HashMap<String, Object>(2);
		try {
			result = smUserDaoImpl.select(page,row,sm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/list.do")
	public @ResponseBody
	Map<String, Object> list(SmUser model, HttpServletRequest request)
			throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>(2);
		List<SmUser> dataList = smUserDaoImpl.queryByList(model);
		// 设置页面数据
		result.put("dataList", dataList);
		return result;
	}

	@RequestMapping(value = "/useradd.do")
	public @ResponseBody
	Map<String, Object> userAdd(SmUser smUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			smUser.setCreatedBy("admin");
			smUser.setActiveFlag(0);
			smUser.setCreatedDate(new Date());
			boolean r = (smUserDaoImpl.add(smUser));
			result.put("success", r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/userupdate.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> userUpdate(String smUserStr) {
		JSONArray array = JSONArray.fromObject(smUserStr);
		// List<SmUser> alertUsers = new ArrayList<SmUser>();
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			boolean r = false;
			for (int i = 0; i < array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				SmUser su = (SmUser) JSONObject
						.toBean(jsonObject, SmUser.class);
				if (su.getId() == null || su.getId() == 0) {
					su.setCreatedBy("admin");
					su.setCreatedDate(new Date());
					su.setActiveFlag(0);
					r = smUserDaoImpl.add(su);
				} else {
					r = smUserDaoImpl.update(su);
				}
			}

			result.put("success", r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/userdelete.do", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> delete(@RequestParam Long[] deleteIds) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean r = false;
		for(Long id :deleteIds){
		r = smUserDaoImpl.delete(id);
		}
		if (r)
			result.put("success", r);

		return result;
	}

	/*
	 * 
	 * @RequestMapping(value="/update.do",method=RequestMethod.POST) public
	 * ModelAndView update(TestTable test) throws Exception {
	 * 
	 * iTestTableDAOImpl.update(test); return new ModelAndView(new
	 * RedirectView("listresult.do")); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping("/listresult.do") public String
	 * listresult(HttpServletRequest req) {
	 * 
	 * @SuppressWarnings("unused") List<TestTable> list = new
	 * ArrayList<TestTable>(); try { list = iTestTableDAOImpl.list(); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * return "listresult"; }
	 */

	@RequestMapping("/userlist.do")
	public String listresult(HttpServletRequest req) {

		return "usermanager/userlist";
	}

}
