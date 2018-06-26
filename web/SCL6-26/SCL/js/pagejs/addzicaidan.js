var id;
var parentId;
var addzicaidanapp = angular.module('addzicaidan', ['CommonApp']);
addzicaidanapp.controller('addzicaidancontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	parentId = id.parentId;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

	//添加一级菜单

	$('.addsecondcaidan').click(function() {
		var zicaidanname = $('#zicaidanname').val();
		var htmlname = $("#caidanpath").val();

		if(!zicaidanname) {
			//      $('.addfirstcaidan').attr('data-target','#myModal');  //点击事件添加属性
			alert('子菜单名不能为空')
			return;
		}

		$.ajax({

			url: yh + "/menu/add",
			method: 'POST',
			data: {
				"name": zicaidanname,
				"htmlname": htmlname,
				"parentId": parentId
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {

			//请求成功
			if(response.success == true) {

				//      			 $('.addfirstcaidan').attr('data-target','#myModal2');
				$('.ts').css('display', 'block');
				//                  $('.ts').html("添加子菜单成功");
				window.location.href = "caidan.html";

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("添加子菜单失败");

			}

		});

	});

});