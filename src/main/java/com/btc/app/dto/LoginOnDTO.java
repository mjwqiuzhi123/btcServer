package com.btc.app.dto;

import javax.validation.constraints.NotNull;

public class LoginOnDTO
{

	  @NotNull(message="账号不能为空")
	  private String adminName;

	  @NotNull(message="密码不能为空")
	  private String adminPwd;

	  public String getAdminName()
	  {
	    return this.adminName;
	  }

	  public void setAdminName(String adminName) {
	    this.adminName = adminName;
	  }

	  public String getAdminPwd() {
	    return this.adminPwd;
	  }

	  public void setAdminPwd(String adminPwd) {
	    this.adminPwd = adminPwd;
	  }

	  public LoginOnDTO(String adminName, String adminPwd) {
	    this.adminName = adminName;
	    this.adminPwd = adminPwd;
	  }

	  public LoginOnDTO()
	  {
	  }
}