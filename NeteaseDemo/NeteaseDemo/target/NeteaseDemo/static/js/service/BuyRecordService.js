'use strict';

App.factory('BuyRecordService', ['$http', '$q', function($http, $q){

    var factory = {
        getCartRecord: getCartRecord,
        buy: buy,
        getBuyRecord: getBuyRecord
    };

    return factory;

    function getCartRecord() {
        return $http.get("/buyer/cart").then(
            function(response) {
                return response.data;
            },

            function(errResponse){
                console.error('Error while fetching cart records');
                return $q.reject(errResponse);
            }
        );
    }

    function buy(cartRecords) {
        return $http.post("/buyer/buyRecord", JSON.stringify(cartRecords)).then(
            function(response) {
                return response.data;
            },

            function(errResponse){
                console.error('Error while buying items');
                return $q.reject(errResponse);
            }
        );
    }

    function getBuyRecord() {
        return $http.get("/buyer/buyRecord").then(
            function(response) {
                return response.data;
            },

            function(errResponse){
                console.error('Error while buying items');
                return $q.reject(errResponse);
            }
        );
    }
}]);