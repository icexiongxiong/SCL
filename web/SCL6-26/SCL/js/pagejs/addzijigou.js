var id;
var parentid;
var addzijigouapp=angular.module('addzijigou',['CommonApp']);
addzijigouapp.controller('addzijigoucontroller',function($scope,$http,URLParam){
	   id = URLParam.getParams();
	   parentid=id.parentid;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

//添加角色
   $('.addsecondjigou').click(function () {
        var zijigouming = $('#zijgming').val();
        var zijigoudescription=$("#zijgdescription").val();

        if(!zijigouming){
         $('.ts').css('display','block');
          $('.ts').html("子组织机构名称不能为空！");
       return;
        }
        
         if(!zijigoudescription){
         $('.ts').css('display','block');
          $('.ts').html("子组织机构描述不能为空！");
       return;
        }

        $.ajax({
        	url:yh+"/organ/add",
        	crossDomain: true,
		    xhrFields: {
		        withCredentials: true
		    },
        	method:'POST',
        	data:{
        		"name":zijigouming,
        		"description":zijigoudescription,
        		"parentid":parentid
        		
        		}
		
        	}).success(function(response){

        		//请求成功
        		if(response.success==true){

                    $('.ts').css('display','block');
                    $('.ts').html("添加子组织机构成功！");
            
			
        		}else
        		{
        		    $('.ts').css('display','block');
                    $('.ts').html("添加子组织机构失败!");
                  			
        		}
	
        	});
        	
       
    });
    



    
    
    


	
});