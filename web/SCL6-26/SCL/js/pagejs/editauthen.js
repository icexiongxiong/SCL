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

	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/authen/" + param,
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
					$('#companyname').val($scope.dataRoot.companyname);
					$('#address').val($scope.dataRoot.address);
					$('#authproject').val($scope.dataRoot.certifyprogram); //认证项目
					$('#authentgist').val($scope.dataRoot.certifygist); //认证依据					
					$('#certificatenumber').val($scope.dataRoot.certificatenumber); //证书编号
					$('#certificatescope').val($scope.dataRoot.certifyscope);
					$('#organizationname').val($scope.dataRoot.certifyorganname);
					if($scope.dataRoot.issuedate != null) {
						var issuedate = getMyDate($scope.dataRoot.issuedate);
						//颁证日期						　
						$('#awarddate').val(issuedate);
					}
					if($scope.dataRoot.firstgettime != null) {
						var firstgettime = getMyDate($scope.dataRoot.firstgettime);
						$('#firstdate').val(firstgettime);
					}
					if($scope.dataRoot.changetime != null) {
						var changetime = getMyDate($scope.dataRoot.changetime);
						$('#changedate').val(changetime);
					}
					$('#state').val($scope.dataRoot.state);

				}

			}

		});

	});

	$('.editkh').click(function() {
		var companyname = $('#companyname').val(); //企业名称
		var address = $("#address").val();
		var authproject = $("#authproject").val(); //企业法人
		var authentgist = $("#authentgist").val(); //注册资金
		var certificatenumber = $("#certificatenumber").val();
		var certificatescope = $("#certificatescope").val();
		var organizationname = $("#organizationname").val();
		var awarddate = $("#awarddate").val();
		var firstdate = $("#firstdate").val();
		var changedate = $("#changedate").val();
		var state = $("#state").val();

		$.ajax({

			url: yh + "/authen/update",
			method: 'POST',
			data: {
				"id": param,
				"companyname": companyname,
				"address": address,
				"certifyprogram": authproject,
				"certifygist": authentgist,
				"certificatenumber": certificatenumber,
				"certifyscope": certificatescope,
				"certifyorganname": organizationname,
				"issuedate": awarddate,
				"firstgettime": firstdate,
				"changetime": changedate,
				"state": state
			},
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

		}).success(function(response) {
			if(response.success == true) {
				$('.ts').css('display', 'block');
				//                $('.ts').html("编辑客户信息成功!");
				window.location.href = "authenlist.html"

			} else if(response.success == false) {
				$('.ts').css('display', 'block');
				$('.ts').html("编辑信息失败!");

			}

		});

	});

});