'use strict';

App.controller('LoginController', ['$scope', 'LoginService', 'md5', function($scope, LoginService, md5) {

    $scope.submit = function(){
        var requestBody = {
            userName: $scope.userName,
            password: md5.createHash($scope.password)
        };
        LoginService.loginRequest(requestBody).then(
            function(data) {
                $scope.message = data.message;
            },
            function(errResponse){
                console.error('Error while fetching Currencies');
            }
        );
    }

}]);