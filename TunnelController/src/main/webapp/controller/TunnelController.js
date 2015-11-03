angular.module("TunnelApp", []).controller("TunnelController", function($scope, $http, $timeout) {
	
	$scope.fetchObject = function() {
		$http({
			method : 'GET',
			url : './services/tunnel/status?simulate=true'
		}).success(function(data, status, headers, config) {
			console.log('Fetched data!');
			$scope.results = data;
		}).error(function(data, status, headers, config) {
			console.log("Communication failed!");
		});
	};
	
	$scope.intervalFunction = function(){
	    $timeout(function() {
	      $scope.fetchObject();
	      $scope.intervalFunction();
	    }, 5000)
	};
	
	$scope.inintFunction = function(){
		  $scope.fetchObject();
		  $scope.intervalFunction();
	};
	
	$scope.update = function(brightness) {
		$http({
			method : 'PUT',
			url : './services/tunnel/control?brightness=' + brightness
		}).success(function(data, status, headers, config) {
			console.log('Put data!');
		}).error(function(data, status, headers, config) {
			console.log("Communication failed!");
		});
	};
	
});