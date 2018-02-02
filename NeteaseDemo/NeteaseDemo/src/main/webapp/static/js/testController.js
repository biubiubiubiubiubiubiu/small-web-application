'use strict';

App.controller('UserController', ['$scope', 'testService', function($scope, testService) {
    var self = this;
    self.testControllerFunction = function(){
        testService.testServiceFunction()
            .then(
                function(data) {
                    $scope.message = data.message;
                },
                function(errResponse){
                    console.error('Error while fetching Currencies');
                }
            );
    };

    self.testControllerFunction();

}]);