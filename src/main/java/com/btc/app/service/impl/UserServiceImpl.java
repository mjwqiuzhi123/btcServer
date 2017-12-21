package com.btc.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.btc.app.bean.UserBean;
import com.btc.app.bean.UserModel;
import com.btc.app.dao.UserMapper;
import com.btc.app.request.dto.CheckIsPhoneRequestDTO;
import com.btc.app.request.dto.LoginOnDTO;
import com.btc.app.request.dto.PageParameter;
import com.btc.app.request.dto.UserLoginRequestDTO;
import com.btc.app.request.dto.UserRegisterRequestDTO;
import com.btc.app.response.dto.UseVeriCodeResultDTO;
import com.btc.app.response.dto.UserLoginDTOResult;
import com.btc.app.service.UserService;
import com.btc.app.util.CommonUtil;
import com.btc.app.util.DateUtil;
import com.btc.app.util.PasswordHash;
import com.btc.app.util.ResponseEntity;
import com.btc.app.util.ServiceCode;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	//add by mjw
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	//add by mjw
	
	@Resource
	UserMapper userDao;
	private WeakHashMap<String, UserBean> userMap = new WeakHashMap<String, UserBean>();

	public int insertUser(UserBean user) {
		return userDao.insert(user);
	}

	public UserBean testConnection() {
		return userDao.testConnect();
	}

	public UserBean searchUser(String phone) {
		if (userMap.containsKey(phone)) {
			return userMap.get(phone);
		} else {
			UserBean bean = userDao.searchUser(phone);
			if (bean != null) {
				userMap.put(phone, bean);
				return bean;
			}
			return null;
		}
	}

	//start by mjw
	public ResponseEntity searchUserByNameAndPwd(UserLoginRequestDTO userLoginRequestDTO, StringBuilder userIdentifier, StringBuilder cellphone) { 
		ResponseEntity responseEntity = new ResponseEntity();
	    try {
	      UserModel userModel = this.userDao.searcUserByPhone(new UserModel(userLoginRequestDTO));

	      if (userModel == null) {
	        UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(0), false, false);
	        responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_ONE);
	        responseEntity.addProperty(resultDTO);
	        return responseEntity;
	      }

	      // 每天登陆失败不超过5次
	      if (userModel.getLastfailedsignintime() != null)
	      {
	        if ((DateUtil.GetDate(userModel.getLastfailedsignintime()).equals(DateUtil.GetDate(new Date()))) && (userModel.getLoginfailedcount() >= 4)) {
	          UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(0), true, true);
	          responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_THREE);
	          responseEntity.addProperty(resultDTO);
	          return responseEntity;
	        }

	      }
	      
	      String encryptPassword = PasswordHash.createHash(userLoginRequestDTO.getPassword(), userModel.getSalt());
	      if (!userModel.getPassword().equals(encryptPassword))
	      {
	        if (userModel.getLastfailedsignintime() != null)
	        {
	          if (DateUtil.GetDate(userModel.getLastfailedsignintime()).equals(DateUtil.GetDate(new Date())))
	          {
	            userModel.setLoginfailedcount(userModel.getLoginfailedcount() + 1); 
	            //break label220; MJW
	          }
	          else{
	        	  userModel.setLoginfailedcount(1);  
	          }
	        }
	        //label220: userModel.setLastfailedsignintime(new Date());
	        userModel.setLastfailedsignintime(new Date());
	        this.userDao.updateByEntity(userModel);

	        UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(5 - userModel.getLoginfailedcount()), true, false);
	        responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_TWO);
	        responseEntity.addProperty(resultDTO);
	        return responseEntity;
	      }

	      userModel.setLastsuccesssignintime(new Date());
	      userModel.setLoginfailedcount(0);
	      this.userDao.updateByEntity(userModel);
	      UserLoginDTOResult resultDTO = new UserLoginDTOResult(Integer.valueOf(5), true, false);
	      userIdentifier.append(userModel.getUseridentifier());
	      cellphone.append(userModel.getPhone());
	      responseEntity.addProperty(resultDTO);
	      return responseEntity;
	    } catch (Exception e) {
	      logger.error(new StringBuilder().append("根据用户名和密码查询用户失败失败的原因是:").append(e.getMessage()).toString());
	      logger.error(new StringBuilder().append("参数phone = ").append(userLoginRequestDTO).toString() == null ? null : userLoginRequestDTO.getPhone());
	      logger.error(new StringBuilder().append("参数password = ").append(userLoginRequestDTO).toString() == null ? null : userLoginRequestDTO.getPassword());
	      responseEntity.setMsg(ServiceCode.EXCEPTION);
	    }return responseEntity;
	  }
	
	public UserModel searchAdminByNameAndPwd(LoginOnDTO loginOnDTO) {
		UserModel userModel = null;
		try {
			userModel = this.userDao.searcUserByPhone(new UserModel(loginOnDTO));
			String encryptPassword = PasswordHash.createHash(loginOnDTO.getAdminPwd(), userModel.getSalt());
			if (!userModel.getPassword().equals(encryptPassword))
				return null;
		} catch (Exception e) {
			logger.error(new StringBuilder().append("管路员登陆异常-----------error--------").append(e.getMessage()).toString());
		}
		return userModel;
	}
	
	public List<UserModel> selectUserList(UserModel userModel, PageParameter parameter) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("t", userModel);
			map.put("page", parameter);
			return this.userDao.getByPage(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(new StringBuilder().append("用户列表展示操作异常---原因是-----:").append(e.getMessage()).toString());
		}
		return null;
	}
	
	public boolean searcUserByPhone(CheckIsPhoneRequestDTO checkIsPhoneRequestDTO) {
	    try {
	      return this.userDao.searcUserByPhone(new UserModel(checkIsPhoneRequestDTO)) == null;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error(new StringBuilder().append("用户手机号检查失败失败的原因是:").append(e.getMessage()).toString());
	      logger.error(new StringBuilder().append("参数phone = ").append(checkIsPhoneRequestDTO).toString() == null ? null : checkIsPhoneRequestDTO.getPhone());
	    }return false;
	  }
	
	public boolean saveUser(UserRegisterRequestDTO userRegisterRequestDTO, UseVeriCodeResultDTO useVeriCodeResultDTO) {
	    try {
	      String salt = CommonUtil.getUUID();
	      String userIdentifier = CommonUtil.getUUID();
	      String password = userRegisterRequestDTO.getPassword();
	      String saltPassword = PasswordHash.createHash(password, salt);
	      userRegisterRequestDTO.setPassword(saltPassword);
	      return this.userDao.saveUser(new UserModel(useVeriCodeResultDTO, salt, userIdentifier, userRegisterRequestDTO));
	    } catch (Exception e) {
	      logger.error(new StringBuilder().append("用户注册失败失败的原因是:").append(e.getMessage()).toString());
	      logger.error(new StringBuilder().append("参数phone = ").append(userRegisterRequestDTO).toString() == null ? null : useVeriCodeResultDTO.getPhone());
	      logger.error(new StringBuilder().append("参数password = ").append(userRegisterRequestDTO).toString() == null ? null : userRegisterRequestDTO.getPassword());
	    }return false;
	  }
	
	public ResponseEntity resetLoginPassword(String phone, String password, String token)
	  {
	    ResponseEntity messageResult = new ResponseEntity();
	    UserModel user = this.userDao.searcUserByPhone(new UserModel(phone));

	    if (user == null) {
	      messageResult.setMsg(ServiceCode.SING_IN_REPONSE_ONE);
	      return messageResult;
	    }

	    String encryptedpassword = null;
	    try {
	      encryptedpassword = PasswordHash.createHash(password, user
	        .getSalt());
	    } catch (Exception e) {
	      logger.error(new StringBuilder().append("【注册】用户密码加密失败;操作类是VericodesServiceI操作方法是SignUpAsync失败的原因是:")
	        .append(e
	        .getMessage()).toString());
	      messageResult.setMsg(ServiceCode.SIGNUPASYNC_ONE);
	      return messageResult;
	    }

	    user.setPassword(encryptedpassword);
	    user.setLoginfailedcount(0);

	    Integer returnCode = this.userDao.updateByEntity(user);
	    if (returnCode.intValue() <= 0) {
	      logger.error(new StringBuilder().append("交易密码保存失败,调用DAO的方法是:update操作接口是UsersDao是,入参是:")
	        .append(user
	        .toString()).toString());
	      messageResult.setMsg(ServiceCode.DATABASE_UPDATE_ERROR);
	      return messageResult;
	    }

	    return messageResult;
	  }	
	//add by mjw
}
