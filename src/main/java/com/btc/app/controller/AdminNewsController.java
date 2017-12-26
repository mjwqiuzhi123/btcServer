package com.btc.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.ContentModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.btc.app.bean.MessageModel;
import com.btc.app.request.dto.PageParameter;
import com.btc.app.service.MessageService;

@RequestMapping({"/admin/news"})
@Controller
public class AdminNewsController
{

  @Autowired
  private MessageService MessageServiceI;

  // use
  @RequestMapping({"/manager.json"})
  public ModelAndView findContentManager(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ModelAndView mv = new ModelAndView();
    PageParameter pageView = null;
    MessageModel model = new MessageModel();
    //model.setType(2);
    String pageNow = request.getParameter("pageNow");
    if (("".equals(pageNow)) || (pageNow == null))
      pageView = new PageParameter();
    else {
      pageView = new PageParameter(Integer.parseInt(pageNow));
    }
    mv.addObject("newsList", this.MessageServiceI.selectNewsList(pageView, model));
    mv.addObject("pageView", pageView);
    mv.setViewName("news/newsManager");
    return mv;
  }
  
  // use
  @RequestMapping({"/addPage.json"})
  public ModelAndView findContentAddPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView("news/newsAdd");
  }
  
  // use modify by mjw
  @RequestMapping(value={"/add.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String findContentAdd(String news, HttpServletRequest request, HttpServletResponse response) throws Exception {
	if(news != null && !news.equals("")){
		MessageModel model = new MessageModel();
		model.setNews(news);
		//model.setType(2);
		boolean returnFlag = this.MessageServiceI.saveNews(model);
		if (returnFlag) {
		      return "forward:manager.json";
		    }
	}
    return "redirect:addPage.json";
  }
}