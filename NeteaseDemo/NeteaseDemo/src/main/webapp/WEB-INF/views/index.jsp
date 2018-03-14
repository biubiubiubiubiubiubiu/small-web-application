<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="nav.jsp"%>

<script src="<c:url value='/static/js/controller/ItemController.js' />"></script>
<script src="<c:url value='/static/js/service/ItemService.js' />"></script>

<div class="container">
    <!-- Nav tabs -->
    <div class="tab-div">
        <ul id="customer-nav" class="nav nav-tabs" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#home" role="tab">所有内容</a>
            </li>
            <li class="nav-item" ng-show="type == 1">
                <a class="nav-link" data-toggle="tab" href="#profile" role="tab">未购买的内容</a>
            </li>
        </ul>
    </div>

    <!-- Tab panes -->
    <div class="div-content tab-content">
        <div class="tab-pane active" id="home" role="tabpanel" ng-controller="ItemController">
            <div class="row itemRow">
                <div class="col-md-3" style="padding-top: 10px" ng-repeat="item in items">
                    <div class="clearfix" ng-if="$index % 4 == 0"></div>
                    <div class="link">
                        <div class="item-div">
                            <a href="/detail?id={{item.id}}">
                                <div class="item-img">
                                    <img class="index-img" src="{{item.imageUrl}}" alt="{{item.title}}">
                                </div>
                                <p class="itemTitle">{{item.title}}</p>
                                <p class="itemTitle">库存: {{item.storage}}</p>
                            </a>
                            <div>
                                <span class="v-unit">￥</span>
                                <span class="price">{{item.price | number:2}}</span>
                                <button ng-show="type == 2 && item.storage > 0" class="deleteButton" ng-click="deleteItem(item.id)">删除</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="tab-pane" id="profile" role="tabpanel">this is no items</div>
    </div>
</div>
