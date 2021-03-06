'use strict';

App.controller('ItemController', ['$scope', '$cookieStore', '$location', 'md5', 'ItemService',
    function($scope, $cookieStore, $location, md5, ItemService) {

        $scope.type = $cookieStore.get("type");
        $scope.notSoldItems = [];
        ItemService.getAllItems().then(function(data){
            $scope.items = data;
            for (var i in $scope.items) {
               if ($scope.items[i].sold == 0) {
                   $scope.notSoldItems.push($scope.items[i]);
               }
            }
        }, function(errResponse){
            alert("无法获取所有商品信息！");
        });

        $scope.deleteItem = function(id) {
            if (window.confirm("确定要删除吗？")) {
                ItemService.deleteItem(id).then(function() {
                    alert("物品删除成功！");
                    window.location.replace("/");
                }, function(errResponse) {
                    alert("物品删除失败！");
                })
            }
        };

        $scope.goDetail = function(id) {
            window.location.replace("/detail?id=" + id)
        }



    }]);