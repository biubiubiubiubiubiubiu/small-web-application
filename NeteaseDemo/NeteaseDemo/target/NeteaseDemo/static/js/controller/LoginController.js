'use strict';

App.controller('LoginController', ['$scope', '$cookieStore', '$location', 'LoginService', 'md5',
    function($scope, $cookieStore, $location, LoginService, md5) {

    $scope.submit = function(){
        var requestBody = {
            username: $scope.username,
            password: md5.createHash($scope.password)
        };
        LoginService.loginRequest(requestBody).then(
            function(data) {
                var expireDate = new Date();
                expireDate.setTime(expireDate.getTime() + (30 * 60 * 1000));
                $cookieStore.put("username", data.username);
                $cookieStore.put("type", data.type);
                $cookieStore.put("label", data.username !== null);
                window.location.replace("/");
            },
            function(errResponse){
                alert("使用的用户名密码不匹配。");
            }
        );
    }

}]);