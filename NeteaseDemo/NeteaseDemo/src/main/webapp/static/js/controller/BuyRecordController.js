App.controller('BuyRecordController', ['$scope', '$cookieStore', '$location', 'md5', 'BuyRecordService',
    function($scope, $cookieStore, $location, md5, BuyRecordService) {
        BuyRecordService.getBuyRecord().then(function(data){
            $scope.buyRecord = data;
            $scope.sum = 0;
            for (var i in data) {
                $scope.sum += data[i].price * data[i].num;
            }
        }, function(errResponse){
            alert("无法获取该商品信息！");
        });
    }]);