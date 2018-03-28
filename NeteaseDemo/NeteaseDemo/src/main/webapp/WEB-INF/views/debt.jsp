<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="nav.jsp"%>
<script src="<c:url value='/static/js/controller/BuyRecordController.js' />"></script>
<script src="<c:url value='/static/js/service/BuyRecordService.js' />"></script>
<div class="container" ng-controller="BuyRecordController">
    <div class="second-tab">
        <h2>已购买内容</h2>
    </div>
    <div class="table-div">
        <table class="table table-striped custab">
            <thead>
            <tr>
                <th>商品图片</th>
                <th class="text-center">商品名称</th>
                <th class="text-center">购买时间</th>
                <th class="text-center">数量</th>
                <th class="text-center">购买价格</th>
            </tr>
            </thead>
            <tr ng-repeat="record in buyRecord">
                <td><a href="/detail?id={{record.itemId}}"> <img class="tab-img" ng-src="{{record.imageUrl}}" alt=""></a></td>
                <td class="text-center"><a href="/detail?id={{record.itemId}}">{{record.title}}</a></td>
                <td class="text-center">{{record.recordingTime}}</td>
                <td class="text-center">{{record.num}}</td>
                <td class="text-center">{{record.price}}</td>
            </tr>
            <tfoot>
                <tr>
                    <td colspan="4"><div style="text-align: right;">总计：</div></td>
                    <td><span class="v-unit">¥</span><span class="value">{{sum | number:2}}</span></td>
                </tr>
            </tfoot>
        </table>
    </div>
</div>
