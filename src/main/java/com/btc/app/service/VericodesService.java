package com.btc.app.service;

import com.btc.app.bean.VericodesModel;
import com.btc.app.util.ResponseEntity;

public interface VericodesService {

	public abstract ResponseEntity sendWithTokenAsync(String cellphone,
			String token, int vericodeType) throws Exception;

	public abstract ResponseEntity verifyVeriAsync(String phone, String code,
			int vericodeType) throws Exception;

	// 注册相关
	public abstract ResponseEntity searchByIndentFierAndType(
			VericodesModel vericodesModel) throws Exception;

}