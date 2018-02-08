'use strict';

App.controller('MainController', ['$scope', '$cookieStore', '$location',
    function($scope, $cookieStore, $location) {

        var login = $cookieStore.get("label");

        $scope.showWelcome = login;

    }]);