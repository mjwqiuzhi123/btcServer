package com.btc.app.base64;

import org.apache.commons.codec.binary.Base64;

public class Base64Util
{
  static byte[] extractMessage(String encryptedCookie)
  {
    byte[] message = Base64.decodeBase64(encryptedCookie);
    byte[] encryptedMessageBytes = new byte[message.length - 16];
    System.arraycopy(message, 16, encryptedMessageBytes, 0, encryptedMessageBytes.length);
    return encryptedMessageBytes;
  }

  static byte[] xor(byte[] a, byte[] b) {
    byte[] result = new byte[a.length];
    for (int i = 0; i < a.length; i++) {
      result[i] = ((byte)(a[i] ^ b[i]));
    }
    return result;
  }

  static String createEncryptedCookie(byte[] iv, byte[] encrypted)
  {
    byte[] result = new byte[iv.length + encrypted.length];
    System.arraycopy(iv, 0, result, 0, iv.length);
    System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
    return Base64.encodeBase64String(result);
  }
}