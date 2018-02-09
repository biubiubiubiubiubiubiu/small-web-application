<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/2/6
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="nav.jsp"%>

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<script src="<c:url value='/static/js/controller/LoginController.js' />"></script>
<script src="<c:url value='/static/js/service/LoginService.js' />"></script>

<div class="container">
    <div class="row">
        <div class="col-md-3 col-md-offset-4 login-box">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Login</h3>
                </div>
                <div class="panel-body" ng-controller="LoginController">
                    <form accept-charset="UTF-8" role="form" >
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="UserName" name="username" type="text" ng-model="username">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="Password" name="password" type="password" ng-model="password" value="">
                            </div>
                            <input class="btn btn-lg btn-primary btn-block" type="submit" value="Login" ng-disabled="!username.length || !password.length" ng-click="submit()">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
