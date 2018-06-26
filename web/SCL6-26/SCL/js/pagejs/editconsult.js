var id;
var param;
var editconsultapp = angular.module('editconsult', ['CommonApp']);
editconsultapp.controller('editconsultcontroller', function($scope, $http, URLParam) {
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

	//绑定注册类型
	$(function() {
		$('#companytype').empty();
		$("#companytype").append("<option value='高新技术企业'>高新技术企业</option><option value='小巨人企业'>小巨人企业</option><option value='科技型中小企业'>科技型中小企业</option><option value='创新基金企业'>创新基金企业</option>");
	});
	//绑定企业领域

	$(function() {
		$.ajax({
			url: yh + "/companyfield/page",
			method: 'POST',
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			data: {
				start: 0,
				limit: 200
			},

		}).success(function(data) {
			if(data.success == true) {

				var ghtml = "";
				$.each(data.root, function(i, items) {

					if(items["fieldParandid"] == "0") {
						//一级菜单循环
						ghtml += "<optgroup  id='" + items["fieldId"] + "' label='" + items["fieldName"] + "'>";

						//二级菜单循环开始  
						//          ghtml+="<optgroup>"; 
						$.each(data.root, function(i, sitems) {
							if(sitems["fieldParandid"] == items["fieldId"]) {
								ghtml += "<option id='" + sitems["fieldId"] + "' style='margin-left:20px'><i class='icon-file-alt'></i>" + sitems["fieldName"] + "";

								//三级菜单循环     
								//           ghtml += "<option>";
								$.each(data.root, function(i, ssitems) {
									if(ssitems["fieldParandid"] == sitems["fieldId"]) {

										ghtml += "<option id='" + ssitems["fieldId"] + "' style='margin-left:40px'><i class='icon-file-alt'></i>" + ssitems["fieldName"] + "</option>";

									}

								});
								ghtml += "</option>";

							}

						});
						ghtml += "</optgroup>";

					}

				});

				$("#lingyu").append(ghtml);
				$("#lingyu").chosen();

			}

		});

	});


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
					$('#legalperson').val($scope.dataRoot.legalperson); //企业法人
					$('#creditcode').val($scope.dataRoot.creditcode); //信用代码
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

					$('#lianxiren').val($scope.dataRoot.contactperson);
					$('#lianxidianhua').val($scope.dataRoot.telphone);
					$('#mainfield').val($scope.dataRoot.mainfield); //主营范围
					//加的字段的绑定信息
					$('#detailaddress').val($scope.dataRoot.detailaddress);  //详细地址
					$('#industrykind option:selected').text($scope.dataRoot.industrykind); //所属行业
					$('#lingyu option:selected').text($scope.dataRoot.lingyu); 
					

				}

			}

		});

	});

	$('.newkh').click(function() {
		var companyname = $('#companyname').val(); //企业名称
		var creditcode = $("#creditcode").val(); //企业信用代码
		var legalperson = $("#legalperson").val(); //企业法人
		var zhucezijin = $("#zhucezijin").val(); //注册资金
		　　　　　
		var buildtime = $("#buildtime").val();
		var companytype = $("#companytype option:selected").text();

		var cmbProvince = $("#cmbProvince option:selected").val();
		var cmbCity = $("#cmbCity option:selected").val();
		var cmbArea = $("#cmbArea option:selected").val();
		var lianxiren = $("#lianxiren").val();
		var lianxidianhua = $("#lianxidianhua").val();
		var mainfield = $("#mainfield").val();

		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(lianxidianhua)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		}

		$.ajax({

			url: yh + "/company/update",
			method: 'POST',
			data: {
				"id": param,
				"companyname": companyname,
				"creditcode": creditcode,
				"legalperson": legalperson,
				"fund": zhucezijin,
				"buildtime": buildtime,
				"companytype": companytype,
				"province": cmbProvince,
				"city": cmbCity,
				"coutry": cmbArea,
				"contactperson": lianxiren,
				"telphone": lianxidianhua,
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
				window.location.href = "consultlist.html"

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("编辑客户信息失败!");

			}

		});

	});

});