var id;
var param;
var editkehuapp = angular.module('editkehu', ['CommonApp']);
editkehuapp.controller('editkehucontroller', function($scope, $http, URLParam) {
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

	//绑定企业规模

	$(function() {
		$('#qiyeguimo').empty();
		$("#qiyeguimo").append("<option value='大型企业'>大型企业</option><option value='暂无标准'>暂无标准</option><option value='中型企业'>中型企业</option><option value='微型企业'>微型企业</option><option value='小型企业'>小型企业</option>");
	});

	//绑定注册类型
	$(function() {
		$('#selectError').empty();
		$("#selectError").append("<option value='高新技术企业'>高新技术企业</option><option value='小巨人企业'>小巨人企业</option><option value='科技型中小企业'>科技型中小企业</option><option value='创新基金企业'>创新基金企业</option>");
	});

	//绑定所有企业信息
	$(function() {
		$.ajax({
			url: yh + "/companydetail/" + param,
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
					$('#qiyeming').val($scope.dataRoot.companyName); //企业名
					if($scope.dataRoot.registerTime != null) {
						var zhuceshijian = getMyDate($scope.dataRoot.registerTime);
						$('#zhuceshijian').val(zhuceshijian); //注册时间
					}
					$('#qiyefaren').val($scope.dataRoot.legalPerson); //企业法人
					$('#zhucezijin').val($scope.dataRoot.fund);
					// 			    $('#selectError').val($scope.dataRoot.parentId);
					$('#selectError option:selected').text($scope.dataRoot.companyType); //注册类型
					$('#qiyeguimo option:selected').text($scope.dataRoot.companyScale); //企业规模
					$('#lingyu option:selected').text($scope.dataRoot.companyField); //领域

					if($scope.dataRoot.growTime != null) {
						var peiyunshijian = getMyDate($scope.dataRoot.growTime);
						//入库培训时间
						　
						$('#peiyunshijian').val(peiyunshijian);
					}

					if($scope.dataRoot.identifyTime != null) {
						var rendingnianfen = getMyDate($scope.dataRoot.identifyTime);

						$('#rendingnianfen').val(rendingnianfen);
					}

					$('#rendingpici').val($scope.dataRoot.identifyBatch);

					$('#jiaoshuijiguan').val($scope.dataRoot.taxOffice);
					$('#quyu').val($scope.dataRoot.region);

					$('#lianxiren').val($scope.dataRoot.contactPerson);
					$('#lianxidianhua').val($scope.dataRoot.telphone);

				}

			}

		});

	});

	$('.newkh').click(function() {
		var qiyeming = $('#qiyeming').val(); //企业名称
		var zhuceshijian = $("#zhuceshijian").val();
		var qiyefaren = $("#qiyefaren").val(); //企业法人
		var zhucezijin = $("#zhucezijin").val(); //注册资金

		var zhuceleixingzhi = $("#selectError option:selected").text(); //取的企业类型值

		var qiyeguimo = $("#qiyeguimo option:selected").val(); //企业规模

		var lingyu = $("#lingyu option:selected").val(); //领域

		var peiyunshijian = $("#peiyunshijian").val();
		var rendingnianfen = $("#rendingnianfen").val();
		var rendingpici = $("#rendingpici").val();
		var jiaoshuijiguan = $("#jiaoshuijiguan").val();
		var quyu = $("#quyu").val();
		var lianxiren = $("#lianxiren").val();
		var lianxidianhua = $("#lianxidianhua").val();

		var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
		if(!reg.test(lianxidianhua)) {
			$('.ts').css('display', 'block');
			$('.ts').html("手机格式错误！");
			return false;
		}

		$.ajax({

			url: yh + "/companydetail/update",
			method: 'POST',
			data: {
				"id": param,
				"companyField": lingyu,
				"companyName": qiyeming,
				"companyScale": qiyeguimo,
				"companyType": zhuceleixingzhi,
				"contactPerson": lianxiren,
				"fund": zhucezijin,
				"growTime": peiyunshijian,
				"identifyBatch": rendingpici,
				"identifyTime": rendingnianfen,
				"legalPerson": qiyefaren,
				"region": quyu,
				"registerTime": zhuceshijian,
				"taxOffice": jiaoshuijiguan,
				"telphone": lianxidianhua,
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("编辑客户信息成功!");
				window.location.href = "kehulist.html"

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("编辑客户信息失败!");

			}

		});

	});

});