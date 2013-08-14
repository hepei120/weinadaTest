Ext.define("AM.view.Grid2View",{
    extend:'Ext.grid.Panel',
    alias:'widget.grid2',
    title:"User列表",
    id : "grid2",
    width:700,
	height:350,
    frame:true,
    border : 0,
    
	selType : "checkboxmodel",// 复选框模式 
	multiSelect:true, //允许多选 
	
	 /*selType:'rowmodel',//行选择模式
       multiSelect:true,//允许多选
       enableKeyNav:true//允许键盘选择
       */
	//selType:'cellmodel'//单元格选择模式
	
	enableKeyNav : true,//允许键盘选择
	columnLines : true, //列线显示
	//trackMouseOver : true, // 鼠标特效  没效果
    
	columns : [
		            Ext.create('Ext.grid.RowNumberer',{}),//显示行号
		           	{
						text:"序号",
						dataIndex:"id",
						width:50
					},{
						text : "帐号",
						dataIndex : "username",
						width : 100,
						field : {
							xtype : "textfield"
						}
					}, {
						text : "密码",
						dataIndex : "password",
						width : 100,
						field : {
							xtype : "textfield"
						}
					} ,{
					   text:'描述',
	                   xtype:'templatecolumn',
	                   tpl:'账号是：{username} ，密码是：{password}',
					   flex: 1 //占满表格剩余的宽度
					}
		],
		tbar:[{xtype : 'button',text : '添加',id : 'add'},
		      {xtype : 'button',text : '删除',id : 'delete'},
		      {xtype : 'button',text : '保存',id : 'save'}, 
		      {xtype : 'button',text : '导出excel',id : 'excel'}
	    ],
	  
		bbar : {
				xtype : 'pagingtoolbar',
				store : 'Grid2Store',
				//dock : 'bottom', //定位在下面				
				displayInfo : true //显示信息
				
		},
		//可编辑
		initComponent : function() {
				this.editing = Ext.create("Ext.grid.plugin.CellEditing");
				this.plugins = [this.editing];
				this.callParent(arguments);
		},
		store : "Grid2Store"
});