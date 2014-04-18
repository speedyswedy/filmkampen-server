'use strict';

angular.module('Filmkampen')
  .service('SessionService', function() {
    this.user = {};
    
    this.setUser = function(u) {
        this.user = u;
    };
    
    this.getUser = function() {
        return this.user;
    };
    
});