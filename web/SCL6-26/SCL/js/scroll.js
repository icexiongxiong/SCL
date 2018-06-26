$(document).ready(function(){
	//监听滚动条滚动事件
	$(window).scroll(function(){
		//滚动条滚动的距离
		var top=$(document).scrollTop();
		//获取右侧滚动条
		var menu=$("#menuright");
		var items=$("#content").find(".item");  //所有的item
		var currentId="";   //当前所在的楼层id
		items.each(function(){
			var m=$(this);
			var itemTop=m.offset().top;  //获取当前元素每个item的top值
		    //判断滚动条的滚动值是否大于当前item层的高度值
		    //这一块-200，为了更好的用户体验，值太绝对，有延时效果
		    if(top>itemTop-200){
		    	//取当前楼层的id赋值给currentId;
		    　currentId="#"+ m.attr("id");	 
		    }else{
		    	return false;
		    }
			
			
		});

		//获取当前有current属性的一行
		var currentLink=menu.find(".current"); 
//		判断currentId是否有值,或者不等于当前行
		if(currentId && currentLink.attr("href")!=currentId){
			currentLink.removeClass("current");   //移除有current的这一行的属性
			menu.find("[href=" + currentId + "]").addClass("current");  //找到当前行添加上current  
		}
		
	});
	
	
});
