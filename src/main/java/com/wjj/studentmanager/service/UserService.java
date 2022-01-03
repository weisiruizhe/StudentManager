package com.wjj.studentmanager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjj.studentmanager.request.UpdatePasswordReq;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {
    ServerResponse regist(String username, String password);

    ServerResponse login(String username, String password, HttpSession session);

    ServerResponse updatePassword(UpdatePasswordReq updatePasswordReq);

    boolean checkAdminRole(User user);

    ServerResponse adminLogin(String username, String password, HttpSession session);
}
