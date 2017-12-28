package com.btc.app.util;
/**
 * @author mjw
 */
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHash
{
  public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
  public static final int SALT_BYTE_SIZE = 24;
  public static final int HASH_BYTE_SIZE = 24;
  public static final int PBKDF2_ITERATIONS = 1000;
  public static final int ITERATION_INDEX = 0;
  public static final int SALT_INDEX = 1;
  public static final int PBKDF2_INDEX = 2;

  public static String createHash(String password, String salt)
    throws NoSuchAlgorithmException, InvalidKeySpecException
  {
    return createHash(password.toCharArray(), salt.getBytes());
  }

  public static String createHash(char[] password, byte[] salt)
    throws NoSuchAlgorithmException, InvalidKeySpecException
  {
    byte[] hash = pbkdf2(password, salt, 1000, 24);

    return toHex(hash);
  }

  public static boolean validatePassword(String password, String correctHash)
    throws NoSuchAlgorithmException, InvalidKeySpecException
  {
    return validatePassword(password.toCharArray(), correctHash);
  }

  public static boolean validatePassword(char[] password, String correctHash)
    throws NoSuchAlgorithmException, InvalidKeySpecException
  {
    String[] params = correctHash.split(":");
    int iterations = Integer.parseInt(params[0]);
    byte[] salt = fromHex(params[1]);
    byte[] hash = fromHex(params[2]);

    byte[] testHash = pbkdf2(password, salt, iterations, hash.length);

    return slowEquals(hash, testHash);
  }

  private static boolean slowEquals(byte[] a, byte[] b)
  {
    int diff = a.length ^ b.length;
    for (int i = 0; (i < a.length) && (i < b.length); i++)
      diff |= a[i] ^ b[i];
    return diff == 0;
  }

  private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
    throws NoSuchAlgorithmException, InvalidKeySpecException
  {
    PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    return skf.generateSecret(spec).getEncoded();
  }

  private static byte[] fromHex(String hex)
  {
    byte[] binary = new byte[hex.length() / 2];
    for (int i = 0; i < binary.length; i++)
    {
      binary[i] = ((byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16));
    }
    return binary;
  }

  private static String toHex(byte[] array)
  {
    BigInteger bi = new BigInteger(1, array);
    String hex = bi.toString(16);
    int paddingLength = array.length * 2 - hex.length();
    if (paddingLength > 0) {
      return String.format(new StringBuilder().append("%0").append(paddingLength).append("d").toString(), new Object[] { Integer.valueOf(0) }) + hex;
    }
    return hex;
  }

  public static void main(String[] args)
  {
    try
    {
      for (int i = 0; i < 10; i++)
      {
        System.out.println("Running tests...");
      }

      for (int i = 0; i < 100; i++);
    }
    catch (Exception ex)
    {
      System.out.println("ERROR: " + ex);
    }
  }
}