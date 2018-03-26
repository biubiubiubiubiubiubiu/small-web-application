App.controller('CartController', ['$scope', '$cookieStore', '$location', 'md5', 'BuyRecordService',
    function($scope, $cookieStore, $location, md5, BuyRecordService) {
        BuyRecordService.getCartRecord().then(function(data){
            var tempRecord = {};
            for (var i in data) {
                if (tempRecord[data[i].title + " " + data[i].price + " " + data[i].itemId] == null) {
                    tempRecord[data[i].title + " " + data[i].price + " " + data[i].itemId] = 0;
                }
                tempRecord[data[i].title + " " + data[i].price + " " + data[i].itemId] += data[i].num;
            }
            $scope.cartRecords = [];
            for (var name in tempRecord) {
                var keys = name.split(" ");
                $scope.cartRecords.push({title: keys[0], num: tempRecord[name], itemId: keys[2], price: keys[1]});
            }
        }, function(errResponse){
            alert("无法获取该商品信息！");
        });

        $scope.goBack = function() {
            window.history.back();
        };

        $scope.buy = function() {
            if (window.confirm("确定购买？")) {
                BuyRecordService.buy($scope.cartRecords).then(function (data) {
                    alert("购买成功！");
                    window.location.replace("/");
                }, function (errorResponse) {
                    alert("购买失败！");
                });
            }
        }
    }]);