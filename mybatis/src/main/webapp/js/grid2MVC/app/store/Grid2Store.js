Ext.define('AM.store.Grid2Store',{
    extend:'Ext.data.Store',
    model:'AM.model.Grid2Model',
    pageSize : 10,//分页大小
   
    proxy:{
    	
    	//异步获取数据，这里的URL可以改为任何动态页面，只要返回JSON数据即可
		type:"ajax",
		url:"/ssh2demo1/extjs/list.action",
		
		reader:{
			type:"json",
			root:"datas",
			totalProperty :'total'		
		},
		
		writer:{
			type:"json"
		}
	},
	/* 不管用
	 * remoteSort: true,  //是否在服务端排序 
	sorters: [{ 
            //排序字段。           
		property: 'password', 
            //排序类型，默认为 ASC  DESC    
		direction: 'ASC' 
    }] ,*/
	autoLoad:true
	
});