package com.btc.app.bean;

import java.io.Serializable;
import java.util.Date;

import com.btc.app.request.dto.LoginOnDTO;

public class UserModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String useridentifier;
	private String phone;
	private String salt;
	private String password;
	private int status;
	private Date lastfailedsignintime;
	private int loginfailedcount;
	private Date lastsuccesssignintime;
	private Date signuptime;

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