var id;
var name;
var addauthendetailapp = angular.module('addauthendetail', ['CommonApp']);
addauthendetailapp.controller('addauthendetailcontroller', function($scope, $http, URLParam) {
	var param = URLParam.getParams();
	id = param.id;
	name = param.name;
	$scope.companyname = name;
		$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

//手机号验证   
	$("#declaretel").blur(function() {
		var declaretel = $("#declaretel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(declaretel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});
	$("#propertytel").blur(function() {
		var propertytel = $("#propertytel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(propertytel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});
	$("#consulttel").blur(function() {
		var consulttel = $("#consulttel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(consulttel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});
	$("#trainservetel").blur(function() {
		var trainservetel = $("#trainservetel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(trainservetel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});

	$("#erptel").blur(function() {
		var erptel = $("#erptel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(erptel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});

	$("#monitortel").blur(function() {
		var monitortel = $("#monitortel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(monitortel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});
		$("#webservetel").blur(function() {
		var monitortel = $("#monitortel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(monitortel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});

	$("#machineservetel").blur(function() {
		var machineservetel = $("#machineservetel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(machineservetel)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});
	//新增客户详情
	$('.kehuxq').click(function() {
		//中心服务项目
		var zx = "[";
		$.each($('input:checkbox[name="centralserver"]:checked'), function(i, items) {
			var value1 = items.value;
			var first = $(this).parents("label").find("input[type=text]").eq(0).val();
			var second = $(this).parents("label").find("input[type=text]").eq(1).val();

			zx += "{\"serverName\":\"" + value1 + "\",\"serverPerson\":\"" + first + "\",\"serverTel\":\"" + second + "\"},";

		});

		zx = zx.substring(0, zx.length - 1);
		zx += "]";
		console.log("中心服务：" + zx);


		//服务年限
		var startyear = $("#startyear").val();
		var endyear = $("#endyear").val();	
	    //判断结束日期与开始日期大小
	    var start = new Date(startyear.replace("-", "/").replace("-", "/"));
        var end = new Date(endyear.replace("-", "/").replace("-", "/"));
		if(start>end) {
			$('.nxts').css('display', 'block');
			$('.nxts').html("结束日期不能小于开始日期！");
			return false;
		}else{
			$('.nxts').css('display', 'none');
		}

		$.ajax({
			url: yh + "/companydetail/add",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"companyid": id, //公司id
				"serverKinds": zx, //中心服务项目
				"startyear":startyear,//服务年限
				"endyear":endyear
			}

		}).success(function(response) {
			//请求成功
			if(response.success == true) {

				$('.ts').css('display', 'block');
				$('.ts').html("新增客户详情成功！");
				//                  window.location.href="kehulist.html";

			} else {
				$('.ts').css('display', 'block');
				$('.ts').html("新增客户详情失败！");

			}

		});

	});
	
	


});