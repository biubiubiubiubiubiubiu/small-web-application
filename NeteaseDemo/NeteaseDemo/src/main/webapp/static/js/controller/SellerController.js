App.controller('SellerController', ['$scope', '$http', '$cookieStore', '$location', 'SellerService', 'md5',

    function MyController($scope, $http,  $cookieStore, $location, SellerService ) {

        $scope.pic = true;
        //the image
        $scope.uploadme;

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
            var fd = new FormData();
            var input_data = {
                title: $scope.title,
                abs: $scope.abs,
                introduction: $scope.text,
                storage: $scope.storage,
                price: $scope.price
            };
            if ($scope.pic) {
                // upload picture urls
                input_data["imageUrl"] = $scope.imageUrl;
                fd.append('item', JSON.stringify(input_data));
                SellerService.createWithOutFile(fd).success(function() {
                    alert("商品信息上传成功！");
                    window.location.replace("/");
                }).error(function() {
                    alert("商品信息上传失败！");
                    console.log('error');
                });
            } else {
                // upload locally
                var imgBlob = $scope.picture;
                fd.append('file', imgBlob);
                fd.append('item', JSON.stringify(input_data));
                SellerService.createWithFile(fd).success(function() {
                    alert("商品信息上传成功！");
                    window.location.replace("/");
                }).error(function() {
                    alert("商品信息上传失败！");
                    console.log('error');
                });
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
                    }
                    reader.readAsDataURL(changeEvent.target.files[0]);
                });
            }
        }
    }
]);

App.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);