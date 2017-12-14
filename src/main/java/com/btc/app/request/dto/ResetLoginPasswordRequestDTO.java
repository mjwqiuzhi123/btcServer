package com.btc.app.request.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.btc.app.util.CommonMsg;

public class ResetLoginPasswordRequestDTO extends CommonMsg
{
	  private static final long serialVersionUID = 1L;

	  @Pattern(regexp="[A-Za-z0-9~!@#$%^&*.]{6,18}", message="密码必须是6位到18位之间")
	  private String password;

	  @NotNull
	  private String token;

	  public String getPassword()
	  {
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

	  public ResetLoginPasswordRequestDTO(String password, String token)
	  {
	    this.password = password;
	    this.token = token;
	  }

	  public ResetLoginPasswordRequestDTO()
	  {
	  }
}