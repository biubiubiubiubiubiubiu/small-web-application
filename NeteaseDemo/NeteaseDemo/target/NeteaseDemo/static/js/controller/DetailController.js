App.controller('DetailController', ['$scope', '$cookieStore', '$location', 'md5', 'ItemService',
    function($scope, $cookieStore, $location, md5, ItemService) {
        var id = location.search.split("=")[1];
        $scope.item = null;
        ItemService.getItemDetail(id).then(function(data){
            $scope.item = data;
            if ($scope.item["sold"] == 1) {
                ItemService.getPrice(id).then(function(data) {
                    $scope.price = data["price"];
                }, function(errResponse) {
                    alert("无法获取商品购买时价格。")
                });
                $scope.buttonWord="已购买";
            } else {
                $scope.buttonWord="放入购物车";
            }
        }, function(errResponse){
            alert("无法获取该商品信息！");
        });
        $scope.type = $cookieStore.get("type");
        $scope.num = 0;


        $scope.goEdit = function() {
            window.location.replace("/itemEdit?id=" + id);
        };

        $scope.toCart = function(title, id, num, price) {
            if (window.confirm("确认将 " + num + " 件 " + title + "放入购物车?")) {
                ItemService.toCart(id, num, price).then(function(){
                    alert("物品已放入购物车！");
                    window.location.replace("/");
                }, function(errResponse){
                    alert("物品放入购物车失败！");
                });
            }
        }
    }]);