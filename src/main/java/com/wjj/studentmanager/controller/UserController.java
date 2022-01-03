package com.wjj.studentmanager.controller;

import com.wjj.studentmanager.common.ResponseEnum;
import com.wjj.studentmanager.request.UpdatePasswordReq;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public ServerResponse login(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 HttpSession session){
        //参数效验
        if (username == null || "".equals(username)){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEED_USER_NAME);
        }
        if (password == null || "".equals(password)){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEED_PASSWORD);
        }
        return userService.login(username,password,session);
    }

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/regist")
    public ServerResponse regist(@RequestParam("username") String username,
                                  @RequestParam("password") String password
                                  ){
        //参数效验
        if (username == null || "".equals(username)){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEED_USER_NAME);
        }
        if (password == null || "".equals(password)){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEED_PASSWORD);
        }
        return userService.regist(username,password);
    }

    /**
     * 修改密码
     * @param updatePasswordReq
     * @return
     */
    @PostMapping("/updatePassword")
    public ServerResponse updatePassword(@RequestBody UpdatePasswordReq updatePasswordReq){
        //参数效验
        if (updatePasswordReq.getPassword().equals(updatePasswordReq.getNewPassword1())){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.PASSWORD_SAME);
        }
        if (!updatePasswordReq.getNewPassword1().equals(updatePasswordReq.getNewPassword2())){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEW_PASSWORD_SAME);
        }
        return userService.updatePassword(updatePasswordReq);
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/adminLogin")
    public ServerResponse adminLogin(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     HttpSession session){
        //参数效验
        //参数效验
        if (username == null || "".equals(username)){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEED_USER_NAME);
        }
        if (password == null || "".equals(password)){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEED_PASSWORD);
        }
        return userService.adminLogin(username, password, session);
    }
}
