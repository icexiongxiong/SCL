var Yewuyuan;
var xgmimaapp = angular.module('xgmima', ['CommonApp']);
xgmimaapp.controller('xgmimacontroller', function($scope, $http, URLParam) {
	Yewuyuan = $.cookie("Yewuyuan");
	$('#yhm').val(Yewuyuan);
	//修改密码
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

	$('.changmima').click(function() {

		var yhm = $('#yhm').val();
		//    var origpassword = $('#origpassword').val();
		var newpassword = $("#newpassword").val();
		var confirmpassword = $("#confirmpassword").val();

		if(!newpassword) {
			$('.ts').css('display', 'block');
			$('.ts').html("新密码不能为空！");
			return;
		} else if(!confirmpassword) {
			$('.ts').css('display', 'block');
			$('.ts').html("确认密码不能为空！");
			return;

		} else if(newpassword != confirmpassword) {

			$('.ts').css('display', 'block');
			$('.ts').html("新密码和确认密码不一致！");
		} else {
			$.ajax({
				url: yh + "/user/pass",
				crossDomain: true,
				xhrFields: {
					withCredentials: true
				},
				method: 'POST',
				data: {
					"username": Yewuyuan,
					"password": newpassword

				},
			}).success(function(response) {
				if(response.success == true) {
					$('.ts').css('display', 'block');
					$('.ts').html("修改密码成功！");

				} else if(response.success == false) {
					$('.ts').css('display', 'block');
					$('.ts').html("修改密码失败！");

				}

			});

		}

	});

});