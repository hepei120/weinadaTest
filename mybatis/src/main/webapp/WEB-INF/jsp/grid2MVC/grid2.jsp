<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>grid2MVC</title>


<script type="text/javascript">
Ext.Loader.setConfig({enabled:true});  

Ext.Loader.setPath('Ext.ux', 'js/ext-4.1/ux');
Ext.require([
    'Ext.grid.*',
    'Ext.toolbar.Paging',
    'Ext.util.*',
    'Ext.data.*',
    'Ext.state.*',
    'Ext.form.*',
    'Ext.ux.form.SearchField',
    'Ext.selection.CellModel',
    'Ext.ux.CheckColumn',
    'Ext.selection.CheckboxModel'
]);


	Ext.application({
		name : 'MyApp',
		launch : function() { 
			Ext.define('User', {  
                extend : 'Ext.data.Model',  
                fields : [ {
					name : 'id',
					type : 'int'
				},{
					name : 'userName',
					type : 'string'
				}, {
					name : 'userCode',
					type : 'string'
				}, {
					name : 'lastUpdDate',
					type:"date", 
					dateFormat:"Y-m-dTH:i:s"
				},{
					name : 'password',
					type : 'string'
				} ,{
					name : 'createdDate',
					type : 'date',
					dateFormat:"Y-m-dTH:i:s"
				},{
					name : 'lastUpdBy',
					type : 'string'
				},{
					name : 'lastUpdDate',
					type : 'date',
					dateFormat:"Y-m-dTH:i:s"
				},{
					name : 'userHomeBlock',
					type : 'string'
				}],
                idProperty : 'id'// 极为重要的配置。关系到表格修改数据的获取  
            }); 
			
			var itemsPerPage = 5;   // set the number of items you want per page
			var store = Ext.create('Ext.data.Store', {
			    id:'simpsonsStore',
			    autoLoad: true,
			    model : 'User', 
			    pageSize: itemsPerPage, // items per page
			    proxy: {
			        type: 'ajax',
			        url : 'usermanager/listbySelect.do',// url that will load data with respect to start and limit params
			        reader: {
			            type: 'json',
			            root: 'rows',
			            totalProperty: 'total'
			        }
			    }
			});
			// specify segment of data you want to load using params
			store.load({
			    params:{
			        start:0,
			        limit: itemsPerPage
			    }
			});
			
			function add(){  
		        store.insert(0,new User());  
		    } ;
			function update() {
				var records = store.getUpdatedRecords();// 获取修改的行的数据，无法获取幻影数据  
		        var phantoms=store.getNewRecords( ) ;//获得幻影行  
		        records=records.concat(phantoms);//将幻影数据与真实数据合并  
		        if (records.length == 0) {  
		            Ext.MessageBox.show({  
		                title : "提示",  
		                msg : "没有任何数据被修改过!"  
		                    // icon: Ext.MessageBox.INFO  
		                });  
		            return;  
		        } else {  
		            Ext.Msg.confirm("请确认", "是否真的要修改数据？", function(button, text) {  
		                if (button == "yes") {  
		                    var data = [];  
		                    alert(records);  
		                    Ext.Array.each(records, function(record) {  
		                        data.push(record.data);  
		                           record.commit();// 向store提交修改数据，页面效果  
		                        });  
		  
		                    Ext.Ajax.request({  
		                        url : 'usermanager/userupdate.do',  
		                        params : {  
		                        	smUserStr : Ext.encode(data)  
		                        },  
		                        method : 'POST',  
		                        timeout : 2000,  
		  
		                        success : function(response, opts) {  
		                            var success = Ext.decode(response.responseText).success;  
		                            // 当后台数据同步成功时  
		                            if (success) {  
		                                Ext.Array.each(records, function(record) {  
		                                            // data.push(record.data);  
		                                            record.commit();// 向store提交修改数据，页面效果  
		                                        });  
		                            } else {  
		                                Ext.MessageBox.show({  
		                                    title : "提示",  
		                                    msg : "数据修改失败!"  
		                                        // icon: Ext.MessageBox.INFO  
		                                    });  
		                            }  
		                        }  
		                    });  
		                }  
		            });  
		  
		        }  
			};
			function deleteUser() {
				   var data = grid.getSelectionModel().getSelection();  
			         if (data.length == 0) {  
			             Ext.MessageBox.show({  
			                 title : "提示",  
			                 msg : "请先选择您要操作的行!" ,
			                 icon: Ext.MessageBox.INFO  
			                 });  
			             return;  
			         } else {  
			             Ext.Msg.confirm("请确认", "是否真的要删除数据？",
			             function(button, text) {  
			                 if (button == "yes") {  
			                     var ids = [];  
			                     Ext.Array.each(data, function(record) {  
			                                 var userId=record.get('id');  
			                                 //如果删除的是幻影数据，则id就不传递到后台了，直接在前台删除即可  
			                                 if(userId){  
			                                     ids.push(userId);  
			                                 }  
			                             });  
			                     Ext.Ajax.request({  
			                         url : 'usermanager/userdelete.do',  
			                         params : {  
			                             deleteIds : ids.join(',')  
			                         },  
			                         method : 'POST',  
			                         // timeout : 2000,//默认30秒  
			                         success : function(response, opts) {  
			                             // store.loadPage(1);  
			                             var success = Ext.decode(response.responseText).success;  
			                             // 当后台数据同步成功时  
			                             if (success) {  
			                                 Ext.Array.each(data, function(record) {  
			                                             store.remove(record);// 页面效果  
			                                         });  
			                                 Ext.MessageBox.show({  
			                                     title : "提示",  
			                                     msg : "数据成功!"  
			                                         // icon: Ext.MessageBox.INFO  
			                                     });  
			                             } else {  
			                                 Ext.MessageBox.show({  
			                                     title : "提示",  
			                                     msg : "数据删除失败!"  
			                                         // icon: Ext.MessageBox.INFO  
			                                     });  
			                             }  
			                         }  
			                     });  
			                 }  
			             });  
			         }  
			}; 
		

			var grid=	Ext.create('Ext.grid.Panel', {
			    title: '用户管理',
			    store: store,
			       tbar : [ 
				 /* {  
				    xtype: 'combo',  
				    queryMode: 'local',  
				    store: Ext.data.StoreManager.lookup('simpsonsStore'),  
				    displayField: 'userName',  
				    valueField: 'id',  
				    triggerAction: 'all',  
				    editable: false  
				},    */
				
				/* {      xtype:'combo',
			           triggerAction:  'all',                    
			           forceSelection: true,
			           id: 'id',
			           name: 'id',
			           valueField: 'id',
			           displayField:   'id',                    
			           queryMode: 'local',
			           store: store,                     
			           width : 50
			         },'-', */'用户名:　', {
			          xtype : 'textfield',
			          id : 'userName',
			          width : 150
			         },'-', {
			               xtype: 'button',
			               text: '搜索',
			               margin: '0 0 0 5',
			               handler: function () {
			                   /*  var name = Ext.getCmp('id').getValue(); //获取文本框值 */
			                    var stage = Ext.getCmp('userName').getValue();
			                      store.on('beforeload', function (store, options) {
			                                       var new_params = { userName: stage};
			                                       Ext.apply(store.proxy.extraParams, new_params);
			                                       });
			                                   store.load({ params: { start: 0, limit: itemsPerPage} });
			                   }
			         
			           },
			     {
					xtype : 'button',
					text : '新增 ',
					iconCls : 'addBtn',
					handler : add
				}, {
					xtype : 'button',
					text : '修改',
					iconCls : "updateBtn",
					handler : update
				}, {
					xtype : 'button',
					text : '删除',
					iconCls : "deleteBtn",
					handler : deleteUser
				} ],
				columns : [ {
					text : 'Id',
					dataIndex : 'id',
					editor: 'textfield'
				}, {
					text : '姓名',
					dataIndex : 'userName',
					editor: 'textfield'
					
				},{
					text : '用户名',
					dataIndex : 'userCode',
					editor: 'textfield'
				
				}, {
					text : '密码',
					dataIndex : 'password'
				} ,{
					text : '创建日期 ',
					dataIndex : 'createdDate'
				}, {
					text : '修改人',
					dataIndex : 'lastUpdBy'
				} ,{
					text : ' 修改时间 ',
					dataIndex : 'lastUpdDate'
				}  ,{
					text : '主页',
					dataIndex : 'userHomeBlock',
					editor: 'textfield',
					flex : 2
				} ],
				 selModel : Ext.create('Ext.selection.CheckboxModel'),  
			    width: '100%',
			    height: '50%',
			    dockedItems: [{
			        xtype: 'pagingtoolbar',
			        store: store,   // same store GridPanel is using
			        dock: 'bottom',
			        displayInfo: true
			    }],
			    selType: 'cellmodel',
			    plugins: [
			        Ext.create('Ext.grid.plugin.CellEditing', {
			            clicksToEdit: 1
			        })
			    ],
			    renderTo: Ext.getBody()
			});
			
	
	}
	});
	
	
	
</script>
</head>
<body>

</body>
</html>