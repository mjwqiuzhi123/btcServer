package com.btc.app.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btc.app.bean.VericodesModel;
import com.btc.app.constant.MessageConstant;
import com.btc.app.dao.VericodesMapper;
import com.btc.app.response.dto.SendMsgDTOResult;
import com.btc.app.response.dto.SendVeriCodeTokenDTOResult;
import com.btc.app.response.dto.UseVeriCodeResultDTO;
import com.btc.app.response.dto.VerifyGraphicCodeDTOResult;
import com.btc.app.service.UserService;
import com.btc.app.service.VericodesService;
import com.btc.app.util.AliMessageUtil;
import com.btc.app.util.CommonUtil;
import com.btc.app.util.DateUtil;
import com.btc.app.util.ResponseEntity;
import com.btc.app.util.ServiceCode;
import com.btc.app.util.SystemProperty;

@Service
public class VericodesServiceImpl implements VericodesService
{

	  //@Autowired
	  //private MessageSend messageSend;

	  @Autowired
	  private AliMessageUtil aliMessageUtil;

	  @Autowired
	  private VericodesMapper vericodesMapper;
	  private static final Logger log = LoggerFactory.getLogger(UserService.class);

	  /* (non-Javadoc)
	 * @see com.btc.app.service.impl.VericodesService#sendWithTokenAsync(java.lang.String, java.lang.String, int)
	 */
	public ResponseEntity sendWithTokenAsync(String cellphone, String token, int vericodeType)
	    throws Exception
	  {
	    ResponseEntity messageResult = new ResponseEntity();
	    VericodesModel vericode3 = new VericodesModel(Integer.valueOf(vericodeType), cellphone, DateUtil.GetDate(new Date()));
	    VericodesModel vericode4 = this.vericodesMapper.selectByEntity2(vericode3);

	    if ((vericode4 != null) && (vericode4.getTimes().intValue() >= 5)) {
	      SendVeriCodeTokenDTOResult resultDTO = new SendVeriCodeTokenDTOResult(-1);
	      messageResult.setMsg(ServiceCode.SNEDWITHTOKENASYNC_THREE);
	      messageResult.addProperty(resultDTO);
	      return messageResult;
	    }
	    //String veriCode = GenerateCode();//生成验证码
	    String veriCode = "123456";//test

	    if ((vericode4 != null) && (vericode4.getTimes().intValue() < 5)) {
	      vericode4.setTimes(Integer.valueOf(vericode4.getTimes().intValue() + 1));

	      if ((vericode4.getVerified().intValue() == 1) || 
	        (vericode4
	        .getUsed().intValue() == 1) || 
	        (DateUtil.dateMinutesSub(new Date(), MessageConstant.SMS_VALID_MINUTES)
	        .after(vericode4.getBuildat()))) {
	        vericode4.setCode(veriCode);
	        vericode4.setUsed(Integer.valueOf(0));
	        vericode4.setBuildat(new Date());
	        vericode4.setErrorcount(Integer.valueOf(0));
	        vericode4.setVerified(Integer.valueOf(0));
	      }
	      else {
	        veriCode = vericode4.getCode();
	      }

	      Integer returnCode = Integer.valueOf(this.vericodesMapper.updateByEntity(vericode4));
	      if (returnCode.intValue() <= 0) {
	        log.error("更新验证码信息失败-----error---返回的结果是:" + returnCode + "验证码参数是:" + vericode4.toString());
	      }
	    }

	    if (vericode4 == null) {
	      vericode4 = new VericodesModel();
	      vericode4.setIdentifier(CommonUtil.getUUID());//token
	      vericode4.setPhone(cellphone);
	      vericode4.setCode(veriCode);
	      vericode4.setErrorcount(Integer.valueOf(0));
	      vericode4.setBuildat(new Date());
	      vericode4.setTimes(Integer.valueOf(1));
	      vericode4.setType(Integer.valueOf(vericodeType));
	      vericode4.setUsed(Integer.valueOf(0));
	      vericode4.setVerified(Integer.valueOf(0));
	      vericode4.setClientid("");

	      Integer returnCode = this.vericodesMapper.save(vericode4);
	      if (returnCode.intValue() <= 0) {
	        log.error("保存验证码信息失败-----error---返回的结果是:" + returnCode + "验证码参数是:" + vericode4.toString());
	      }

	    }

//	    SendMsgDTOResult sendMsgDTOResult = new SendMsgDTOResult();
//	    sendMsgDTOResult.setCode(veriCode);
//	    sendMsgDTOResult.setMessageType(1);
//	    sendMsgDTOResult.setPhone(cellphone);
//	    AliMessageUtil.sendMsg(sendMsgDTOResult);

	    messageResult.addProperty(new SendVeriCodeTokenDTOResult(5 - vericode4.getTimes().intValue()));
	    messageResult.setMsg(ServiceCode.SUCCESS);
	    return messageResult;
	  }

	  private String GenerateCode()
	  {
	    String smsEnable = SystemProperty.getProperty("sms.enable");
	    if ((smsEnable == null) || (smsEnable.isEmpty()) || (!smsEnable.equalsIgnoreCase("true"))) {
	      log.info("SMS发送被关闭，短信验证码固定为123456，如需打开，请配置sms.enable=true(yj_wealth.properties)");
	      return "123456";
	    }

	    return String.valueOf((int)((Math.random() * 9.0D + 1.0D) * 100000.0D));
	  }

