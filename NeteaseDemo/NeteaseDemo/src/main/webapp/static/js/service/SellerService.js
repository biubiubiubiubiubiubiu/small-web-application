'use strict';

App.factory('SellerService', ['$http', '$q', function($http, $q){

    var factory = {
        createWithFile: createWithFile,
        createWithOutFile: createWithOutFile
    };

    return factory;
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
}]);