'use strict';

App.factory('ItemService', ['$http', '$q', function($http, $q){

    var factory = {
        getAllItems: getAllItems
    }

    return factory;
    function getAllItems() {
        return $http.get("/seller/item/all").then(
            function(response) {
                return response.data;
            },

            function(errResponse){
                console.error('Error while fetching users');
                return $q.reject(errResponse);
            }
        );
    }
}]);