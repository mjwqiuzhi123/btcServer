package com.btc.app.service;


import java.util.List;

import com.btc.app.bean.UserBean;
import com.btc.app.bean.UserModel;
import com.btc.app.request.dto.CheckIsPhoneRequestDTO;
import com.btc.app.request.dto.LoginOnDTO;
import com.btc.app.request.dto.PageParameter;
import com.btc.app.request.dto.UserLoginRequestDTO;
import com.btc.app.request.dto.UserRegisterRequestDTO;
import com.btc.app.response.dto.UseVeriCodeResultDTO;
import com.btc.app.util.ResponseEntity;

public interface UserService {
    int insertUser(UserBean user);
    UserBean testConnection();
    UserBean searchUser(String phone);
    //add by mjw
    UserModel searchAdminByNameAndPwd(LoginOnDTO loginOnDTO);
    List<UserModel> selectUserList(UserModel userModel, PageParameter parameter);
    public boolean searcUserByPhone(CheckIsPhoneRequestDTO checkIsPhoneRequestDTO);
    public boolean saveUser(UserRegisterRequestDTO userRegisterRequestDTO, UseVeriCodeResultDTO useVeriCodeResultDTO);
    public ResponseEntity resetLoginPassword(String phone, String password, String token);
    public ResponseEntity searchUserByNameAndPwd(UserLoginRequestDTO userLoginRequestDTO, StringBuilder userIdentifier, StringBuilder cellphone);
    //add by mjw
}
