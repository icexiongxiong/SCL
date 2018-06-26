var totalRecord; //总的记录数
var limitRecord;　
var totalPage;
var currentPage;
var newstart;
var limit = 5;
var start = 0;
var pageData;
var userticketlistapp = angular.module('userticketlist', ['CommonApp']);
userticketlistapp.controller('userticketlistcontroller', function($scope, $http, URLParam) {
	leader = $.cookie("leader");
	organizationname = $.cookie("organizationname");
	console.log("leader:"+leader)
	var data = 0;
	if((organizationname == "中心主任" && leader == 'true') || organizationname == "管理员") {
		data = 1;
	} else if(leader == "true") {
		data = 2;
	} else {
		data = 0;
	}
	$scope.datas = data;

	$(function() {
		$("#content").removeAttr("style");
		$("#content").attr("style", "min-height: 1200px");
		$("#sidebar-left").attr("style", "min-height: 1200px")
	});
	//科室信息
	$(function() {
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
		//人员
		$http({
			url: yh + "/user/combo",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'GET',
			withCredentials: true,
			cache: false,
			dataType: "json",

		}).success(function(response) {
			if(response.success == true) {
				$scope.renyuans = response.root;
			}
		});

		$http({
			url: yh + "/useticket/search",
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

	//科室搜索
	$("#keshi").change(function() {
		keshiname = $("#keshi option:selected").text();
		keshiname = $.trim(keshiname);
		renyuanname = $("#renyuan option:selected").text();
		renyuanname = $.trim(renyuanname);
		var searchText = $("#search").val();
		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;
		$http({
			url: yh + "/useticket/search",
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
				"username": renyuanname,
				"companyname": searchText,
				"organName": keshiname

			}
		}).success(function(response) {

			if(response.success == true) {
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
	//人员搜索
	$("#renyuan").change(function() {
		keshiname = $("#keshi option:selected").text();
		keshiname = $.trim(keshiname);
		renyuanname = $("#renyuan option:selected").text();
		renyuanname = $.trim(renyuanname);
		var searchText = $("#search").val();
		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;
		$http({
			url: yh + "/useticket/search",
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
				"username": renyuanname,
				"companyname": searchText,
				"organName": keshiname

			}
		}).success(function(response) {

			if(response.success == true) {
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

	$('.cx').click(function() {
		keshiname = $("#keshi option:selected").text();
		keshiname = $.trim(keshiname);
		renyuanname = $("#renyuan option:selected").text();
		renyuanname = $.trim(renyuanname);
		var searchText = $("#search").val();
		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;

		$http({
			url: yh + "/useticket/search",
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
				"companyname": searchText

			}
		}).success(function(response) {

			if(response.success == true) {
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

	$('.prev').click(function() {
		keshiname = $("#keshi option:selected").text();
		keshiname = $.trim(keshiname);
		renyuanname = $("#renyuan option:selected").text();
		renyuanname = $.trim(renyuanname);
		var searchText = $("#search").val();
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
			url: yh + "/useticket/search",
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
				"organName": keshiname,
				"username": renyuanname,
				"companyname": searchText
			}
		}).success(function(response) {

			if(response.success == true) {
				//      $scope.kehuroots = response.root; 

				$scope.pageData = response;

			}
		});

	});

	$('.next').click(function() {
		keshiname = $("#keshi option:selected").text();
		keshiname = $.trim(keshiname);
		renyuanname = $("#renyuan option:selected").text();
		renyuanname = $.trim(renyuanname);
		var searchText = $("#search").val();
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
			url: yh + "/useticket/search",
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
				"organName": keshiname,
				"username": renyuanname,
				"companyname": searchText

			}
		}).success(function(response) {
			if(response.success == true) {

				$scope.pageData = response;

			}
		});

	});

	$scope.editkh = function(id) {
		window.location.href = "edituseticket.html?id=" + id;

	}

	$scope.delkh = function(id) {

		$.ajax({
			url: yh + "/useticket/delete/" + id,
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