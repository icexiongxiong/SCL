var id;
var projectallapp = angular.module('projectall', ['CommonApp']);
projectallapp.controller('projectallcontroller', function($scope, $http, URLParam) {
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
				$("#legalphone").val($scope.dataRoot.legaltelphone);
				$("#stockmessage").val($scope.dataRoot.stockmessage);
				$("#fund").val($scope.dataRoot.fund);
				if($scope.dataRoot.buildtime != null) {
					var buildtime = getMyDate($scope.dataRoot.buildtime);
					$('#settime').val(buildtime); //注册时间
				}
				$('#companytype option:selected').text($scope.dataRoot.companytype); //公司类型
				//省、市、县
				var cmbProvince = $scope.dataRoot.province;
				var cmbCity = $scope.dataRoot.city;
				var cmbArea = $scope.dataRoot.county;

				$("#cmbProvince option[value='" + cmbProvince + "']").attr("selected", true);
				$("#cmbCity option[value='" + cmbCity + "']").attr("selected", true);
				$("#cmbArea option[value='" + cmbArea + "']").attr("selected", true);				
				$("#companynum").val($scope.dataRoot.companynum);
				
				$("#linkman").val($scope.dataRoot.contactperson);
				$("#contactway").val($scope.dataRoot.telphone);
				$("#weixin").val($scope.dataRoot.weixin);
				$("#email").val($scope.dataRoot.email);
				$("#mainfield").val($scope.dataRoot.mainfield);
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

				//中心服务项目
				var centralservers = $("input[name='centralserver']");
				var serverKindValues = $scope.dataRoot.serverKindList;
				$.each(centralservers, function(index, item) {
					for(var j = 0; serverKindValues.length > j; j++) {
						if(item.value == serverKindValues[j].serverName) {
							$(item).parents(".checker").find("span").addClass('checked');
							$(this).parents("label").find("input[type=text]").eq(0).val(serverKindValues[j].serverPerson);
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