Ext.QuickTips.init();
Ext.Loader.setConfig({
	enabled:true
});

/*Ext.application({
    
	name: 'AM', 
    appFolder: 'app',
    controllers:[
         'deptCtrl'
    ],
    launch: function(){ 
        Ext.create('Ext.container.Viewport',{ 
            layout: 'border',       
            items:{
                 xtype:'deptTree'
            }  
        });
    }
    
    */
Ext.application({
	requires: ['Ext.container.Viewport'],
	name: 'AM', 
    appFolder: 'js/grid2MVC/app',
    controllers:[
         'Main'
    ],
    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'ViewportMain'
                }
            ]
        });
    }
    
});
