package com.btc.app.controller;

//import com.borrow.supermarket.jedis.CurrentUser;
//import com.borrow.supermarket.util.ResponseEntity;
//import com.borrow.supermarket.util.ServiceCode;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.btc.app.util.ResponseEntity;
import com.btc.app.util.ServiceCode;

public class BaseController
{

//  @Autowired
//  private CurrentUser currentUser;

  public ResponseEntity getValidErrors(BindingResult bind)
  {
    ResponseEntity messageResultBind = new ResponseEntity();
    messageResultBind.setMsg(ServiceCode.PARAMETER_ERROR);

    Map hashMap = new HashMap();
    for (FieldError fieldError : bind.getFieldErrors()) {
      hashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    messageResultBind.addProperty(JSONObject.fromObject(hashMap));
    return messageResultBind;
  }

  public String getValidErrorsforStr(BindingResult bind)
  {
    Map hashMap = new HashMap();
    for (FieldError fieldError : bind.getFieldErrors()) {
      hashMap.put(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return hashMap.toString();
  }

//  public CurrentUser getCurrentUser(HttpServletRequest request) {
//    String useridentifier = (String)request.getAttribute("usersInfo");
//
//    if ((useridentifier == null) || (useridentifier.isEmpty())) {
//      return null;
//    }
//    return this.currentUser.getUser(useridentifier);
//  }
}