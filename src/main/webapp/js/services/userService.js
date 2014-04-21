'use strict';

angular.module('Filmkampen')
  .service('UserService', function($http, $resource, SessionService) {

    var USER = $resource("http://filmkampen-server.herokuapp.com/rest/user/:id", {id: "@id"});
    var USER_BY_USERNAME = $resource("http://filmkampen-server.herokuapp.com/rest/user/findUserByUsername/:username", {username: "@username"});
    
    var USERS = $resource("http://filmkampen-server.herokuapp.com/rest/user", {},
        { get: {method: 'GET', isArray: true},
          post: {method: 'POST'}});
          
    var RESET_PASSWORD = $resource("http://filmkampen-server.herokuapp.com/rest/user/resetPassword", {},
        { post: {method: 'POST'}});
        
    var NEW_ACCOUNT = $resource("http://filmkampen-server.herokuapp.com/rest/newAccount", {},
        { post: {
                method:"POST"
            }
        });
    
    this.getUsers = function() {
        return USERS.get();    
    };
    
    this.getUser = function(username) {
        return USER_BY_USERNAME.get({username : username});
    };
    
    this.getCurrentUser = function() {
        $scope.user = SessionService.getUser().username;
    };
    
    this.newAccount = function(user, success, error) {
        return NEW_ACCOUNT.post({}, user, success, error);
    };
    
    this.resetPassword = function(email) {
        RESET_PASSWORD.post(email);
    };
    
});