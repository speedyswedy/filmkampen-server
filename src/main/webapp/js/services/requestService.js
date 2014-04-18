'use strict';

angular.module('Filmkampen')
  .service('RequestService', function(){
    var requests = [];
    
    this.add = function(name) {
        requests.push(name);
    };
    
    this.get = function() {
        return requests;
    }
    
});