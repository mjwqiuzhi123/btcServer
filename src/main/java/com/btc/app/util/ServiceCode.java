package com.btc.app.util;

import org.apache.commons.lang3.StringUtils;

public enum ServiceCode
{
	  PARAMETER_ERROR("400", "输入参数错误"), 
	  SING_USER_COOKIE_NAME_CODE("401", "未授权: (Unauthorized)"), 
	  SING_IN_REPONSE_ONE("500", "该用户不存在"), 
	  SING_IN_REPONSE_FIVE("501", "用户已存在"), 
	  SING_IN_REPONSE_TWO("502", "手机号或密码输入错误,请重新输入"), 
	  SING_IN_REPONSE_THREE("503", "用户被锁定"), 
	  INTERVAL_NOT_ENOUGH("510", "时间间隔不足"), 
	  DATABASE_UPDATE_ERROR("601", "数据库更新错误"), 
	  SEARCHBYINDENTIFIERANDTYPE_THREE("10006", "没有找到短信发送记录"), 
	  SEARCHBYINDENTIFIERANDTYPE_FOUR("10007", "未知错误"), 
	  SIGNUPASYNC_ONE("10008", "用户密码加密失败"), 
	  REGISTER_ONE("10012", "此号码已注册，请选择登录，如果您忘记密码，请选择重置密码"), 
	  CHECKCELLPHONE_ONE("10022", "此用户不存在"), 
	  VERIFYVERIASYNC_ONE("20004", "没有找到短信验证码发送记录"), 
	  VERIFYVERIASYNC_TWO("20005", "验证短信验证码超过错误次数，请重新获取"), 
	  CHECKCELLPHONE_TWO("10023", "此用户存在"), 
	  SNEDWITHTOKENASYNC_THREE("10003", "短信已超过当天发送次数"), 
	  SEARCHBYINDENTIFIERANDTYPE_ONE("10004", "短信验证码没有被验证，请先验证短信验证码"), 
	  SEARCHBYINDENTIFIERANDTYPE_TWO("10005", "短信验证码已经被使用，请重新获取"), 
	  VERIFYVERIASYNC_THREE("20006", "短信证码验证错误"), 
	  SAVEORDER_ONE("20006", "该产品不存在"), 
	  ERROR("9999", "操作失败"), 
	  SUCCESS("0000", "操作成功"), 
	  EXCEPTION("5555", "服务端异常"), 
	  RECORD_NOT_FOUND("550", "没有找到记录");

	  private String code;
	  private String text;

	  private ServiceCode(String code, String text)
	  {
	    this.code = code;
	    this.text = text;
	  }

	  public String getCode() {
	    return this.code;
	  }

	  public void setCode(String code) {
	    this.code = code;
	  }

	  public String getText() {
	    return this.text;
	  }

	  public void setText(String text) {
	    this.text = text;
	  }

	  public static ServiceCode getEnums(String code)
	  {
	    for (ServiceCode enums : values()) {
	      if (StringUtils.equalsIgnoreCase(code, enums.getCode())) {
	        return enums;
	      }
	    }
	    return null;
	  }
}