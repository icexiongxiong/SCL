var Yewuyuan;
var parentId;
var totalRecord; //总的记录数
var limitRecord;　
var totalPage;
var currentPage;
var newstart;
var limit = 20;
var start = 0;
var pageData;
var caidanapp = angular.module('caidan', ['CommonApp']);
caidanapp.controller('caidancontroller', function($scope, $http, URLParam) {
	Yewuyuan = $.cookie("Yewuyuan");
	$('.userName').text(Yewuyuan);

	//菜单管理的动态添加
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/menu/page",
			method: 'POST',
			cache: false,
			dataType: "json",
			crossDomain: true,
			data: {
				start: start,
				limit: limit
			},
			xhrFields: {
				withCredentials: true
			},
			success: function(data) {
				var ghtml = "";
				$.each(data.root, function(i, items) {
					if(items["parentId"] == "0") {
						//一级菜单循环
						ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["name"] + "</td><td><a class='btn btn-success'><i class='addzi white zoom-in' style='width:75px' id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "'>新增</i></a><a class='btn btn-info'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "' class='editparent white edit' style='width:75px'>修改</i></a><a class='btn btn-danger' id='del'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "' class='delparent white trash' style='width:75px'>删除</i></a></td>";
						//二级菜单循环开始  
						ghtml += "<tr>"
						$.each(data.root, function(i, sitems) {
							if(sitems["parentId"] == items["id"]) {
								ghtml += "<tr><td style='width:100px;text-align:center'>" + sitems["id"] + "</td><td class='center' style='width:400px;'><ul style='list-style:none'><li style='position:relative'><a class='submenu'><i class='icon-file-alt'></i><span class='hidden-tablet'>" + sitems["name"] + "</span></a></li></ul></td><td><a class='btn btn-info'><i class='editchild white edit' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentId='" + sitems["parentId"] + "' data-htmlname='" + sitems["htmlname"] + "'>修改</i></a><a class='btn btn-danger'><i class='delzi white trash' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentId='" + sitems["parentId"] + "' data-htmlname='" + sitems["htmlname"] + "'>删除</i></a></td></tr>";
							}

						});
						ghtml += "</tr></tr>";

					}

				});

				$('.editcaidan').append(ghtml);
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

	//分页
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
			url: yh + "/menu/page",
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
			var ghtml = "";
			$.each(data.root, function(i, items) {
				if(items["parentId"] == "0") {
					//一级菜单循环
					ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["name"] + "</td><td><a class='btn btn-success'><i class='addzi white zoom-in' style='width:75px' id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "'>新增</i></a><a class='btn btn-info'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "' class='editparent white edit' style='width:75px'>修改</i></a><a class='btn btn-danger' id='del'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "' class='delparent white trash' style='width:75px'>删除</i></a></td>";
					//二级菜单循环开始  
					ghtml += "<tr>"
					$.each(data.root, function(i, sitems) {
						if(sitems["parentId"] == items["id"]) {
							ghtml += "<tr><td style='width:100px;text-align:center'>" + sitems["id"] + "</td><td class='center' style='width:400px;'><ul style='list-style:none'><li style='position:relative'><a class='submenu'><i class='icon-file-alt'></i><span class='hidden-tablet'>" + sitems["name"] + "</span></a></li></ul></td><td><a class='btn btn-info'><i class='editchild white edit' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentId='" + sitems["parentId"] + "' data-htmlname='" + sitems["htmlname"] + "'>修改</i></a><a class='btn btn-danger'><i class='delzi white trash' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentId='" + sitems["parentId"] + "' data-htmlname='" + sitems["htmlname"] + "'>删除</i></a></td></tr>";
						}

					});
					ghtml += "</tr></tr>";

				}

			});
			$('.editcaidan').empty();
			$('.editcaidan').append(ghtml);
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
			url: yh + "/menu/page",
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
			var ghtml = "";
			$.each(data.root, function(i, items) {
				if(items["parentId"] == "0") {
					//一级菜单循环
					ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["name"] + "</td><td><a class='btn btn-success'><i class='addzi white zoom-in' style='width:75px' id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "'>新增</i></a><a class='btn btn-info'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "' class='editparent white edit' style='width:75px'>修改</i></a><a class='btn btn-danger' id='del'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentId='" + items["parentId"] + "' data-htmlname='" + items["htmlname"] + "' class='delparent white trash' style='width:75px'>删除</i></a></td>";
					//二级菜单循环开始  
					ghtml += "<tr>"
					$.each(data.root, function(i, sitems) {
						if(sitems["parentId"] == items["id"]) {
							ghtml += "<tr><td style='width:100px;text-align:center'>" + sitems["id"] + "</td><td class='center' style='width:400px;'><ul style='list-style:none'><li style='position:relative'><a class='submenu'><i class='icon-file-alt'></i><span class='hidden-tablet'>" + sitems["name"] + "</span></a></li></ul></td><td><a class='btn btn-info'><i class='editchild white edit' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentId='" + sitems["parentId"] + "' data-htmlname='" + sitems["htmlname"] + "'>修改</i></a><a class='btn btn-danger'><i class='delzi white trash' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentId='" + sitems["parentId"] + "' data-htmlname='" + sitems["htmlname"] + "'>删除</i></a></td></tr>";
						}

					});
					ghtml += "</tr></tr>";

				}

			});
			$('.editcaidan').empty();
			$('.editcaidan').append(ghtml);
		});

	});

	$("body").delegate(".addzi", "click", function() {
		var id = $(this).attr('id');
		window.location.href = "addzicaidan.html?parentId=" + id;
	})

	$("body").delegate(".delparent", "click", function() {
			var id = $(this).attr('id');
			$.ajax({
				url: yh + "/menu/delete/" + id,
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
					$('.ts').html("删除菜单成功");
					window.location.reload();

				} else if(response.success == false) {
					$('.ts').css('display', 'block');
					$('.ts').html("删除父菜单之前必须先删除所有的子菜单");
				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("删除菜单失败");

				}

			});
	})
	
	$("body").delegate(".delzi", "click", function() {
				var id = $(this).attr('id');
			$.ajax({
				url: yh + "/menu/delete/" + id,
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
					//                $('.ts').html("删除菜单成功");
					window.location.reload();

				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("删除菜单失败");
				}

			});
	})
	

	$("body").delegate(".editparent", "click", function() {
		var id = $(this).attr('id');
		window.location.href = "editcaidan.html?id=" + id;

	})
	$("body").delegate(".editchild", "click", function() {
		var id = $(this).attr('id');
	    window.location.href = "editzicaidan.html?id=" + id;

	})

});