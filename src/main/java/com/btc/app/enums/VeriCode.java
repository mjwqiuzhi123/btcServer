package com.btc.app.enums;

public class VeriCode
{
  public static enum VeriCodeType
  {
	  SignIn, SignUp, ResetLoginPassword, ResetPaymentPassword, VeriImage;
  }
  
  public static void main(String[] args) {
	  //VeriCodeType.SignUp.ordinal();
	  System.out.println(VeriCodeType.SignUp.ordinal());
}
  
}