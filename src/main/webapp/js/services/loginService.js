'use strict';

angular.module('Filmkampen')
  .service('LoginService', function($http, $location, $cookieStore, AuthorizationService, ApiService, SessionService, Base64) {
    
    this.login = function(username, password) {
        var credentials = "Basic " + Base64.encode(username + ":" + password);
      
        var success = function (response) {
            if (response.trim() == "OK") {
                ApiService.init(credentials);
                SessionService.setUser({"username": username})
                $cookieStore.put('credentials', credentials);
                $location.path('/home');
            } else {
                $location.path('/start');  
          }
        };

      var error = function (response) {
          alert("Could not login.");
      };
      
      AuthorizationService.login(credentials).success(success).error(error);
    };
    
});

