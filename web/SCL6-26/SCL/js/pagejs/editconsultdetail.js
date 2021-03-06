var comapnyid;
var id;
var editdetailapp = angular.module('editdetail', ['CommonApp']);
editdetailapp.controller('editdetailcontroller', function($scope, $http, URLParam) {
	param = URLParam.getParams();
	id = param.id;
	companyid = param.companyid;
	console.log("id:" + id);
	console.log("companyid:" + companyid);
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
	//反向绑定单条记录详情
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$http({
			url: yh + "/companydetail/" + id,
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'GET',
			withCredentials: true,
			cache: false,
			dataType: "json",
		}).success(function(response) {
			if(response.success == true) {
				$scope.dataRoot = response.data;
				//				$scope.detailid = $scope.dataRoot.id;
				//				detail_id = $scope.detailid;

				//公司性质           
				var radios = $("input[name='optionsRadios']");
				var companyNature = $scope.dataRoot.companyNature;
				$.each(radios, function(index, item) {
					if(item.value == companyNature) {
						$(item).parents(".radio").find("span").addClass('checked');

					}

				})

				//研发机构类型
				var researchKinds = $("input[name='yfjg']");
				var KindsValues = $scope.dataRoot.researchKind;
				if(KindsValues != null) {
					var KindsValues = KindsValues.split(',');

					$.each(researchKinds, function(index, item) {
						for(var j = 0; KindsValues.length > j; j++) {

							if(item.value == KindsValues[j]) {

								$(item).parents(".checker").find("span").addClass('checked');

							}
						}

					})
				}

				//中心服务项目
				var centralservers = $("input[name='centralserver']");
				var serverKindValues = $scope.dataRoot.serverKindList;
				$.each(centralservers, function(index, item) {
					for(var j = 0; serverKindValues.length > j; j++) {
						if(item.value == serverKindValues[j].serverName) {
							$(item).parents(".checker").find("span").addClass('checked');
							$(this).parents("label").find("input[type=text]").eq(0).val(getMyDate(serverKindValues[j].serverPerson));
							$(this).parents("label").find("input[type=text]").eq(1).val(serverKindValues[j].serverTel);
						}
					}
				})

				//仪器设备共享情况
				var Radios5 = $("input[name='Radios5']");
				var instrumentShare = $scope.dataRoot.instrumentShare;
				$.each(Radios5, function(index, item) {
					if(item.value == instrumentShare) {
						$(item).parents(".radio").find("span").addClass('checked');

					}
					$(this).parents("label").find("input[type=text]").eq(0).val($scope.dataRoot.instrumentName);
					$(this).parents("label").find("input[type=text]").eq(1).val($scope.dataRoot.instrumentType);
					$(this).parents("label").find("input[type=text]").eq(2).val($scope.dataRoot.instrumentMoney);
				})

				//科技创新券使用类型
				var innovatetypes = $("input[name='kjcx']");
				var KindsValues = $scope.dataRoot.innovatetype;
				if(KindsValues != null) {
					var KindsValues = KindsValues.split(',');

					$.each(innovatetypes, function(index, item) {
						for(var j = 0; KindsValues.length > j; j++) {

							if(item.value == KindsValues[j]) {

								$(item).parents(".checker").find("span").addClass('checked');

							}
						}

					})
				}
				//申领时间
				if($scope.dataRoot.applytime != null) {
					var applytime = getMyDate($scope.dataRoot.applytime);
					$('#applytime').val(applytime); //注册时间
				}
				//申领金额
				$("#applymoney").val($scope.dataRoot.applymoney);
				$("#cashmoney").val($scope.dataRoot.cashmoney);

				//服务年限
				if($scope.dataRoot.startyear != null) {
					var startyear = getMyDate($scope.dataRoot.startyear);
					$('#startyear').val(startyear); //注册时间
				}

				if($scope.dataRoot.endyear != null) {
					var endyear = getMyDate($scope.dataRoot.endyear);
					$('#endyear').val(endyear); //注册时间
				}

			}

		});
	});
	//手机号验证   
	$("#legalphone").blur(function() {
		var legalphone = $("#legalphone").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(legalphone)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});

	$("#contactway").blur(function() {
		var contactway = $("#contactway").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(contactway)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		} else {
			$('.ts').css('display', 'none');
		}

	});
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

	//邮箱验证
	//$("#email").blur(function(){
	//	var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	// var email_val = $("#Email").val();
	// if(!search_str.test(email_val)){       
	//	   $('.yx').css('display','block');
	//     $('.yx').html("邮箱格式错误！");
	//         return false;
	//		}else{
	//		$('.yx').css('display','none');
	//		}
	//});

	$('.editxq').click(function() {
		var companyNature = $('input[name="optionsRadios"]:checked').val(); //公司性质单选按钮

		//获取研发机构类型 :
		var yanfas = [];
		$.each($('input:checkbox[name="yfjg"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {
				yanfas.push(items["value"]);
			}

		});
		var newyanfas = yanfas.toString()

		//中心服务项目
		var zx = "[";
		$.each($('input:checkbox[name="centralserver"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {
				var value1 = items.value;
				var first = $(this).parents("label").find("input[type=text]").eq(0).val();
				var second = $(this).parents("label").find("input[type=text]").eq(1).val();
				zx += "{\"serverName\":\"" + value1 + "\",\"serverPerson\":\"" + first + "\",\"serverTel\":\"" + second + "\"},";
			}
		});

		zx = zx.substring(0, zx.length - 1);
		zx += "]";

		//仪器共享情况

		var radio5value;
		$.each($('input[name="Radios5"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {　　　　
				radio5value = items.value;
			}

		});
		if(radio5value == "是") {
			var machinename = $("#machinename").val();
			var machinetype = $("#machinetype").val();
			var machinemoney = $("#machinemoney").val();

		}
		//科技创新券
		var applytime = $("#applytime").val(); //申领时间
		var applymoney = $("#applymoney").val();
		var cashmoney = $("#cashmoney").val();
		var cx = [];
		$.each($('input:checkbox[name="kjcx"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {
				cx.push(items["value"]);
			}

		});
		var cxs = cx.toString()
		console.log(cxs);　　 //服务年限
		var startyear = $("#startyear").val();
		var endyear = $("#endyear").val();
		//判断结束日期与开始日期大小
		var start = new Date(startyear.replace("-", "/").replace("-", "/"));
		var end = new Date(endyear.replace("-", "/").replace("-", "/"));
		if(start > end) {
			$('.nxts').css('display', 'block');
			$('.nxts').html("结束日期不能小于开始日期！");
			return false;
		} else {
			$('.nxts').css('display', 'none');
		}

		$http({
			url: yh + "/companydetail/update",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"id": id,
				"companyid": companyid,
				"companyNature": companyNature, //公司性质
				"researchKind": newyanfas, //研发机构类型  	    
				"serverkinds": zx, //中心服务项目
				"instrumentShare": radio5value, //大中仪器共享情况
				"instrumentName": machinename,
				"instrumentType": machinetype,
				"instrumentMoney": machinemoney,
				"innovatetype": cxs, //创新券
				"applytime": applytime,
				"applymoney": applymoney,
				"cashmoney": cashmoney,
				"startyear": startyear,
				"endyear": endyear
			}
		}).success(function(data) {
			if(data.success == true) {

				$('.ts').css('display', 'block');
				$('.ts').html("更新客户详情成功！");


			} else {
				$('.ts').css('display', 'block');
				$('.ts').html("更新客户详情失败！");

			}
		});

	});

});