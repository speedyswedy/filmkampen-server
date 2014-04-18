'use strict';

angular.module('Filmkampen')
  .controller('HomeCtrl', function ($scope, $location, SessionService) {
    
    $scope.location = $location;
    $scope.username = SessionService.getUser().username;
    
    $scope.go = function(path) {
        $scope.location.path(path);
    }
    
  });

