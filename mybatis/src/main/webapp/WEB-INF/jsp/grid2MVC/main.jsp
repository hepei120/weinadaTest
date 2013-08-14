<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script type="text/javascript">

Ext.onReady(function() {
			/**
			 * 上,panel.Panel
			 */
			this.topPanel = Ext.create('Ext.panel.Panel', {
				height : 80,
				html : '业务基础平台',
				region : 'north',
				split : true,
				bbar : [{
					iconCls : 'icon-user',
					text : '管理员'
				},'-',{
					text : Ext.Date.format(new Date(),'Y年m月d日')
				},'->',{
					text : '退出',
					iconCls : 'icon-logout'
				}],
				bodyStyle : 'backgroud-color:#99bbe8;line-height : 50px;padding-left:20px;font-size:22px;color:#000000;font-family:黑体;font-weight:bolder;' +
						'background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, rgba(153,187, 232, 0.4) ), color-stop(50%, rgba(153, 187, 232, 1) ),color-stop(0%, rgba(153, 187, 232, 0.4) ) )'

					});
			/**
			 * 左,panel.Panel
			 */
			this.leftPanel = Ext.create('Ext.panel.Panel', {
						region : 'west',
						title : '导航栏',
						width : 230,
						layout : 'accordion',
						collapsible : true
					});
			
			
			var currentItem;
			this.rightPanel  = Ext.createWidget('tabpanel', {
		        resizeTabs: true,
		        enableTabScroll: true,
		        margin:'10',
		        width: 600,
		        height: 250,
		        defaults: {
		            autoScroll:true,
		            bodyPadding: 10
		        },
		        region : 'center',
		    	tabWidth : 120,
		        items: [{
		            title: '选项卡',
		            html: '选项卡内容',
		            closable: true
		        }]
		        
		    });
		    
		    function addTab (text,url) {
		    	var panel = Ext.create('Ext.panel.Panel',{
					title : text,
					closable : true,
					iconCls : 'icon-activity',
					html : '<iframe width="100%" height="100%" frameborder="0" src="'+url+'"></iframe>'
				});
		        rightPanel.add(panel);
		        rightPanel.setActiveTab(panel);
		    }

			/**
			 * 组建树
			 */
			var buildTree = function(json) {
				return Ext.create('Ext.tree.Panel', {
							rootVisible : false,
							border : false,
							store : Ext.create('Ext.data.TreeStore', {
										root : {
											expanded : true,
											children : json.children
										}
									}),
							listeners : {
								'itemclick' : 
									function(view, record, item,index, e) {
									var id = record.get('id');
									var text = record.get('text');
									var leaf = record.get('leaf');
									var url = record.get('hrefTarget');
									if (leaf) {
										addTab(text,url);
									}
								},
								scope : this
							}
						});
			};
			/**
			 * 加载菜单树
			 */
			Ext.Ajax.request({
						url : 'menumanager/queryByList.do',
						success : function(response) {
							var json = Ext.JSON.decode(response.responseText)
							Ext.each(json.data, function(el) {
										var panel = Ext.create(
												'Ext.panel.Panel', {
													id : el.id,
													title : el.text,
													layout : 'fit'
												});
										panel.add(buildTree(el));
										leftPanel.add(panel);
									});
						},
						failure : function(request) {
							Ext.MessageBox.show({
										title : '操作提示',
										msg : "连接服务器失败",
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
						},
						method : 'post'
					});
			/**
			 * Viewport
			 */
			Ext.create('Ext.container.Viewport', {
						layout : 'border',
						renderTo : Ext.getBody(),
						items : [this.topPanel, this.leftPanel, this.rightPanel]
					});
		});


</script>
</head>

</html>
