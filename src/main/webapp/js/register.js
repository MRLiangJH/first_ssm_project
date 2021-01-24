// 校验用户名
function checkUsername() {
    var flag;
    $.ajaxSetup({async: false});
    $.post("/user/checkUser", $("#input_username").serialize(), function (data) {
        if (!data.success) {
            $("#username_msg").text(data.error);
            flag = false;
        } else {
            flag =true;
        }
    });
    return flag;
}
function clearUsernameMsg() {
    $("#username_msg").text(null);
}

// 校验邮箱
function checkEmail() {
    const email = $("#input_email").val();
    const reg_email = /^\w+@\w+.\w+$/;
    const flag = reg_email.test(email);
    if (!flag) {
        $("#input_email").css("border", "1px solid red");
        $("#email_msg").text("邮箱输入有误");
    }
    return flag;
}

function clearEmailMsg() {
    $("#input_email").css("border", "");
    $("#email_msg").text(null);
}

// 校验密码
function checkPassword() {
    const input_password = $("#input_password");
    const password = input_password.val();
    const reg_password = /^\w{6,20}$/;
    const flag = reg_password.test(password);
    if (!flag) {
        input_password.css("border", "1px solid red");
        $("#password_msg").text("请输入6-20位数的密码");
    }
    const conformPassword = $("#input_confirm_password").val();
    if (conformPassword != null) {
        if (checkConfirmPassword()) {
            clearConfirmPasswordMsg();
        }
    }
    return flag;
}

function clearPasswordMsg () {
    $("#input_password").css("border", "");
    $("#password_msg").text(null);
}

// 确认密码
function checkConfirmPassword() {
    const password = $("#input_password").val();
    const inputConfirmPassword = $("#input_confirm_password");
    const confirmPassword = inputConfirmPassword.val();
    if (confirmPassword !== password) {
        inputConfirmPassword.css("border", "1px solid red");
        $("#confirm_password_msg").text("确认密码有误");
        return false;
    }
    return true;
}

function clearConfirmPasswordMsg() {
    $("#input_confirm_password").css("border", "");
    $("#confirm_password_msg").text(null);
}

// 校验验证码
function check_Code() {
    const code = $("#input_code").val();
    const reg_checkCode = /^[0-9A-Za-z]{4}$/;
    const flag = reg_checkCode.test(code);
    if (!flag) {
        $("#input_code").css("border", "1px solid red");
    }
    return flag;
}
function clearCheckCodeMsg() {
    $("#input_code").css("border", "");
}

// submit
function check_Submit() {

    if (checkUsername() && checkEmail() && checkPassword() && checkConfirmPassword() && check_Code()) {

        $.post("/user/registerUser", $("#registerForm").serialize(), function (data) {

            if (data.success) {
                location.href = "register_ok.html";
            } else {
                $("#submit_msg").text(data.error);
                return false;
            }
        });

    }

    return false;
};

$(function () {

    $("#registerForm").on({submit:check_Submit});

    $("#input_username").on({focus:clearUsernameMsg, blur:checkUsername});

    $("#input_email").on({focus:clearEmailMsg, blur:checkEmail});

    // 密码校验
    $("#input_password").on({focus:clearPasswordMsg, blur: checkPassword});

    // 确认密码校验
    $("#input_confirm_password").on({focus:clearConfirmPasswordMsg, blur:checkConfirmPassword});

    $("#input_code").on({focus:clearCheckCodeMsg, blur:check_Code});

})