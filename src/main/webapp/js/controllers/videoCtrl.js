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
            $scope.endTime = player.seekable.end(0);
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
            player.loop = false;
            if (player) {
                if (player.currentTime == 0) {
                    player.onloadeddata=$scope.startPlayer(player);
                }
            } else {
               $scope.start(); 
            }
        }, 500);
    }
    
    window.onload = $scope.start();
    
});