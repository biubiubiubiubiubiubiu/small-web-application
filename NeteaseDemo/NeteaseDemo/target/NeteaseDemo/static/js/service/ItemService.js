'use strict';

App.factory('ItemService', ['$http', '$q', function($http, $q){

    var factory = {
        getItemDetail: getItemDetail,
        getAllItems: getAllItems,
        createWithFile: createWithFile,
        createWithOutFile: createWithOutFile,
        editItemWithoutFile: editItemWithoutFile,
        deleteItem: deleteItem,
        buy: buy
    };

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

    function getItemDetail(id) {
        return $http.get("/seller/item/" + id).then(
            function(response) {
                return response.data;
            },

            function(errResponse){
                console.error('Error while fetching users');
                return $q.reject(errResponse);
            }
        );
    }

    function createWithFile(formData) {

        return $http.post("/seller/item", formData, {
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }
        });
    }

    function createWithOutFile(formData) {
        return $http.post("/seller/item/noFile", formData, {
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }
        })
    }

    function editItemWithoutFile(formData) {
        return $http.post("/seller/item/editWithOutFile", formData, {
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }
        })
    }

    function deleteItem(id) {
        return $http.delete("/seller/item/" + id).then(
            function(response) {
                return response.data;
            },

            function(errResponse){
                console.error('Error while fetching users');
                return $q.reject(errResponse);
            })
    }

    function buy(id, num, price) {
        var requestBody = {
            itemId: id,
            num: num,
            price: price,
            recordingTime: new Date().getTime()
        };
        return $http.post("buyer/toCart", JSON.stringify(requestBody)).then(
            function(response) {
                return response.data;
            },

            function(errResponse){
                console.error('购买失败');
                return $q.reject(errResponse);
            }
        )
    }
}]);