package com.btc.app.base64;

import java.io.PrintStream;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Base64Server
{
  byte[] iv = { 54, 31, 49, -90, -38, 32, 41, 70, -19, 71, 27, 36, 35, 34, 20, -33 };
  byte[] key = { -24, -31, 25, -60, 88, 9, 28, 74, -39, 35, 27, 36, 35, 52, 26, -7 };

  public String encrypted(String cellphone, String uuid, String DateTime)
    throws GeneralSecurityException
  {
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(1, new SecretKeySpec(this.key, "AES"), new IvParameterSpec(this.iv));

    String originalText = "cellphone=" + cellphone + "&uuid=" + uuid + "&DateTime=" + DateTime;

    byte[] encrypted = cipher.doFinal(originalText.getBytes());
    byte[] iv = cipher.getIV();
    return Base64Util.createEncryptedCookie(iv, encrypted);
  }

  public Map<String, Object> deEncrypted(String encryptedCookie)
    throws GeneralSecurityException
  {
    byte[] encryptedMessageBytes = Base64Util.extractMessage(encryptedCookie);

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(2, new SecretKeySpec(this.key, "AES"), new IvParameterSpec(this.iv));

    String decryptedCookie = new String(cipher
      .doFinal(encryptedMessageBytes));

    System.out.println("plainText: " + decryptedCookie);
    Map deResultMap = new HashMap();
    int startIndex = decryptedCookie.indexOf("cellphone") + 10;
    int endIndex = decryptedCookie.indexOf("&", startIndex);
    deResultMap.put("cellphone", decryptedCookie
      .substring(startIndex, endIndex));

    int startIndexU = decryptedCookie.indexOf("&uuid=") + 6;
    int endIndexU = decryptedCookie.indexOf("&DateTime=", startIndexU);
    deResultMap.put("uuid", decryptedCookie
      .substring(startIndexU, endIndexU));

    int startIndexDate = decryptedCookie.indexOf("&DateTime=") + 10;
    deResultMap.put("dateTime", decryptedCookie.substring(startIndexDate));
    return deResultMap;
  }
}