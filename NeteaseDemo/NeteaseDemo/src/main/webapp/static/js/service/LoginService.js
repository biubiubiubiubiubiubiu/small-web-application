'use strict';

App.factory('LoginService', ['$http', '$q', function($http, $q){

    var factory = {
        loginRequest: loginRequest
    }

    return factory;
    function loginRequest(requestData) {
        return $http.post('/login', JSON.stringify(requestData))
            .then(
                function(response) {
                    return response.data;
                },

                function(errResponse){
                    console.error('Error while fetching users');
                    return $q.reject(errResponse);
                }
            )
    }
}]);