package com.comverse.timesheet.web.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.comverse.timesheet.web.bean.TestTable;
import com.comverse.timesheet.web.bean.TreeNode;
import com.comverse.timesheet.web.impl.ITestTableDAOImpl;

@Controller
public class ResultController extends BaseController {
	// 将spring 配置文件中的bean 通过setter注入进来

	@Autowired
	private ITestTableDAOImpl iTestTableDAOImpl;

	@RequestMapping(value = "/listbySelect.do")
	public @ResponseBody
	Map<String, Object> listbySelect(HttpServletRequest request,
			ModelMap modelMap, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>(2);
		try {
			result = iTestTableDAOImpl.listbySelect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/result.do")
	public ModelAndView viewUser(TestTable test) throws Exception {
		iTestTableDAOImpl.add(test);
		return new ModelAndView(new RedirectView("listresult.do"));
	}

	@RequestMapping("/resultTest.do")
	public ModelAndView resultTest(TestTable test) throws Exception {
		return new ModelAndView("grid2MVC/grid2");
	}

	@RequestMapping("/main.do")
	public ModelAndView main() throws Exception {
		return new ModelAndView("grid2MVC/main");
	}

	@RequestMapping(value = "/ajaxTree.do")
	public @ResponseBody
	Map<String, Object> ajaxTree() throws IOException {
		Map<String, Object> result = new HashMap<String, Object>(2);
		TreeNode  childrenNode =new TreeNode();
		childrenNode.setId(3);
		childrenNode.setText("二级(1)");
		childrenNode.setLeaf(true);
		
		TreeNode  childrenNode1 =new TreeNode();
		childrenNode1.setId(4);
		childrenNode1.setText("二级(2)");
		childrenNode1.setLeaf(true);
		
		TreeNode  childrenNode2 =new TreeNode();
		childrenNode2.setId(5);
		childrenNode2.setText("二级(3)");
		childrenNode2.setLeaf(true);
		
		TreeNode  childrenNode3 =new TreeNode();
		childrenNode3.setId(6);
		childrenNode3.setText("二级(4)");
		childrenNode3.setLeaf(false);
		
		TreeNode  childrenNode4=new TreeNode();
		childrenNode4.setId(7);
		childrenNode4.setText("'三级(1)");
		childrenNode4.setLeaf(true);
		TreeNode  childrenNode5=new TreeNode();
		childrenNode5.setId(8);
		childrenNode5.setText("'三级(2)");
		childrenNode5.setLeaf(true);
		List<TreeNode> treelist1=new ArrayList<TreeNode>();
		treelist1.add(childrenNode4);
		treelist1.add(childrenNode5);
		childrenNode3.setChildren(treelist1);
		List<TreeNode> treelist=new ArrayList<TreeNode>();
		treelist.add(childrenNode3);
		treelist.add(childrenNode2);
		treelist.add(childrenNode1);
		treelist.add(childrenNode);
		
		TreeNode tree =new TreeNode();
		tree.setId(1);
		tree.setText("我的办公桌");
		tree.setLeaf(false);
		tree.setChildren(treelist);
		
		TreeNode  childrenNode21 =new TreeNode();
		childrenNode21.setId(6);
		childrenNode21.setText("用户管理");
		childrenNode21.setLeaf(false);
		
		List<TreeNode> treelist2=new ArrayList<TreeNode>();
		treelist2.add(childrenNode21);
		
		TreeNode tree2 =new TreeNode();
		tree2.setId(2);
		tree2.setText("系统管理");
		tree2.setLeaf(false);
		tree2.setChildren(treelist2);
		
		List<TreeNode> data=new ArrayList<TreeNode>();
		data.add(tree);
		data.add(tree2);
		try {
			result.put("success", "true");
			result.put("data", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public ModelAndView update(TestTable test) throws Exception {

		iTestTableDAOImpl.update(test);
		return new ModelAndView(new RedirectView("listresult.do"));
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public Object delete(@RequestParam Long id) throws Exception {
		boolean issucess = iTestTableDAOImpl.delete(id);
		return issucess;
	}

	@RequestMapping("/listresult.do")
	public String listresult(HttpServletRequest req) {
		@SuppressWarnings("unused")
		List<TestTable> list = new ArrayList<TestTable>();
		try {
			list = iTestTableDAOImpl.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "listresult";
	}

}
