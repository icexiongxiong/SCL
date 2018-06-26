
var addjueseapp=angular.module('addjuese',['CommonApp']);
addjueseapp.controller('addjuesecontroller',function($scope,$http,URLParam){
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

//添加角色
   $('.addjs').click(function () {
        var jueseming = $('#jsming').val();
        var jsdescription=$("#jsdescription").val();

        if(!jueseming){
         $('.ts').css('display','block');
          $('.ts').html("角色名称不能为空！");
       return;
        }
        
         if(!jsdescription){
         $('.ts').css('display','block');
          $('.ts').html("角色描述不能为空！");
       return;
        }

        $.ajax({
        	url:yh+"/role/add",
        	crossDomain: true,
		    xhrFields: {
		        withCredentials: true
		    },
        	method:'POST',
        	data:{
        		"name":jueseming,
        		"description":jsdescription    		
        		}
  		
        	}).success(function(response){
  
        		//请求成功
        		if(response.success==true){

                    $('.ts').css('display','block');
//                  $('.ts').html("添加角色成功！");
                     window.location.href="juese.html";
            
			
        		}else
        		{
        		    $('.ts').css('display','block');
                    $('.ts').html("添加角色失败！");
                  			
        		}
	
        	});
        	
       
    });
    

    
    
    


	
});