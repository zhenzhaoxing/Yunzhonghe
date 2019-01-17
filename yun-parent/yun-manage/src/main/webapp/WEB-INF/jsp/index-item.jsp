<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 <link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
  </head>
  
  <body>
  <div>
    <a class="easyui-linkbutton" onclick="importItems()" >一键导入数据到索引库</a>
  </div>
   <script type="text/javascript">
   function importItems(){
       $.ajax({
			url:'http://localhost:8083/item/init',
			type:'post',
			dataType:'jsonp',
			jsonpCallback:'abc',//参数值 这个是 告诉控制器返回到那个函数
			jsonp:'callback',//参数名
			/* success:function(data){
				alert(data[0]+"success");
			} */
		}); 
   
   
   }
   
   
   //返回的
   function abc(data){
	    if(data.status == 200){
				$.messager.alert('提示','导入索引库成功！');
				
			} else {
				$.messager.alert('提示','导入索引库失败！');
			}
}
   </script>
   
   
   
  </body>
</html>
