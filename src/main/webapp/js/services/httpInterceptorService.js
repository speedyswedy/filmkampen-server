'use strict';

angular.module('Filmkampen')
    .factory('HttpInterceptorService', function httpInterceptor ($q, $window, $location) {
  
  return function (promise) {
      var success = function (response) {
          return response;
      };

      var error = function (response) {
          if (response.status === 401) {
              $location.url('/start');
          }

          return $q.reject(response);
      };

      return promise.then(success, error);
  };
});

