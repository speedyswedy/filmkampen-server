"use strict";
angular.module("com.2fdevs.videogular.plugins.movieselection", [])
	.directive(
		"vgMovieSelection",
		["VG_EVENTS", "VG_STATES", function(VG_EVENTS, VG_STATES){
			return {
				restrict: "E",
				require: "^videogular",
				scope: true,
				templateUrl: "views/movieSelection.html",
				link: function(scope, elem, attr, API) {

					var movie1 = angular.element('#movie1');
					var movie2 = angular.element('#movie2');
					var movie3 = angular.element('#movie3');
					var movie4 = angular.element('#movie4');
					
					function selectMovie(movie) {
						console.log(angular.element(movie.currentTarget).text().trim());
					};

					function onClickOverlayPlay(event) {
						API.playPause();
					}

					function onPlay(target, params) {
						console.log(scope.currentTime);
						scope.roundProgressData.label = scope.currentTime;
						scope.roundProgressData.percent = (scope.currentTime / scope.totalTime) * 100;

					}

					scope.onUpdateTime = function(currentTime, totalTime) {
						scope.currentTime = currentTime;
						scope.totalTime = totalTime;
						scope.roundProgressData.label = currentTime;
					};

					// Here I synchronize the value of label and percentage in order to have a nice demo
			        scope.$watch('roundProgressData', function (newValue, oldValue) {
			          newValue.percentage = newValue.label / 100;
			        }, true);

					elem.bind("click", onClickOverlayPlay);
					movie1.bind("click", selectMovie);
					movie2.bind("click", selectMovie);
					movie3.bind("click", selectMovie);
					movie4.bind("click", selectMovie);

					//API.$on(VG_EVENTS.ON_PLAY, onPlay);
					API.$on(VG_EVENTS.ON_UPDATE_TIME, onPlay);
						
				}
			}
		}
	]);
