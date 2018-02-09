'use strict';

App.controller('MainController', ['$scope', '$cookieStore', '$location',
    function($scope, $cookieStore, $location) {

        var login = $cookieStore.get("label");

        $scope.showWelcome = login;

        if (login != null) {
            $scope.username = $cookieStore.get("username");
            $scope.type = $cookieStore.get("type");
        }

        $scope.exit = function() {
            $cookieStore.remove("label");
            $cookieStore.remove("username");
            $cookieStore.remove("type");
            window.location.replace("/");
        }

    }]);