package com.btc.app.dao;

import com.btc.app.bean.MessageModel;

public abstract interface MessageDao extends BaseMapper<MessageModel> {
	
	public abstract Integer hasNews(MessageModel model);// add by mjw 获取首页信息数量

}