define(function (require, exports, module) {

    var $ = require('../js/jquery.min-cmd');
    $.md5 = require('../js/jq-md5-cmd');

    var init = (function () {

        // 初始化的时候监听键盘事件
        document.onkeydown = function (e) {
            var event = e || window.event || arguments.callee.caller.arguments[0];
            if (event && event.keyCode == 13) {
                $login.click();
            }
        };

        var loginModule = {
            btnLogin: null,
            randomString: null,

            init: function () {
                var _this = this;
            },

            checkAcount: function () {
                var _this = this;
            },
            checkVerifyCode: function () {
                var _this = this;
            }
        };

        var dialogModule = {
            materialCallback: null,

            materialAlert: function (title, text, callback) {
                document.getElementById('materialModalTitle').innerHTML = title;
                document.getElementById('materialModalText').innerHTML = text;
                document.getElementById('materialModalButtonCANCEL').style.display = 'none';
                document.getElementById('materialModal').className = 'show';
                materialCallback = callback;
            },

            materialConfirm: function (title, text, callback) {
                materialAlert(title, text, callback);
                document.getElementById('materialModalButtonCANCEL').style.display = 'block';
            },

            closeMaterialAlert: function (e, result) {
                e.stopPropagation();
                document.getElementById('materialModal').className = 'hide';
                if (typeof materialCallback == 'function') materialCallback(result);
            }
        };
    });
});


function checkVerifyCode() {

}