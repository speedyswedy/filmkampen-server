'use strict';

angular.module('Filmkampen')
  .service('LogoutService', function($http, $location, AuthorizationService) {
    
    this.logout = function() {
      AuthorizationService.logout();
    };
    
});

