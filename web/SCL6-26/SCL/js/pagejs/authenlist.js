var totalRecord; //总的记录数
var limitRecord;　
var totalPage;
var currentPage;
var newstart;
var limit = 5;
var start = 0;
var pageData;
var authlistapp = angular.module('authlist', ['CommonApp']);
authlistapp.controller('authlistcontroller', function($scope, $http, URLParam) {
	leader = $.cookie("leader");
	organizationname = $.cookie("organizationname");
	var data = 0;
	if((organizationname == "中心主任" && leader == 'true') || organizationname == "管理员") {
		data = 1;
	} else if(leader == 'true') {
		data = 2;
	}
	$scope.datas = data;

	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		//绑定部门
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
		//人员,url地址待改
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
			url: yh + "/authen/search",
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
		limit = newstart + limit;
		$http({
			url: yh + "/authen/search",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"start": start,
				"limit": limit,
				"organName": keshiname,
				"username": renyuanname,
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
			url: yh + "/authen/search",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"start": start,
				"limit": limit,
				"organName": keshiname,
				"username": renyuanname,
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

	$('.cx').click(function() {

		keshiname = $("#keshi option:selected").text();
		keshiname = $.trim(keshiname);
		renyuanname = $("#renyuan option:selected").text();
		renyuanname = $.trim(renyuanname);
		var searchText = $("#search").val();
		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;

		$http({
			url: yh + "/authen/search",
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
			url: yh + "/authen/search",
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
			url: yh + "/authen/search",
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
		window.location.href = "editauthen.html?id=" + id;

	}

	$scope.delkh = function(id) {

		$.ajax({
			url: yh + "/authen/delete/" + id,
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
				$('.ts').html("删除失败！");

			}

		});
	}

});