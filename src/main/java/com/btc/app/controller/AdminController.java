package com.btc.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.btc.app.bean.UserModel;
import com.btc.app.request.dto.LoginOnDTO;
import com.btc.app.request.dto.PageParameter;
import com.btc.app.service.UserService;

@Controller
@RequestMapping({"/admin"})
public class AdminController extends BaseController{
	@Resource
    private UserService userService;

	// start by mjw
	  @RequestMapping({"/login.json"})
	  public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response)
	  {
	    return new ModelAndView("login");
	  }
	  
	  @RequestMapping({"/loginOn.json"})
	  public ModelAndView loginOn(HttpServletRequest request, HttpServletResponse response, ModelMap model, @Valid @ModelAttribute("loginOn") LoginOnDTO loginDTO, BindingResult bind)
	  {
	    UserModel adminModel = (UserModel)request.getSession().getAttribute("adminInfo");
	    if (adminModel != null) {
	      request.getSession().setAttribute("adminInfo", adminModel);
	      return new ModelAndView("forward:main.json");
	    }

	    ModelAndView mv = new ModelAndView();
	    if (loginDTO == null) {
	      mv.addObject("error", "账号和密码不能为空");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    if ((loginDTO.getAdminName() == null || (loginDTO.getAdminName().trim().equals("")))) {
	      mv.addObject("error", "管理员名称不能为空");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    if ((loginDTO.getAdminPwd() == null) || (loginDTO.getAdminPwd().trim().equals(""))) {
	      mv.addObject("error", "管理员密码不能为空");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

//	    if (!loginDTO.getAdminName().trim().equals("guochaojun")) {
//	      mv.addObject("error", "Sorry 你的账号不是指定账号请联系管理员");
//	      mv.setViewName("forward:login.json");
//	      return mv;
//	    }

	    UserModel userModel = this.userService.searchAdminByNameAndPwd(loginDTO);
	    if (userModel == null) {
	      mv.addObject("error", "管理员账号密码错误");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    request.getSession().setAttribute("adminInfo", userModel);
	    return new ModelAndView("forward:main.json");
	  }

	  @RequestMapping({"/main.json"})
	  public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	  {
	    return new ModelAndView("main");
	  }
	  
	  @RequestMapping({"/loginOut.json"})
	  public String loginOut(HttpServletRequest request, HttpServletResponse response) {
	    UserModel adminModel = (UserModel)request.getSession().getAttribute("adminInfo");
	    if (adminModel != null) {
	      request.getSession().setAttribute("adminInfo", null);
	    }
	    return "forward:login.json";
	  }
	  
	  @RequestMapping({"/userManager.json"})
	  public ModelAndView safeManager(HttpServletRequest request, HttpServletResponse response, UserModel userModel)
	    throws Exception
	  {
	    ModelAndView mv = new ModelAndView();
	    PageParameter pageView = null;
	    String pageNow = request.getParameter("pageNow");
	    if ((pageNow == null) || ("".equals(pageNow)))
	      pageView = new PageParameter();
	    else {
	      pageView = new PageParameter(Integer.parseInt(pageNow));
	    }
	    mv.addObject("userList", this.userService.selectUserList(userModel, pageView));
	    mv.addObject("userModel", userModel);
	    mv.addObject("pageView", pageView);
	    mv.setViewName("userManager");
	    return mv;
	  }
	//end by mjw
}