	  /* (non-Javadoc)
	 * @see com.btc.app.service.impl.VericodesService#verifyVeriAsync(java.lang.String, java.lang.String, int)
	 */
	public ResponseEntity verifyVeriAsync(String phone, String code, int vericodeType)
	    throws Exception
	  {
	    ResponseEntity messageResult = new ResponseEntity();
	    try
	    {
	      VericodesModel vericodeQuery = new VericodesModel(Integer.valueOf(vericodeType), phone, DateUtil.dateMinutesSub(new Date(), MessageConstant.SMS_VALID_MINUTES));
	      VericodesModel vericodeResult = this.vericodesMapper.selectByEntity2(vericodeQuery);

	      if (vericodeResult == null) {
	        messageResult.setMsg(ServiceCode.VERIFYVERIASYNC_ONE);
	        return messageResult;
	      }

	      if (vericodeResult.getErrorcount().intValue() >= 2) {
	        messageResult.setMsg(ServiceCode.VERIFYVERIASYNC_TWO);
	        return messageResult;
	      }

	      if (vericodeResult.getCode().equalsIgnoreCase(code)) {
	        if (vericodeResult.getVerified().intValue() == 0) {
	          vericodeResult.setVerified(Integer.valueOf(1));
	          Integer returnCode = Integer.valueOf(this.vericodesMapper.updateByEntity(vericodeResult));
	          if (returnCode.intValue() <= 0) {
	            messageResult.setMsg(ServiceCode.DATABASE_UPDATE_ERROR);
	            return messageResult;
	          }
	        }

	        VerifyGraphicCodeDTOResult resultDTO = new VerifyGraphicCodeDTOResult(0, vericodeResult.getIdentifier());
	        messageResult.setMsg(ServiceCode.SUCCESS);
	        messageResult.addProperty(resultDTO);
	        return messageResult;
	      }

	      vericodeResult.setErrorcount(Integer.valueOf(vericodeResult.getErrorcount().intValue() + 1));
	      vericodeResult.setVerified(Integer.valueOf(1));
	      this.vericodesMapper.updateByEntity(vericodeResult);

	      VerifyGraphicCodeDTOResult resultDTO = new VerifyGraphicCodeDTOResult(5 - vericodeResult.getTimes().intValue());
	      messageResult.setMsg(ServiceCode.VERIFYVERIASYNC_THREE);
	      messageResult.addProperty(resultDTO);
	      return messageResult;
	    } catch (Exception e) {
	      messageResult.setMsg(ServiceCode.EXCEPTION);
	    }return messageResult;
	  }
	  
	  //注册相关
	  /* (non-Javadoc)
	 * @see com.btc.app.service.impl.VericodesService#searchByIndentFierAndType(com.btc.app.bean.VericodesModel)
	 */
	public ResponseEntity searchByIndentFierAndType(VericodesModel vericodesModel)
	    throws Exception
	  {
	    ResponseEntity messageResult = new ResponseEntity();
	    VericodesModel vericod = null;
	    UseVeriCodeResultDTO returnDTO = null;
	    try {
	      VericodesModel verico = new VericodesModel(vericodesModel.getCode(), vericodesModel.getType(), DateUtil.dateMinutesSub(new Date(), 30));

	      vericod = this.vericodesMapper.selectByEntity(verico);
	      if (vericod != null) {
	        if (vericod.getVerified().intValue() == 0) {
	          returnDTO = new UseVeriCodeResultDTO(vericodesModel.getPhone());
	          messageResult.setMsg(ServiceCode.SEARCHBYINDENTIFIERANDTYPE_ONE);
	          messageResult.addProperty(returnDTO);
	          return messageResult;
	        }

	        if (vericod.getUsed().intValue() == 1) {
	          returnDTO = new UseVeriCodeResultDTO(vericodesModel.getPhone());
	          messageResult.setMsg(ServiceCode.SEARCHBYINDENTIFIERANDTYPE_TWO);
	          messageResult.addProperty(returnDTO);
	          return messageResult;
	        }

	        vericod.setUsed(Integer.valueOf(1));
	        this.vericodesMapper.updateByEntity(vericod);
	        returnDTO = new UseVeriCodeResultDTO(vericod.getPhone());
	        messageResult.addProperty(returnDTO);
	        return messageResult;
	      }
	      messageResult.setMsg(ServiceCode.SEARCHBYINDENTIFIERANDTYPE_THREE);
	      return messageResult;
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	      log.error("按照验证码类型和token查询对应的数据信息:操作类是:VericodesServiceI方法是:searchByIndentFierAndTyper参数是:【验证码类型是:" + vericodesModel
	        .getType() + "token的值是:" + vericodesModel.getIdentifier() + "操作失败=======================error原因是】" + e.getMessage());

	      returnDTO = new UseVeriCodeResultDTO("");
	      messageResult.setMsg(ServiceCode.SEARCHBYINDENTIFIERANDTYPE_FOUR);
	      messageResult.addProperty(returnDTO);
	    }return messageResult;
	  }
}