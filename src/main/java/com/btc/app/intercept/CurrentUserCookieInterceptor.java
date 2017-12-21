package com.btc.app.intercept;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.btc.app.base64.Base64Server;
import com.btc.app.service.UserService;
import com.btc.app.util.DateUtil;
import com.btc.app.util.ResponseEntity;
import com.btc.app.util.ServiceCode;

public class CurrentUserCookieInterceptor extends HandlerInterceptorAdapter
{
  private static final Logger log = Logger.getLogger(CurrentUserCookieInterceptor.class);

  @Autowired
  private UserService userServiceI;

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    try
    {
      String useridentifier = (String)request.getAttribute("usersInfo");
      if ((useridentifier != null) && (!useridentifier.isEmpty())) {
        return true;
      }
      String cookieName = null;

      Cookie[] cookies = request.getCookies();

      if (cookies == null) return true;

      for (Cookie cook : cookies) {
        if (cook.getName().equals("CBDC")) {
          cookieName = cook.getValue();;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
          break;
        }

      }

      if ((cookieName == null) || ("".equals(cookieName))) {
        return true;
      }

      Base64Server server = new Base64Server();
      Map deEncrypResult = server.deEncrypted(cookieName);

      String dateTimeStr = String.valueOf(deEncrypResult.get("dateTime"));
      Date dateTimeDe = DateUtil.returnDateTime(dateTimeStr, "yyyy-MM-dd HH:mm:ss");

      if (!dateTimeDe.after(new Date())) {
        return true;
      }

      request.setAttribute("usersInfo", String.valueOf(deEncrypResult.get("uuid")));
    }
    catch (Exception e) {
      ResponseEntity result = new ResponseEntity();
      result.setMsg(ServiceCode.SING_USER_COOKIE_NAME_CODE);
      log.error("request failure: " + result.getAsJSON());
      response.getWriter().print(result.getAsJSON());
      return false;
    }
    return true;
  }
}