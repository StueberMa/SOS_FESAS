angular.module("TunnelApp", []).controller("TunnelController", function($scope, $http, $timeout) {
	
	$scope.fetchObject = function() {
		$http({
			method : 'GET',
			url : './services/hello/sayObjectHello'
		}).success(function(data, status, headers, config) {
			 console.log('Fetched data!');
			$scope.results = data;
		}).error(function(data, status, headers, config) {
			alert("Communication failed!");
		});
	};
	
	$scope.intervalFunction = function(){
	    $timeout(function() {
	      $scope.fetchObject();
	      $scope.intervalFunction();
	    }, 20000)
	};
	
	$scope.inintFunction = function(){
		  $scope.fetchObject();
		  $scope.intervalFunction();
	};
	
});