package com.btc.app.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.btc.app.response.dto.SendMsgDTOResult;

@Component
public class AliMessageUtil
{
	private static final String accessKeyId = SystemProperty.getProperty("aliyun.message.accessKeyId");
	private static final String accessKeySecret = SystemProperty.getProperty("aliyun.message.accessKeySecret");
	private static final String signName = SystemProperty.getProperty("aliyun.message.setSignName");
	private static final String registerMessage = SystemProperty.getProperty("aliyun.message.setTemplateCode.register");
	private static final String resetPasswordMessage = SystemProperty.getProperty("aliyun.message.setTemplateCode.resetPassword");
	private static final String defaultConnectTimeout = SystemProperty.getProperty("aliyun.message.defaultConnectTimeout");
	private static final String defaultReadTimeout = SystemProperty.getProperty("aliyun.message.defaultReadTimeout");

	@Async
	public static void sendMsg(SendMsgDTOResult sendMsgDTOResult) throws ClientException 
	{
		  try {
			    System.setProperty("sun.net.client.defaultConnectTimeout", defaultConnectTimeout);
			    System.setProperty("sun.net.client.defaultReadTimeout", defaultReadTimeout);
			    String product = "Dysmsapi";
			    String domain = "dysmsapi.aliyuncs.com";
			    IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
	
			    DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
			    IAcsClient acsClient = new DefaultAcsClient(profile);
			    SendSmsRequest request = new SendSmsRequest();
			    request.setPhoneNumbers(sendMsgDTOResult.getPhone());
			    request.setSignName(signName);
			    if (sendMsgDTOResult.getMessageType() == 1)
			      request.setTemplateCode(registerMessage);
			    else {
			      request.setTemplateCode(resetPasswordMessage);
			    }
			    request.setTemplateParam("{\"name\":\"" + sendMsgDTOResult.getPhone() + "\", \"code\":\"" + sendMsgDTOResult.getCode() + "\"}");
			    request.setOutId(sendMsgDTOResult.getPhone());
			    System.out.println("hello");
			    SendSmsResponse sendSmsResponse = (SendSmsResponse)acsClient.getAcsResponse(request);
			    if ((sendSmsResponse.getCode() != null) && (sendSmsResponse.getCode().equals("OK"))) {
			      System.out.println("发送成功");
			    }
			  }
			  catch (Exception e)
			  {
			    e.printStackTrace();
			  }
	}
	
		public static void main(String[] args) throws ClientException {
		  SendMsgDTOResult sendMsgDTOResult = new SendMsgDTOResult();
		  sendMsg(sendMsgDTOResult);
		}
}