var addconsultapp = angular.module('addconsult', ['CommonApp']);
addconsultapp.controller('addconsultcontroller', function($scope, $http, URLParam) {
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});
	//绑定技术领域  
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
	//验证控件，加载公司客户
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
				creditcode: {
					validators: {
						notEmpty: {
							message: '企业信用代码不能为空！'
						}
					}
				},
				legalperson: {
					validators: {
						notEmpty: {
							message: '企业法人不能为空！'
						}
					}
				},
				zhucezijin: {
					validators: {
						notEmpty: {
							message: '注册资金不能为空！'
						}
					}
				},
				buildtime: {
					validators: {
						notEmpty: {
							message: '成立时间不能为空！'
						}
					}
				},
				lianxiren: {
					validators: {
						notEmpty: {
							message: '联系人不能为空！'
						}
					}
				},
				lianxidianhua: {
					message: '这是一个无效的电话号',
					validators: {
						notEmpty: {
							message: '联系电话不能为空！'
						},
						stringLength: {
							min: 11,
							max: 11,
							message: '请输入11位手机号码'
						},
						regexp: {
							regexp: /^1[3|5|8]{1}[0-9]{9}$/,
							message: '请输入正确的手机号码'
						}
					}
				},
				mainfield: {
					validators: {
						notEmpty: {
							message: '主营范围不有为空!'
						}
					}
				}

			}
		});

		// Validate the form manually

		$http({
			url: yh + "/company/combo",
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded'
			},
			method: 'GET',
			withCredentials: true,
			cache: false,
			dataType: "json",

		}).success(function(response) {
			if(response.success == true) {
				$scope.companys = response.root;
			}
		});

	});

	$('.addqiye').click(function() {
		$('#defaultForm').bootstrapValidator('validate');
		//     $('#defaultForm').data('bootstrapValidator').validate();  
		if(!$('#defaultForm').data('bootstrapValidator').isValid()) {
			//没有通过验证
			$('.ts').css('display', 'block');
			$('.ts').html("请完善企业信息后提交！");

		} else {
			var companyname = $('#companyname').val(); //企业名称
			var creditcode = $("#creditcode").val();
			var legalperson = $("#legalperson").val(); //企业法人
			var zhucezijin = $("#zhucezijin").val(); //注册资金
			var buildtime = $("#buildtime").val();
			var companytype = $("#companytype option:selected").val(); //企业类型
			var cmbProvince = $("#cmbProvince option:selected").val(); //省、市、县
			var cmbCity = $("#cmbCity option:selected").val();
			var cmbArea = $("#cmbArea option:selected").val();
			var lianxiren = $("#lianxiren").val();
			var lianxidianhua = $("#lianxidianhua").val();
			var mainfield = $("#mainfield").val(); //主营范围			
			//后面加的字段			
			var detailaddress=$("#detailaddress").val(); //详情细地址
			var whetherpublic = $("#whetherpublic option:selected").val(); //企业是否上市
			var publiccode = $("#publiccode").val();//上市代码
			var publictime = $("#publictime").val(); //上市时间
			var publictype = $("#publictype").val();  //上市类型
			var stockcode = $("#stockcode").val();  //股票代码			
			var industrykind = $("#industrykind").val(); //所属行业			
			var staffnum = $("#staffnum").val();   //职工人数
			var sciencestaffnum = $("#sciencestaffnum").val(); //科技人员人数
			
			$('.ts').css('display', 'none');

			$.ajax({

				url: yh + "/company/add",
				crossDomain: true,
				xhrFields: {
					withCredentials: true
				},
				method: 'POST',
				data: {
					"companyname": companyname, //公司名称
					"creditcode": creditcode,
					"legalperson": legalperson, //法人
					"fund": zhucezijin, //资金
					"buildtime": buildtime,
					"companytype": companytype, //企业类型
					"province": cmbProvince,
					"city": cmbCity,
					"coutry": cmbArea,
					"contactperson": lianxiren,
					"telphone": lianxidianhua,
					"mainfield": mainfield,
					"detailaddress":detailaddress,
					"whetherpublic":whetherpublic,
					"publiccode":publiccode,
					"publictime":publictime,
					"publictype":publictype,
					"stockcode":stockcode,
					"industrykind":industrykind,
					"staffnum":staffnum,
					"sciencestaffnum":sciencestaffnum
				}

			}).success(function(response) {

				//请求成功
				if(response.success == true) {

					$('.ts').css('display', 'block');
					$('.ts').html("新增客户成功！");
					//                  window.location.href="kehulist.html";

				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("新增客户失败！");

				}

			});

		}
	});

	//重置 
	$('.reset').click(function() {
		$('#defaultForm').data('bootstrapValidator').resetForm(true);
	});

	//是否显示上市选项    
	$("#whetherpublic").change(function() {
		var whethervalue = $("#whetherpublic option:selected").val();
		if(whethervalue == "是") {
			$("#publiccode").removeAttr('disabled');
			$("#publictime").removeAttr('disabled');
			$("#publictype").removeAttr('disabled');
			$("#stockcode").removeAttr('disabled');
		} else if(whethervalue == "否") {
			$("#publiccode").attr('disabled', 'disabled');
			$("#publictime").attr('disabled', 'disabled');
			$("#publictype").attr('disabled', 'disabled');
			$("#stockcode").attr('disabled', 'disabled');
			$("#publiccode").val("");
			$("#publictime").val("");
			$("#publictype").val("");
			$("#stockcode").val("");

		} else {
			$("#publiccode").attr('disabled', 'disabled');
			$("#publictime").attr('disabled', 'disabled');
			$("#publictype").attr('disabled', 'disabled');
			$("#stockcode").attr('disabled', 'disabled');
		}

	})

});