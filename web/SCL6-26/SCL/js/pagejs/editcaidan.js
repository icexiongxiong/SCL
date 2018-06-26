var id;
var param;
var editcaidanapp = angular.module('editcaidan', ['CommonApp']);
editcaidanapp.controller('editcaidancontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	param = id.id;
	console.log("看看有没有获取到传过来的id:" + param);
	//绑定菜单内容
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/menu/get/" + param,
			method: 'get',
			cache: false,
			dataType: "json",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			success: function(response) {
				if(response.success == true) {
					$scope.dataRoot = response.data;
					$('#parentcaidanname').val($scope.dataRoot.name);
					$('#parentcaidanpath').val($scope.dataRoot.htmlname);
					$('#childcaidanname').val($scope.dataRoot.name);
					$('#childcaidanpath').val($scope.dataRoot.htmlname);
					$('#childjiedian').val($scope.dataRoot.parentId);

				}

			}

		});

	});

	//修改父菜单信息

	$('.editparent').click(function() {
		var parentcaidanname = $('#parentcaidanname').val();
		var htmlname = $("#parentcaidanpath").val();

		if(!parentcaidanname) {
			$('.ts').css('display', 'block');
			$('.ts').html("菜单名称不能为空！");
			return;
		}

		$.ajax({

			url: yh + "/menu/update",
			method: 'POST',
			data: {
				"id": param,
				"name": parentcaidanname,
				"htmlname": htmlname,
				"parentId": '0'
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("修改菜单成功");
				window.location.href = "caidan.html";

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改菜单失败");

			}

		});

	});

	//修改子菜单信息
	$('.editchild').click(function() {
		console.log("看看有没有获取到传过来的id:" + param);
		var childcaidanname = $('#childcaidanname').val();
		var childcaidanpath = $("#childcaidanpath").val();
		var jiedian = $("#childjiedian").val();
		if(!childcaidanname) {
			//    $('.addfirstcaidan').attr('data-target','#myModal');  //点击事件添加属性
			alert('子菜单名不能为空！');
			return;
		}

		if(!jiedian) {
			//    $('.addfirstcaidan').attr('data-target','#myModal');  //点击事件添加属性
			alert('父节点不能为空！');
			return;
		}

		$.ajax({

			url: yh + "/menu/update",
			method: 'POST',
			data: {
				"id": param,
				"name": childcaidanname,
				"htmlname": childcaidanpath,
				"parentId": jiedian
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("修改菜单成功");
				window.location.href = "caidan.html";

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改菜单失败");

			}

		});

	});

});