'use strict';

angular.module('Filmkampen')
  .controller('PasswordSentCtrl', function ($scope, $location) {
  
  $scope.location = $location;
  
  $scope.go = function(path) {
        $scope.location.path(path);
  };
  
  $scope.goBack = function() {
        window.history.back();
  };
  
});

