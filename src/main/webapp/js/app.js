'use strict';

angular.module('Filmkampen', ['ngRoute','dropbox','ngResource','ngCookies'])
  .config(function ($locationProvider, $routeProvider, DropboxProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/start.html',
        controller: 'StartCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/home', {
        templateUrl: 'views/home.html',
        controller: 'HomeCtrl'
      })
      .when('/newGame', {
        templateUrl: 'views/newGame.html',
        controller: 'FilmCtrl'
      })
      .when('/video', {
        templateUrl: 'views/video.html',
        controller: 'VideoCtrl'
      })
      .when('/result/:currentTime/:selectedMovie', {
        templateUrl: 'views/result.html',
        controller: 'ResultCtrl'
      })
      .when('/settings', {
        templateUrl: 'views/settings.html',
        controller: 'UserCtrl'
      })
      .when('/changePassword', {
        templateUrl: 'views/changePassword.html',
        controller: 'ChangePasswordCtrl'
      })
      .when('/passwordSent', {
        templateUrl: 'views/passwordSent.html',
        controller: 'PasswordSentCtrl'
      })
      .when('/newAccount', {
        templateUrl: 'views/newAccount.html',
        controller: 'NewAccountCtrl'
      })
      .when('/logout', {
        templateUrl: 'views/loggedOut.html',
        controller: 'LogoutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  }).config(function (DropboxProvider) {
    DropboxProvider.config('7w6skyoccuryvqr', 'https://ide.monaca.mobi/components/ngDropbox/callback.html');
  }).config(['$httpProvider', function ($httpProvider) {
        $httpProvider.responseInterceptors.push('HttpInterceptorService');
        $httpProvider.defaults.useXDomain = true;
        $httpProvider.defaults.headers.common = {};
        $httpProvider.defaults.headers.post = {};
        $httpProvider.defaults.headers.put = {};
        $httpProvider.defaults.headers.patch = {};
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
        $httpProvider.defaults.headers.common['Content-Type'] = 'application/json;charset=utf-8';
  }]).run(function() {
    FastClick.attach(document.body);
  }).run(function (ApiService) {
    ApiService.init();
});

