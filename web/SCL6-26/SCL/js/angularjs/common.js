(function(){
    var app1 = angular.module('CommonApp', []);
// 自定义 URLParam 服务，解析URL 中的参数
    app1.service('URLParam', function ($location) {
        this.getParams = function (url) {
            var expParam = /[\?&].*?(?==)/g;
            var expValue = /=.*?(?=[#&])/g;

            url = url || $location.absUrl();
            url += '&';
            url = window.decodeURIComponent(url);
            if(url == null || url.indexOf('?') == -1) {
                return null;
            } else {
                var params = url.match(expParam);
                var values = url.match(expValue);

                var result = new Object();
                for (var i = 0; i < params.length; i++) {
                    if(params[i].charAt(1) != '$') {   // 过滤掉$开头的属性
                        result[params[i].substring(1)] = values[i].substring(1);
                    }
                }
                return result;
            }
        };
    });
    app1.filter('toHtml', function ($sce) {
        return function (str) {
            return $sce.trustAsHtml(str);
        };
    });
})();