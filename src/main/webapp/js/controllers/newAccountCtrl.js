'use strict';

angular.module('Filmkampen')
  .controller('NewAccountCtrl', function ($scope, $http, $location, UserService, LoginService) {
  
  $scope.location = $location;
  
  $scope.go = function(path) {
        $scope.location.path(path);
  };
  
  $scope.goBack = function() {
        window.history.back();
  };
  
  $scope.newAccount = function () {
      
      var success = function (response) {
          alert("success:" + $scope.username);
          LoginService.login($scope.username, $scope.password);
      };
      
      var error = function (response) {
          $scope.error = "Username " + $scope.username + " already exist!";
      };
      
      var user = {'userName': $scope.username, 'password' : $scope.password};
      UserService.newAccount(user, success, error);
  };
});

