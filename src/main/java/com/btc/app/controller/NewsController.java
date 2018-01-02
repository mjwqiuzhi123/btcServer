package com.btc.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.btc.app.bean.MessageModel;
import com.btc.app.request.dto.PageParameter;
import com.btc.app.service.MessageService;
import com.btc.app.util.ResponseEntity;

@RequestMapping({ "/front/news" })
@Controller
public class NewsController {

	@Autowired
	private MessageService MessageServiceI;

	// use
	@RequestMapping({ "/manager.json" })
	public ModelAndView newsManager(HttpServletRequest request,HttpServletResponse response) throws Exception {
		int flag = 0;
		if(request.getAttribute("updateflag") != null && (boolean)request.getAttribute("updateflag"))
			flag = 1;// 1代表更新成功
		if(request.getAttribute("updateflag") != null && (boolean)request.getAttribute("deleteflag"))
			flag = 2;// 2代表删除成功
		ModelAndView mv = new ModelAndView();
		PageParameter pageView = null;
		MessageModel model = new MessageModel();
		String pageNow = request.getParameter("pageNow");
		if (("".equals(pageNow)) || (pageNow == null))
			pageView = new PageParameter();
		else {
			pageView = new PageParameter(Integer.parseInt(pageNow));
		}
		mv.addObject("newsList",this.MessageServiceI.selectNewsList(pageView, model));
		mv.addObject("pageView", pageView);
		mv.addObject("flag", flag);
		mv.setViewName("news/newsManager");
		return mv;
	}

	// return add news page
	@RequestMapping({ "/addPage.json" })
	public ModelAndView newsAddPage(HttpServletRequest request,HttpServletResponse response, String update, MessageModel model) throws Exception {
		ModelAndView mv = new ModelAndView();
		if(update != null)
			mv.addObject(model);
		return new ModelAndView("news/newsAdd");
	}

	// add news
	@RequestMapping(value = { "/add.json" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public String newsAdd(String news, HttpServletRequest request, HttpServletResponse response, MessageModel model) throws Exception {
		boolean returnFlag = false;
		if (news != null && !news.equals("")) {
			if(model.getId() > 0){
				returnFlag = this.MessageServiceI.updateNews(model);
			}
			else
				returnFlag = this.MessageServiceI.saveNews(model);
			if (returnFlag) {
				return "forward:manager.json";
			}
		}
		return "redirect:addPage.json";
	}
	
	// update news
	@RequestMapping(value = { "/update.json" })
	public String newsUpdate(HttpServletRequest request, HttpServletResponse response, MessageModel model) throws Exception {
		request.setAttribute("update", "update");
		request.setAttribute("model", model);
		return "forward:addPage.json";
	}

	//get news
	@RequestMapping({ "/shownews.json" })
	public ResponseEntity showNews(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResponseEntity re = new ResponseEntity();
		//MessageServiceI.selectNewsList();
		return null;
	}
}