$(document).ready(function () {

    // 登录注册按钮
    var $btnLogin = $('#btn-login');
    var $btnRegister = $('#btn-register');

    // 三个登录用的数据
    var login_username = $('#login-username')();
    var login_password = $('#login-password')();
    var login_verify_code = $('#login-verify-code')();

    // 四个注册用的数据
    var register_username = $('#register-username')();
    var register_password = $('#register-password')();
    var register_confirm = $('#register-confirm')();
    var register_verify_code = $('#register-verify-code')();

    // 监听键盘Enter事件（登录）
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
            $btnLogin.click();
        }
    };

    $btnLogin.on('click', function () {

        alert(login_username.val() + ", " + login_password.val() + ", " + login_verify_code.val());

        if (checkUsername(login_username.val()) && checkPassword(login_password.val()) && checkVerifyCode(login_verify_code.val())) {
            $.ajax({
                type: "post",
                async: false,
                datatype: "json",
                data: {
                    'account': login_username,
                    'password': login_password,
                    'verify-code': login_verify_code,
                },
                url: "/doLogin",
                success: function (data) {
                    console.log('success');
                },
                error: function (data) {
                    console.log('ERROR');
                }
            });
        }
    });

    $btnRegister.on('click', function () {
        if (checkUsername(register_username.val())
            && checkPassword(register_password.val())
            && confirmPassword(register_password.val(), register_confirm.val())
            && checkVerifyCode(register_verify_code.val())) {
            $.ajax({
                type: "post",
                async: false,
                datatype: "json",
                data: {
                    'account': register_username.val(),
                    'password': register_password.val(),
                    'confirm': register_confirm.val(),
                    'verify-code': $('#check-code').val(),
                },
                url: "/doRegister",
                success: function (data) {
                    console.log('success');
                },
                error: function (data) {
                    console.log('ERROR');
                }
            });
        }
    });
});


/**
 * 登录结果处理
 * @param data
 */
function processLoginResult(data) {
    console.log(data);
}

function checkUsername(username) {

    if (!username.match("^\\d{n}$")) {
        return false;
    }
    return true;
}

function confirmPassword(password, confirm) {
    if (password.equals(confirm)) {
        return true;
    }
    return false;
}

function checkPassword(password) {
    if (!password.match("^\d{9}$")) {
        return false;
    }
    return true;
}

function checkVerifyCode(verfiycode) {
    if (verfiycode.length == 4) {
        return true
    }
}