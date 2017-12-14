package com.btc.app.constant;

import com.btc.app.util.SystemProperty;

public class MessageConstant
{
  public static String verifyCodeTemplate = SystemProperty.getProperty("template.sms.verifyCode");
  public static String registerTemplate = SystemProperty.getProperty("template.sms.register");
  public static final String MSGAUTHOR_DATE = "30";
  public static final String MSGAUTHOR_TYPE = "10";
  public static int SMS_VALID_MINUTES = 30;
  public static final String MSGREGISTER_TYPE = "20";
  public static final String SMS_KEY = "sms_key";
}