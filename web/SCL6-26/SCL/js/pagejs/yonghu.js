var Yewuyuan;
var parentId;
var totalRecord; //总的记录数
var limitRecord;　
var totalPage;
var currentPage;
var newstart;
var limit = 10;
var pageData;
var yonghuapp = angular.module('yonghu', ['CommonApp']);
yonghuapp.controller('yonghucontroller', function($scope, $http, URLParam) {
	Yewuyuan = $.cookie("Yewuyuan");

	//角色管理的动态添加	
	$(function() {
			$("#content").removeAttr("style");
			$("#content").css('min-height', '1200px');

		$.ajax({
			url: yh + "/user/page",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				start: 0,
				limit: 10
			},
			cache: false,
			dataType: "json"
		}).success(function(data) {
			if(data.success == true) {

				var ghtml = "";
				$.each(data.root, function(i, items) {
					//一级菜单循环 , onclick='edituser()'                                                                                                                                                                                
					ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["username"] + "</td><td><a class='btn btn-info'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "' class='edituser white edit' style='width:75px'>修改</i></a><a class='btn btn-success' id='del'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "'  class='yhpz white trash' style='width:75px'>用户角色配置</i></a><a class='btn btn-danger' id='del'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "'   class='delyonghu white trash' style='width:75px'>删除</i></a></td>";

					ghtml += "</tr>";

				});

				$('.yhcontent').append(ghtml);
				$scope.pageData = data;
				pageData = $scope.pageData;
				var totalRecord = data.total;
				$scope.limit = data.limit;
				limitRecord = $scope.limit;
				var start = data.start;
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
			url: yh + "/user/page",
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
		}).success(function(data) {
			if(data.success == true) {
				var ghtml = "";
				$.each(data.root, function(i, items) {
					//一级菜单循环
					ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["username"] + "</td><td> <a class='btn btn-info'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "' class='edituser white edit' style='width:75px'>修改</i></a><a class='btn btn-success' id='del'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "'  class='yhpz white trash' style='width:75px'>用户角色配置</i></a><a class='btn btn-danger' id='del'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "'   class='delyonghu white trash' style='width:75px'>删除</i></a></td>";

					ghtml += "</tr>";

				});
				$('.yhcontent').empty();
				$('.yhcontent').append(ghtml);
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
			url: yh + "/user/page",
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
		}).success(function(data) {
			if(data.success == true) {
				var ghtml = "";
				$.each(data.root, function(i, items) {
					//一级菜单循环
					ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["username"] + "</td><td><a class='btn btn-info'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "' class='edituser white edit' style='width:75px'>修改</i></a><a class='btn btn-success' id='del'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "'  class='yhpz white trash' style='width:75px'>用户角色配置</i></a><a class='btn btn-danger' id='del'><i name='" + items["username"] + "' id='" + items["id"] + "' organizationid='" + items["organizationid"] + "' leader='" + items["leader"] + "'   class='delyonghu white trash' style='width:75px'>删除</i></a></td>";

					ghtml += "</tr>";

				});
				$('.yhcontent').empty();
				$('.yhcontent').append(ghtml);
			}
		});

	});

	$("body").delegate(".edituser", "click", function() {
		var id = $(this).attr('id');
		window.location.href = "edityonghu.html?id=" + id;
	})

	$("body").delegate(".delyonghu", "click", function() {
		var id = $(this).attr('id');
		$.ajax({
			url: yh + "/user/delete/" + id,
			xhrFields: {
				withCredentials: true
			},

			crossDomain: true,
			method: 'GET',
			cache: false,
			dataType: "json"
		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("用户删除成功！");
				window.location.reload();

			} else {
				$('.ts').css('display', 'block');
				$('.ts').html("用户删除失败！");

			}

		});
	})

	$("body").delegate(".yhpz", "click", function() {
		var id = $(this).attr('id');
		var name = $(this).attr('name');
		window.location.href = "yonghuquanxian.html?id=" + id + "&name=" + name;

	})

});