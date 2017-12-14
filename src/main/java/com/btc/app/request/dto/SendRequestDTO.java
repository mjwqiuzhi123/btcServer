package com.btc.app.request.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.btc.app.util.CommonMsg;

public class SendRequestDTO extends CommonMsg
{
	  private static final long serialVersionUID = 1L;

	  @Pattern(regexp="1[3|4|5|6|7|8][0-9]\\d{8}", message="手机号码格式不正确")
	  private String phone;

	  @NotNull(message="验证类型不能为空")
	  @Min(1L)
	  @Max(4L)
	  private int type;

	  public String getPhone()
	  {
	    return this.phone;
	  }

	  public void setPhone(String phone) {
	    this.phone = phone;
	  }

	  public int getType()
	  {
	    return this.type;
	  }

	  public void setType(int type) {
	    this.type = type;
	  }
}