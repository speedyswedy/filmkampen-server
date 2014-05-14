'use strict';

angular.module('Filmkampen')
    .directive('dynamicUrl', function () {
        return {
          restrict: 'A',
          link: function postLink(scope, element, attrs) {
            element.attr('src', scope.media.fullUrl);
          }
    };
});