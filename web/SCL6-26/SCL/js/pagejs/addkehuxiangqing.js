var id;
var name;
var person;
var type;
var time;
var linkman;
var contactway;
var fund;
var years;
var addkehuxiangqingapp = angular.module('addkehuxiangqing', ['CommonApp']);
addkehuxiangqingapp.controller('addkehuxiangqingcontroller', function($scope, $http, URLParam) {
	var param = URLParam.getParams();

	id = param.id
	name = param.name;
	person = param.person;
	type = param.type;
	time = param.time;
	linkman = param.linkman;
	contactway = param.contactway;
	fund = param.fund;
	$scope.typenames = type; //企业类型
	$scope.companyname = name; //企业名称
	$scope.person = person; //企业法人
	$scope.linkman = linkman;
	$scope.contactway = contactway;
	$scope.fund = fund;
	$scope.time = time;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

	//新增客户详情
	$('.kehuxq').click(function() {

		var companyname = $('#companyname').val(); //企业名称
		var creditcode = $("#creditcode").val();
		var legalperson = $("#legalperson").val();
		var legalphone = $("#legalphone").val();
		var stockmessage = $("#stockmessage").val();
		var fund = $("#fund").val();
		var settime = $("#settime").val();
		var companytype = $("#companytype option:selected").val();
		var cmbProvince = $("#cmbProvince option:selected").val();
		var cmbCity = $("#cmbCity option:selected").val();
		var cmbArea = $("#cmbArea option:selected").val();
		var companynum = $("#companynum").val();
		var linkman = $("#linkman").val();
		var contackway = $("#contackway").val();
		var weixin = $("#weixin").val();
		var email = $("#email").val();
		var product = $("#product").val();
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
		alert("公司类型：" + ck);

		//获取研发机构类型 :
		var yanfas = [];
		$.each($('input:checkbox[name="yfjg"]:checked'), function(i, items) {

			yanfas.push(items["value"]);

		});
		var newyanfas = yanfas.toString()
		alert("研发机构类型：" + newyanfas);

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

		alert("承担项目类型：" + pk);

		//研发人员
		var researchyear = $("#researchyear").val();
		var researchnum = $("#researchnum").val();
		var money = $("#money").val();

		//四技合同
		var exploitcontract = $("#exploitcontract").val();
		var transfercontract = $("#transfercontract").val();
		var servecontract = $("#servecontract").val();
		var consultcontract = $("#consultcontract").val();

		//产学研情况
		var radio3value = $('input[name="Radios3"]:checked').val();
		if(radio3value == "是") {
			var agency = $("#agency").val();

		}
		//科技金融
		var radio4value = $('input[name="Radios4"]:checked').val();

		//管理体系情况

		var tx = "[";
		$.each($('input:checkbox[name="managesystem"]:checked'), function(i, items) {
			var txvalue1 = items.value;
			var ipts = $(this).parents("label").find("input[type=text]")
			var txvalue2 = ipts.val();

			tx += "{\"systemName\":\"" + txvalue1 + "\",\"periodTime\":\"" + txvalue2 + "\"},";

		});

		tx = tx.substring(0, tx.length - 1);
		tx += "]";
		alert("管理体系：" + tx);

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
		alert("中心服务：" + zx);

		//仪器共享情况

		var radio5value = $('input[name="Radios5"]:checked').val();
		if(radio5value == "是") {
			var machinename = $("#machinename").val();
			var machinetype = $("#machinetype").val();
			var machinemoney = $("#machinemoney").val();

		}

		//      if(!qiyeming){
		//       $('.ts').css('display','block');
		//        $('.ts').html("企业名称不能为空！");
		//     return;
		//      }
		//      
		//      if(!zhuceshijian){
		//       $('.ts').css('display','block');
		//        $('.ts').html("注册时间不能为空！");
		//     return;
		//      	
		//      }
		// 
		//      
		//         if(!qiyefaren){
		//       $('.ts').css('display','block');
		//        $('.ts').html("企业法人不能为空！");
		//     return;
		//      } 
		//                  	
		//        if(!zhucezijin){
		//       $('.ts').css('display','block');
		//        $('.ts').html("注册资金不能为空！");
		//     return;
		//      }  
		//      
		//      if(!qiyeguimo){
		//       $('.ts').css('display','block');
		//        $('.ts').html("企业规模不能为空！");
		//     return;
		//      }
		//      
		//       if(!peiyunshijian){
		//       $('.ts').css('display','block');
		//        $('.ts').html("入库培育时间不能为空！");
		//     return;
		//      } 
		//        if(!rendingnianfen){
		//       $('.ts').css('display','block');
		//        $('.ts').html("认定年份不能为空！");
		//     return;
		//      }  
		//
		//        if(!rendingpici){
		//       $('.ts').css('display','block');
		//        $('.ts').html("认定批次不能为空！");
		//     return;
		//      } 
		//      
		//       if(!jiaoshuijiguan){
		//       $('.ts').css('display','block');
		//        $('.ts').html("缴税机关不能为空！");
		//     return;
		//      }   
		//     
		//        if(!quyu){
		//       $('.ts').css('display','block');
		//        $('.ts').html("区域不能为空！");
		//     return;
		//      } 
		// 
		//       if(!lianxiren){
		//       $('.ts').css('display','block');
		//        $('.ts').html("联系人不能为空！");
		//     return;
		//      }
		//        if(!lianxidianhua){
		//       $('.ts').css('display','block');
		//        $('.ts').html("联系电话不能为空！");
		//     return;
		//      }  
		//      
		//        if(!zhuceleixing){
		//       $('.ts').css('display','block');
		//        $('.ts').html("注册类型不能为空！");
		//     return;
		//      }   
		//      
		//         if(!lingyu){
		//       $('.ts').css('display','block');
		//        $('.ts').html("所属领域不能为空！");
		//     return;
		//      }     
		//	
		$.ajax({
			url: yh + "/companydetail/add",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				"companyId": id, //公司id
				"companyName": companyname, //公司名称
				"creditCode": creditcode, //信用代码
				"legalPerson": legalperson, //法人
				"legalContact": legalphone, //法人联系方式
				"stockeMessage": stockmessage, //股东信息
				"fund": fund, //资金
				"buildtime": settime, //成立时间
				"companyType": companytype, //企业类型
				"province": cmbProvince, //省
				"city": cmbCity, //市
				"county": cmbArea, //县
				"companyNum": companynum, //企业人数
				"contactPerson": linkman, //联系人
				"companyContact": contackway, //联系方式
				"weixin": weixin,
				"companyEmail": email,
				"productField": product, //产品领域

				"commonBrand": commonbrand, //普通商标
				"softwareCopyright": softcopyright, //软件著作权
				"famousBrand": famousbrand, //著名商标
				"resoundBrand": resoundbrand, //驰名商标
				"invention": inventpatent, //发明专利
				"utilityModel": newpatent, //实用新型专利
				"appearModel": appearpatent, //外观专利
				"otherModel": otherpatent, //其他专利

				"companyNature": companyNature, //公司性质
				"companyKinds": ck, //公司类型
				"researchKind": newyanfas, //研发机构类型
				"projectKinds": pk, //承担项目类型

				"recordYear": researchyear, //研发年
				"researchNum": researchnum, //研发数量
				"researchMoney": money, //研发金额
				"contractExploit": exploitcontract, //技术开发合同
				"contractTransfer": transfercontract, //转让合同
				"contractService": servecontract, //服务合同
				"contractConsult": consultcontract, //咨询合同        	    
				"industryStudyResearch": radio3value, //产学研情况
				"operateAgency": agency, //合作机构        	    

				"finance": radio4value, //金融情况
				"systemKinds": tx, //管理体系情况
				"serverKinds": zx, //中心服务项目
				"instrumentShare": radio5value, //共享情况
				"instrumentName": machinename,
				"instrumentType": machinetype,
				"instrumentMoney": machinemoney

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

	//$("#shareyes").click(function(){
	//	$("#machinename").attr("disabled","false");
	//
	//});
	$("#shareno").click(function() {
		$("#machinename").attr('disabled', 'disabled');

	});

});