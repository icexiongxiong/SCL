var id;
var param;
var name; 
var jsquanxianapp=angular.module('jsquanxian',['CommonApp']);
jsquanxianapp.controller('jsquanxiancontroller',function($scope,$http,URLParam){
	id = URLParam.getParams();	
    param=id.id;
    name=id.name;
    console.log("看看获取过来的角色id:"+param)
      $('.jueseName').text(name);
	//角色权限管理的动态添加
	 $(function(){	
   	$.ajax({
   		url:yh+"/role/menutree/"+param,
   		method:'GET',
     cache:false,
     dataType: "json",
       crossDomain: true,
		  xhrFields: {
		        withCredentials: true
		    },
   	
   		success:function(data){
   			if(data.success==true){	
   	          	var ghtml="";
          $.each(data.root, function(i,items) {
          	if(items["pid"]=="0"){       	
         		ghtml+="<ul class='messageList' style='list-style:none'>"
         		if(items["checked"]){
         			ghtml+="<li><div class='checkbox check-warning'><lable for='"+ items["id"]+"'><input type='checkbox' id='"+ items["id"] +"' checked>"+ items["name"] +"</label></div></li>";
         		}else{
         			ghtml+="<li><div class='checkbox check-warning'><lable for='"+ items["id"]+"'><input type='checkbox' id='"+ items["id"] +"'>"+ items["name"] +"</label></div></li>";
		        }

          //二级菜单循环开始  
           ghtml+="<ul class='messageList' style='list-style:none'>";
           $.each(data.root[i].children, function(i,sitems) {
           	
            if(sitems["pid"]==items["id"])
              	{
     		 if(sitems["checked"]){
         			ghtml+="<li><div class='checkbox check-warning'><lable for='"+ items["id"]+"'><input type='checkbox' id='"+ sitems["id"] +"'  checked >"+ sitems["name"] +"</label></div></li>";
         		}else{
         			ghtml+="<li><div class='checkbox check-warning'><lable for='"+ items["id"]+"'><input type='checkbox'  id='"+ sitems["id"] +"'>"+ sitems["name"] +"</label></div></li>";
		        } 
     		  
     		  
              	}
              	
              });
               ghtml += "</ul></ul>";
           	
           		}
 
           	  });
           	


$('.span7').append(ghtml); 			
   				
		
   			}

			
   		}
		 		
	});

       	
    });

  
     function iFrameCity1() {
            setTimeout(iFrameCity,1000);
        }
        function iFrameCity () {
     	
    $('.editqx').click(function(){
      var ids = [];  
    	$.each($('input:checkbox:checked'),function(i,items){

//              if(this.checked){
	        ids.push(items["id"]);
          
//             }
                	     
        });
  

	$.ajax({
			url:yh+"/role/grant/"+param+"/"+ids,
			xhrFields: {
		        withCredentials: true
		    },
		   
       		crossDomain: true ,
			method:'GET',			
         	cache:false,
           	dataType: "json"			
		}).success(function(response){
			if(response.success==true){
				   $('.ts').css('display','block');
                  $('.ts').html("角色配置权限成功！");
               
                  
				
			}else{
				 $('.ts').css('display','block');
                  $('.ts').html("角色配置权限失败！");
				
			}
			
			
		});


    	
    }) 
        	

        }
        iFrameCity1();
        
   
        
        
        

	
  });
	


	
	
	
	
	
	


