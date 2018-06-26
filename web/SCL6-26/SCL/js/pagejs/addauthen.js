
var addauthenapp=angular.module('addauthen',['CommonApp']);
addauthenapp.controller('addauthencontroller',function($scope,$http,URLParam){
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
            companyname: {
                validators: {
                    notEmpty: {
                        message: '企业名称不能为空！',
                        
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: '企业地址不能为空！'
                    }
                }
            },
            authproject: {
                validators: {
                    notEmpty: {
                        message: '认证项目不能为空！'
                    }
                }
            },
             authentgist: {
                validators: {
                    notEmpty: {
                        message: '认证依据不能为空！'
                    }
                }
            },
            certificatenumber: {
                validators: {
                    notEmpty: {
                        message: '证书编号不能为空！'
                    }
                }
            },
            certificatescope: {
                validators: {
                    notEmpty: {
                        message: '证书范围不能为空！'
                    }
                }
            },
            organizationname: {
                validators: {
                    notEmpty: {
                        message: '认证机构名称不能为空！'
                    }
                }
            },
             awarddate: {
                validators: {
                    notEmpty: {
                        message: '颁证日期不能为空！'
                    }
                }
            },
             firstdate: {
                validators: {
                    notEmpty: {
                        message: '初次获证日期不能为空！'
                    }
                }
            },
            changedate: {
                validators: {
                    notEmpty: {
                        message: '换证日期不能为空！'
                    }
                }
            },
            state: {
                validators: {
                    notEmpty: {
                        message: '证书状态不能为空！'
                    }
                }
            }

        }
    });

    // Validate the form manually

});
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});


   			  		  
   			  
  $('.addqiye').click(function() {
        $('#defaultForm').bootstrapValidator('validate');
//     $('#defaultForm').data('bootstrapValidator').validate();  
       if(!$('#defaultForm').data('bootstrapValidator').isValid()){
       	//没有通过验证
          $('.ts').css('display','block');
          $('.ts').html("请完善企业信息后提交！");
   
        } else {
	    var companyname = $('#companyname').val();   //企业名称
        var address=$("#address").val(); 
        var authproject=$("#authproject").val();  
        var authentgist=$("#authentgist").val(); 
        var certificatenumber=$("#certificatenumber").val();
        var certificatescope=$("#certificatescope").val();
        var organizationname=$("#organizationname").val();  
        var awarddate=$("#awarddate").val(); 
        var firstdate=$("#firstdate").val();       
        var changedate=$("#changedate").val(); 
        var state=$("#state option:selected").val(); //状态
         $('.ts').css('display','none');
   
	
        $.ajax({
        
        	url:yh+"/authen/add",
        	crossDomain: true,
		    xhrFields: {
		        withCredentials: true
		    },
        	method:'POST',
        	data:{
        		"companyname":companyname,  //公司名称
        		"address":address,  //注册时间
        		"certifyprogram":authproject,  
        	    "certifygist":authentgist,   
        	    "certificatenumber":certificatenumber,   //注册类型
        	    "certifyscope":certificatescope,
        	    "certifyorganname":organizationname,
        	    "issuedate":awarddate,
        	    "firstgettime":firstdate,
        	    "changetime":changedate,
        	    "state":state       
        		}
		
        	}).success(function(response){

        		//请求成功
        		if(response.success==true){

                    $('.ts').css('display','block');
                    $('.ts').html("新增认证信息成功！");
//                  window.location.href="kehulist.html";
   
			
        		}else
        		{
        		    $('.ts').css('display','block');
                    $('.ts').html("新增认证信息失败！");
                  			
        		}
	
        	});
	
   
   
}
    });


   //重置 
    $('.reset').click(function() {
        $('#defaultForm').data('bootstrapValidator').resetForm(true);
    });  
    
    
    


	
});