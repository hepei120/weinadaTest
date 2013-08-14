
//-----注意文件名要和类名一致----------------
Ext.define('AM.controller.deptCtrl', { 
    extend: 'Ext.app.Controller', 
    init:function(){
         this.control({
            "deptTree button[id=allopen]" :{ //别名deptTree的button
               click:function(b,e){
                  var tree = b.ownerCt.ownerCt;//得到父层的父层
                  tree.expandAll();//调用展开所有方法
               }
            },
            "deptTree button[id=allclose]":{  //button和[]之间不能有空格
               click:function(b,e){
                  var tree = b.ownerCt.ownerCt;//得到父层的父层
                  tree.collapseAll();//调用展开所有方法
               }
            },
            'deptTree button[id = add]':{
                click:function(b,e){
                   var tree = b.ownerCt.ownerCt;
                   var nodes = tree.getChecked();
                   if(nodes.length == 1){
                      var node = nodes[0];
                      node.appendChild({
                            checked:true,
                            text:'技术架构',
                            id:'0103',
                            leaf:true
                      
                      });
                      
                   }else{
                      alert("请选择一个节点")
                   }
                }
            	
            },
            "deptTree":{
            	itemclick:function(tree,record,item,index,e,eOpts ){
            	   alert(record.get('id'));
            	}
                
            	
            }
            
         });
    
    },
  
    
    //粘合 models stores views
    models: [],
    stores: [],
    views:  ['deptView']

    
    

      
      
}); 
            