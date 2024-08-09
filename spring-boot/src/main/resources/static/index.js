angular.module('rest', []).controller('indexController', function ($scope, $http) {

    const productsContextPath = 'http://localhost:8080/app/rest/products';
    const cartPositionContextPath = 'http://localhost:8080/app/rest/cart-positions';
    const productsPerPage = 2;

    $scope.loadProducts = function (pageNumber) {
        $http.get(productsContextPath, {
            params: {
                pageNumber: pageNumber,
                minPrice: $scope.minPrice,
                maxPrice: $scope.maxPrice
            }
        })
            .then(function (response) {
                console.log(response);
                $scope.ProductsList = response.data;
            });

        $http.get(productsContextPath + '/count', {params: {minPrice: $scope.minPrice, maxPrice: $scope.maxPrice}})
            .then(function (response) {
                let pageAmount = Math.ceil(response.data / productsPerPage);
                let a = new Array(pageAmount);
                for (let i = 1; i <= pageAmount; ++i) a[i - 1] = i;
                $scope.Count = a;
            })
    };

    $scope.loadCartPositions = function () {
        $http.get(cartPositionContextPath)
            .then(function (response) {
                console.log(response);
                $scope.CartPositionsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(productsContextPath + '/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };

    $scope.createProduct = function () {
        $http.post(productsContextPath, $scope.newProduct)
            .then(function (response) {
                $scope.loadProducts();
                $scope.newProduct = null;
            });
    }

    $scope.addProductToCart = function (productName, productPrice) {
        console.log(productName + ' ' + productPrice);
        $http.post(cartPositionContextPath, {
            "product": {
                "name": productName,
                "price": productPrice
            },
            "amount": 1
        })
            .then(function (response) {
                $scope.loadCartPositions();
            })
    }

    $scope.deleteCartPosition = function (cartPositionId) {
        $http.delete(cartPositionContextPath + '/' + cartPositionId)
            .then(function (response) {
                $scope.loadCartPositions();
            });
    };

    $scope.updateCartPosition = function (id, amount) {
        $http.put(cartPositionContextPath + '/' + id, {}, {params: {amount: amount}})
            .then(function (response) {
                $scope.loadCartPositions();
            });
    }

    $scope.loadProducts();
    $scope.loadCartPositions();
});