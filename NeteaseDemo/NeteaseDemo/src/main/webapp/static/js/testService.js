'use strict';

App.factory('testService', ['$http', '$q', function($http, $q){
    return {
        testServiceFunction: function() {
            return $http.get('http://localhost:8080/hello')
                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while fetching users');
                        return $q.reject(errResponse);
                    }
                );
        }

    };

}]);