var id;
var param;
var editcollectapp = angular.module('editcollect', ['CommonApp']);
editcollectapp.controller('editcollectcontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	param = id.id;
	console.log("id:" + param);

	//转换时间戳   
	function getMyDate(str) {
		var oDate = new Date(str),
			oYear = oDate.getFullYear(),
			oMonth = oDate.getMonth() + 1,
			oDay = oDate.getDate(),

			oTime = oYear + '-' + getzf(oMonth) + '-' + getzf(oDay); //最后拼接时间  
		return oTime;
	};
	//补0操作
	function getzf(num) {
		if(parseInt(num) < 10) {
			num = '0' + num;
		}
		return num;
	}

	//绑定所有收券信息
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/collectticket/" + param,
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
					$('#organizationname').val($scope.dataRoot.organizationname);
					$('#companyaddress').val($scope.dataRoot.address);
					$('#companyperson').val($scope.dataRoot.person);
					$('#organizationtel').val($scope.dataRoot.phone);
					$('#servescope').val($scope.dataRoot.serverarea);
					$('#ticketmoney').val($scope.dataRoot.ticketmoney);
					$('#cashmoney').val($scope.dataRoot.cashmoney);
					$('#organizationemail').val($scope.dataRoot.email);

				}

			}

		});
	});

	$('.newkh').click(function() {
		var organizationname = $('#organizationname').val();
		var companyaddress = $("#companyaddress").val();
		var companyperson = $("#companyperson").val();
		var organizationtel = $("#organizationtel").val();
		var servescope = $("#servescope").val();
		var ticketmoney = $("#ticketmoney").val();
		var cashmoney = $("#cashmoney").val();
		var organizationemail = $("#organizationemail").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(organizationtel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		}

		$.ajax({

			url: yh + "/collectticket/update",
			method: 'POST',
			data: {
				"id": param,
				"organizationname": organizationname,
				"address": companyaddress,
				"person": companyperson,
				"phone": organizationtel,
				"serverarea": servescope,
				"ticketmoney": ticketmoney,
				"cashmoney": cashmoney,
				"email": organizationemail
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("编辑客户信息成功!");
				window.location.href = "collectticketlist.html"

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("编辑客户信息失败!");

			}

		});

	});

});