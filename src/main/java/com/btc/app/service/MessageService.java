package com.btc.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.ContentModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btc.app.bean.MessageModel;
import com.btc.app.dao.MessageDao;
import com.btc.app.request.dto.PageParameter;

@Service
public class MessageService {
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private MessageDao messageDaoI;

	// 添加首页信息
	public boolean saveNews(MessageModel model) {
		try {
//			if (this.messageDaoI.hasContent(model).intValue() > 0)
//				return this.messageDaoI.updateContent(model)
//						.intValue() > 0;
			return this.messageDaoI.saveNews(model).intValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新闻录入操作异常---原因是-----:" + e.getMessage());
		}
		return false;
	}

	// 获取首页信息
	public List<String> selectNewsList(PageParameter parameter, MessageModel model) {
		try {
			Map map = new HashMap();
			map.put("page", parameter);
			return this.messageDaoI.getNews(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取新闻操作异常---原因是-----:" + e.getMessage());
		}
		return null;
	}
}