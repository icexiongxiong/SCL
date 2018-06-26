var Yewuyuan;
var parentId;
var jueseapp = angular.module('juese', ['CommonApp']);
jueseapp.controller('juesecontroller', function($scope, $http, URLParam) {
	Yewuyuan = $.cookie("Yewuyuan");
	$('.userName').text(Yewuyuan);
	//角色管理的动态添加

	$(function() {
		$("#content").removeAttr("style");
		$("#content").css('min-height', '1200px');
		$.ajax({
			url: yh + "/role/page",
			crossDomain: true,
			xhrFields: {
				withCredentials: true
			},
			method: 'POST',
			data: {
				start: 0,
				limit: 50
			},
			cache: false,
			dataType: "json"
		}).success(function(data) {
			if(data.success == true) {

				var ghtml = "";
				$.each(data.root, function(i, items) {

					//一级菜单循环
					ghtml += "<tr><td style='width:100px;text-align:center'>" + items["id"] + "</td><td class='center' style='width:400px;'>" + items["name"] + "</td><td> <a class='btn btn-info'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-description='" + items["description"] + "' class='editjuese white edit' style='width:75px'>修改</i></a><a class='btn btn-success' id='del'><i id='" + items["id"] + "' name='" + items["name"] + "' data-description='" + items["description"] + "'   class='cdqx white trash' style='width:75px'>角色访问菜单权限</i></a><a class='btn btn-danger' id='del'><i id='" + items["id"] + "' data-name='" + items["name"] + "' data-description='" + items["description"] + "'   class='deljuese white trash' style='width:75px'>删除</i></a></td>";

					ghtml += "</tr>";

				});

				$('.jscontent').append(ghtml);

			}

		});

	});

	function iFrameCity1() {
		setTimeout(iFrameCity, 1000);
	}

	function iFrameCity() {

		$('.editjuese').click(function() {
			var id = $(this).attr('id');

			window.location.href = "editjuese.html?id=" + id;

		})

		$('.deljuese').click(function() {
			var id = $(this).attr('id');

			$.ajax({
				url: yh + "/role/delete/" + id,
				xhrFields: {
					withCredentials: true
				},

				crossDomain: true,
				method: 'GET',
				cache: false,
				dataType: "json"
			}).success(function(response) {
				if(response.success == true) {
					window.location.reload();

				} else {
					$('.ts').css('display', 'block');
					$('.ts').html("删除角色失败");

				}

			});

		})

		$('.cdqx').click(function() {
			var id = $(this).attr('id');
			var name = $(this).attr('name');
			window.location.href = "juesequanxian.html?id=" + id + "&name=" + name;

		})

	}
	iFrameCity1();

});