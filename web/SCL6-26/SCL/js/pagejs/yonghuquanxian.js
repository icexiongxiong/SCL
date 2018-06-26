var id;
var param;
var name;
var yonghuquanxianapp = angular.module('yonghuquanxian', ['CommonApp']);
yonghuquanxianapp.controller('yonghuquanxiancontroller', function($scope, $http, URLParam) {
	id = URLParam.getParams();
	param = id.id;
	name = id.name;
	$('.yonghuming').text(name);
	//角色权限管理的动态添加
	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');

		$.ajax({
			url: yh + "/user/getrole/" + param,
			method: 'GET',
			cache: false,
			dataType: "json",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},

			success: function(data) {
				if(data.success == true) {
					var ghtml = "";
					$.each(data.root, function(i, items) {

						ghtml += "<ul class='messageList' style='list-style:none'>"
						if(items["checked"]) {
							ghtml += "<li><div class='checkbox check-warning'><lable for='" + items["id"] + "'><input type='checkbox' id='" + items["id"] + "' checked>" + items["name"] + "</label></div></li>";
						} else {
							ghtml += "<li><div class='checkbox check-warning'><lable for='" + items["id"] + "'><input type='checkbox' id='" + items["id"] + "'>" + items["name"] + "</label></div></li>";
						}

						ghtml += "</ul>";

					});

					$('.span7').append(ghtml);

				}

			}

		});

	});

	function iFrameCity1() {
		setTimeout(iFrameCity, 1000);
	}

	function iFrameCity() {

		$('.edityonghu').click(function() {
			var ids = [];
			$.each($('input:checkbox:checked'), function(i, items) {

				//              if(this.checked){
				ids.push(items["id"]);

				//             }

			});

			$.ajax({
				url: yh + "/user/grant/" + param + "/" + ids,
				xhrFields: {
					withCredentials: true
				},

				crossDomain: true,
				method: 'GET',
				cache: false,
				dataType: "json"
			}).success(function(response) {
				if(response.success == true) {
					$('.ts').css('display', 'block');
					$('.ts').html("用户角色配权成功！");
					//                window.location.reload();

				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("用户角色配权失败！");

				}

			});

		})

	}
	iFrameCity1();

});