package com.btc.app.bean;

import java.io.Serializable;
import java.util.Date;

import com.btc.app.request.dto.CheckIsPhoneRequestDTO;
import com.btc.app.request.dto.LoginOnDTO;
import com.btc.app.request.dto.UserLoginRequestDTO;
import com.btc.app.request.dto.UserRegisterRequestDTO;
import com.btc.app.response.dto.UseVeriCodeResultDTO;

public class UserModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String useridentifier;
	private String username;
	private String phone;
	private String salt;
	private String password;
	private String address;
	private String email;
	private boolean sex;
	private int status;
	private Date lastfailedsignintime;
	private int loginfailedcount;
	private Date lastsuccesssignintime;
	private Date signuptime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserModel() {
	}

	public UserModel(LoginOnDTO loginonDTO) {
		this.phone = loginonDTO.getAdminName().trim();
		this.password = loginonDTO.getAdminPwd().trim();
	}
	
	public UserModel(CheckIsPhoneRequestDTO checkIsPhoneRequestDTO) {
		this.phone = (checkIsPhoneRequestDTO.getPhone() != null ? checkIsPhoneRequestDTO
				.getPhone().trim() : null);
	}
	
	public UserModel(UseVeriCodeResultDTO useVeriCodeResultDTO, String salt, String userIdentifier, UserRegisterRequestDTO userRegisterRequestDTO) {
		this.phone = useVeriCodeResultDTO.getPhone();
		this.salt = salt;
		this.useridentifier = userIdentifier;
		this.username = userRegisterRequestDTO.getUsername();
		this.password = userRegisterRequestDTO.getPassword();
		this.address = userRegisterRequestDTO.getAddress();
		this.email = userRegisterRequestDTO.getEmail();
		this.sex = userRegisterRequestDTO.isSex();
		this.status = 1;
	}
	
	public UserModel(UserLoginRequestDTO userLoginRequestDTO) {
		this.phone = userLoginRequestDTO.getPhone();
		this.password = userLoginRequestDTO.getPassword();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUseridentifier() {
		return this.useridentifier;
	}

	public void setUseridentifier(String useridentifier) {
		this.useridentifier = useridentifier;
	}

	public Date getLastfailedsignintime() {
		return this.lastfailedsignintime;
	}

	public void setLastfailedsignintime(Date lastfailedsignintime) {
		this.lastfailedsignintime = lastfailedsignintime;
	}

	public int getLoginfailedcount() {
		return this.loginfailedcount;
	}

	public void setLoginfailedcount(int loginfailedcount) {
		this.loginfailedcount = loginfailedcount;
	}

	public Date getLastsuccesssignintime() {
		return this.lastsuccesssignintime;
	}

	public void setLastsuccesssignintime(Date lastsuccesssignintime) {
		this.lastsuccesssignintime = lastsuccesssignintime;
	}

	public UserModel(String phone) {
		this.phone = phone;
	}

	public Date getSignuptime() {
		return this.signuptime;
	}

	public void setSignuptime(Date signuptime) {
		this.signuptime = signuptime;
	}
}