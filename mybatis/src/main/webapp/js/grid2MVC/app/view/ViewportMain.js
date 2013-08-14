Ext.define('AM.view.ViewportMain', {
	extend : 'Ext.Viewport',
	layout : 'fit',
	 alias:'widget.ViewportMain',
	hideBorders : true,
	requires : [ 'AM.view.Header', 'AM.view.Menu', 'AM.view.TabPanel',
			'AM.view.South' ],
	initComponent : function() {
		var me = this;
		Ext.apply(me, {
			items : [ {
				id : 'desk',
				layout : 'border',
				items : [ Ext.create('AM.view.Header'),
						Ext.create('AM.view.Menu'),
						Ext.create('AM.view.TabPanel'),
						Ext.create('AM.view.South') ]
			} ]
		});
		me.callParent(arguments);
	}
})
