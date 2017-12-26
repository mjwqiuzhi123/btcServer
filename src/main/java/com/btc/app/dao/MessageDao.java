package com.btc.app.dao;

import java.util.List;

import com.btc.app.bean.MessageModel;

public abstract interface MessageDao extends BaseMapper<MessageModel>
{ 
  public abstract Integer saveNews(MessageModel model);// add by mjw 添加首页信息
  
  public abstract Integer updateNews(MessageModel model);// add by mjw 更新首页信息
  
  public abstract Integer hasNews(MessageModel model);// add by mjw 获取首页信息数量
  
  public abstract List<String> getNews(MessageModel model);// add by mjw 获取全部首页信息
}