var id;
var param;
var edituseticketapp = angular.module('edituseticket', ['CommonApp']);
edituseticketapp.controller('edituseticketcontroller', function($scope, $http, URLParam) {
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
			url: yh + "/useticket/" + param,
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
					$('#qiyeming').val($scope.dataRoot.companyname); //企业名
					$('#companyaddress').val($scope.dataRoot.companyaddress); //企业法人
					$('#companyperson').val($scope.dataRoot.companyperson);
					$('#companytel').val($scope.dataRoot.companyphone);
					$('#email').val($scope.dataRoot.companyemail);

					if($scope.dataRoot.applicationdate != null) {
						var shenqingriqi = getMyDate($scope.dataRoot.applicationdate);
						$('#shenqingriqi').val(shenqingriqi); //创新券申请日期
					}
					$('#innovationmoney').val($scope.dataRoot.innovationmoney);
					$('#innovationpurpose').val($scope.dataRoot.innovationpurpose);
					$('#projectname').val($scope.dataRoot.projectname);
					$('#contactmoney').val($scope.dataRoot.contractvalue);
					$('#usemoney').val($scope.dataRoot.usevalue);
					$('#cash option:selected').text($scope.dataRoot.cash);

					$('#actualcash').val($scope.dataRoot.actualcashmoney); //实际兑付金额

					if($scope.dataRoot.cashdate != null) {
						var cashdate = getMyDate($scope.dataRoot.cashdate);
						$('#cashdate').val(cashdate); //创新券申请日期
					}
					$('#check').val($scope.dataRoot.checkcondition);

				}

			}

		});

	});

	$('.newkh').click(function() {
		var qiyeming = $('#qiyeming').val(); //企业名称
		var companyaddress = $("#companyaddress").val();
		var companyperson = $("#companyperson").val();
		var companytel = $("#companytel").val();
		var email = $("#email").val();
		var shenqingriqi = $("#shenqingriqi").val();
		var innovationmoney = $("#innovationmoney").val(); //创新券金额       
		var innovationpurpose = $("#innovationpurpose").val(); //创新券用途       
		var projectname = $("#projectname").val(); //服务项目名称 		
		var contactmoney = $("#contactmoney").val(); //合同金额
		var usemoney = $("#usemoney").val(); //创新券使用金额      
		var cash = $("#cash option:selected").val(); //是否兑付
		console.log("看看有没有值:" + cash);
		var actualcash = $("#actualcash").val(); //实际兑付
		var cashdate = $("#cashdate").val(); //兑付日期
		var check = $("#check").val(); //抽查情况       

		$.ajax({

			url: yh + "/useticket/update",
			method: 'POST',
			data: {
				"id": param,
				"companyname": qiyeming, //公司名称
				"companyaddress": companyaddress, //注册时间
				"companyperson": companyperson, //法人
				"companyphone": companytel, //资金
				"companyemail": email, //注册类型
				"applicationdate": shenqingriqi,
				"innovationmoney": innovationmoney,
				"innovationpurpose": innovationpurpose,
				"projectname": projectname,
				"contractvalue": contactmoney,
				"usevalue": usemoney,
				"cash": cash,
				"actualcashmoney": actualcash,
				"cashdate": cashdate,
				"checkcondition": check
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("编辑客户信息成功!");
				window.location.href = "useticketlist.html"

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("编辑客户信息失败!");

			}

		});

	});

});