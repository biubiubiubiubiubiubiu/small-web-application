App.controller('DetailController', ['$scope', '$cookieStore', '$location', 'md5', 'ItemService',
    function($scope, $cookieStore, $location, md5, ItemService) {
        var id = location.search.split("=")[1];
        ItemService.getItemDetail(id).then(function(data){
            $scope.item = data;
        }, function(errResponse){
            alert("无法获取该商品信息！");
        });
        $scope.type = $cookieStore.get("type");
        $scope.num = 0;


        $scope.goEdit = function() {
            window.location.replace("/itemEdit?id=" + id);
        };

        $scope.buy = function(title, id, num, price) {
            if (window.confirm("确认将 " + num + " 件 " + title + "放入购物车?")) {
                ItemService.buy(id, num, price).then(function(){
                    alert("购买成功！")
                    window.location.replace("/");
                }, function(errResponse){
                    alert("购买失败！");
                });
            }
        }
    }]);