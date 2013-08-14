<html>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <meta charset="UTF-8">
    <title>Basic CRUD Application - jQuery EasyUI CRUD Demo</title>
    
       

      

</head>
<body>
   
    <div class="easyui-accordion" style="width:200px;height:300px;">
        
        <div title="TreeMenu" data-options="iconCls:'icon-search'" style="padding:10px;">
            <ul class="easyui-tree">
                <li>
                    <span>Foods</span>
                    <ul>
                        <li>
                            <span>Fruits</span>
                            <ul>
                                <li>apple</li>
                                <li>orange</li>
                            </ul>
                        </li>
                        <li>
                            <span>Vegetables</span>
                            <ul>
                                <li>tomato</li>
                                <li>carrot</li>
                                <li>cabbage</li>
                                <li>potato</li>
                                <li>lettuce</li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>