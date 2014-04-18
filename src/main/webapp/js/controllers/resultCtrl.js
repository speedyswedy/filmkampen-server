'use strict';

angular.module('Filmkampen')
  .controller('ResultCtrl', function ($scope, $location,  $routeParams, $timeout) {
    
    $scope.location = $location;
    
    $scope.currentTime = $routeParams.currentTime;
    
    $scope.selectedMovie = $routeParams.selectedMovie;
    
    $scope.movies = ['Die Hard','The Hulk','Gladiator'];
    
    $scope.go = function(path) {
        $scope.location.path(path);
    }
    
    $scope.newClip = function() {
        $timeout(function() {
            $scope.go('/video');
        }, 500);
    }
    
    $scope.correct = function(movie) {
        if (movie == $scope.selectedMovie) {
            return "green";
        }
        return "";
    }
    
    
    
  });

