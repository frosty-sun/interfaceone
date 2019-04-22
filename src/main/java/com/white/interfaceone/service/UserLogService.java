package com.white.interfaceone.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.white.interfaceone.util.ConstantParam;
import org.slf4j.LoggerFactory;

public class UserLogService {
	
	private static Logger log= LoggerFactory.getLogger(UserLogService.class);
	
	/**
	 * 
	 * @Title: responseMap 
	 * @Description: TODO(成功响应参数封装) 
	 * @param @param paraMap
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> responseMapSuccess(Map<String, Object> paraMap){
		Map<String, Object> responseMapParam = new HashMap<>();
		responseMapParam.put(ConstantParam.PARAM_DATA, paraMap);
		responseMapParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseMapParam.put(ConstantParam.RESPONSE_MASSAGE, "短信验证码已成功获取。验证码有效时间为5分钟！");
		log.info("获取验证码成功！手机号：" + paraMap.get(ConstantParam.SMS_VERIFICATION_PHONE) + "对应的验证码为：" + paraMap.get(ConstantParam.SMS_VERIFICATION_CODE) + "今日第：" + paraMap.get(ConstantParam.PARAM_NUMBER) + "次获取验证码，有效时间为5分钟！");
		return responseMapParam;
	}
	
	/**
	 * 
	 * @Title: responseMapFailure 
	 * @Description: TODO(获取短信验证码失败) 
	 * @param @param paraMap
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> responseMapFailure(Map<String, Object> paraMap ,Long waitingTime){
		Map<String, Object> responseMapParam = new HashMap<>();
		Long waitingTime1 = 0L;
		Long waitingTime3 = 0L;
		if (waitingTime > ConstantParam.ONE_MINUTE) {
			waitingTime1 = waitingTime/60;
			waitingTime3 = waitingTime1/60;
			responseMapParam.put(ConstantParam.RESPONSE_MASSAGE, "Please wait " 
					+  waitingTime3 + " hours, " 
					+ waitingTime1%60 + " minutes and " 
					+ waitingTime%60 + " seconds before trying！");
			paraMap.put(ConstantParam.PARAM_WATING_TIME, String.valueOf(waitingTime3 + ":" + waitingTime1%60 + ":"+ waitingTime%60 ));
		}else {
			responseMapParam.put(ConstantParam.RESPONSE_MASSAGE, "获取验证码过于频繁，请等待："+ waitingTime + "秒后在尝试重新获取验证码!");
		}
		responseMapParam.put(ConstantParam.PARAM_DATA, paraMap);
		responseMapParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		log.info("获取验证码过于频繁，" 
				+ String.valueOf("请等待：" 
				+ waitingTime3 + "小时" 
				+ waitingTime1%60 + "分"
				+ waitingTime%60 + "秒 后在尝试重新获取验证码!"));
		return responseMapParam;
	}
	
	
	/**
	 * 
	 * @Title: phoneErrorMag 
	 * @Description: TODO(手机号验证失败) 
	 * @param @param paraMap
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> phoneErrorMag(){
		Map<String, Object> responseMapParam = new HashMap<>();
		responseMapParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseMapParam.put(ConstantParam.RESPONSE_MASSAGE, "手机号码验证失败，请检查手机号码是否正确!");
		log.info("手机号码验证失败，请检查手机号码是否正确!");
		return responseMapParam;
	}

}
