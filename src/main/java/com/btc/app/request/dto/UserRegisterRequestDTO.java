package com.btc.app.request.dto;

import javax.validation.constraints.Pattern;

public class UserRegisterRequestDTO
{
	  private static final long serialVersionUID = 1L;

	  @Pattern(regexp="[A-Za-z0-9~!@#$%^&*.]{6,18}", message="密码必须是6位到18位之间")
	  private String password;
	  private String token;
	  private String code = "";
	  private String invitationCode;

	  public String getInvitationCode()
	  {
	    return this.invitationCode;
	  }

	  public void setInvitationCode(String invitationCode) {
	    this.invitationCode = invitationCode;
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

	  public UserRegisterRequestDTO(String password, String token, String code)
	  {
	    this.password = password;
	    this.token = token;
	    this.code = code;
	  }

	  public UserRegisterRequestDTO(String password, String token, String code, String invitationCode)
	  {
	    this.password = password;
	    this.token = token;
	    this.code = code;
	    this.invitationCode = invitationCode;
	  }

	  public UserRegisterRequestDTO()
	  {
	  }
}