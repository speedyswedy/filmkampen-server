'use strict';

angular.module('Filmkampen')
  .controller('FilmCtrl', function ($scope, $location, RequestService, $timeout, Dropbox) {
    
    // assign a promise to scope
    //$scope.accountInfo = Dropbox.accountInfo();
    
    $scope.location = $location;
    
    $scope.movies = ['Die Hard','The Hulk','Gladiator'];
    
    $scope.go = function(path) {
        $scope.location.path(path);
    }
    
    $scope.addRequest = function(name) {
        if (!$scope.requestExist(RequestService.get(), name)) {
            RequestService.add(name);
        }
    }
    
    $scope.requestExist = function(array, request) {
        var exist = false;
        for(var i=0;i<array.length;i++) {
            if (array[i] == request) {
                exist = true;
            }
        }
        return exist;
    }
    
    $scope.updateRequest = function() {
        $scope.requests = RequestService.get();
    }
    
    $scope.updateRequest();
    
  });