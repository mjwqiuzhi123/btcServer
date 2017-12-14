package com.btc.app.response.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SendMsgDTOResult
{
  private int messageType;
  private String phone;
  private String code;

  public int getMessageType()
  {
    return this.messageType;
  }

  public void setMessageType(int messageType) {
    this.messageType = messageType;
  }

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

  public SendMsgDTOResult(int messageType, String phone, String code) {
    this.messageType = messageType;
    this.phone = phone;
    this.code = code;
  }

  public SendMsgDTOResult()
  {
  }

  public String toJson() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }
}