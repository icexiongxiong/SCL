    //js实现前台导航菜单连动加载

    $(function() {
    	$.ajax({
    		url: yh + "/menu/navi",
    		method: 'POST',
    		cache: false,
    		dataType: "json",
    		crossDomain: true,
    		xhrFields: {
    			withCredentials: true
    		},
    		success: function(data) {
    			var ghtml = "";
    			$.each(data.root, function(i, items) {
    				if(items["parentId"] == "0") {
    					//一级菜单循环
    					ghtml += "<ul class='nav nav-tabs nav-stacked main-menu'>";
    					ghtml += "<li><a style='color:greenyellow' href='" + items["htmlname"] + "'><i class='icon-bar-chart'></i><span class='hidden-tablet'>" + items["name"] + "</span>" + "</a></li>"
    					//二级菜单循环开始  
    					ghtml += "<ul>";
    					$.each(data.root[i].children, function(i, sitems) {
    						if(sitems["parentId"] == items["id"]) {
    							ghtml += "<li><a class='submenu' style='color:white' href='" + sitems["htmlname"] + "'><i class='icon-file-alt'></i><span class='hidden-tablet'>" + sitems["name"] + "</span></a></li>";
    						}

    					});
    					ghtml += "</ul></ul>";

    				}

    			});

    			$('.daohang').append(ghtml);

    		}

    	});

    });