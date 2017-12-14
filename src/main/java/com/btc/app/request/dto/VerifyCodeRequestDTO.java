package com.btc.app.request.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.btc.app.util.CommonMsg;

public class VerifyCodeRequestDTO extends CommonMsg {
	private static final long serialVersionUID = 4684375736110070961L;

	@Pattern(regexp = "1[3|4|5|6|7|8][0-9]\\d{8}", message = "手机号码格式不正确")
	private String phone;

	@NotNull(message = "手机验证码不能为空")
	@Length(min = 6, max = 6, message = "手机验证码值是6位")
	private String code;

	@NotNull(message = "验证类型不能为空")
	@Min(1L)
	@Max(4L)
	private int type;

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}
}