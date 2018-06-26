var id;
var param;
var pass;
var edityonghuapp = angular.module('edityonghu', ['CommonApp']);
edityonghuapp.controller('edityonghucontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	param = id.id;
	console.log("看看有没有获取到传过来的id:" + param);
	//绑定用户信息
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/user/" + param,
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
					$('#yonghuming').val($scope.dataRoot.username);
					$('.bumenselect option:selected').text($scope.dataRoot.organizationname);
					var learder = $scope.dataRoot.leader;
					$(".shifoulingdao option[value='" + learder + "']").attr("selected", true);
				}

			}

		});

	});

	$(function() {
		$.ajax({
			url: yh + "/organ/combo",
			method: 'POST',
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(data) {
			//请求成功
			if(data.success == true) {
				var len1 = data.root.length;

				$(".bumenselect").empty(); //首先清空select现在有的内容

				for(var i = 0; i < len1; i++) {

					$(".bumenselect").append("<option  value=" + data.root[i].id + ">" + data.root[i].name + "</option>");
				}
			}

		});

	});

	$(function() {
		$('.shifoulingdao').empty();
		$(".shifoulingdao").append("<option  value='true'>领导</option><option value='false'>非领导</option>");
	});

	//修改用户

	$('.edityonghu').click(function() {
		var pass = $scope.dataRoot.password;
		var yonghuming = $('#yonghuming').val();
		var bumenid = $('.bumenselect option:selected').val();
		var shifoulingdao = $('.shifoulingdao option:selected').val();

		if(!yonghuming) {
			$('.ts').css('display', 'block');
			$('.ts').html("用户名称不能为空！");
			return;
		}

		if(!bumenid) {
			$('.ts').css('display', 'block');
			$('.ts').html("必须选择部门名！");
			return;

		}

		if(!shifoulingdao) {
			$('.ts').css('display', 'block');
			$('.ts').html("必须选择是否领导！");
			return;
		}

		$.ajax({

			url: yh + "/user/update",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"id": param,
				"username": yonghuming,
				"organizationid": bumenid,
				"password": pass,
				"leader": shifoulingdao
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改用户信息成功！");

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("修改用户信息失败！");

			}

		});

	});

});