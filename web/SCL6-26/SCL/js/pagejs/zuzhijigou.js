var Yewuyuan;
var parentId;
var totalRecord; //总的记录数
var limitRecord;　
var totalPage;
var currentPage;
var newstart;
var limit = 20;
var start = 0;
var zuzhiapp = angular.module('zuzhi', ['CommonApp']);
zuzhiapp.controller('zuzhicontroller', function($scope, $http, URLParam) {

	//菜单管理的动态添加
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');

		$.ajax({
			url: yh + "/organ/page",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"start": start,
				"limit": limit
			},
			cache: false,
			dataType: "json",

			success: function(data) {
				if(data.success == true) {
					var ghtml = "";
					$.each(data.root, function(i, items) {

						if(items["parentid"] == "0") {
							//一级菜单循环
							ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["name"] + "</td><td><a class='btn btn-success'><i class='addfirst white zoom-in' style='width:75px' id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentid='" + items["parentid"] + "' data-description='" + items["description"] + "'>新增</i></a><a class='btn btn-info'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentid='" + items["parentid"] + "' data-description='" + items["description"] + "' class='editfirst white edit' style='width:75px'>修改</i></a><a class='btn btn-danger' id='del'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-parentid='" + items["parentid"] + "' data-description='" + items["description"] + "' class='delfirst white trash' style='width:75px'>删除</i></a></td>";
							//二级菜单循环开始  
							ghtml += "<tr>"
							$.each(data.root, function(i, sitems) {

								if(sitems["parentid"] == items["id"]) {
									ghtml += "<tr><td style='width:100px;text-align:center'>" + sitems["id"] + "</td><td class='center' style='width:400px;'><ul style='list-style:none'><li style='position:relative'><i class='icon-file-alt'></i><span class='hidden-tablet'>" + sitems["name"] + "</span></li></ul></td><td><a class='btn btn-success'><i class='addsecond white zoom-in' style='width:75px' id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentid='" + sitems["parentid"] + "' data-description='" + sitems["description"] + "'>新增</i></a><a class='btn btn-info'><i class='editsecond white edit' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentid='" + sitems["parentid"] + "' data-description='" + sitems["description"] + "'>修改</i></a><a class='btn btn-danger'><i class='delsecond white trash' style='width:75px'  id='" + sitems["id"] + "' data-name='" + sitems["name"] + "' data-parentid='" + sitems["parentid"] + "' data-description='" + sitems["description"] + "'>删除</i></a></td></tr>";
									//三级菜单循环     
									ghtml += "<tr>";
									$.each(data.root, function(i, ssitems) {
										if(ssitems["parentid"] == sitems["id"]) {

											ghtml += "<tr><td style='width:100px;text-align:center'>" + ssitems["id"] + "</td><td class='center' style='width:400px;'><ul style='list-style:none'><li style='position:relative'><ul style='list-style:none'><li><i class='icon-file-alt'></i><span class='hidden-tablet'>" + ssitems["name"] + "</span></li></ul></li></ul></td><td><a class='btn btn-info'><i class='editthird white edit' style='width:75px'  id='" + ssitems["id"] + "' data-name='" + ssitems["name"] + "' data-parentid='" + ssitems["parentid"] + "' data-description='" + ssitems["description"] + "'>修改</i></a><a class='btn btn-danger'><i class='delthird white trash' style='width:75px'  id='" + ssitems["id"] + "' data-name='" + ssitems["name"] + "' data-parentId='" + ssitems["parentId"] + "' data-htmlname='" + ssitems["htmlname"] + "'>删除</i></a></td></tr>";

										}

									});
									ghtml += "</tr></tr>";

								}

							});
							ghtml += "</tr>";

						}

					});

					$('.editjigou').append(ghtml);

				}

			}

		});

	});

	function iFrameCity1() {
		setTimeout(iFrameCity, 1000);
	}

	function iFrameCity() {

		$('.addfirst').click(function() {
			var id = $(this).attr('id');
			window.location.href = "addjigou.html?parentid=" + id;

		});

		$('.editfirst').click(function() {
			var id = $(this).attr('id');

			window.location.href = "editjigou.html?id=" + id;

		});

		$('.addsecond').click(function() {
			var id = $(this).attr('id');

			window.location.href = "addzijigou.html?parentid=" + id;

		});

		$('.editsecond').click(function() {
			var id = $(this).attr('id');

			window.location.href = "editsecondjigou.html?id=" + id;

		});

		//删除父节点的
		$('.delfirst').click(function() {
			var id = $(this).attr('id');
			$.ajax({
				url: yh + "/organ/delete/" + id,
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

					window.location.reload();

				} else if(response.success == false) {
					$('.ts').css('display', 'block');
					$('.ts').html("删除父机构之前必须先删除所有的子机构");
				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("删除机构失败");

				}

			});

		})

		//删除第二层机构
		$('.delsecond').click(function() {
			var id = $(this).attr('id');
			$.ajax({
				url: yh + "/organ/delete/" + id,
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

					window.location.reload();

				} else if(response.success == false) {
					$('.ts').css('display', 'block');
					$('.ts').html("删除父机构之前必须先删除所有的子机构");
				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("删除机构失败");

				}

			});

		})

		//删除三层机构
		$('.delthird').click(function() {
			var id = $(this).attr('id');
			$.ajax({
				url: yh + "/organ/delete/" + id,
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

					window.location.reload();

				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("删除机构失败");
				}

			});

		})

	}
	iFrameCity1();

});