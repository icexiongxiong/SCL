var totalRecord; //总的记录数
var limitRecord;　
var totalPage;
var currentPage;
var newstart;
var limit = 3;
var start = 0;
var pageData;
var id;
var name;
var companyid;
var consultdetailsapp = angular.module('consultdetails', ['CommonApp']);
consultdetailsapp.controller('consultdetailscontroller', function($scope, $http, URLParam) {

	param = URLParam.getParams();
	id = param.id;　　　 //id就是companyid
	name = param.name;
	$scope.name = name;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
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
				"start": start,
				"limit": limit,
				"companyid": id
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

	$('.cx').click(function() {
		var searchText = $("#search").val();
		var startyear = $("#startyear").val();
		var endyear = $("#endyear").val();	
		alert(searchText);
		alert(startyear);
		alert(endyear);
		var newdang = $('.dang').text();
		newstart = (newdang - 1) * limitRecord;
		if(searchText!=null && startyear!=null && endyear!=null){
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
				"comanyname": searchText,
				"startyear": startyear,
				"endyear": endyear

			}
		}).success(function(response) {
			if(response.success == true) {
				//      $scope.kehuroots = response.root; 
				$scope.pageData = response;

			}
		});
			
		}else if(searchText!=null && startyear!=null && endyear==null){
			alert(222)
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
				"comanyname": searchText,
				"startyear": startyear

			}
		}).success(function(response) {
			if(response.success == true) {
				//      $scope.kehuroots = response.root; 
				$scope.pageData = response;

			}
		});
			
		}else if(searchText!=null && startyear==null && endyear==null){
			alert(333)
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
				"comanyname": searchText

			}
		}).success(function(response) {

			if(response.success == true) {
				//      $scope.kehuroots = response.root; 
				$scope.pageData = response;

			}
		});
			
		}else{
			alert(444)
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
				"limit": limit
			}
		}).success(function(response) {

			if(response.success == true) {
				//      $scope.kehuroots = response.root; 
				$scope.pageData = response;

			}
		});
			
		}


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
				"limit": limit

			}
		}).success(function(response) {
			if(response.success == true) {

				$scope.pageData = response;

			}
		});

	});

	$scope.editkh = function(id, companyid) {
		window.location.href = "editconsultdetail.html?id=" + id + "&companyid=" + companyid;
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