package com.btc.app.service;


import java.util.List;

import com.btc.app.bean.UserBean;
import com.btc.app.bean.UserModel;
import com.btc.app.dto.LoginOnDTO;
import com.btc.app.dto.PageParameter;

public interface UserService {
    int insertUser(UserBean user);
    UserBean testConnection();
    UserBean searchUser(String phone);
    //add by mjw
    UserModel searchAdminByNameAndPwd(LoginOnDTO loginOnDTO);
    List<UserModel> selectUserList(UserModel userModel, PageParameter parameter);
    //add by mjw
}
