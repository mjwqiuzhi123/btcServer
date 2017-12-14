package com.btc.app.util;

import java.math.BigDecimal;
import java.util.UUID;

public class CommonUtil
{
  public static String WithHtmlUnderline(String value)
  {
    return "<u> " + value + " </u>";
  }

  public static String getSuffixOfBankCardNo(String bankCardNo) {
    int length = bankCardNo.length();
    if (length >= 4) {
      return bankCardNo.substring(length - 4);
    }
    return "";
  }

  public static String getUUID() {
    UUID uuid = UUID.randomUUID();
    String str = uuid.toString();
    return str.replace("-", "");
  }

  public static String BatchNo() {
    UUID uuid = UUID.randomUUID();
    String str = uuid.toString();
    return str.replace("-", "").substring(0, 20).toUpperCase();
  }

  public static String encryptCredentialNo(String credentialNo)
  {
    int length = credentialNo.length();

    if (length > 10) {
      String symbol = "";
      for (int i = 0; i < length - 10; i++) {
        symbol = symbol + "*";
      }

      return credentialNo.substring(0, 6) + symbol + credentialNo.substring(length - 4);
    }
    return credentialNo;
  }

  public static String contentShield(String content, String type)
  {
    String formatContent = null;
    if (type.equals("phone")) {
      formatContent = content.substring(0, content.length() - content.substring(3).length()) + "****" + content.substring(7);
    }

    if (type.equals("name")) {
      formatContent = "*" + content.substring(1);
    }
    return formatContent;
  }

  public static BigDecimal amountToFloor2(BigDecimal amount) {
    return amount.setScale(2, 1);
  }

  public static BigDecimal amountToFloor4(BigDecimal amount) {
    return amount.setScale(4, 1);
  }

  public static int convertToInt(String value) {
    if ((value == null) || (value.isEmpty())) {
      return 0;
    }
    return Integer.parseInt(value);
  }

  public static char getRandomChar() {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return chars.charAt((int)(Math.random() * 26.0D));
  }
}