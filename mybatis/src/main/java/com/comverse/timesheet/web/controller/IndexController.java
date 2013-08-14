package com.comverse.timesheet.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.comverse.timesheet.web.bean.TestTable;

@Controller
public class IndexController extends BaseController {
	// 将spring 配置文件中的bean 通过setter注入进来

	
	
	@RequestMapping("/index.do")
	public String viewUser(TestTable test) throws Exception {
		return "main";
	}
	
	
	

	

}
