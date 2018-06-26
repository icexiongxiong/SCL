var addyonghuapp = angular.module('addyonghu', ['CommonApp']);
addyonghuapp.controller('addyonghucontroller', function($scope, $http, URLParam) {
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

	//绑定部门	
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
				$(".bumenselect").append("<option selected='selected'  value=0>请选择部门..</option>");
				for(var i = 0; i < len1; i++) {

					$(".bumenselect").append("<option  value=" + data.root[i].id + ">" + data.root[i].name + "</option>");
				}
			}

		});

	});

	//添加用户
	$('.addyonghu').click(function() {
		var yonghuming = $('#yonghuming').val();
		var yonghumingma = $("#yonghumingma").val();
		var bumenid = $('.bumenselect option:selected').val();
		var shifoulingdao = $('.shifoulingdao option:selected').val();

		if(!yonghuming) {
			$('.ts').css('display', 'block');
			$('.ts').html("用户名称不能为空！");
			return;
		}

		if(!yonghumingma) {
			$('.ts').css('display', 'block');
			$('.ts').html("用户密码不能为空！");
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
			url: yh + "/user/add",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"username": yonghuming,
				"password": yonghumingma,
				"organizationid": bumenid,
				"leader": shifoulingdao
			}

		}).success(function(response) {

			//请求成功
			if(response.success == true) {

				$('.ts').css('display', 'block');
				//                  $('.ts').html("用户添加成功！");
				window.location.href = "yonghu.html";

			} else {
				$('.ts').css('display', 'block');
				$('.ts').html("用户添加失败！");

			}

		});

	});

});