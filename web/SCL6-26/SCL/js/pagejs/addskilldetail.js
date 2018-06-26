var id;
var name;
var addskilldetailapp = angular.module('addskilldetail', ['CommonApp']);
addskilldetailapp.controller('addskilldetailcontroller', function($scope, $http, URLParam) {
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
		var companyNature = $('input[name="optionsRadios"]:checked').val(); //公司性质单选按钮
				//四技合同
		var exploitcontract = $("#exploitcontract").val();
		var transfercontract = $("#transfercontract").val();
		var servecontract = $("#servecontract").val();
		var consultcontract = $("#consultcontract").val();

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

		//科技创新券
		var applytime = $("#applytime").val(); //申领时间
		var applymoney = $("#applymoney").val();
		var cashmoney = $("#cashmoney").val();
		var cx = [];
		$.each($('input:checkbox[name="kjcx"]:checked'), function(i, items) {

			cx.push(items["value"]);

		});
		var cxs = cx.toString()


		//服务年限
		var startyear = $("#startyear").val();
		var endyear = $("#endyear").val();	
	    //判断结束日期与开始日期大小
	    var start = new Date(startyear.replace("-", "/").replace("-", "/"));
        var end = new Date(endyear.replace("-", "/").replace("-", "/"));
		if(start>end) {
			$('.nxts').css('display', 'block');
			$('.nxts').html("结束日期不能小于开始日期！");
			return false;
		}else{
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
				"companyNature": companyNature, //公司性质
				"contractExploit": exploitcontract, //技术开发合同
				"contractTransfer": transfercontract, //转让合同
				"contractService": servecontract, //服务合同
				"contractConsult": consultcontract, //咨询合同        
				"serverKinds": zx, //中心服务项目
				"applytime":applytime,  //科技创新券
				"applymoney":applymoney,
				"cashmoney":cashmoney,
				"innovatetype":cxs,
				"startyear":startyear,//服务年限
				"endyear":endyear
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
			

			$("#shareno").click(function() {
				$("#machinename").attr('disabled', 'disabled');
				$("#machinetype").attr('disabled', 'disabled');
				$("#machinemoney").attr('disabled', 'disabled');
		
			});
			$("#shareyes").click(function() {
				$('#machinename').removeAttr("disabled");
				$('#machinetype').removeAttr("disabled");
				$('#machinemoney').removeAttr("disabled");
		
			});

});