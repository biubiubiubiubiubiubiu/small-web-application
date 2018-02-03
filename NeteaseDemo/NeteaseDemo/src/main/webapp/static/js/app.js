'use strict';

var App = angular.module('myApp',[
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

App.config(function ($routeProvider){
    $routeProvider.when('/', {
        templateUrl: '/static/template/main.html',
        controller: 'UserController'
    })
});