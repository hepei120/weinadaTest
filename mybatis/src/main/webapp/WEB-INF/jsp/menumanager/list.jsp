<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>

</head>
<body>

	<table id="dg" title="菜单管理" class="easyui-datagrid"
		 url="${pageContext.request.contextPath}/menumanager/ajaxlist.do"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="menuName" width="50">菜单名</th>
				<th field="createdBy" width="50">创建人</th>
				<th field="menuCode" width="50">菜单编码</th>
				<th field="menuLink" width="50">菜单编码</th>
				<th field="activeFlag" width="50">是否有效</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newMenu()">新建</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editMenu()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyMenu()">移除</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">菜单信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>菜单名:</label> <input name="menuName" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>菜单名:</label> <input name="menuCode" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>菜单连接:</label> <input name="menuLink" class="easyui-validatebox"
					required="true">
			</div>
			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveMenu()">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	<script type="text/javascript">
        var url;
        function newMenu(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            url = '${ctx}/menumanager/add.do';
        }
        function editMenu(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','编辑');
                $('#fm').form('load',row);
                url = '${ctx}/menumanager/update.do?id='+row.id;
            }
        }
        function saveMenu(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (!result.success){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyRole(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','你确定要删除该菜单?',function(r){
                    if (r){
                        $.post('${ctx}/rolemanager/delete.do',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
	<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
</body>
</html>