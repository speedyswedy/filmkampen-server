'use strict';

angular.module('Filmkampen')
  .service('AuthorizationService', function($http, $location) {

  return {
      login: function (credentials) {
          $http.defaults.headers.common['Authorization'] = credentials;
          return $http.post('http://filmkampen-server.herokuapp.com/rest/login');
      }
  };
});



