package com.btc.app.dao;

import java.util.List;
import java.util.Map;

import com.btc.app.bean.UserBean;
import com.btc.app.bean.UserModel;

public interface UserMapper {
    int insert(UserBean user);
    UserBean searchUser(String phone);
    UserBean testConnect();
    //add by mjw
    UserModel searcUserByPhone(UserModel paramUserModel);
    List<UserModel> getByPage(Map<String, Object> paramMap);
    //add by mjw
}
