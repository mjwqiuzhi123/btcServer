package com.btc.app.service.impl;

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
import com.btc.app.dto.LoginOnDTO;
import com.btc.app.dto.PageParameter;
import com.btc.app.service.UserService;
import com.btc.app.util.PasswordHash;

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

	//add by mjw
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
	//add by mjw
}
