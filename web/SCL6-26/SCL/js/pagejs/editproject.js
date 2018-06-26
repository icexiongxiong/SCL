var id;
var param;
var editauthenapp = angular.module('editauthen', ['CommonApp']);
editauthenapp.controller('editauthencontroller', function($scope, $http, URLParam) {
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

	//绑定所有企业信息
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/company/" + param,
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
					$('#companyname').val($scope.dataRoot.companyname); //企业名					
					$('#creditcode').val($scope.dataRoot.creditcode);
					$('#legalperson').val($scope.dataRoot.legalperson); //企业法人
					$('#legalphone').val($scope.dataRoot.legaltelphone);
					$('#stockmessage').val($scope.dataRoot.stockmessage);

					$('#zhucezijin').val($scope.dataRoot.fund); //资金
					if($scope.dataRoot.buildtime != null) {
						var buildtime = getMyDate($scope.dataRoot.buildtime);
						$('#buildtime').val(buildtime); //注册时间
					}
					$('#companytype option:selected').text($scope.dataRoot.companytype); //企业类型

					//省、市、县，地址
					var cmbProvince = $scope.dataRoot.province;
					var cmbCity = $scope.dataRoot.city;
					var cmbArea = $scope.dataRoot.county;

					$("#cmbProvince option[value='" + cmbProvince + "']").attr("selected", true);
					$("#cmbCity option[value='" + cmbCity + "']").attr("selected", true);
					$("#cmbArea option[value='" + cmbArea + "']").attr("selected", true);
					$('#companynum').val($scope.dataRoot.companynum);
					$('#lianxiren').val($scope.dataRoot.contactperson);
					$('#lianxidianhua').val($scope.dataRoot.telphone);
					$('#weixin').val($scope.dataRoot.weixin);
					$('#email').val($scope.dataRoot.email);
					$('#mainfield').val($scope.dataRoot.mainfield); //主营范围

				}

			}

		});

	});

	$('.editqiye').click(function() {
		var companyname = $('#companyname').val(); //企业名称
		var creditcode = $("#creditcode").val();
		var legalperson = $("#legalperson").val(); //企业法人
		var legalphone = $("#legalphone").val();
		var stockmessage = $("#stockmessage").val();
		var zhucezijin = $("#zhucezijin").val(); //注册资金
		var buildtime = $("#buildtime").val();
		var companytype = $("#companytype option:selected").val(); //企业类型
		var cmbProvince = $("#cmbProvince option:selected").val(); //省、市、县
		var cmbCity = $("#cmbCity option:selected").val();
		var cmbArea = $("#cmbArea option:selected").val();
		var companynum = $("#companynum").val();
		var lianxiren = $("#lianxiren").val();
		var lianxidianhua = $("#lianxidianhua").val();
		var weixin = $("#weixin").val();
		var email = $("#email").val();
		var mainfield = $("#mainfield").val(); //主营范围

		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(lianxidianhua)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		}
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(legalphone)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		}

		$.ajax({

			url: yh + "/company/update",
			method: 'POST',
			data: {
				"id": param,
				"companyname": companyname, //公司名称
				"creditcode": creditcode,
				"legalperson": legalperson, //法人
				"legaltelphone": legalphone,
				"stockmessage": stockmessage,
				"fund": zhucezijin, //资金
				"buildtime": buildtime,
				"companytype": companytype, //企业类型
				"province": cmbProvince,
				"city": cmbCity,
				"coutry": cmbArea,
				"companynum": companynum,
				"contactperson": lianxiren,
				"telphone": lianxidianhua,
				"weixin": weixin,
				"email": email,
				"mainfield": mainfield
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("编辑客户信息成功!");
				window.location.href = "authenbasiclist.html"

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("编辑客户信息失败!");

			}

		});

	});

});