'use strict';

angular.module('Filmkampen')
  .controller('LogoutCtrl', function ($scope, $http, $location, $cookieStore, LogoutService) {
  
  $scope.location = $location;
  
  $scope.go = function(path) {
        $scope.location.path(path);
  };
  
  $scope.logout = function () {
      $cookieStore.put('credentials', "");
      LogoutService.logout("");
  };
  
  $scope.logout();
  
});

