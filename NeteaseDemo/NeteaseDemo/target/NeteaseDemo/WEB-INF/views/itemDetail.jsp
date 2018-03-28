<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="nav.jsp"%>
<script src="<c:url value='/static/js/controller/DetailController.js' />"></script>
<script src="<c:url value='/static/js/service/ItemService.js' />"></script>

<div class="container" ng-controller="DetailController">
    <div class="row col-md-12">
        <div class="col-md-5 detail-div">
            <img class="detail-img" ng-src="{{item.imageUrl}}">
        </div>
        <div class="col-md-7" style="padding-top: 50px;">
            <h2>{{item.title}}</h2>
            <p>摘要：{{item.abs}}</p>
            <div style="width: 200px">
                价格：<span class="v-unit">￥</span>
                <span class="price">{{item.price | number:2}}</span>
            </div>
            <div style="padding-top: 20px" ng-show="type == 1">
                <label for="num">购买数量</label>
                <input ng-model="num" type="number" min="0" name="num" class="form-control" id="num" style="width: 140px; display: inline-block" required>件
            </div>
            <div ng-show="type == 2" style="padding-top: 8%">
                <button style="cursor: hand" type="button" class="btn btn-primary" ng-click="goEdit()">编辑商品信息</button>
            </div>
            <div ng-show="type == 1" style="padding-top: 8%">
                <span class="buy-button">
                    <button ng-click="toCart(item.title, item.id, num, item.price)" style="cursor: hand" type="button" class="btn btn-success" ng-class="{'disabled': num == null || num == 0 || item.sold==1}">
                        {{buttonWord}}
                    </button>
                </span>
                <span class="buyPrice" ng-show="item.sold==1">当时购买价格：￥{{price}}</span>
            </div>
        </div>
    </div>
    <div class="detail-div">
        <h3 style="border-bottom-style:solid; border-bottom-width: 1px; border-bottom-color: #888888">详细信息</h3>
        {{item.introduction}}
    </div>
</div>
