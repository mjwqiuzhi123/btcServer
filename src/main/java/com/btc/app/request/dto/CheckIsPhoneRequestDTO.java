package com.btc.app.request.dto;

import javax.validation.constraints.Pattern;

import com.btc.app.response.dto.UseVeriCodeResultDTO;

public class CheckIsPhoneRequestDTO
{

	  @Pattern(regexp="1[3|4|5|6|7|8][0-9]\\d{8}", message="手机号码格式不正确")
	  private String phone;

	  public String getPhone()
	  {
	    return this.phone;
	  }

	  public void setPhone(String phone) {
	    this.phone = phone;
	  }

	  public CheckIsPhoneRequestDTO()
	  {
	  }

	  public CheckIsPhoneRequestDTO(SendRequestDTO sendRequestDTO) {
	    this.phone = sendRequestDTO.getPhone();
	  }

	  public CheckIsPhoneRequestDTO(UseVeriCodeResultDTO useVeriCodeResultDTO) {
	    this.phone = useVeriCodeResultDTO.getPhone();
	  }
}