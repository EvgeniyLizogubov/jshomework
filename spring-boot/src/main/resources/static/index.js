angular.module('rest', []).controller('indexController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/app/rest/products';
    const productsPerPage = 2;

    $scope.loadProducts = function (pageNumber) {
        $http.get(contextPath, {params: {pageNumber: pageNumber, minPrice: $scope.minPrice, maxPrice: $scope.maxPrice}})
            .then(function (response) {
                console.log(response);
                $scope.ProductsList = response.data;
            });

        $http.get(contextPath + '/count', {params: {minPrice: $scope.minPrice, maxPrice: $scope.maxPrice}})
            .then(function (response) {
                let pageAmount = Math.ceil(response.data / productsPerPage);
                let a = new Array(pageAmount); for (let i= 1; i<=pageAmount; ++i) a[i-1] = i;
                $scope.Count = a;
            })
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.createProduct = function () {
        $http.post(contextPath, $scope.newProduct)
            .then(function (response) {
                $scope.loadProducts();
                $scope.newProduct = null;
            });
    }

    $scope.loadProducts();
});