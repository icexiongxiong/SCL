	//注销
	$("#zhuxiao").click(function(){
   	$.ajax({
   		url:yh+"/logout",
   		method:'POST',
     cache:false,
     dataType: "json",
       crossDomain: true,
		  xhrFields: {
		        withCredentials: true
		    },
   		success:function(data){
   			if(data.success==true){
   				
   			  window.location.href = "login.html";	
   			}
		
   		}
		 		
	});
		
		
	});