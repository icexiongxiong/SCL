var kehuxiangqingapp = angular.module('kehuxiangqing', ['CommonApp']);
kehuxiangqingapp.controller('kehuxiangqingcontroller', function($scope, $http, URLParam) {
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
				companyname: {
					validators: {
						notEmpty: {
							message: '企业名称不能为空！',

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
							message: '研发总投入不能为空！'
						}
					}
				},
				preinvest: {
					validators: {
						notEmpty: {
							message: '上年度研发投入不能为空！'
						}
					}
				},
				presales: {
					validators: {
						notEmpty: {
							message: '上年度销售收入不能为空！'
						}
					}
				},
				newsales: {
					validators: {
						notEmpty: {
							message: '新增销售收入不能为空！'
						}
					}
				},
				projectnum: {
					validators: {
						notEmpty: {
							message: '承担项目总量不能为空！'
						}
					}
				},
				yearprojectnum: {
					validators: {
						notEmpty: {
							message: '近一年承担项目数量不能为空！'
						}
					}
				},
				transnum: {
					validators: {
						notEmpty: {
							message: '成果转化数量不能为空！'
						}
					}
				},
				yeartransnum: {
					validators: {
						notEmpty: {
							message: '近一年成果转化数量不能为空！'
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
				yaarnewpatent: {
					validators: {
						notEmpty: {
							message: '近一年新增专利不能为空！'
						}
					}
				},
				newinvent: {
					validators: {
						notEmpty: {
							message: '新增发明专利不能为空！'
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
				thousplan1: {
					validators: {
						notEmpty: {
							message: '千人计划不能为空！'
						}
					}
				},
				academician1: {
					validators: {
						notEmpty: {
							message: '院士不能为空！'
						}
					}
				},
				newteamperson: {
					validators: {
						notEmpty: {
							message: '近一年团队新增人员不能为空！'
						}
					}
				},
				thousplan2: {
					validators: {
						notEmpty: {
							message: '千人计划不能为空！'
						}
					}
				},
				academician2: {
					validators: {
						notEmpty: {
							message: '院士不能为空！'
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

				newarea: {
					validators: {
						notEmpty: {
							message: '近一年新增面积不能为空！'
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
				yeargrowperson: {
					validators: {
						notEmpty: {
							message: '近一年引进培养人才不能为空！'
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
				newdevicenum: {
					validators: {
						notEmpty: {
							message: '新增研发设备数量不能为空！'
						}
					}
				},
				newdevicevalue: {
					validators: {
						notEmpty: {
							message: '新增研发设备原值不能为空！'
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

		// Validate the form manually

	});

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

	$('.yanfasave').click(function() {
		$('#defaultForm').bootstrapValidator('validate');
		// 	   $('#defaultForm').data('bootstrapValidator').validate();  
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
			　　　　　
			var platkind = $("#platkind option:selected").val(); //平台类别        

			var totalinvest = $('#totalinvest').val();
			var preinvest = $('#preinvest').val();
			var presales = $('#presales').val();
			var newsales = $('#newsales').val();
			var projectnum = $('#projectnum').val();
			var yearprojectnum = $('#yearprojectnum').val();
			var transnum = $('#transnum').val();
			var yeartransnum = $('#yeartransnum').val();
			var patentnum = $('#patentnum').val();
			var inventpatent = $('#inventpatent').val();
			var yaarnewpatent = $('#yaarnewpatent').val();
			var newinvent = $('#newinvent').val();
			var teamperson = $('#teamperson').val();
			var thousplan1 = $('#thousplan1').val();
			var academician1 = $('#academician1').val();
			var newteamperson = $('#newteamperson').val();
			var thousplan2 = $('#thousplan2').val();
			var academician2 = $('#academician2').val();
			var sitearea = $('#sitearea').val();
			var newarea = $('#newarea').val();
			var growperson = $('#growperson').val();
			var yeargrowperson = $('#yeargrowperson').val();
			var devicenum = $('#devicenum').val();
			var devicevalue = $('#devicevalue').val();
			var newdevicenum = $('#newdevicenum').val();
			var newdevicevalue = $('#newdevicevalue').val();
			var keyskill = $('#keyskill').val();
			var interstandard = $('#interstandard').val();
			var nationstandard = $('#nationstandard').val();
			var industrystandard = $('#industrystandard').val();
			var companytype = $("#companytype option:selected").val();
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
					"researchTotalincome": totalinvest,
					"preyearResearchinput": preinvest,
					"preyearSales": presales,
					"newSales": newsales,
					"projectGross": projectnum,
					"projectnum": yearprojectnum,
					"resultTransfornum": transnum,
					"yearResulttransfornum": yeartransnum,
					"patentNum": patentnum,
					"inventPatent": inventpatent,
					"newPatent": yaarnewpatent,
					"newInventpatent": newinvent,
					"researchgroupPerson": teamperson,
					"thousandPlan1": thousplan1,
					"academician1": academician1,
					"teamNewperson": newteamperson,
					"thousandPlan2": thousplan2,
					"academician2": academician2,
					"reasearchSitearea": sitearea,
					"newArea": newarea,
					"introducePersontrain": growperson,
					"yearintroducePersontrain": yeargrowperson,
					"deviceNum": devicenum,
					"deviceValue": devicevalue,
					"newDevicenum": newdevicenum,
					"newDevicevalue": newdevicevalue,
					"keyTechnical": keyskill,
					"internalStandard": interstandard,
					"nationalStandard": nationstandard,
					"industrialStandard": industrystandard,
					"companyKindname": companytype
				}

			}).success(function(response) {

				if(response.success == true) {
					$('.ts').css('display', 'block');
					$('.ts').html("新增调研平台信息成功！");
					//                  window.location.href="yanfa.html";

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

});