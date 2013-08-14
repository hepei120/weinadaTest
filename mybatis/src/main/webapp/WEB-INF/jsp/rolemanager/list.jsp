<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>

</head>
<body>

	<table id="dg" title="角色管理" class="easyui-datagrid"
		 url="${pageContext.request.contextPath}/rolemanager/ajaxlist.do"
		toolbar="#toolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="roleName" width="50">角色名</th>
				<th field="createdBy" width="50">创建人</th>
				<th field="roleCode" width="50">角色编码</th>
				<th field="createdDate" width="50">创建时间</th>
				<th field="activeFlag" width="50">是否有效</th>
			</tr>
		</thead>
	</table>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newRole()">新建</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editRole()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyRole()">移除</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">角色信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>角色名:</label> <input name="roleName" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>角色名:</label> <input name="roleCode" class="easyui-validatebox"
					required="true">
			</div>
			
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveRole()">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
	<script type="text/javascript">
        var url;
        function newRole(){
            $('#dlg').dialog('open').dialog('setTitle','新建');
            $('#fm').form('clear');
            url = '${ctx}/rolemanager/add.do';
        }
        function editRole(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','编辑');
                $('#fm').form('load',row);
                url = '${ctx}/rolemanager/update.do?id='+row.id;
            }
        }
        function saveRole(){
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
                $.messager.confirm('确认','你确定要删除该角色?',function(r){
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