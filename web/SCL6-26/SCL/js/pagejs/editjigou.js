var id;
var param;
var parentid;
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
			url: yh + "/organ/get/" + param,
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
					$('#jgming').val($scope.dataRoot.name);
					$('#jgdescription').val($scope.dataRoot.description);
					$('#fujiedian').val($scope.dataRoot.parentid);
					parentid = $scope.dataRoot.parentid;

				}

			}

		});

	});

	//修改机构

	$('.editjg').click(function() {
		console.log("看看是否有父节点:" + parentid);
		var jigouming = $('#jgming').val();
		var jigoudescription = $("#jgdescription").val();
		var fujiedian = $("#fujiedian").val();

		if(!jigouming) {
			$('.ts').css('display', 'block');
			$('.ts').html("组织机构名称不能为空！");
			return;
		}

		if(!jigoudescription) {
			$('.ts').css('display', 'block');
			$('.ts').html("组织机构描述不能为空！");
			return;
		}

		if(!fujiedian) {
			$('.ts').css('display', 'block');
			$('.ts').html("所属父节点不能为空！");
			return;
		}

		$.ajax({

			url: yh + "/organ/update",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"id": param,
				"name": jigouming,
				"description": jigoudescription,
				"parentid": parentid

			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改组织机构成功!");

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改组织机构失败!");

			}

		});

	});

});