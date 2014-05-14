'use strict';

angular.module('Filmkampen')
  .controller('AddVideoCtrl', function ($scope, $location) {
    
    $scope.location = $location;
  
    $scope.go = function(path) {
        $scope.location.path(path);
    };
      
});

