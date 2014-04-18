'use strict';

angular.module('Filmkampen')
  .controller('VideoCtrl', function ($scope, $location, $timeout) {
    
    $scope.location = $location;
  
    $scope.movies = ['Die Hard','The Hulk','Gladiator'];
    
    $scope.go = function(path) {
        $scope.location.path(path);
    }
    
    $scope.progress = function(player) {
        
        $timeout(function() {
            $scope.currentTime = player.currentTime;
            $scope.endTime = player.seekable.end();
            
            if ($scope.currentTime < $scope.endTime) {
                $scope.progress(player);
            }
        
        }, 100);
       
    };
    
    $scope.startPlayer = function(player) {
        player.play();
        $scope.progress(player);
    }
    
    $scope.stopPlayer = function() {
        var player = document.getElementById("video");
        if (player) {
            player.pause();
        }
    }
    
    $scope.start = function() {
        $timeout(function() {
            var player = document.getElementById("video");
            if (player) {
                if (player.currentTime == 0) { 
                    $scope.startPlayer(player);
                }
            } else {
               $scope.start(); 
            }
        }, 500);
    }
    
    $scope.start();
    
});