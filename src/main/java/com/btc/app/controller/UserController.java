package com.btc.app.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.btc.app.base64.Base64Server;
import com.btc.app.bean.UserBean;
import com.btc.app.bean.VericodesModel;
import com.btc.app.enums.VeriCode;
import com.btc.app.enums.VeriCodeNum;
import com.btc.app.request.dto.CheckIsPhoneRequestDTO;
import com.btc.app.request.dto.ResetLoginPasswordRequestDTO;
import com.btc.app.request.dto.UserLoginRequestDTO;
import com.btc.app.request.dto.UserRegisterRequestDTO;
import com.btc.app.response.dto.UseVeriCodeResultDTO;
import com.btc.app.service.UserService;
import com.btc.app.service.VericodesService;
import com.btc.app.util.Constant;
import com.btc.app.util.DateUtil;
import com.btc.app.util.MD5Utils;
import com.btc.app.util.ResponseEntity;
import com.btc.app.util.ServiceCode;

/**
 * Created by cuixuan
 */

@Controller
@RequestMapping("/front/user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;

	// start by mjw
    @Autowired
    private VericodesService vericodesService;
	//end by mjw
    
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
    
    // start by mjw
    
 // App login
    @RequestMapping(value={"/userlogin.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
    public @ResponseBody ResponseEntity userLogin(HttpServletRequest request, HttpServletResponse response, @Valid UserLoginRequestDTO userLoginRequestDTO, BindingResult bind)
    {
      ResponseEntity responseEntity = new ResponseEntity();
      try {
        if (bind.hasErrors()) {
          return getValidErrors(bind);
        }
        //CurrentUser currentUser = getCurrentUser(request);
        StringBuilder userIdentifier = new StringBuilder();
        StringBuilder cellphone = new StringBuilder();
		ResponseEntity result = this.userService.searchUserByNameAndPwd(userLoginRequestDTO, userIdentifier, cellphone);
			if (!result.getResultCode().equals("0000")) {

				// if (currentUser != null) {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						if (cookies[i].getName().equals("CBDC")) {
							cookies[i].setMaxAge(0);
							cookies[i].setPath("/");
							response.addCookie(cookies[i]);
							break;
						}
					}
				}
				return result;
			}
//          }
//
        int expireMinutes = 30;
        String clientID = request.getHeader("ClientID");
        if ((clientID != null) && (!clientID.isEmpty()))
          expireMinutes = 10080;
        Cookie cookie = new Cookie("CBDC", new Base64Server().encrypted(cellphone.toString(), userIdentifier.toString(), DateUtil.dateMinutesAdd(new Date(), expireMinutes)));
        cookie.setMaxAge((expireMinutes + 10) * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.addHeader("P3P", "CP=CAO PSA OUR");
        return responseEntity;
      } catch (Exception e) {
        responseEntity.setMsg(ServiceCode.EXCEPTION);
      }return responseEntity;
    }
    
    @RequestMapping(value={"/userloginOut.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=utf-8"})
    public @ResponseBody ResponseEntity userloginOut(HttpServletRequest request, HttpServletResponse response)
    {
      Cookie[] cookies = request.getCookies();

      if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
          if (cookies[i].getName().equals("CBDC")) {
            cookies[i].setMaxAge(0);// 设置Cookie立即失效  
            cookies[i].setPath("/");
            response.addCookie(cookies[i]);
            break;
          }
        }
      }

      ResponseEntity messageResult = new ResponseEntity();
      return messageResult;
    }
    
    // 用户注册
    @RequestMapping(value={"/register.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
    public @ResponseBody ResponseEntity userRegister(HttpServletRequest request, HttpServletResponse response, @Valid UserRegisterRequestDTO userRegisterRequestDTO, BindingResult bind)
    {
      ResponseEntity responseEntity = new ResponseEntity();
      try {
        if (bind.hasErrors()) {
          return getValidErrors(bind);
        }

        responseEntity = this.vericodesService.searchByIndentFierAndType(new VericodesModel(userRegisterRequestDTO.getToken(), VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.SignUp)));
        if (!responseEntity.getResultCode().equals("0000")) {
          return responseEntity;
        }

        UseVeriCodeResultDTO useVeriCodeResultDTO = (UseVeriCodeResultDTO)responseEntity.getDTO(UseVeriCodeResultDTO.class);

        boolean isExit = this.userService.searcUserByPhone(new CheckIsPhoneRequestDTO(useVeriCodeResultDTO));
        if (!isExit) {
          responseEntity.setMsg(ServiceCode.REGISTER_ONE);
          return responseEntity;
        }

        boolean userModel = this.userService.saveUser(userRegisterRequestDTO, useVeriCodeResultDTO);
        if (!userModel) {
          responseEntity.setMsg(ServiceCode.ERROR);
          return responseEntity;
        }
        return responseEntity;
      } catch (Exception e) {
        responseEntity.setMsg(ServiceCode.EXCEPTION);
      }return responseEntity;
    }
    
    @RequestMapping(value={"/resetPassword.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
    public @ResponseBody ResponseEntity resetLoginPassword(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("resetLoginPassword") ResetLoginPasswordRequestDTO resetLoginPasswordRequestDTO, BindingResult bind)
      throws Exception
    {
      if (bind.hasErrors()) {
        return getValidErrors(bind);
      }

      ResponseEntity messageResult = this.vericodesService.searchByIndentFierAndType(new VericodesModel(resetLoginPasswordRequestDTO.getToken(), VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.ResetLoginPassword)));
      if (!messageResult.getResultCode().equals("0000")) {
        return messageResult;
      }

      UseVeriCodeResultDTO useVeriCodeResultDTO = (UseVeriCodeResultDTO)messageResult.getDTO(UseVeriCodeResultDTO.class);
      ResponseEntity messageResult1 = this.userService.resetLoginPassword(useVeriCodeResultDTO.getPhone(), resetLoginPasswordRequestDTO.getPassword(), resetLoginPasswordRequestDTO.getToken());

      return messageResult1;
    }
    //end by mjw
}
