App.controller('DetailController', ['$scope', '$cookieStore', '$location', 'md5', 'ItemService',
    function($scope, $cookieStore, $location, md5, ItemService) {
        var id = location.search.split("=")[1];
        ItemService.getItemDetail(id).then(function(data){
            $scope.item = data;
        }, function(errResponse){
            alert("无法获取该商品信息！");
        });
        $scope.type = $cookieStore.get("type");


        $scope.goEdit = function() {
            window.location.replace("/itemEdit?id=" + id);
        }
    }]);