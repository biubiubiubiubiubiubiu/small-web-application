App.controller('SellerController', ['$scope', '$http', '$cookieStore', '$location', 'md5', 'ItemService',

    function MyController($scope, $http,  $cookieStore, $location, md5, ItemService ) {

        $scope.pic = true;
        $scope.title = "";
        var edit = false;
        var id = 0;
        //the image
        $scope.uploadme;
        $scope.validateUpload;
        if (location.search.split("=")[0].split("?")[1] == "id") {
            id = location.search.split("=")[1];
            ItemService.getItemDetail(id).then(function(data){
                $scope.title = data.title;
                $scope.abs = data.abs;
                $scope.imageUrl = data.imageUrl;
                $scope.text = data.introduction;
                $scope.price = data.price;
                edit = true;
            }, function(errResponse){
                alert("无法获取该商品信息！");
            });

        }

        function dataURItoBlob(dataURI) {
            var binary = atob(dataURI.split(',')[1]);
            var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
            var array = [];
            for (var i = 0; i < binary.length; i++) {
                array.push(binary.charCodeAt(i));
            }
            return new Blob([new Uint8Array(array)], {
                type: mimeString
            });
        }

        $scope.public = function() {
            // get all ng-models
            if ($scope.sellerForm.$valid && (($scope.imageUrl != null && $scope.imageUrl.length > 0) || $scope.validateUpload)) {
                var fd = new FormData();
                var input_data = {
                    id: id,
                    title: $scope.title,
                    abs: $scope.abs,
                    introduction: $scope.text,
                    price: $scope.price
                };
                if ($scope.pic) {
                    // upload picture urls
                    input_data["imageUrl"] = $scope.imageUrl;
                    fd.append('item', JSON.stringify(input_data));
                    if (!edit) {
                        ItemService.createWithOutFile(fd).success(function () {
                            alert("商品信息上传成功！");
                            window.location.replace("/");
                        }).error(function () {
                            alert("商品信息上传失败！");
                            console.log('error');
                        });
                    } else {
                        ItemService.editItemWithoutFile(fd).success(function() {
                            alert("商品信息上传成功！");
                            window.location.replace("/");
                        }).error(function() {
                            alert("商品信息上传失败！");
                            console.log('error');
                        })
                    }
                } else {
                    // upload locally
                    var imgBlob = $scope.picture;
                    fd.append('file', imgBlob);
                    fd.append('item', JSON.stringify(input_data));
                    if (!edit) {
                        ItemService.createWithFile(fd).success(function () {
                            alert("商品信息上传成功！");
                            window.location.replace("/");
                        }).error(function () {
                            alert("商品信息上传失败！");
                            console.log('error');
                        });
                    } else {
                        ItemService.editItemWithFile(fd).success(function() {
                            alert("商品信息上传成功！");
                            window.location.replace("/");
                        }).error(function() {
                            alert("商品信息上传失败！");
                            console.log('error');
                        })
                    }
                }
            }
            if( $scope.sellerForm.$valid && !(($scope.imageUrl != null && $scope.imageUrl.length > 0) || $scope.validateUpload)){
                alert("商品信息不全，请确认是否填写有遗漏");
            }
        }

    }]);


//your directive
App.directive("fileread", [
    function() {
        return {
            scope: {
                fileread: "="
            },
            link: function(scope, element, attributes) {
                element.bind("change", function(changeEvent) {
                    var reader = new FileReader();
                    reader.onload = function(loadEvent) {
                        scope.$apply(function() {
                            scope.fileread = loadEvent.target.result;
                        });
                    };
                    if (changeEvent.target.files[0] != null) {
                        reader.readAsDataURL(changeEvent.target.files[0]);
                    }
                });
            }
        }
    }
]);

App.directive('fileModel', ['$parse', '$rootScope', function ($parse, $rootScope) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            var validExt = ["png", "jpg", "jpeg"];
            var maxSize = 1 * 1024 * 1024;
            element.bind('change', function(){
                if (element[0].files[0] != null) {
                    scope.$apply(function () {
                        var fileInfo = element[0].files[0];
                        modelSetter(scope, fileInfo);
                        var ext = fileInfo["name"].substr(fileInfo["name"].lastIndexOf(".") + 1).toLocaleLowerCase();
                        $rootScope.validateUpload = validExt.lastIndexOf(ext) != -1 && fileInfo["size"] < maxSize;
                    });
                } else {
                    $rootScope.validateUpload = false;
                }
            });
        }
    };
}]);
