var id;
var param;
var parentid;
var editsecondjigouapp = angular.module('editsecondjigou', ['CommonApp']);
editsecondjigouapp.controller('editsecondjigoucontroller', function($scope, $http, URLParam) {
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
					$('#secondjgming').val($scope.dataRoot.name);
					$('#secondjgdescription').val($scope.dataRoot.description);
					$('#secondfujiedian').val($scope.dataRoot.parentid);
					parentid = $scope.dataRoot.parentid;

				}

			}

		});

	});

	//修改机构

	$('.editsecondjg').click(function() {
		console.log("看看是否有父节点:" + parentid);
		var secondjgming = $('#secondjgming').val();
		var secondjgdescription = $("#secondjgdescription").val();
		var secondfujiedian = $("#secondfujiedian").val();

		if(!secondjgming) {
			$('.ts').css('display', 'block');
			$('.ts').html("组织机构名称不能为空！");
			return;
		}

		if(!secondjgdescription) {
			$('.ts').css('display', 'block');
			$('.ts').html("组织机构描述不能为空！");
			return;
		}

		if(!secondfujiedian) {
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
				"name": secondjgming,
				"description": secondjgdescription,
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