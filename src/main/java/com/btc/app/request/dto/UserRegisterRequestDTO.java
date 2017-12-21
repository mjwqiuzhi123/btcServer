package com.btc.app.request.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterRequestDTO {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户名不能为空")
	@Pattern(regexp = "[A-Za-z0-9~!@#$%^&*.]{6,50}", message = "用户名必须是6位到50位之间")
	private String username;
	@Pattern(regexp = "[A-Za-z0-9~!@#$%^&*.]{6,18}", message = "密码必须是6位到18位之间")
	private String password;
	private String address;
	@Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
	private String email;
	private boolean sex;
	private String token;
	private String code = "";
	private String invitationCode;

	public String getInvitationCode() {
		return this.invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public UserRegisterRequestDTO(String password, String token, String code) {
		this.password = password;
		this.token = token;
		this.code = code;
	}

	public UserRegisterRequestDTO(String password, String token, String code,
			String invitationCode) {
		this.password = password;
		this.token = token;
		this.code = code;
		this.invitationCode = invitationCode;
	}

	public UserRegisterRequestDTO() {
	}
}