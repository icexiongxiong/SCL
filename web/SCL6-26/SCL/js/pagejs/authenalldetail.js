var id;
var authenallapp = angular.module('authenall', ['CommonApp']);
authenallapp.controller('authenallcontroller', function($scope, $http, URLParam) {
	param = URLParam.getParams();
	id = param.id;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

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
	//反向绑定单条记录详情
	$(function() {
		$http({
			url: yh + "/companydetail/query/" + id,
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
				$("#companyname").val($scope.dataRoot.companyname);
				$("#creditcode").val($scope.dataRoot.creditcode);
				$("#legalperson").val($scope.dataRoot.legalperson);
				$("#legalphone").val($scope.dataRoot.legaltelphone); //法人联系方式
				$("#stockmessage").val($scope.dataRoot.stockmessage);
				$("#fund").val($scope.dataRoot.fund);
				if($scope.dataRoot.buildtime != null) {
					var buildtime = getMyDate($scope.dataRoot.buildtime);
					$('#settime').val(buildtime); //注册时间
				}
				$('#companytype option:selected').text($scope.dataRoot.companytype); //公司类型
				//省、市、县
				var cmbProvince = $scope.dataRoot.province;
				console.log(cmbProvince);
				var cmbCity = $scope.dataRoot.city;
				var cmbArea = $scope.dataRoot.county;

				$("#cmbProvince option[value='" + cmbProvince + "']").attr("selected", true);
				$("#cmbCity option[value='" + cmbCity + "']").attr("selected", true);
				$("#cmbArea option[value='" + cmbArea + "']").attr("selected", true);
				$("#companynum").val($scope.dataRoot.companynum); //企业人数

				$("#linkman").val($scope.dataRoot.contactperson);
				$("#contactway").val($scope.dataRoot.telphone);
				$("#weixin").val($scope.dataRoot.weixin); 
				$("#email").val($scope.dataRoot.email); 
				$("#mainfield").val($scope.dataRoot.mainfield);

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

				//科技创新券使用类型
				var innovatetypes = $("input[name='kjcx']");
				var KindsValues = $scope.dataRoot.innovatetype;
				if(KindsValues != null) {
					var KindsValues = KindsValues.split(',');

					$.each(innovatetypes, function(index, item) {
						for(var j = 0; KindsValues.length > j; j++) {

							if(item.value == KindsValues[j]) {

								$(item).parents(".checker").find("span").addClass('checked');

							}
						}

					})
				}
				//申领时间
				if($scope.dataRoot.applytime != null) {
					var applytime = getMyDate($scope.dataRoot.applytime);
					$('#applytime').val(applytime); //注册时间
				}
				//申领金额
				$("#applymoney").val($scope.dataRoot.applymoney);
				$("#cashmoney").val($scope.dataRoot.cashmoney);

				//服务年限
				if($scope.dataRoot.startyear != null) {
					var startyear = getMyDate($scope.dataRoot.startyear);
					$('#startyear').val(startyear); //注册时间
				}

				if($scope.dataRoot.endyear != null) {
					var endyear = getMyDate($scope.dataRoot.endyear);
					$('#endyear').val(endyear); //注册时间
				}

			}

		});
	});

});