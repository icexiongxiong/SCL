var loginapp=angular.module('login',['CommonApp']);
loginapp.controller('logincontroller',function($scope,$http,URLParam){
	
	//验证控件
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
    　　　 container: '#ts',
        message: '这些信息无效',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空',
                        
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }

        }
    });



});

	
	$("#denglu").click(function(){		
		var username=$("#username").val();
		var password=$("#password").val();

   $('#defaultForm').data('bootstrapValidator').validate();  
       if(!$('#defaultForm').data('bootstrapValidator').isValid()){

   
       }else{

	     $.ajax({
         url:yh+"/login",
         type: "POST",
         cache:false,
         dataType: "json",
         crossDomain: true,
		  xhrFields: {
		        withCredentials: true
		    },
         
         data:{
         	"username":username,
			"password":password
         },
         success: function(response){
         	if(response.success==true){
         		
         	
         	$scope.data=response.data;
         		$.cookie("Yewuyuan",$scope.data.username,{path:"/"
					//存储登录的用户名
				});
				 $.cookie("organizationname", $scope.data.organizationname, {path: "/"   
                    //存储是否是主任
                });
                $.cookie("leader", $scope.data.leader, {path: "/"  
                    //存储是否是领导
             });
			  window.location.href = "index.html";
			  }else
			  {
			  	 $('.tip').css('display','block');
                 $('.tip').html("用户名或密码错误！");
			  }
             
          }
        });	
		
	 }	
		
	
		
	});
	
	
	
	
});
