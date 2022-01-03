package com.wjj.studentmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjj.studentmanager.common.Constant;
import com.wjj.studentmanager.request.UpdatePasswordReq;
import com.wjj.studentmanager.common.ResponseEnum;
import com.wjj.studentmanager.common.ServerResponse;
import com.wjj.studentmanager.entity.User;
import com.wjj.studentmanager.filter.UserFilter;
import com.wjj.studentmanager.mapper.UserMapper;
import com.wjj.studentmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse regist(String username, String password) {
        try {
            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            if (username == null || password == null){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.INVALID_PARAM);
            }
            userQueryWrapper.eq("username",username);
            User user = userMapper.selectOne(userQueryWrapper);
            if (user != null){
                return ServerResponse.getInstance().responseEnum(ResponseEnum.USERNAME_EXIST);
            }
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.insert(user);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.REGISTE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.getInstance().responseEnum(ResponseEnum.INNER_ERROR);
        }
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @Override
    public ServerResponse login(String username, String password, HttpSession session) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username).eq("password",password);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_FAILED);
        }
        user.setPassword(null);
        session.setAttribute(Constant.STUDENT_MANAGER_USER,user);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.LOGIN_SUCCESS);
    }

    /**
     * 修改密码
     * @param updatePasswordReq
     * @return
     */
    @Override
    public ServerResponse updatePassword(UpdatePasswordReq updatePasswordReq) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", UserFilter.user.getId()).eq("password",updatePasswordReq.getPassword());
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null){
            return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_FAILED);
        }
        user.setPassword(updatePasswordReq.getNewPassword1());
        userMapper.updateById(user);
        return ServerResponse.getInstance().responseEnum(ResponseEnum.UPDATE_SUCCESS);
    }

    @Override
    public boolean checkAdminRole(User user) {
        return user.getRole().equals(2);
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @Override
    public ServerResponse adminLogin(String username, String password, HttpSession session) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username).eq("password",password);
        User user = userMapper.selectOne(userQueryWrapper);
        UserServiceImpl userService = new UserServiceImpl();
        if (userService.checkAdminRole(user)){
            //是管理员，执行操作
            //保存用户信息时不保存密码
            user.setPassword(null);
            session.setAttribute(Constant.STUDENT_MANAGER_USER,user);
            return ServerResponse.getInstance().responseEnum(ResponseEnum.GET_SUCCESS).data(user);
        }else{
            return ServerResponse.getInstance().responseEnum(ResponseEnum.NEED_ADMIN);
        }
    }


}
