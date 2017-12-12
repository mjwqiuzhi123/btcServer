package com.btc.app.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.btc.app.bean.UserBean;
import com.btc.app.bean.UserModel;
import com.btc.app.request.dto.LoginOnDTO;
import com.btc.app.request.dto.PageParameter;
import com.btc.app.request.dto.UserRegisterRequestDTO;
import com.btc.app.service.UserService;
import com.btc.app.util.Constant;
import com.btc.app.util.MD5Utils;
import com.btc.app.util.ResponseEntity;

/**
 * Created by cuixuan
 */

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;
      //add by mjw
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
	//add by mjw
    
    @RequestMapping("/login")
    public @ResponseBody
    String login(HttpServletRequest request, HttpSession session) {
        JSONObject json = new JSONObject();
        if (session.getAttribute(Constant.CURRENT_USER) != null) {
            json.put("status", "error");
            json.put("message", "you have already login.");
            json.put("code", -1);
            return json.toJSONString();
        }
        String phone = request.getParameter("phone");
        String psw = request.getParameter("password");
        if (phone == null || psw == null || phone.length() != 11 || psw.length() < 6) {
            json.put("status", "error");
            json.put("message", "phone or password error");
            json.put("code", -2);
            return json.toJSONString();
        }
        psw = MD5Utils.MD5Encode(psw, Constant.ENCODE_STRING, true);
        if (phone != null && psw != null) {
            UserBean user = userService.searchUser(phone);
            if (user == null) {
                json.put("status", "error");
                json.put("message", "this number of phone is not registered, please register first.");
                json.put("code", -3);
                return json.toJSONString();
            } else if (!user.getPassword().equals(psw)) {
                json.put("status", "error");
                json.put("message", "password is not right.");
                json.put("code", -4);
                return json.toJSONString();
            }
            json.put("status", "success");
            json.put("code", 0);
            json.put("cookie", session.getId());
            session.setAttribute(Constant.CURRENT_USER, user);
        }
        return json.toJSONString();
    }

    @RequestMapping("/logout")
    public @ResponseBody
    String logout(HttpServletRequest request, HttpSession session, Model model) {
        JSONObject json = new JSONObject();
        if (session.getAttribute(Constant.CURRENT_USER) == null) {
            json.put("status", "error");
            json.put("message", "you have not login, please login first.");
            json.put("code", -1);
        } else {
            session.removeAttribute(Constant.CURRENT_USER);
            json.put("status", "success");
            json.put("code", 0);
        }
        return json.toJSONString();
    }

    @Transactional
    @RequestMapping("/register")
    public @ResponseBody
    String register(HttpServletRequest request, HttpSession session, Model model) {
        JSONObject json = new JSONObject();
        String username = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        if (phone == null || password == null || phone.length() != 11 || password.length() < 6) {
            json.put("status", "error");
            json.put("message", "phone or password error");
            json.put("code", -1);
            return json.toJSONString();
        }
        //判断email是否已经注册
        if (userService.searchUser(phone) != null) {
            json.put("status", "error");
            json.put("message", "the account of this phone already registered, please login directly.");
            json.put("code", -2);
            return json.toJSONString();
        } else {
            UserBean user = new UserBean();
            user.setPhone(phone);
            user.setPassword(MD5Utils.MD5Encode(password, Constant.ENCODE_STRING, true));
            user.setUsername(username);
            user.setAccount_status(0);
            int lines = userService.insertUser(user);
            if (lines <= 0) {
                json.put("status", "error");
                json.put("message", "db has some error, please check it quickly.");
                json.put("code", -3);
                return json.toJSONString();
            }
            json.put("status", "success");
            json.put("code", 0);
            json.put("cookie", session.getId());
            session.setAttribute(Constant.CURRENT_USER, user);
        }
        return json.toJSONString();
    }
    
    @RequestMapping(value={"/register.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=utf-8"})
    public ResponseEntity userRegister(HttpServletRequest request, HttpServletResponse response, @Valid UserRegisterRequestDTO userRegisterRequestDTO, BindingResult bind)
    {
      ResponseEntity responseEntity = new ResponseEntity();
      try {
        if (bind.hasErrors()) {
          return getValidErrors(bind);
        }

        responseEntity = this.vericodesServiceImpl.searchByIndentFierAndType(new VericodesModel(userRegisterRequestDTO.getToken(), VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.SignUp)));
        if (!responseEntity.getResultCode().equals("0000")) {
          return responseEntity;
        }

        UseVeriCodeResultDTO useVeriCodeResultDTO = (UseVeriCodeResultDTO)responseEntity.getDTO(UseVeriCodeResultDTO.class);

        boolean isExit = this.userServiceI.searcUserByPhone(new CheckIsPhoneRequestDTO(useVeriCodeResultDTO));
        if (!isExit) {
          responseEntity.setMsg(ServiceCode.REGISTER_ONE);
          return responseEntity;
        }

        boolean userModel = this.userServiceI.saveUser(userRegisterRequestDTO, useVeriCodeResultDTO);
        if (!userModel) {
          responseEntity.setMsg(ServiceCode.ERROR);
          return responseEntity;
        }
        return responseEntity;
      } catch (Exception e) {
        responseEntity.setMsg(ServiceCode.EXCEPTION);
      }return responseEntity;
    }
}
