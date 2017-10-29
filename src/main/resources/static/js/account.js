$(document).ready(function () {

    // 登录注册按钮
    var $btnLogin = $('#btn-login');
    var $btnRegister = $('#btn-register');

    // 监听键盘Enter事件（登录）
    document.onkeydown = function (event) {
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if (e && e.keyCode == 13) {
            $btnLogin.click();
        }
    };

    $btnLogin.on('click', function () {

        // 三个登录用的数据
        var login_username = $('#login-username').val();
        var login_password = $('#login-password').val();
        var login_verify_code = $('#login-verify-code').val();

        // if (checkUsername(login_username) && checkPassword(login_password) && checkVerifyCode(login_verify_code)) {
        $.ajax({
            type: "post",
            async: false,
            datatype: "json",
            data: {
                'username': login_username,
                'password': login_password,
                'verify-code': login_verify_code
            },
            url: "/doLogin",
            success: function (data) {
                console.log(data);
                console.log('success');
            },
            error: function (data) {
                console.log('ERROR');
            }
        });
        // }
    });

    $btnRegister.on('click', function () {

        // 四个注册用的数据
        var register_username = $('#register-username').val();
        var register_password = $('#register-password').val();
        var register_confirm = $('#register-confirm').val();
        var register_verify_code = $('#register-verify-code').val();

        $.ajax({
            type: "post",
            async: false,
            datatype: "json",
            data: {
                'username': register_username,
                'password': register_password,
                'confirm': register_confirm,
                'verifyCode': register_verify_code
            },
            url: "/doRegister",
            success: function (data) {
                console.log(data);
                console.log('success');
            },
            error: function (data) {
                console.log(data);
                console.log('ERROR');
            }
        });
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