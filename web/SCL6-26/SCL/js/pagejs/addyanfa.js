var addyanfaapp = angular.module('addyanfa', ['CommonApp']);
addyanfaapp.controller('addyanfacontroller', function($scope, $http, URLParam) {
	$scope.size = 0;
	$scope.s2 = 0;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');

	});

	//验证控件
	$(document).ready(function() {
		$('#defaultForm').bootstrapValidator({
			message: '这些信息无效',
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				platname: {
					validators: {
						notEmpty: {
							message: '研发平台名称不能为空！',
						}
					}
				},
				companyname: {
					validators: {
						notEmpty: {
							message: '项目承担单位不能为空！'
						}
					}
				},
				totalinvest: {
					validators: {
						notEmpty: {
							message: '近三年研发总投入不能为空！'
						}
					}
				},
				totalbenefit: {
					validators: {
						notEmpty: {
							message: '近三年产生的效益不能为空！'
						}
					}
				},
				projectnum: {
					validators: {
						notEmpty: {
							message: '近三年承担项目总量不能为空！'
						}
					}
				},
				transnum: {
					validators: {
						notEmpty: {
							message: '近三年成果转化数量不能为空！'
						}
					}
				},
				patentnum: {
					validators: {
						notEmpty: {
							message: '企业拥有专利数不能为空！'
						}
					}
				},
				inventpatent: {
					validators: {
						notEmpty: {
							message: '发明专利不能为空！'
						}
					}
				},
				teamperson: {
					validators: {
						notEmpty: {
							message: '研发团队人员不能为空！'
						}
					}
				},
				thousplanname: {
					validators: {
						notEmpty: {
							message: '千人计划姓名不能为空！'
						}
					}
				},
				academicianname: {
					validators: {
						notEmpty: {
							message: '院士姓名不能为空！'
						}
					}
				},
				sitearea: {
					validators: {
						notEmpty: {
							message: '研发场地面积不能为空！'
						}
					}
				},
				growperson: {
					validators: {
						notEmpty: {
							message: '引进培养人才不能为空！'
						}
					}
				},
				devicenum: {
					validators: {
						notEmpty: {
							message: '研发设备数量不能为空！'
						}
					}
				},
				devicevalue: {
					validators: {
						notEmpty: {
							message: '研发设备原值不能为空！'
						}
					}
				},
				keyskill: {
					validators: {
						notEmpty: {
							message: '关键突破技术不能为空！'
						}
					}
				},
				interstandard: {
					validators: {
						notEmpty: {
							message: '国际标准不能为空！'
						}
					}
				},
				nationstandard: {
					validators: {
						notEmpty: {
							message: '国家标准不能为空！'
						}
					}
				},
				industrystandard: {
					validators: {
						notEmpty: {
							message: '行业标准不能为空！'
						}
					}
				}

			}
		});

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

	$("#inventpatent").blur(function() {
		var inventpatent = $("#inventpatent").val(); //发明专利
		var patentnum = $("#patentnum").val(); //企业拥有专利数
		if(inventpatent > patentnum) {
			$('.fm').css('display', 'block');
			$('.fm').html("发明专利不能大于专利数！");
		} else {
			$('.fm').css('display', 'none');
		}

	});

	$('.yanfasave').click(function() {
		$('#defaultForm').bootstrapValidator('validate');
		if(!$('#defaultForm').data('bootstrapValidator').isValid()) {
			//没有通过验证
			$('.ts').css('display', 'block');
			$('.ts').html("请完善研发平台信息后提交！");

		} else {

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
			var sitearea = $('#sitearea').val();
			var growperson = $('#growperson').val();
			var devicenum = $('#devicenum').val();
			var devicevalue = $('#devicevalue').val();
			var keyskill = $('#keyskill').val();
			var interstandard = $('#interstandard').val();
			var nationstandard = $('#nationstandard').val();
			var industrystandard = $('#industrystandard').val();
//			var companytype = $("#companytype option:selected").val();
          
			
			//客户新增字段
			var thousplannum = $("#thousplannum option:selected").val();  //千人计划人数
			var thousandpplanname = $('#thousandpplanname').val();  //千人计划姓名
			var thousandpplanname2 = $('#thousandpplanname2').val(); 
			var thousandpplanname3 = $('#thousandpplanname3').val(); 
			var thousandpplanname4 = $('#thousandpplanname4').val(); 
			var thousandpplanname5 = $('#thousandpplanname5').val(); 
			var academiciannum = $("#academiciannum option:selected").val(); //院士人数
			var academicianname = $('#academicianname').val();  //院士姓名
			var academicianname2 = $('#academicianname2').val();
			var academicianname3= $('#academicianname3').val();
			var academicianname4 = $('#academicianname4').val();
			var academicianname5 = $('#academicianname5').val();
			var companytypes = $('#companytypes').val();
			//去掉拼接字符串后面的逗号
			var companynewtypes = companytypes.substring(0,companytypes.length-1); //拼接后企业类型
			var detailaddress = $('#detailaddress').val();  //详细地址			
			
			$('.ts').html("");
			$.ajax({
				url: yh + "/companyresearch/add",
				crossDomain: true,
				xhrFields: {
					withCredentials: true
				},
				method: 'POST',
				data: {
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
					"reasearchSitearea": sitearea,
					"introducePersontrain": growperson,
					"deviceNum": devicenum,
					"deviceValue": devicevalue,
					"keyTechnical": keyskill,
					"internalStandard": interstandard,
					"nationalStandard": nationstandard,
					"industrialStandard": industrystandard,
					"thousandpplannum": thousplannum,
					"thousandpplanname":thousandpplanname,
					"thousandpplanname2":thousandpplanname2,
					"thousandpplanname3":thousandpplanname3,
					"thousandpplanname4":thousandpplanname4,
					"thousandpplanname5":thousandpplanname5,
					"academiciannum":academiciannum,
					"academicianname":academicianname,
					"academicianname2":academicianname2,
					"academicianname3":academicianname3,
					"academicianname4":academicianname4,
					"academicianname5":academicianname5,
					"companyKindname": companynewtypes,
					"detailaddress":detailaddress
				}

			}).success(function(response) {

				if(response.success == true) {
					$('.ts').css('display', 'block');
					$('.ts').html("新增调研平台信息成功！");
					window.location.href = "yanfa.html";

				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("新增调研平台信息失败！");

				}

			});

		}

	});

	//重置 
	$('.reset').click(function() {
		$('#defaultForm').data('bootstrapValidator').resetForm(true);
		$('.ts').html("");
	});

	//控制千人计划显示
	$scope.disableInput = function(index) {
		if(index > $scope.size) {
			return true;
		} else {
			return false;
		}
	}
	//控制院士人数
	$scope.disableInput2 = function(index) {
		if(index > $scope.s2) {
			return true;
		} else {
			return false;
		}
	}
	//多次选择下拉框的值放入文本框,传入后台的时候,去掉最后一个字符
	var newVal = '';
	$("#companytype").change(function() {
		newVal += $("#companytype>option:selected").val() + ',';
		$("#companytypes").val(newVal);
		
	});
});