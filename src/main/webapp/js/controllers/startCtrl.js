'use strict';

angular.module('Filmkampen')
  .controller('StartCtrl', function ($scope, $location) {
  
    $scope.location = $location;
     
    $scope.go = function(path) {
        $scope.location.path(path);
    }
    
    $scope.newAccount = function(path) {
        
    }
    
    $scope.login = function() {
        $scope.go('/login')
    }
    
  });

