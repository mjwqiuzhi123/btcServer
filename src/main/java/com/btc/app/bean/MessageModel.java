package com.btc.app.bean;

import java.util.Date;
import java.util.List;

public class MessageModel {
	
	private Integer id;
	private String title;
	private String picLocation;
	private String news;
	private String newsUrl;
	private Date createTime;
	private Date publishTime;
	private int punlishFlag;
	private Integer commentCount;
	private List<String> comments;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPunlishFlag() {
		return punlishFlag;
	}

	public void setPunlishFlag(int punlishFlag) {
		this.punlishFlag = punlishFlag;
	}

	public String getPicLocation() {
		return picLocation;
	}

	public void setPicLocation(String picLocation) {
		this.picLocation = picLocation;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
}
