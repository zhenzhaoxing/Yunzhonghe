<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>My JSP 'inde.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script  src="angularjs/angular.min.js"></script>
    <script>
    var app=angular.module('myApp', []);//定义的模快
    //定义控制器
    app.controller('mycontroller', function($scope,$http) {
    //加法
    $scope.add=function(){
      return parseInt($scope.x)+parseInt($scope.y)
    }
    //减法
     $scope.get=function(){
      $http.get('/user/anjs').success(
        function(response){
       // alert(response.msg)
        $scope.ms=response.msg;
        }
      )
    }	
    
    
      //减法
     $scope.sub=function(){
      return parseInt($scope.x1)-parseInt($scope.y1)
    }	
    
    });
    
  </script>
  </head>
 <!--  
  <body ng-app ng-init="myname='你好你的手拿开'">
  请输入你的姓名：<input ng-model="myname">
<br>
{{myname}},你好

<input ng-model="mynameis">
  
  {{mynameis}}
  
  
  </body> -->
  
  
  
  
  <body ng-app='myApp' ng-controller="mycontroller">
   x:<input ng-model="x">
   y:<input ng-model="y">
  
  结果为 {{add()}}
  <br>
  
   x1:<input ng-model="x1">
   y1:<input ng-model="y1">
  
  结果为 {{sub()}}
  <button ng-click="get()">获取</button>
    <td>{{ms.name}}</td>
	<td>{{ms.age}}</td>
  
  </body> 
</html>
