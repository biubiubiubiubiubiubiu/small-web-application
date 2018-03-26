<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="nav.jsp"%>

<script src="<c:url value='/static/js/controller/CartController.js' />"></script>
<script src="<c:url value='/static/js/service/BuyRecordService.js' />"></script>


<div class="container" ng-controller="CartController">
    <div class="second-tab">
        <h2>已添加至购物车内容</h2>
    </div>
    <div class="table-div">
        <table class="table table-striped custab">
            <thead>
            <tr>
                <th></th>
                <th>商品名称</th>
                <th class="text-center">数量</th>
                <th class="text-center">购买价格</th>
            </tr>
            </thead>
            <tr ng-repeat="record in cartRecords">
                <td>{{$index + 1}}</td>
                <td>{{record.title}}</td>
                <td class="text-center">{{record.num}}</td>
                <td class="text-center">{{record.price}}</td>
            </tr>
        </table>
    </div>
    <div class="function-div">
        <button type="button" class="btn btn-primary" ng-click="goBack()">退出</button>
        <button type="button" class="btn btn-primary" ng-click="buy()">购买</button>
    </div>
</div>
