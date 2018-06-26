var id;
var skillallapp = angular.module('skillall', ['CommonApp']);
skillallapp.controller('skillallcontroller', function($scope, $http, URLParam) {
	param = URLParam.getParams();
	id = param.id;
	console.log("id:" + id);

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
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
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
				$("#fund").val($scope.dataRoot.fund);
				if($scope.dataRoot.buildtime != null) {
					var buildtime = getMyDate($scope.dataRoot.buildtime);
					$('#buildtime').val(buildtime); //注册时间
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

				$("#lianxiren").val($scope.dataRoot.contactperson);
				$("#lianxidianhua").val($scope.dataRoot.telphone);
				$("#mainfield").val($scope.dataRoot.mainfield);

				//公司性质           
				var radios = $("input[name='optionsRadios']");
				var companyNature = $scope.dataRoot.companyNature;
				$.each(radios, function(index, item) {
					if(item.value == companyNature) {
						$(item).parents(".radio").find("span").addClass('checked');

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