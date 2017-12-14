package com.btc.app.response.dto;

import com.btc.app.util.CommonMsg;

public class SendVeriCodeTokenDTOResult extends CommonMsg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int remainCount;

	public int getRemainCount() {
		return this.remainCount;
	}

	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
	}

	public SendVeriCodeTokenDTOResult() {
	}

	public SendVeriCodeTokenDTOResult(int remainCount) {
		this.remainCount = remainCount;
	}
}