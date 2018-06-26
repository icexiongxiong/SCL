
var adduseticketapp=angular.module('adduseticket',['CommonApp']);
adduseticketapp.controller('adduseticketcontroller',function($scope,$http,URLParam){
	$scope.size = 0;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

//验证控件
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
        message: '这些信息无效',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            qiyeming: {
                validators: {
                    notEmpty: {
                        message: '企业/团队名称不能为空！',
                        
                    }
                }
            },
            companyaddress: {
                validators: {
                    notEmpty: {
                        message: '所在地址不能为空！'
                    }
                }
            },
            companyperson: {
                validators: {
                    notEmpty: {
                        message: '联系人不能为空！'
                    },
                }
            },
              email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空！'
                    },
                   emailAddress: {
                        message: '邮箱地址格式有误!'
                   }
                }
            },
              innovationmoney: {
                validators: {
                    notEmpty: {
                        message: '创新券金额不能为空！'
                    }
                }
            },
             innovationpurpose: {
                validators: {
                    notEmpty: {
                        message: '创新券用途不能为空！'
                    }
                }
            },
             projectname: {
                validators: {
                    notEmpty: {
                        message: '服务项目名称不能为空！'
                    }
                }
            },
             contactmoney: {
                validators: {
                    notEmpty: {
                        message: '合同金额不能为空！'
                    }
                }
            },
             usemoney: {
                validators: {
                    notEmpty: {
                        message: '创新券使用金额不能为空！'
                    }
                }
            },
             actualcash: {
                validators: {
                    notEmpty: {
                        message: '实际兑付金额不能为空！'
                    }
                }
            },
            check: {
                validators: {
                    notEmpty: {
                        message: '抽查情况不能为空！'
                    }
                }
            },
             companytel: {
              	message: '这是一个无效的电话号',
                validators: {
                    notEmpty: {
                        message: '联系电话不能为空！'
                    },
                    stringLength:{
                    	min:11,
                    	max:11,
                    	message:'请输入11位手机号码'
                    },
                    regexp:{
                    	regexp:/^1[3|5|8]{1}[0-9]{9}$/,
                    	message:'请输入正确的手机号码'
                    }
                }
            }

        }
    });

    // Validate the form manually

});
  
  //绑定企业领域  
 $(function(){	
 	 	   $.ajax({       	
        		url:yh+"/companyfield/page",
        		method:'POST',
        		  crossDomain: true,
		  xhrFields: {
		        withCredentials: true
		    },
		    data:{
		    	start:0,
		    	limit:200
		    },
	       		
       }).success(function(data){ 
          if(data.success==true){
    
           var ghtml="";
          $.each(data.root, function(i,items) {

          	if(items["fieldParandid"]=="0"){  
         //一级菜单循环
         ghtml+="<optgroup  id='"+ items["fieldId"] + "' label='"+ items["fieldName"] +"'>";
   
          //二级菜单循环开始  
//          ghtml+="<optgroup>"; 
             $.each(data.root, function(i,sitems) {        	
            if(sitems["fieldParandid"]==items["fieldId"]){
            ghtml+="<option id='"+sitems["fieldId"]+"' style='margin-left:20px'><i class='icon-file-alt'></i>"+ sitems["fieldName"] +"";


     		//三级菜单循环     
//           ghtml += "<option>";
           $.each(data.root, function (i, ssitems){
              if (ssitems["fieldParandid"] == sitems["fieldId"])
                   {

                 ghtml +="<option id='"+ssitems["fieldId"]+"' style='margin-left:40px'><i class='icon-file-alt'></i>"+ssitems["fieldName"]+"</option>";
                   
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
   			  		  
   			  
  $('.addqiye').click(function() {
        $('#defaultForm').bootstrapValidator('validate');
//     $('#defaultForm').data('bootstrapValidator').validate();  
       if(!$('#defaultForm').data('bootstrapValidator').isValid()){
       	//没有通过验证
          $('.ts').css('display','block');
          $('.ts').html("请完善企业信息后提交！");
   
        } else {
	    var qiyeming = $('#qiyeming').val();   //企业名称
        var companyaddress=$("#companyaddress").val(); 
        var companyperson=$("#companyperson").val(); 
        var companytel=$("#companytel").val();
        var email=$("#email").val(); 
        var shenqingriqi=$("#shenqingriqi").val();         
        var innovationmoney=$("#innovationmoney").val(); //创新券金额       
        var innovationpurpose=$("#innovationpurpose").val();  //创新券用途       
 		var  projectname=$("#projectname").val();  //服务项目名称 		
 		var  contactmoney=$("#contactmoney").val(); //合同金额
 		var  usemoney=$("#usemoney").val(); //创新券使用金额      
        var cash=$("#cash option:selected").val();  //是否兑付
 	    var actualcash=$("#actualcash").val(); //实际兑付
 	    var cashdate=$("#cashdate").val(); //兑付日期
 	    var check=$("#check").val();  //抽查情况       
 	    
 	    //客户新增字段
 	   var collectnum = $("#collectnum option:selected").val(); //收券机构数
 	   var collectname1=$("#collectname1").val();  //机构名
 	   var collectname2=$("#collectname2").val(); 
 	   var collectname3=$("#collectname3").val(); 
 	   var collectname4=$("#collectname4").val(); 
 	   var collectname5=$("#collectname").val(); 	   	    
         $('.ts').css('display','none');	
        $.ajax({
        
        	url:yh+"/useticket/add ",
        	crossDomain: true,
		    xhrFields: {
		        withCredentials: true
		    },
        	method:'POST',
        	data:{
        		"companyname":qiyeming,  //公司名称
        		"companyaddress":companyaddress,  //注册时间
        		"companyperson":companyperson,  //法人
        	    "companyphone":companytel,    //资金
        	    "companyemail":email,   //注册类型
        	    "applicationdate":shenqingriqi,
        	    "innovationmoney":innovationmoney,
        	    "innovationpurpose":innovationpurpose,
        	    "projectname":projectname,
        	    "contractvalue":contactmoney,
        	    "usevalue":usemoney,
        	    "cash":cash,
        	    "actualcashmoney":actualcash,
        	    "cashdate":cashdate,
        	    "checkcondition":check,
        	    //新增字段
        	    "collectnum":collectnum,
        	    "collectname1":collectname1,
        	    "collectname2":collectname2,
        	    "collectname3":collectname3,
        	    "collectname4":collectname4,
        	    "collectname5":collectname5       	    
        		}
		
        	}).success(function(response){

        		//请求成功
        		if(response.success==true){

                    $('.ts').css('display','block');
                    $('.ts').html("新增客户成功！");
//                  window.location.href="kehulist.html";
   
			
        		}else
        		{
        		    $('.ts').css('display','block');
                    $('.ts').html("新增客户失败！");
                  			
        		}
	
        	});
	
   
   
}
    });


   //重置 
    $('.reset').click(function() {
        $('#defaultForm').data('bootstrapValidator').resetForm(true);
    });  
    
   	//控制收券机构
	$scope.disableInput = function(index) {
		if(index > $scope.size) {
			return true;
		} else {
			return false;
		}
	} 
    


	
});