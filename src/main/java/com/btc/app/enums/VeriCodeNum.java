package com.btc.app.enums;

public class VeriCodeNum {
	public static Integer ToCodeType(VeriCode.VeriCodeType vericodeType) {
		Integer codetype = Integer.valueOf(0);
		// switch
		// (1.$SwitchMap$com$borrow$supermarket$enums$VeriCode$VeriCodeType[vericodeType.ordinal()])
		// {
		switch (vericodeType.ordinal()) {
		case 1:
			codetype = Integer.valueOf(2);
			break;
		case 2:
			codetype = Integer.valueOf(3);
			break;
		case 3:
			codetype = Integer.valueOf(4);
			break;
		case 4:
			codetype = Integer.valueOf(1);
		}
		return codetype;
	}
}