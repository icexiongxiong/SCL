var totalRecord; //总的记录数
var limitRecord;　
var totalPage;
var currentPage;
var newstart;
var limit = 5;
var start = 0;
var pageData;
var kehulistapp = angular.module('kehulist', ['CommonApp']);
kehulistapp.controller('kehulistcontroller', function($scope, $http, URLParam) {

	//科室信息
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$http({
			url: yh + "/organ/keshi",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'GET',
			withCredentials: true,
			cache: false,
			dataType: "json",

		}).success(function(response) {
			if(response.success == true) {
				$scope.keshis = response.root;
			}
		});

		$http({
			url: yh + "/companydetail/page",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"start": start,
				"limit": limit
			}
		}).success(function(response) {

			if(response.success == true) {
				//      $scope.kehuroots = response.root; 
				$scope.pageData = response;
				pageData = $scope.pageData;
				var totalRecord = response.total;
				$scope.limit = response.limit;
				limitRecord = $scope.limit;
				var start = response.start;
				if(start == 0) {
					currentPage = 1;
				}

				if(totalRecord / limitRecord > parseInt(totalRecord / limitRecord)) {　
					totalPage = parseInt(totalRecord / limitRecord) + 1;
				} else {
					totalPage = parseInt(totalRecord / limitRecord)
				}
				$('.zong').text(totalPage);
				$('.dang').text(currentPage);

			}
		});

	});

	$("#keshi").change(function() {

		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;
		var keshiname = $("#keshi option:selected").text();
		var keshiname = $.trim(keshiname);

		$http({
			url: yh + "/companydetail/search",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"start": newstart,
				"limit": limit,
				"organName": keshiname

			}
		}).success(function(response) {

			if(response.success == true) {
				$scope.pageData = response;

			}
		});

	});

	$('.cx').click(function() {

		var searchText = $("#search").val();
		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;

		$http({
			url: yh + "/companydetail/search",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"start": newstart,
				"limit": limit,
				"companyName": searchText

			}
		}).success(function(response) {

			if(response.success == true) {
				//      $scope.kehuroots = response.root; 
				$scope.pageData = response;

			}
		});

	});

	$('.prev').click(function() {
		var dang = $('.dang').text();
		if(dang == 1) {
			$('.ts').css('display', 'block');
			$('.ts').html("当前已是第一条!");
		} else {
			$('.dang').text(--dang);
		}

		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;

		$http({
			url: yh + "/companydetail/page",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"start": newstart,
				"limit": limit
			}
		}).success(function(response) {

			if(response.success == true) {
				//      $scope.kehuroots = response.root; 

				$scope.pageData = response;

			}
		});

	});

	$('.next').click(function() {
		var dang = $('.dang').text();
		var zongNum = $('.zong').text();
		if(dang == zongNum) {
			$('.ts').css('display', 'block');
			$('.ts').html("当前已是最后一条!");

		} else {
			$('.dang').text(++dang);
		}
		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;

		$http({
			url: yh + "/companydetail/page",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"start": newstart,
				"limit": limit

			}
		}).success(function(response) {
			if(response.success == true) {

				$scope.pageData = response;

			}
		});

	});

	$scope.addxq = function(id, companyName, person, time, type, linkman, contactway, fund) {
		window.location.href = "addkehuxiangqing.html?id=" + id + "&name=" + companyName + "&person=" + person + "&time=" + time + "&type=" + type + "&linkman=" + linkman + "&contactway=" + contactway + "&fund=" + fund;

	}

	$scope.editkh = function(id) {
		window.location.href = "editkehu.html?id=" + id;

	}

	$scope.delkh = function(id) {

		$.ajax({
			url: yh + "/companydetail/delete/" + id,
			xhrFields: {
				withCredentials: true
			},

			crossDomain: true,
			method: 'GET',
			cache: false,
			dataType: "json"
		}).success(function(response) {
			if(response.success == true) {
				window.location.reload();

			} else {
				$('.ts').css('display', 'block');
				$('.ts').html("用户删除失败！");

			}

		});
	}

});