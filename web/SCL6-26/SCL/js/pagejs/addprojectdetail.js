var id;
var name;
var addprojectdetailapp = angular.module('addprojectdetail', ['CommonApp']);
addprojectdetailapp.controller('addprojectdetailcontroller', function($scope, $http, URLParam) {
	var param = URLParam.getParams();
	id = param.id;
	name = param.name;
	$scope.companyname = name;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

	//新增客户详情
	$('.kehuxq').click(function() {
		//商标
		var commonbrand = $("#commonbrand").val();
		var softcopyright = $("#softcopyright").val();
		var famousbrand = $("#famousbrand").val();
		var resoundbrand = $("#resoundbrand").val();
		var inventpatent = $("#inventpatent").val();
		var newpatent = $("#newpatent").val();
		var appearpatent = $("#appearpatent").val();
		var otherpatent = $("#otherpatent").val();

		var companyNature = $('input[name="optionsRadios"]:checked').val(); //公司性质单选按钮
		//获取公司类型
		var ck = "[";
		$.each($('input:checkbox[name="gongsileixing"]:checked'), function(i, items) {
			var value1 = items.value;
			var ipts = $(this).parents("label").find("input[type=text]")
			var value2 = ipts.val();
			ck += "{\"identifyYear\":\"" + value2 + "\",\"kindName\":\"" + value1 + "\"},";

		});

		ck = ck.substring(0, ck.length - 1);
		ck += "]";
		console.log("公司类型：" + ck);

		//承担项目类型：
		var pk = "[";
		$.each($('input:checkbox[name="projectkind"]:checked'), function(i, items) {
			var value1 = items.value;
			var first = $(this).parents("label").find("input[type=text]").eq(0).val();
			var second = $(this).parents("label").find("input[type=text]").eq(1).val();

			pk += "{\"projectName\":\"" + value1 + "\",\"declareYear\":\"" + first + "\",\"period\":\"" + second + "\"},";

		});

		pk = pk.substring(0, pk.length - 1);
		pk += "]";

		console.log("承担项目类型：" + pk);

		//研发人员
		var researchyear = $("#researchyear").val();
		var researchnum = $("#researchnum").val();
		var money = $("#money").val();

		//产学研情况
		var radio3value = $('input[name="Radios3"]:checked').val();
		if(radio3value == "是") {
			var agency = $("#agency").val();

		}

		//中心服务项目
		var zx = "[";
		$.each($('input:checkbox[name="centralserver"]:checked'), function(i, items) {
			var value1 = items.value;
			var first = $(this).parents("label").find("input[type=text]").eq(0).val();
			var second = $(this).parents("label").find("input[type=text]").eq(1).val();

			zx += "{\"serverName\":\"" + value1 + "\",\"serverPerson\":\"" + first + "\",\"serverTel\":\"" + second + "\"},";

		});

		zx = zx.substring(0, zx.length - 1);
		zx += "]";
		console.log("中心服务：" + zx);

		//科技创新券
		var applytime = $("#applytime").val(); //申领时间
		var applymoney = $("#applymoney").val();
		var cashmoney = $("#cashmoney").val();
		var cx = [];
		$.each($('input:checkbox[name="kjcx"]:checked'), function(i, items) {

			cx.push(items["value"]);

		});
		var cxs = cx.toString();
		console.log("科技创新券：" + cxs);

		//服务年限
		var startyear = $("#startyear").val();
		var endyear = $("#endyear").val();
		//判断结束日期与开始日期大小
		var start = new Date(startyear.replace("-", "/").replace("-", "/"));
		var end = new Date(endyear.replace("-", "/").replace("-", "/"));
		if(start > end) {
			$('.nxts').css('display', 'block');
			$('.nxts').html("结束日期不能小于开始日期！");
			return false;
		} else {
			$('.nxts').css('display', 'none');
		}

		$.ajax({
			url: yh + "/companydetail/add",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"companyid": id, //公司id
				"commonBrand": commonbrand, //普通商标
				"softwareCopyright": softcopyright, //软件著作权
				"famousBrand": famousbrand, //著名商标
				"resoundBrand": resoundbrand, //驰名商标
				"invention": inventpatent, //发明专利
				"utilityModel": newpatent, //实用新型专利
				"appearModel": appearpatent, //外观专利
				"otherModel": otherpatent, //其他专利
				"companyKinds": ck, //公司类型
				"projectKinds": pk, //承担项目类型
				"recordYear": researchyear, //研发年
				"researchNum": researchnum, //研发数量
				"researchMoney": money, //研发金额
				"industryStudyResearch": radio3value, //产学研情况
				"operateAgency": agency, //合作机构     
				"serverKinds": zx, //中心服务项目
				"applytime": applytime, //科技创新券
				"applymoney": applymoney,
				"cashmoney": cashmoney,
				"innovatetype": cxs,
				"startyear": startyear, //服务年限
				"endyear": endyear
			}

		}).success(function(response) {
			//请求成功
			if(response.success == true) {

				$('.ts').css('display', 'block');
				$('.ts').html("新增客户详情成功！");
				//                  window.location.href="kehulist.html";

			} else {
				$('.ts').css('display', 'block');
				$('.ts').html("新增客户详情失败！");

			}

		});

	});

	//控制产学研	
	$("#techno").click(function() {
		$("#agency").attr('disabled', 'disabled');
	});
	$("#teachyes").click(function() {
		$('#agency').removeAttr("disabled");
	});

});