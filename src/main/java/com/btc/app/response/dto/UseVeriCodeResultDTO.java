package com.btc.app.response.dto;

import com.btc.app.util.CommonMsg;

public class UseVeriCodeResultDTO extends CommonMsg {
	private static final long serialVersionUID = 1L;
	private String phone;

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UseVeriCodeResultDTO() {
	}

	public UseVeriCodeResultDTO(String phone) {
		this.phone = phone;
	}
}