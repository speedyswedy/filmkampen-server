'use strict';

angular.module('Filmkampen')
    .service('ApiService', function($http, $cookies) {
        
  return {
      init: function (credentials) {
          $http.defaults.headers.common['Authorization'] = credentials || $cookies.credentials;
      }
  };
});

