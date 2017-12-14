package com.btc.app.response.dto;

import com.btc.app.util.CommonMsg;

public class UserLoginDTOResult extends CommonMsg {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer remainCount;
	private boolean userExist;
	private boolean lock;

	public Integer getRemainCount() {
		return this.remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}

	public boolean isUserExist() {
		return this.userExist;
	}

	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}

	public boolean isLock() {
		return this.lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

	public UserLoginDTOResult(Integer remainCount, boolean userExist,
			boolean lock) {
		this.remainCount = remainCount;
		this.userExist = userExist;
		this.lock = lock;
	}

	public UserLoginDTOResult() {
	}
}