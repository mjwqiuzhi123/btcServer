package com.btc.app.bean;

public class MessageModel {
	
	private Integer id;
	private String news;
	private Integer type;

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
