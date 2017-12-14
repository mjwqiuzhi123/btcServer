package com.btc.app.request.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserLoginRequestDTO
{

  @Pattern(regexp="1[3|4|5|6|7|8][0-9]\\d{8}", message="手机号码格式不正确")
  private String phone;

  @NotNull(message="密码不能为空")
  private String password;

  public String getPhone()
  {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserLoginRequestDTO(String phone, String password) {
    this.phone = phone;
    this.password = password;
  }

  public UserLoginRequestDTO()
  {
  }
}