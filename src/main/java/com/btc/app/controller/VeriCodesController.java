package com.btc.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btc.app.enums.VeriCode;
import com.btc.app.enums.VeriCodeNum;
import com.btc.app.request.dto.CheckIsPhoneRequestDTO;
import com.btc.app.request.dto.SendRequestDTO;
import com.btc.app.request.dto.VerifyCodeRequestDTO;
import com.btc.app.service.UserService;
import com.btc.app.service.VericodesService;
import com.btc.app.util.ResponseEntity;
import com.btc.app.util.ServiceCode;

@RequestMapping({"/front/veriCodes"})
@RestController
public class VeriCodesController extends BaseController
{

  @Autowired
  private VericodesService vericodesService;

  @Autowired
  private UserService userServiceI;

  // 生成验证码 发送验证码
  @RequestMapping(value={"/send.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity send(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("contentModel") SendRequestDTO sendRequestDTO, BindingResult bind)
    throws Exception
  {
    if (bind.hasErrors()) {
      return getValidErrors(bind);
    }
    ResponseEntity messageResult = new ResponseEntity();
    boolean flag = this.userServiceI.searcUserByPhone(new CheckIsPhoneRequestDTO(sendRequestDTO));
    if ((sendRequestDTO.getType() == VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.SignUp).intValue()) && (!flag)) {
      messageResult = new ResponseEntity();
      messageResult.setMsg(ServiceCode.CHECKCELLPHONE_TWO);
      return messageResult;
    }

    if ((sendRequestDTO.getType() == VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.ResetLoginPassword).intValue()) && (flag)) {
      messageResult = new ResponseEntity();
      messageResult.setMsg(ServiceCode.CHECKCELLPHONE_ONE);
      return messageResult;
    }

    messageResult = this.vericodesService.sendWithTokenAsync(sendRequestDTO.getPhone(), null, sendRequestDTO.getType());
    return messageResult;
  }

  // 验证发送的验证码
  @RequestMapping(value={"/verifyCode.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity verifyCode(HttpServletRequest request, @Valid VerifyCodeRequestDTO verifyCodeRequestDTO, BindingResult bind)
    throws Exception
  {
    if (bind.hasErrors()) {
      return getValidErrors(bind);
    }
    ResponseEntity messageResult = this.vericodesService.verifyVeriAsync(verifyCodeRequestDTO.getPhone(), verifyCodeRequestDTO.getCode(), verifyCodeRequestDTO.getType());
    return messageResult;
  }
}