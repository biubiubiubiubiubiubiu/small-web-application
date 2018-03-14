<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="nav.jsp"%>
<script src="<c:url value='/static/js/controller/SellerController.js' />"></script>
<script src="<c:url value='/static/js/service/ItemService.js' />"></script>
<div class="container">
    <div class="public-tab">
        <h2>内容修改</h2>
    </div>
    <div class="form-div" ng-controller="SellerController">
        <form name="sellerForm">
            <div class="form-group">
                <label for="title">标题</label>
                <input ng-model="title" class="form-control" name="title" id="title" placeholder="2-80字符" required>
            </div>
            <div class="form-group">
                <label for="abstract">摘要</label>
                <input ng-model="abs" type="text" class="form-control" name="abstract" id="abstract" placeholder="2-140字符" required>
            </div>
            <div id="uploadType">
                <label>图片</label>
                <input class="pic-radio" name="pic" type="radio" ng-checked=true ng-model="pic" ng-value="true"> 图片地址
                <input class="pic-radio" name="pic" type="radio" ng-model="pic" ng-value="false"> 本地上传
            </div>
            <div>
                <div ng-show="pic!=null">上传图片</div>
                <div>
                    <input ng-model="imageUrl" ng-show="pic" type="text" class="form-control" id="url" placeholder="请输入图片地址">
                    <div  ng-show="!pic">
                        <div><input type="file" fileread="uploadme" file-model="picture"/></div>
                        <img ng-show="uploadme != null" ng-src="{{uploadme}}" width="200" height="200" alt="Image preview...">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="textArea">正文</label>
                <textarea ng-model="text" class="form-control" name="textArea" id="textArea" rows="3" placeholder="2-1000个字符" required></textarea>
            </div>
            <div class="form-group">
                <label for="storage">更新库存</label>
                <input ng-model="storage" type="number" min="0" name="storage" class="form-control" id="storage" style="width: 140px; display: inline-block" required>件
            </div>
            <div class="form-group">
                <label for="price">价格</label>
                <input ng-model="price" type="number" step="0.01" min="0" name="storage" class="form-control" id="price" style="width: 140px; display: inline-block" required>元
            </div>
            <button style="cursor: hand" ng-disabled="sellerForm.$invalid" type="submit" class="btn btn-primary" ng-click="public()">发布</button>
        </form>
    </div>
</div>

