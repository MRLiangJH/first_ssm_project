package com.atSCUT.controller;

import com.atSCUT.domain.User;
import com.atSCUT.service.UserManage;
import com.atSCUT.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserManage userManage;

    public UserController(UserManage userManage) {
        this.userManage = userManage;
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.POST, produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Result<Object> isUserExist(@Param("username") String username) {
        Result<Object> result = new Result<>();
        if (username == null || username.length() == 0) {
            result.setSuccess(false);
            result.setError("用户名不能为空");
            return result;
        }
        System.out.println(username);
        logger.trace(username);
        System.out.println("checkUSer-----------");
        boolean userExist = userManage.isUserExist(username);

        if (userExist) {
            result.setSuccess(false);
            result.setError("该用户已存在");
        } else {
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public Result<User> registerUser(User user, @Param("checkCode") String checkCode ,HttpSession session) {
        String checkCode_img = (String) session.getAttribute("checkCode_key");
        System.out.println(checkCode_img);
        System.out.println(checkCode);
        session.removeAttribute("checkCode_key");
        Result<User> result = new Result<>();
        if (checkCode_img == null || !checkCode_img.equalsIgnoreCase(checkCode)) {
            result.setSuccess(false);
            result.setError("验证码有误");
            return result;
        }

        user.setRegister_time(new Date());
//        result.setData(user);
        boolean b = userManage.registerUser(user);
        if (b) {
            // 注册成功
            result.setSuccess(true);
        } else {
            // 注册失败
            result.setError("注册失败");
            result.setSuccess(false);
        }
        return result;
    }
}
