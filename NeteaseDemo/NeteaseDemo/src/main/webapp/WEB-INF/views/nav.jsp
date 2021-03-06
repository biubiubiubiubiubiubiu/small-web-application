<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html ng-app="myApp">
<head>
    <title>java</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>
</head>
<body ng-controller="MainController">
<nav class="navbar navbar-toggleable-md navbar-light bg-faded navbar-fixed-top" >
    <logo><a class="navbar-brand" href="/">Netease Demo</a></logo>
    <div id= "mynav" class="collapse navbar-collapse" id="navbarSupportedContent">
        <div class="inline col-md-2" style="margin-top: auto; margin-bottom: auto">
            <div ng-hide=showWelcome>游客您好，请<a href="/login">[登录]</a></div>
            <div class="inline" ng-show=showWelcome>
                <div style="display: inline-block">您好，{{username}}! </div>
                <div style="display: inline-block" id="exitButton" ng-click="exit()">[退出]</div>
            </div>
        </div>
        <div class="col-md-7"></div>
        <div class="myNav navbar-right" align="center">
            <ul class="navbar-nav">
                <li><a class="nav-link" href="/">首页</a></li>
                <li><a class="nav-link" href="/debt" ng-show="type == 1">账务</a></li>
                <li><a class="nav-link" href="/cart" ng-show="type == 1">购物车</a></li>
                <li><a class="nav-link" href="/public" ng-show="type == 2">发布</a></li>
            </ul>
        </div>
    </div>
</nav>

<script src="<c:url value='/static/js/lib/angular.js' />"></script>
<script src="<c:url value='/static/js/lib/angular-route.js' />"></script>
<script src="<c:url value='/static/js/lib/angular-cookies.js' />"></script>
<script src="<c:url value='/static/js/lib/angular-sanitize.js' />"></script>
<script src="<c:url value='/static/js/lib/angular-resource.js' />"></script>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-md5/0.1.10/angular-md5.min.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/controller/MainController.js' />"></script>
</body>
</html>
