var Yewuyuan;
var indexapp = angular.module('index', ['CommonApp']);
indexapp.controller('indexcontroller', function($scope, $http, URLParam) {
	//方法1：前台进行判断

	Yewuyuan = $.cookie("Yewuyuan");
	//判断是否进行了登录绑定登录的用户名
	if(!Yewuyuan) {
		window.location.href = "login.html";
		$('.userName').text('');
	} else {
		$('.userName').text(Yewuyuan);
	}

	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

	//注销
	$("#zhuxiao").click(function() {
		$.ajax({
			url: yh + "/logout",
			method: 'POST',
			cache: false,
			dataType: "json",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			success: function(data) {
				if(data.success == true) {

					window.location.href = "login.html";
				}

			}

		});

	});

});