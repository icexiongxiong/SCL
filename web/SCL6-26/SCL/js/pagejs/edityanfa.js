var id;
var param;
var edityanfaapp = angular.module('edityanfa', ['CommonApp']);
edityanfaapp.controller('edityanfacontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	param = id.id;

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

	//绑定企业领域

	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
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

	//绑定平台类别

	$(function() {
		$("#platkind").empty();
		$("#platkind").append("<option value='国家级'>国家级</option><option value='省级'>省级</option><option value='市级'>市级</option>");
	});

	//绑定企业类型

	$(function() {
		$("#companytype").empty();
		$("#companytype").append("<option value='高新技术企业'>高新技术企业</option><option value='小巨人企业'>小巨人企业</option><option value='科技型中小企业'>科技型中小企业</option><option value='创新基金企业'>创新基金企业</option><option value='高新技术企业'>高新技术企业</option><option value='科技型中小企业'>科技型中小企业</option>");
	});

	//绑定所有企业信息
	$(function() {
		$.ajax({
			url: yh + "/companyresearch/" + param,
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
					$('#platname').val($scope.dataRoot.researchName); //研发平台名称
					$('#companyname').val($scope.dataRoot.researchCompany);
					$('#lingyu').val($scope.dataRoot.researchField); //所属领域
					　　　
					$('#cmbProvince').val($scope.dataRoot.province);　　　
					$('#cmbCity').val($scope.dataRoot.city);　　　
					$('#cmbArea').val($scope.dataRoot.county);　　　
					$('#platkind').val($scope.dataRoot.platKind);　
					$('#platClass').val($scope.dataRoot.platClass);　　
					$('#totalinvest').val($scope.dataRoot.researchTotalincome);　
					$('#totalbenefit').val($scope.dataRoot.totalbenefit);　

					$('#projectnum').val($scope.dataRoot.projectGross);

					$('#transnum').val($scope.dataRoot.resultTransfornum);

					$('#patentnum').val($scope.dataRoot.patentNum);
					$('#inventpatent').val($scope.dataRoot.inventPatent);

					$('#teamperson').val($scope.dataRoot.researchgroupPerson);
					$('#thousplannum').val($scope.dataRoot.thousandpplannum);
					$('#thousplanname').val($scope.dataRoot.thousandpplanname);
					$('#academiciannum').val($scope.dataRoot.academiciannum);
					$('#academicianname').val($scope.dataRoot.academicianname);

					$('#sitearea').val($scope.dataRoot.reasearchSitearea);

					$('#growperson').val($scope.dataRoot.introducePersontrain);

					$('#devicenum').val($scope.dataRoot.deviceNum);
					$('#devicevalue').val($scope.dataRoot.deviceValue);

					$('#keyskill').val($scope.dataRoot.keyTechnical);
					$('#interstandard').val($scope.dataRoot.internalStandard);
					$('#nationstandard').val($scope.dataRoot.nationalStandard);
					$('#industrystandard').val($scope.dataRoot.industrialStandard);
					$('#companytype').val($scope.dataRoot.companyKindname);

				}

			}

		});

	});

	$('.yanfasave').click(function() {
		var platname = $('#platname').val(); //研发平台名称
		var companyname = $("#companyname").val();
		var lingyu = $("#lingyu option:selected").val(); //所属领域

		var cmbProvince = $("#cmbProvince option:selected").val(); //省

		var cmbCity = $("#cmbCity option:selected").val(); //市

		var cmbArea = $("#cmbArea option:selected").val(); //县     
		　　　　　
		var platkind = $("#platkind option:selected").val(); //平台级别
		//待客户确认平台类别

		var platClass = $('#platClass').val();
		var totalinvest = $('#totalinvest').val();
		var totalbenefit = $('#totalbenefit').val(); //近三年产生效益			
		var projectnum = $('#projectnum').val(); //近三年承担项目总量
		var transnum = $('#transnum').val();

		var patentnum = $('#patentnum').val();
		var inventpatent = $('#inventpatent').val();

		var teamperson = $('#teamperson').val();

		var thousplannum = $('#thousplannum').val();
		var thousplanname = $('#thousplanname').val();
		var academiciannum = $('#academiciannum').val();
		var academicianname = $('#academicianname').val();

		var sitearea = $('#sitearea').val();

		var growperson = $('#growperson').val();

		var devicenum = $('#devicenum').val();
		var devicevalue = $('#devicevalue').val();

		var keyskill = $('#keyskill').val();
		var interstandard = $('#interstandard').val();
		var nationstandard = $('#nationstandard').val();
		var industrystandard = $('#industrystandard').val();
		var companytype = $("#companytype option:selected").val();
		$('.ts').html("");

		$.ajax({

			url: yh + "/companyresearch/update",
			method: 'POST',
			data: {
				"id": param,
				"researchName": platname,
				"researchCompany": companyname,
				"researchField": lingyu,
				"province": cmbProvince,
				"city": cmbCity,
				"county": cmbArea,
				"platKind": platkind,

				"platClass": platClass,

				"researchTotalincome": totalinvest,
				"totalbenefit": totalbenefit,
				"projectGross": projectnum,
				"resultTransfornum": transnum,
				"patentNum": patentnum,
				"inventPatent": inventpatent,
				"researchgroupPerson": teamperson,
				"thousandpplannum": thousplannum,
				"thousandpplanname": thousplanname,
				"reasearchSitearea": sitearea,
				"introducePersontrain": growperson,
				"deviceNum": devicenum,
				"deviceValue": devicevalue,
				"keyTechnical": keyskill,
				"internalStandard": interstandard,
				"nationalStandard": nationstandard,
				"industrialStandard": industrystandard,
				"companyKindname": companytype
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				window.location.href = "yanfa.html";

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("编辑研发平台信息失败!");

			}

		});

	});

});