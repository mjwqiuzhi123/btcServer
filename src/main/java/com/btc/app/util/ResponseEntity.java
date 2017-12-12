package com.btc.app.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;

import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseEntity
 {
	private static final Logger log = LoggerFactory.getLogger(ResponseEntity.class);

	protected String resultCode = ServiceCode.SUCCESS.getCode();

	protected String message = ServiceCode.SUCCESS.getText();

	private List<Object> properties = new ArrayList<Object>();

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getProperties() {
		return this.properties;
	}

	public void setProperties(List<Object> properties) {
		this.properties = properties;
	}

	public void addProperty(Object property) {
		this.properties.add(property);
	}

	public void clearProperty() {
		this.properties.clear();
	}

	public void setMsg(ServiceCode code) {
		this.resultCode = code.getCode();
		this.message = code.getText();
	}

	public void setError(String message) {
		this.resultCode = ServiceCode.ERROR.getCode();
		this.message = message;
	}

	@JsonIgnore
	public String getAsJSON() {
		String json = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(df);
			DefaultSerializerProvider sp = new DefaultSerializerProvider.Impl();
			sp.setNullValueSerializer(new NullSerializer());
			mapper.setSerializerProvider(sp);
			json = mapper.writeValueAsString(this);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return json;
	}

	private Object getObjectFromJSONStr(Class<?> clazz) {
		Object jsonStr = getProperties().get(0);
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		System.out.print(JSONObject.toBean(jsonObject, clazz));
		return JSONObject.toBean(jsonObject, clazz);
	}

	public Object getDTO(Class<?> clazz) {
		Object classObject = null;
		try {
			classObject = getObjectFromJSONStr(clazz);
		} catch (Exception e) {
			log.error("根据指定的类型获取指定的对象信息失败---------ERROR错误的原因是:"
					+ e.getMessage());
		}
		return classObject;
	}
}