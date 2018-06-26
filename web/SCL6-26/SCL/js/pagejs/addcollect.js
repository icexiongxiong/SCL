
var addcollectapp=angular.module('addcollect',['CommonApp']);
addcollectapp.controller('addcollectcontroller',function($scope,$http,URLParam){
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
            organizationname: {
                validators: {
                    notEmpty: {
                        message: '收券机构名称不能为空！',
                        
                    }
                }
            },
            organizationaddress: {
                validators: {
                    notEmpty: {
                        message: '所在地址不能为空！'
                    }
                }
            },
            organizationperson: {
                validators: {
                    notEmpty: {
                        message: '联系人不能为空！'
                    }
                }
            },
            organizationtel: {
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
            },
              servescope: {
                validators: {
                    notEmpty: {
                        message: '服务范围不能为空！'
                    }
                }
            },
              ticketmoney: {
                validators: {
                    notEmpty: {
                        message: '收券金额不能为空！'
                    }
                }
            },
             cashmoney: {
                validators: {
                    notEmpty: {
                        message: '兑付金额不能为空！'
                    }
                }
            },
             organizationemail: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空！'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误!'
                   }
                }
            }

        }
    });

    // Validate the form manually

});

   			  		  
   			  
  $('.addqiye').click(function() {
        $('#defaultForm').bootstrapValidator('validate');
//     $('#defaultForm').data('bootstrapValidator').validate();  
       if(!$('#defaultForm').data('bootstrapValidator').isValid()){
       	//没有通过验证
          $('.ts').css('display','block');
          $('.ts').html("请完善企业信息后提交！");
   
        } else {
	    var organizationname = $('#organizationname').val();   //企业名称
        var organizationaddress=$("#organizationaddress").val(); 
        var organizationperson=$("#organizationperson").val();  //企业法人
        var organizationtel=$("#organizationtel").val();  //注册资金  
        var servescope=$("#servescope").val();
        var ticketmoney=$("#ticketmoney").val();
        var cashmoney=$("#cashmoney").val();  
        var organizationemail=$("#organizationemail").val(); 
        	    //客户新增字段
 	   var collectnum = $("#collectnum option:selected").val(); //收券机构数
 	   var collectname1=$("#collectname1").val();  //机构名
 	   var collectname2=$("#collectname2").val(); 
 	   var collectname3=$("#collectname3").val(); 
 	   var collectname4=$("#collectname4").val(); 
 	   var collectname5=$("#collectname").val(); 
         $('.ts').css('display','none');

        $.ajax({
        
        	url:yh+"/collectticket/add ",
        	crossDomain: true,
		    xhrFields: {
		        withCredentials: true
		    },
        	method:'POST',
        	data:{
        		"organizationname":organizationname,  //公司名称
        		"address":organizationaddress,  //注册时间
        		"person":organizationperson,  //法人
        	    "phone":organizationtel,    //资金
        	    "serverarea":servescope,   //注册类型
        	    "ticketmoney":ticketmoney,
        	    "cashmoney":cashmoney,
        	    "email":organizationemail,
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
                    $('.ts').html("新增收券机构成功！");
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