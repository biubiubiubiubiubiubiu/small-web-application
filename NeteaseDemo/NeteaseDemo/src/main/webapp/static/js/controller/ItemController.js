'use strict';

App.controller('ItemController', ['$scope', '$cookieStore', '$location', 'md5', 'ItemService',
    function($scope, $cookieStore, $location, md5, ItemService) {
        ItemService.getAllItems().then(function(data){
            $scope.items = data;
        }, function(errResponse){
            alert("无法获取所有商品信息！");
        });

    }]);