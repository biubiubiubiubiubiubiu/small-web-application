<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="nav.jsp"%>

<div class="container">
    <!-- Nav tabs -->
    <div class="tab-div">
        <ul id="customer-nav" class="nav nav-tabs" role="tablist" ng-show="username">
            <li class="nav-item" ng-show="username">
                <a class="nav-link active" data-toggle="tab" href="#home" role="tab">所有内容</a>
            </li>
            <li class="nav-item" ng-show="type == 1">
                <a class="nav-link" data-toggle="tab" href="#profile" role="tab">未购买的内容</a>
            </li>
        </ul>
    </div>

    <!-- Tab panes -->
    <div class="div-content tab-content">
        <div class="tab-pane active" id="home" role="tabpanel">this is all items</div>
        <div class="tab-pane" id="profile" role="tabpanel">this is no items</div>
    </div>
</div>
