
var addkehuapp=angular.module('addkehu',['CommonApp']);
addkehuapp.controller('addkehucontroller',function($scope,$http,URLParam){
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
                        message: '企业名称不能为空！',
                        
                    }
                }
            },
            zhuceshijian: {
                validators: {
                    notEmpty: {
                        message: '注册时间不能为空！'
                    }
                }
            },
            qiyefaren: {
                validators: {
                    notEmpty: {
                        message: '企业法人不能为空！'
                    }
                }
            },
             zhucezijin: {
                validators: {
                    notEmpty: {
                        message: '注册资金不能为空！'
                    }
                }
            },
              peiyunshijian: {
                validators: {
                    notEmpty: {
                        message: '入库培训时间不能为空！'
                    }
                }
            },
              rendingnianfen: {
                validators: {
                    notEmpty: {
                        message: '认定年份不能为空！'
                    }
                }
            },
             rendingpici: {
                validators: {
                    notEmpty: {
                        message: '认定批次不能为空！'
                    }
                }
            },
             jiaoshuijiguan: {
                validators: {
                    notEmpty: {
                        message: '缴税机关不能为空！'
                    }
                }
            },
             quyu: {
                validators: {
                    notEmpty: {
                        message: '区域不能为空！'
                    }
                }
            },
             lianxiren: {
                validators: {
                    notEmpty: {
                        message: '联系人不能为空！'
                    }
                }
            },
              lianxidianhua: {
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
        var zhuceshijian=$("#zhuceshijian").val(); 
        var qiyefaren=$("#qiyefaren").val();  //企业法人
        var zhucezijin=$("#zhucezijin").val();  //注册资金
//      var zhuceleixing=$("#selectError option:selected").val(); //取的企业类型id --nature_id
        var zhuceleixingzhi=$("#selectError option:selected").text();//取的企业类型值
      
        var qiyeguimo=$("#qiyeguimo option:selected").val();  //企业规模
     
        var lingyu=$("#lingyu option:selected").val(); //领域
   
        var peiyunshijian=$("#peiyunshijian").val();
        var rendingnianfen=$("#rendingnianfen").val();
        var rendingpici=$("#rendingpici").val();  
        var jiaoshuijiguan=$("#jiaoshuijiguan").val(); 
        var quyu=$("#quyu").val();       
        var lianxiren=$("#lianxiren").val(); 
        var lianxidianhua=$("#lianxidianhua").val();
         $('.ts').css('display','none');
      //$("#selMonth").text();  获取select的文本值  
    
//      if(!qiyeming){
//       $('.ts').css('display','block');
//        $('.ts').html("企业名称不能为空！");
//     return;
//      }
//      
//      if(!zhuceshijian){
//       $('.ts').css('display','block');
//        $('.ts').html("注册时间不能为空！");
//     return;
//      	
//      }
// 
//      
//         if(!qiyefaren){
//       $('.ts').css('display','block');
//        $('.ts').html("企业法人不能为空！");
//     return;
//      } 
//                  	
//        if(!zhucezijin){
//       $('.ts').css('display','block');
//        $('.ts').html("注册资金不能为空！");
//     return;
//      }  
//      
//      if(!qiyeguimo){
//       $('.ts').css('display','block');
//        $('.ts').html("企业规模不能为空！");
//     return;
//      }
//      
//       if(!peiyunshijian){
//       $('.ts').css('display','block');
//        $('.ts').html("入库培育时间不能为空！");
//     return;
//      } 
//        if(!rendingnianfen){
//       $('.ts').css('display','block');
//        $('.ts').html("认定年份不能为空！");
//     return;
//      }  
//
//        if(!rendingpici){
//       $('.ts').css('display','block');
//        $('.ts').html("认定批次不能为空！");
//     return;
//      } 
//      
//       if(!jiaoshuijiguan){
//       $('.ts').css('display','block');
//        $('.ts').html("缴税机关不能为空！");
//     return;
//      }   
//     
//        if(!quyu){
//       $('.ts').css('display','block');
//        $('.ts').html("区域不能为空！");
//     return;
//      } 
// 
//       if(!lianxiren){
//       $('.ts').css('display','block');
//        $('.ts').html("联系人不能为空！");
//     return;
//      }
//        if(!lianxidianhua){
//       $('.ts').css('display','block');
//        $('.ts').html("联系电话不能为空！");
//     return;
//      }  
//      
//        if(!zhuceleixing){
//       $('.ts').css('display','block');
//        $('.ts').html("注册类型不能为空！");
//     return;
//      }   
//      
//         if(!lingyu){
//       $('.ts').css('display','block');
//        $('.ts').html("所属领域不能为空！");
//     return;
//      }     
	
        $.ajax({
        
        	url:yh+"/companydetail/add",
        	crossDomain: true,
		    xhrFields: {
		        withCredentials: true
		    },
        	method:'POST',
        	data:{
        		"companyName":qiyeming,  //公司名称
        		"registerTime":zhuceshijian,  //注册时间
        		"legalPerson":qiyefaren,  //法人
        	    "fund":zhucezijin,    //资金
        	    "companyType":zhuceleixingzhi,   //注册类型
        	    "companyScale":qiyeguimo,
        	    "companyField":lingyu,
        	    "growTime":peiyunshijian,
        	    "identifyTime":rendingnianfen,
        	    "identifyBatch":rendingpici,
        	    "taxOffice":jiaoshuijiguan,
        	    "region":quyu,
        	    "contactPerson":lianxiren,
        	    "telphone":lianxidianhua
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
    
    
    


	
});