Ext.define('AM.store.deptStore',{
    extend:'Ext.data.TreeStore',
    dafaultRoodId:'root',
    proxy:{
       type:'ajax',
       url:'',
       
    }
	
});