var id;
var parentid;
var addjigouapp=angular.module('addjigou',['CommonApp']);
addjigouapp.controller('addjigoucontroller',function($scope,$http,URLParam){
	   id = URLParam.getParams();
	   parentid=id.parentid;
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});


   $('.addjg').click(function () {
        var jigouming = $('#jgming').val();
        var jigoudescription=$("#jgdescription").val();

        if(!jigouming){
         $('.ts').css('display','block');
          $('.ts').html("组织机构名称不能为空！");
       return;
        }
        
         if(!jigoudescription){
         $('.ts').css('display','block');
          $('.ts').html("组织机构描述不能为空！");
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
        		"name":jigouming,
        		"description":jigoudescription,
        		"parentid":parentid
        		
        		}
		
        	}).success(function(response){

        		//请求成功
        		if(response.success==true){

                    $('.ts').css('display','block');
                    $('.ts').html("添加组织机构成功！");
            
			
        		}else
        		{
        		    $('.ts').css('display','block');
                    $('.ts').html("添加组织机构失败!");
                  			
        		}
	
        	});
        	
       
    });
    



    
    
    


	
});