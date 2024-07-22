angular.module('rest', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/rest/products';

    $scope.loadProducts = function () {
        $http.get(contextPath)
            .then(function (response) {
                console.log(response);
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    #scope.loadProducts();
})