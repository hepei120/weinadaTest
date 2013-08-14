﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui-1.3.3/jquery-1.6.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>spring3</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			
		});
		
	    function addTab(title, url){
	        if ($('#tt').tabs('exists', title)){
	            $('#tt').tabs('select', title);
	        } else {
	            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	            $('#tt').tabs('add',{
	                title:title,
	                content:content,
	                closable:true
	            });
	        }
	    }

	</script>
</head>
<!-- easyui-layout 可分上下左右中五部分，中间的是必须的，支持href，这样就可以不用iframe了 -->
<body class="easyui-layout" id="mainBody">
	<!-- 上 -->
	<div region="north" split="false"
		style="height: 10px; text-align: center;" border="false">
		<h1>欢迎： ${userSessionInfo.name}</h1>
	</div>

	<!-- 左-->

	

	<div region="west" class="menudiv" split="true" title="系统菜单"
		style="width: 200px; overflow: hidden;">
		<div id="menuDiv" class="easyui-accordion" fit="false" border="false"
			animate="false">
			<div title="用户管理" style="overflow: hidden;">
				<ul>
					<li id="rightLi${child.id}" style="cursor: pointer;"
						onclick="addTab('用户管理','${ctx}/usermanager/userlist.do')">用户管理</li>
				</ul>
				<ul>
					<li id="rightLi${child.id}" style="cursor: pointer;"
						onclick="addTab('角色管理','${ctx}/rolemanager/list.do')">角色管理</li>
				</ul>
				<ul>
					<li id="rightLi${child.id}" style="cursor: pointer;"
						onclick="addTab('菜单管理','${ctx}/menumanager/list.do')">菜单管理</li>
				</ul>
			</div>
			<div title="部门管理" style="overflow: hidden;">
				<ul>
					<li id="rightLi${child.id}" onclick="addTab('组织管理','${ctx}/orgmanager/list.do')">组织管理</li>
					<li id="rightLi${child.id}">部门管理</li>
				</ul>
			</div>
			<div title="XXX管理" style="overflow: hidden;">
				<ul>
					<li id="rightLi${child.id}">资产管理</li>
					<li id="rightLi${child.id}">授权管理</li>
				</ul>
			</div>
			<div title="XXX管理" style="overflow: hidden;">
				<ul>
					<li id="rightLi${child.id}">报表管理</li>
					<li id="rightLi${child.id}">审计管理</li>
					<li id="rightLi${child.id}">日志管理</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- 中 -->
	<div id="tt" region="center" class="easyui-tabs" title="所在位置: 首页"
		style="overflow-x: hidden; padding: 0px;">
		<div title="Home"></div>
	</div>
</body>
</html>
