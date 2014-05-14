'use strict';

angular.module('Filmkampen')
  .controller('VideoCtrl', function ($scope, $location, $timeout, $sce) {
      
    $scope.currentTime = 0;
	$scope.totalTime = 0;
	$scope.state = null;
	$scope.volume = 1;
	$scope.isCompleted = false;
	$scope.API = null;
    $scope.location = $location;

	$scope.roundProgressData = {
      label: 11,
      percentage: 0.11
    };

    // Here I synchronize the value of label and percentage in order to have a nice demo
    $scope.$watch('roundProgressData', function (newValue, oldValue) {
      newValue.percentage = newValue.label / 100;
    }, true);

	$scope.onPlayerReady = function(API) {
		$scope.API = API;
		//$scope.API.play();
	};

	$scope.onCompleteVideo = function() {
		$scope.isCompleted = true;
	};

	$scope.onUpdateState = function(state) {
		$scope.state = state;
	};

	$scope.onUpdateTime = function(currentTime, totalTime) {
		$scope.currentTime = currentTime;
		$scope.totalTime = totalTime;
		$scope.roundProgressData.label = currentTime;
	};

	$scope.onUpdateVolume = function(newVol) {
		$scope.volume = newVol;
	};

	$scope.onUpdateSize = function(width, height) {
		$scope.config.width = width;
		$scope.config.height = height;
	};

	$scope.stretchModes = [
		{label: "None", value: "none"},
		{label: "Fit", value: "fit"},
		{label: "Fill", value: "fill"}
	];

	$scope.config = {
		width: 260,
		height: 200,
		autoHide: false,
		autoHideTime: 3000,
		autoPlay: false,
		responsive: false,
		stretch: $scope.stretchModes[1],
		sources: [
			{src: $sce.trustAsResourceUrl("https://s3.amazonaws.com/filmkampen/30d1253c2e7623b57985145c39cfd639.mp4"), type: "video/mp4"},
			{src: $sce.trustAsResourceUrl("https://s3.amazonaws.com/filmkampen/826f409bdd8f2a6ff6cb6c07552efe48.webm"), type: "video/webm"},
			{src: $sce.trustAsResourceUrl("ttps://s3.amazonaws.com/filmkampen/bba8038ad965e1add29622bf611c8258.ogv"), type: "video/ogg"}
		],
		transclude: true,
		theme: {
			url: "styles/themes/default/videogular.css"
		},
		plugins: {
			poster: {
				url: "https://s3.amazonaws.com/filmkampen/36a244f353c12b7ca6458034128d067d_4.jpg"
			},
			ads: {
				companion: "companionAd",
				companionSize: [728, 90],
				network: "6062",
				unitPath: "iab_vast_samples",
				adTagUrl: "http://pubads.g.doubleclick.net/gampad/ads?sz=400x300&iu=%2F6062%2Fiab_vast_samples&ciu_szs=300x250%2C728x90&gdfp_req=1&env=vp&output=xml_vast2&unviewed_position_start=1&url=[referrer_url]&correlator=[timestamp]&cust_params=iab_vast_samples%3Dlinear"
			}
		}
	};

	$scope.changeSource = function() {
		$scope.config.sources = [
			{src: $sce.trustAsResourceUrl("http://vjs.zencdn.net/v/oceans.mp4"), type: "video/mp4"},
			{src: $sce.trustAsResourceUrl("http://vjs.zencdn.net/v/oceans.webm"), type: "video/webm"}
		];
	};  
    
    $scope.go = function(path) {
        $scope.location.path(path);
    }
     
    
});