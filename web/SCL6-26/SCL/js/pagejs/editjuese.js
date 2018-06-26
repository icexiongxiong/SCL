var id;
var param;
var editjueseapp = angular.module('editjuese', ['CommonApp']);
editjueseapp.controller('editjuesecontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	param = id.id;
	console.log("看看有没有获取到传过来的id:" + param);
	//绑定菜单内容
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/role/get/" + param,
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'get',
			cache: false,
			dataType: "json",

			success: function(response) {
				if(response.success == true) {
					$scope.dataRoot = response.data;
					$('#jsming').val($scope.dataRoot.name);
					$('#jsdescription').val($scope.dataRoot.description);

				}

			}

		});

	});

	//修改角色

	$('.editjs').click(function() {
		var jsming = $('#jsming').val();
		var jsdescription = $("#jsdescription").val();

		if(!jsming) {
			$('.ts').css('display', 'block');
			$('.ts').html("角色名称不能为空！");
			return;
		}

		if(!jsdescription) {
			$('.ts').css('display', 'block');
			$('.ts').html("角色描述不能为空！");
			return;
		}

		$.ajax({

			url: yh + "/role/update",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"id": param,
				"name": jsming,
				"description": jsdescription
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改角色成功");

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改角色失败");

			}

		});

	});

});