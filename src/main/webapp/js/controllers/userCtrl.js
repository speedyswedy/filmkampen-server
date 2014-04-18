'use strict';

angular.module('Filmkampen')
  .controller('UserCtrl', function ($scope, $location, UserService) {
  
    $scope.location = $location;
    
    $scope.getUsers = function() {
        $scope.users = UserService.getUsers();
    }
    
    $scope.getCurrentUser = function() {
        $scope.user = UserService.getCurrentUser();
    }
    
    $scope.saveUser = function(user) {
        UserService.saveUser(user);
    }
    
    $scope.go = function(path) {
        $scope.location.path(path);
    }
    
  });