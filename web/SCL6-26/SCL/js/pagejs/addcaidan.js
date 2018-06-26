
var addcaidanapp=angular.module('addcaidan',['CommonApp']);
addcaidanapp.controller('addcaidancontroller',function($scope,$http,URLParam){
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
	});

//添加一级菜单

   $('.addfirstcaidan').click(function () {
        var firstcaidanname = $('#firstcaidanname').val();
        var htmlname=$("#caidanpath").val();
        if(!firstcaidanname){
        		$('.ts').css('display','block');
                $('.ts').html("一级菜单名不能为空");
//      $('.addfirstcaidan').attr('data-target','#myModal');  //点击事件添加属性    
       return;
        }

        $.ajax({
        	
        		url:yh+"/menu/add",
        		method:'POST',
        		data:{
        			"name":firstcaidanname,
        			"htmlname":htmlname,
        			"parentId":'0'
        		},
        		  crossDomain: true,
		  xhrFields: {
		        withCredentials: true
		    },
	
        		
        	}).success(function(response){
  
        		//请求成功
        		if(response.success==true){
        
//      			 $('.addfirstcaidan').attr('data-target','#myModal2');
                    $('.ts').css('display','block');
//                  $('.ts').html("添加菜单成功");
                    window.location.href="caidan.html";
            
			
        		}else if(response.success==false)
        		{
        			alert('添加菜单失败!');
        			
        		}
	
        	});
        	
       
    });
    

    
    
    


	
});