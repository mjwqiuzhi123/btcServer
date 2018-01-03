package com.btc.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btc.app.bean.CommentModel;
import com.btc.app.bean.MessageModel;
import com.btc.app.dao.CommentDao;
import com.btc.app.dao.MessageDao;
import com.btc.app.request.dto.PageParameter;

@Service
public class MessageService {
	private static final Logger logger = LoggerFactory
			.getLogger(MessageService.class);

	@Autowired
	private MessageDao messageDaoI;
	
	@Autowired
	private CommentDao commentDaoI;

	// 添加新闻信息
	public boolean saveNews(MessageModel model) {
		try {
			// if (this.messageDaoI.hasContent(model).intValue() > 0)
			// return this.messageDaoI.updateContent(model)
			// .intValue() > 0;
			return this.messageDaoI.save(model).intValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新闻录入操作异常---原因是-----:" + e.getMessage());
		}
		return false;
	}

	// 添加新闻信息
	public boolean updateNews(MessageModel model) {
		try {
			// if (this.messageDaoI.hasContent(model).intValue() > 0)
			// return this.messageDaoI.updateContent(model)
			// .intValue() > 0;
			return this.messageDaoI.update(model).intValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新闻录入操作异常---原因是-----:" + e.getMessage());
		}
		return false;
	}

	// 添加新闻信息
	public boolean deleteNews(MessageModel model) {
		try {
			// if (this.messageDaoI.hasContent(model).intValue() > 0)
			// return this.messageDaoI.updateContent(model)
			// .intValue() > 0;
			return this.messageDaoI.delete(model).intValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新闻录入操作异常---原因是-----:" + e.getMessage());
		}
		return false;
	}

	// 获取新闻信息
	public List<MessageModel> selectNewsList(PageParameter parameter,
			MessageModel model) {
		try {
			Map map = new HashMap();
			map.put("page", model);
			map.put("model", parameter);
			return this.messageDaoI.getByPage(map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取新闻操作异常---原因是-----:" + e.getMessage());
		}
		return null;
	}
	
	// 获取新闻信息
	public MessageModel selectNews(MessageModel model) {
		try {
			return this.messageDaoI.getInfo(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取新闻操作异常---原因是-----:" + e.getMessage());
		}
		return null;
	}
	
	// 展示新闻信息
	public List<MessageModel> ShowNewsList(MessageModel model) {
		try {
			return this.messageDaoI.getAll(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取新闻操作异常---原因是-----:" + e.getMessage());
		}
		return null;
	}
	
	// 展示新闻信息
	public boolean insertComment(CommentModel model) {
		try {
			return this.commentDaoI.save(model) > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取新闻操作异常---原因是-----:" + e.getMessage());
		}
		return false;
	}
}