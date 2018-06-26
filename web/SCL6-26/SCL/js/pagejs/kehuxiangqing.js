var detail_id;
var phone = /^1[34578]\d{9}$/;
var kehuxiangqingapp = angular.module('kehuxiangqing', ['CommonApp']);
kehuxiangqingapp.controller('kehuxiangqingcontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	param = id.id;
	$scope.researchyear = undefined;
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

	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$http({
			url: yh + "/companydetail/" + param,
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
				$scope.detailid = $scope.dataRoot.id;
				detail_id = $scope.detailid;
				$('#companyname').val($scope.dataRoot.companyName); //企业名称
				$('#creditcode').val($scope.dataRoot.creditCode); //信用代码
				$('#legalperson').val($scope.dataRoot.legalPerson);
				$('#legalphone').val($scope.dataRoot.legalContact);
				$('#stockmessage').val($scope.dataRoot.stockeMessage);
				$('#fund').val($scope.dataRoot.fund);
				$('#settime').val($scope.dataRoot.buildtime); //成立时间
				//企业类型
				var companyType = $scope.dataRoot.companyType;

				$("#companytype option[value='" + companyType + "']").attr("selected", true);

				//省、市、县
				var cmbProvince = $scope.dataRoot.province;
				var cmbCity = $scope.dataRoot.city;
				var cmbArea = $scope.dataRoot.county;

				$("#cmbProvince option[value='" + cmbProvince + "']").attr("selected", true);
				$("#cmbCity option[value='" + cmbCity + "']").attr("selected", true);
				$("#cmbArea option[value='" + cmbArea + "']").attr("selected", true);

				$('#companynum').val($scope.dataRoot.companyNum); //企业人数
				$('#linkman').val($scope.dataRoot.contactPerson); //联系人
				$('#contactway').val($scope.dataRoot.telphone); //联系方式
				$('#weixin').val($scope.dataRoot.weixin);
				$('#email').val($scope.dataRoot.companyEmail);
				$('#product').val($scope.dataRoot.productField); //领域产品

				$('#commonbrand').val($scope.dataRoot.commonBrand); //普通商标
				$("#softcopyright").val($scope.dataRoot.softwareCopyright); //软件著作权
				$("#famousbrand").val($scope.dataRoot.famousBrand);
				$("#resoundbrand").val($scope.dataRoot.resoundBrand);
				$("#inventpatent").val($scope.dataRoot.invention);
				$("#newpatent").val($scope.dataRoot.utilityModel); //实用新型专利
				$("#appearpatent").val($scope.dataRoot.appearModel);
				$("#otherpatent").val($scope.dataRoot.otherModel);

				//公司性质           
				var radios = $("input[name='optionsRadios']");
				var companyNature = $scope.dataRoot.companyNature;
				$.each(radios, function(index, item) {
					if(item.value == companyNature) {
						$(item).parents(".radio").find("span").addClass('checked');

					}

				})

				//公司类型 
				var companykinds = $("input[name='gongsileixing']");
				var companyKindValues = $scope.dataRoot.companyKindList;
				//页面上复选框的数量
				var companykindslen = companykinds.length;
				//返回值数量
				var companyKindValueslen = companyKindValues.length;

				$.each(companykinds, function(index, item) {
					for(var j = 0; companyKindValues.length > j; j++) {
						if(item.value == companyKindValues[j].kindName) {
							$(item).parents(".checker").find("span").addClass('checked');
							var identifyYear = getMyDate(companyKindValues[j].identifyYear);
							$(this).parents("label").find("input[type=text]").val(identifyYear);
						}
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

				//承担项目类型

				var projectkinds = $("input[name='projectkind']");
				var projectKindValues = $scope.dataRoot.projectKindList;
				$.each(projectkinds, function(index, item) {
					for(var j = 0; projectKindValues.length > j; j++) {
						if(item.value == projectKindValues[j].projectName) {
							$(item).parents(".checker").find("span").addClass('checked');
							$(this).parents("label").find("input[type=text]").eq(0).val(getMyDate(projectKindValues[j].declareYear));
							$(this).parents("label").find("input[type=text]").eq(1).val(projectKindValues[j].period);
						}
					}
				})

				//研发人员：年
				if($scope.dataRoot.recordYear != null) {
					var recordYear = getMyDate($scope.dataRoot.recordYear);
					$("#researchyear").val(recordYear);
				}

				$("#researchnum").val($scope.dataRoot.researchNum);
				$("#money").val($scope.dataRoot.researchMoney);
				//合同
				$("#exploitcontract").val($scope.dataRoot.contractExploit);
				$("#transfercontract").val($scope.dataRoot.contractTransfer);
				$("#servecontract").val($scope.dataRoot.contractService);
				$("#consultcontract").val($scope.dataRoot.contractConsult);

				//产学研情况
				var Radios3 = $("input[name='Radios3']");
				var industryStudyResearch = $scope.dataRoot.industryStudyResearch;
				$.each(Radios3, function(index, item) {
					if(item.value == industryStudyResearch) {
						$(item).parents(".radio").find("span").addClass('checked');

					}
					$(this).parents("label").find("input[type=text]").val($scope.dataRoot.operateAgency);

				})

				//金融情况
				var Radios4 = $("input[name='Radios4']");
				var finance = $scope.dataRoot.finance;
				$.each(Radios4, function(index, item) {
					if(item.value == finance) {
						$(item).parents(".radio").find("span").addClass('checked');

					}

				})

				// 质量管理体系
				var managesystems = $("input[name='managesystem']");
				var systemKindValues = $scope.dataRoot.systemKindList;
				$.each(managesystems, function(index, item) {
					for(var j = 0; systemKindValues.length > j; j++) {
						if(item.value == systemKindValues[j].systemName) {
							$(item).parents(".checker").find("span").addClass('checked');
							var periodTime = getMyDate(systemKindValues[j].periodTime);
							$(this).parents("label").find("input[type=text]").val(periodTime);
						}
					}
				})
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
			}

		});
	});
	//手机号验证   
	$("#legalphone").blur(function() {
		var legalphone = $("#legalphone").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(legalphone)) {
			$('.tx').css('display', 'block');
			$('.tx').html("手机格式错误！");
			return false;
		} else {
			$('.tx').css('display', 'none');
		}

	});

	$("#contactway").blur(function() {
		var contactway = $("#contactway").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(contactway)) {
			$('.tj').css('display', 'block');
			$('.tj').html("手机格式错误！");
			return false;
		} else {
			$('.tj').css('display', 'none');
		}

	});
	$("#declaretel").blur(function() {
		var declaretel = $("#declaretel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(declaretel)) {
			$('.fw').css('display', 'block');
			$('.fw').html("手机格式错误！");
			return false;
		} else {
			$('.fw').css('display', 'none');
		}

	});
	$("#propertytel").blur(function() {
		var propertytel = $("#propertytel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(propertytel)) {
			$('.fw').css('display', 'block');
			$('.fw').html("手机格式错误！");
			return false;
		} else {
			$('.fw').css('display', 'none');
		}

	});
	$("#consulttel").blur(function() {
		var consulttel = $("#consulttel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(consulttel)) {
			$('.fw').css('display', 'block');
			$('.fw').html("手机格式错误！");
			return false;
		} else {
			$('.fw').css('display', 'none');
		}

	});
	$("#trainservetel").blur(function() {
		var trainservetel = $("#trainservetel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(trainservetel)) {
			$('.fw').css('display', 'block');
			$('.fw').html("手机格式错误！");
			return false;
		} else {
			$('.fw').css('display', 'none');
		}

	});

	$("#erptel").blur(function() {
		var erptel = $("#erptel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(erptel)) {
			$('.fw').css('display', 'block');
			$('.fw').html("手机格式错误！");
			return false;
		} else {
			$('.fw').css('display', 'none');
		}

	});

	$("#monitortel").blur(function() {
		var monitortel = $("#monitortel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(monitortel)) {
			$('.fw').css('display', 'block');
			$('.fw').html("手机格式错误！");
			return false;
		} else {
			$('.fw').css('display', 'none');
		}

	});

	$("#machineservetel").blur(function() {
		var machineservetel = $("#machineservetel").val();
		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(machineservetel)) {
			$('.fw').css('display', 'block');
			$('.fw').html("手机格式错误！");
			return false;
		} else {
			$('.fw').css('display', 'none');
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
		var id = $scope.detailid;
		var companyname = $('#companyname').val(); //企业名称
		var creditcode = $("#creditcode").val();
		var legalperson = $("#legalperson").val();
		var legalphone = $("#legalphone").val();
		var stockmessage = $("#stockmessage").val();
		var fund = $("#fund").val();
		var settime = $("#settime").val();
		var companytype = $("#companytype option:selected").val();
		var cmbProvince = $("#cmbProvince option:selected").val();
		var cmbCity = $("#cmbCity option:selected").val();
		var cmbArea = $("#cmbArea option:selected").val();
		var companynum = $("#companynum").val();
		var linkman = $("#linkman").val();
		var contackway = $("#contackway").val();
		var weixin = $("#weixin").val();
		var email = $("#email").val();
		var product = $("#product").val();
		var commonbrand = $("#commonbrand").val();
		var softcopyright = $("#softcopyright").val();
		var famousbrand = $("#famousbrand").val();
		var resoundbrand = $("#resoundbrand").val();
		var inventpatent = $("#inventpatent").val();
		var newpatent = $("#newpatent").val();
		var appearpatent = $("#appearpatent").val();
		var otherpatent = $("#otherpatent").val();
		var companyNature = $('input[name="optionsRadios"]:checked').val(); //公司性质单选按钮

		//获取公司类型	
		var ck = "[";
		$.each($('input:checkbox[name="gongsileixing"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {
				var value1 = items.value;
				var ipts = $(this).parents("label").find("input[type=text]")
				var value2 = ipts.val();
				ck += "{\"identifyYear\":\"" + value2 + "\",\"kindName\":\"" + value1 + "\"},";
			}
		});

		ck = ck.substring(0, ck.length - 1);
		ck += "]";

		//获取研发机构类型 :
		var yanfas = [];
		$.each($('input:checkbox[name="yfjg"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {
				yanfas.push(items["value"]);
			}

		});
		var newyanfas = yanfas.toString()

		//承担项目类型：
		var pk = "[";
		$.each($('input:checkbox[name="projectkind"]'), function(i, items) {

			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {
				var value1 = items.value;
				var first = $(this).parents("label").find("input[type=text]").eq(0).val();
				var second = $(this).parents("label").find("input[type=text]").eq(1).val();
				pk += "{\"projectName\":\"" + value1 + "\",\"declareYear\":\"" + first + "\",\"period\":\"" + second + "\"},";
			}
		});

		pk = pk.substring(0, pk.length - 1);
		pk += "]";

		//研发人员
		var researchyear = $("#researchyear").val();
		var researchnum = $("#researchnum").val();
		var money = $("#money").val();

		//四技合同
		var exploitcontract = $("#exploitcontract").val();
		var transfercontract = $("#transfercontract").val();
		var servecontract = $("#servecontract").val();
		var consultcontract = $("#consultcontract").val();

		//产学研情况 
		var radio3value;
		$.each($('input[name="Radios3"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {　　　　
				radio3value = items.value;
			}

		});
		if(radio3value == "是") {
			var agency = $("#agency").val();

		}

		//科技金融  
		var radio4value;
		$.each($('input[name="Radios4"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {　　　　
				radio4value = items.value;
			}

		});

		//管理体系情况

		var tx = "[";
		$.each($('input:checkbox[name="managesystem"]'), function(i, items) {
			var spans = $(this).parent("span").attr("class");
			if(spans == "checked") {
				var txvalue1 = items.value;
				var ipts = $(this).parents("label").find("input[type=text]")
				var txvalue2 = ipts.val();
				tx += "{\"systemName\":\"" + txvalue1 + "\",\"periodTime\":\"" + txvalue2 + "\"},";
			}
		});

		tx = tx.substring(0, tx.length - 1);
		tx += "]";

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

		$http({
			url: yh + "/companydetail/updatedetail",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'POST',
			withCredentials: true,
			cache: false,
			dataType: "json",
			params: {
				"id": id, //公司id
				"companyName": companyname, //公司名称
				"creditCode": creditcode, //信用代码
				"legalPerson": legalperson, //法人
				"legalContact": legalphone, //法人联系方式
				"stockeMessage": stockmessage, //股东信息
				"fund": fund, //资金
				"buildtime": settime, //成立时间
				"companyType": companytype, //企业类型
				"province": cmbProvince, //省
				"city": cmbCity, //市
				"county": cmbArea, //县
				"companyNum": companynum, //企业人数
				"contactPerson": linkman, //联系人
				"companyContact": contackway, //联系方式
				"weixin": weixin,
				"companyEmail": email,
				"productField": product, //产品领域

				"commonBrand": commonbrand, //普通商标
				"softwareCopyright": softcopyright, //软件著作权
				"famousBrand": famousbrand, //著名商标
				"resoundBrand": resoundbrand, //驰名商标
				"invention": inventpatent, //发明专利
				"utilityModel": newpatent, //实用新型专利
				"appearModel": appearpatent, //外观专利
				"otherModel": otherpatent, //其他专利

				"companyNature": companyNature, //公司性质
				"companyKinds": ck, //公司类型
				"researchKind": newyanfas, //研发机构类型
				"projectKinds": pk, //承担项目类型

				"recordYear": researchyear, //研发年
				"researchNum": researchnum, //研发数量
				"researchMoney": money, //研发金额
				"contractExploit": exploitcontract, //技术开发合同
				"contractTransfer": transfercontract, //转让合同
				"contractService": servecontract, //服务合同
				"contractConsult": consultcontract, //咨询合同        	    
				"industryStudyResearch": radio3value, //产学研情况
				"operateAgency": agency, //合作机构        	    

				"finance": radio4value, //金融情况
				"systemKinds": tx, //管理体系情况
				"serverkinds": zx, //中心服务项目
				"instrumentShare": radio5value, //共享情况
				"instrumentName": machinename,
				"instrumentType": machinetype,
				"instrumentMoney": machinemoney

			}
		}).success(function(data) {
			if(data.success == true) {

				$('.ts').css('display', 'block');
				//                  $('.ts').html("更新客户详情成功！");
				window.location.href = "kehulist.html";

			} else {
				$('.ts').css('display', 'block');
				$('.ts').html("更新客户详情失败！");

			}
		});

	});

});