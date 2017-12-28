package com.btc.app.util;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String toJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}