package com.white.interfaceone.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.white.interfaceone.entity.BankCard;
import com.white.interfaceone.entity.User;
import com.white.interfaceone.util.ConstantParam;
import com.white.interfaceone.util.UtilMethod;


/**
 * 
 * @ClassName: ResponseMessage
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年9月8日 上午9:11:51
 *
 */
public class ResponseMessage {
	
	
	private static Logger log= LoggerFactory.getLogger(ResponseMessage.class);
	
	/**
	 * 
	* @Title: registrationSuccess 
	* @Description: TODO(注册成功) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> registrationSuccess(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, ConstantParam.MESSAGE_SESSION);
		log.info("=======注册成功！==========");
		return responseParam;
	}
	
	
	/**
	 * 
	* @Title: connectionRegistrationFailed 
	* @Description: TODO(数据库连接异常，注册失败) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> connectionRegistrationFailed(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "数据库连接异常！");
		log.info("=======数据库连接异常！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: informationErrorRegistrationFailed 
	* @Description: TODO(用户名为空，注册失败) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> informationErrorRegistrationFailed(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "用户名或者密码为空!");
		log.info("=======用户名为空，注册失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: informationErrorRegistrationFailed 
	 * @Description: TODO(密码长度小于8位，注册失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> informationErrorRegistrationFailed1(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "密码长度小于8位，注册失败！");
		log.info("=======密码长度小于8位，注册失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: informationErrorRegistrationFailed 
	 * @Description: TODO(密码长度大于32位，注册失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> informationErrorRegistrationFailed2(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "密码长度大于32位，注册失败！");
		log.info("=======密码长度大于32位，注册失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: informationErrorRegistrationFailed 
	 * @Description: TODO(密码长度大于32位，注册失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> informationErrorRegistrationFailed3(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "短信验证码为空，注册失败！");
		log.info("=======短信验证码为空，注册失败！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: userRepetitionRegistrationFailed 
	* @Description: TODO(用户名重复，注册失败) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> userRepetitionRegistrationFailed(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "用户名重复，注册失败！");
		log.info("=======用户名重复，注册失败！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: loginSuccessful 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> loginSuccessful(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "账号密码验证成功，token获取失败！");
		log.info("=======账号密码验证成功，token获取失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: loginSuccessful 
	 * @Description: TODO(登陆成功，获取到UUID) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginSuccessful1(User param){
		Map<String , Object> responseParam1 = new LinkedHashMap<>();
		Map<String , Object> responseParam = new LinkedHashMap<>();
		//集成token传参
		responseParam1.put(ConstantParam.PARAM_TOKEN, param.getStrUUID());
		responseParam.put(ConstantParam.PARAM_DATA, responseParam1);
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "登录成功！");
		log.info("=======登录成功！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: loginFailed 
	* @Description: TODO(账号密码错误response) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> loginFailed(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "用户名或密码错误，登录失败！");
		log.info("=======用户名或密码错误，登录失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: loginFailed 
	 * @Description: TODO(手机号码不符合规范response) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginFailed2(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "手机号输入有误，注册失败，请验证手机号码是否正确！");
		log.info("=======手机号输入有误，注册失败，请验证手机号码是否正确！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: loginFailed 
	 * @Description: TODO(手机号码不符合规范response) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginFailed3(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "两次输入的密码不匹配，请重新输入！");
		log.info("=======两次输入的密码不匹配，请重新输入！==========");
		return responseParam;
	}
	
	
	/**
	 * 
	 * @Title: loginFailed 
	 * @Description: TODO(短信验证码过期，请重新获取response) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginFailed4(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "短信验证码过期，请重新获取！");
		log.info("=======短信验证码过期，请重新获取！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: loginFailed 
	 * @Description: TODO(短信验证码过期，请重新获取response) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginFailed5(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "短信验证码输入错误，请重新输入");
		log.info("=======短信验证码输入错误，请重新输入！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: loginFailed1 
	 * @Description: TODO(账号密码为response) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginFailed1(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "用户名或密码为空，登录失败!");
		log.info("=======用户名或密码为空，登录失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: loginFailed1 
	 * @Description: TODO(账号验证失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginFailed6(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "账号验证失败！");
		log.info("=======账号验证失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: loginFailed1 
	 * @Description: TODO(修改密码成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> loginFailed7(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "修改密码成功！");
		log.info("=======修改密码成功！==========");
		return responseParam;
	}
	
	
	
	/**
	 * 
	* @Title: verificationPhoneFailed 
	* @Description: TODO(手机号为空或不正确，绑定失败) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> verificationPhoneFailed(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		Map<String , Object> responseParam1 = new LinkedHashMap<>();
		responseParam1.put(ConstantParam.PARAM_CERTIFICATION_STATUS, ConstantParam.PARAM_AUTHENTIATION_FAILD);
		responseParam.put(ConstantParam.PARAM_DATA, responseParam1);
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "手机号为空或不正确，绑定失败！");
		log.info("=======手机号为空或不正确，绑定失败！==========");
		return responseParam;
	}

	/**
	 * 
	 * @Title: verificationPhoneFailed 
	 * @Description: TODO(绑定次数过多，24小时后在进行验证) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> verificationPhoneFailed1(Long certificationTime){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		Map<String , Object> responseParam1 = new LinkedHashMap<>();
		certificationTime = ConstantParam.ROUND_THE_CLOCK_UNIX - (UtilMethod.unixString() - certificationTime);
		responseParam1.put(ConstantParam.PARAM_WATING_TIME, UtilMethod.getUnixToTime(certificationTime).get(ConstantParam.PARAM_ENGLISH));
		responseParam.put(ConstantParam.PARAM_DATA, responseParam1);
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "绑定次数太频繁，请等待 " + UtilMethod.getUnixToTime(certificationTime).get(ConstantParam.PARAM_CHINESE) + " 再试！");
		log.info("=======绑定次数太频繁，请等待：" + UtilMethod.getUnixToTime(certificationTime).get(ConstantParam.PARAM_ENGLISH) + "再试！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: verificationUserNameFailed 
	* @Description: TODO(姓名为空或不正确，绑定失败) 
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> verificationUserNameFailed(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		Map<String , Object> responseParam1 = new LinkedHashMap<>();
		responseParam1.put(ConstantParam.PARAM_CERTIFICATION_STATUS, ConstantParam.PARAM_AUTHENTIATION_FAILD);
		responseParam.put(ConstantParam.PARAM_DATA, responseParam1);
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "姓名为空或不正确，绑定失败！");
		log.info("=======姓名为空或不正确，绑定失败！==========");
		return responseParam;
	}
	/**
	 *
	* @Title: verificationUserNameFailed
	* @Description: TODO(身份证已绑定)
	* @param @return    设定文件
	* @return Map<String,Object>    返回类型
	* @throws
	 */
	public static Map<String, Object> verificationUserNameFailed01(User paramUser){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "账号：" + paramUser.getPhone() + "已被身份证" + paramUser.getIdCard() + "绑定！");
		log.info("=======账号：" + paramUser.getPhone() + "已被身份证" + paramUser.getIdCard() + "绑定！==========");
		return responseParam;
	}

	/**
	 * 
	* @Title: verificationIdCardFailed 
	* @Description: TODO(身份证号为空或不正确，绑定失败)
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> verificationIdCardFailed(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		Map<String , Object> responseParam1 = new LinkedHashMap<>();
		responseParam1.put(ConstantParam.PARAM_CERTIFICATION_STATUS, ConstantParam.PARAM_AUTHENTIATION_FAILD);
		responseParam.put(ConstantParam.PARAM_DATA, responseParam1);
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "身份证号为空或不正确，绑定失败！");
		log.info("=======身份证号为空或不正确，绑定失败！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: bindingSuccess 
	* @Description: TODO(绑定成功)
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> bindingSuccess(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		Map<String , Object> responseParam1 = new LinkedHashMap<>();
		responseParam1.put(ConstantParam.PARAM_CERTIFICATION_STATUS, ConstantParam.PARAM_SUCCESSFUL_CERTIFICATION);
		responseParam.put(ConstantParam.PARAM_DATA, responseParam1);
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "绑定成功！");
		log.info("=======绑定成功！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: bindBankCardSuccessfully 
	* @Description: TODO(绑定成功)
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> bindBankCardSuccessfully(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "绑定成功！");
		log.info("=======绑定成功！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: bindBankCardSuccessfully 
	 * @Description: TODO(银行卡归属) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> bindBankCardSuccessfully1(Map<String, String> param){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.PARAM_DATA, param);
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "银行卡归属查询成功！");
		log.info("=======银行卡归属查询成功！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: bindBankCardSuccessfully 
	 * @Description: TODO(银行卡归属) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> bindBankCardError(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "行卡归属查询失败，请至少输入6位！");
		log.info("=======银行卡归属查询失败，请至少输入6位！==========");
		return responseParam;
	}
	
	/**
	 * 
	* @Title: bindingBankCardFailed 
	* @Description: TODO(银行卡绑定成功)
	* @param @return    设定文件 
	* @return Map<String,Object>    返回类型 
	* @throws
	 */
	public static Map<String, Object> duccessfullyTiedTheCard(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "银行卡绑定成功！");
		log.info("=======银行卡绑定成功！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: cardingFailedOne 
	 * @Description: TODO(身份证号输入错误，绑定失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> cardingFailedOne(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "身份证号输入错误，绑定失败！");
		log.info("=======身份证号输入错误，绑定失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: cardingFailedTwo 
	 * @Description: TODO(持卡人姓名输入错误，绑定失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> cardingFailedTwo(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "持卡人姓名输入错误，绑定失败！");
		log.info("=======持卡人姓名输入错误，绑定失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: cardingFailedThree 
	 * @Description: TODO(银行名称输入错误，绑定失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> cardingFailedThree(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "银行名称输入错误，绑定失败！");
		log.info("=======银行名称输入错误，绑定失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: cardingFailedFour 
	 * @Description: TODO(帐号输入错误，绑定失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> cardingFailedFour(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "帐号输入错误，绑定失败！");
		log.info("=======帐号输入错误，绑定失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: cardingFailedFives 
	 * @Description: TODO(银行卡号输入错误，绑定失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> cardingFailedFives(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "银行卡号输入错误，绑定失败！");
		log.info("=======银行卡号输入错误，绑定失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: bankCardRepeat 
	 * @Description: TODO(此卡已被绑定，绑定失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> bankCardRepeat(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "此卡已被绑定，绑定失败！");
		log.info("=======此卡已被绑定，绑定失败！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: rechargeSuccessful 
	 * @Description: TODO(充值成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeSuccessful(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "充值成功！");
		log.info("=========充值成功！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(支付密码错误，充值失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "支付密码错误，充值失败！");
		log.info("=======支付密码错误，充值失败！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(支付密码错误，充值，提现金额输入错误导致失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne5(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "输入金额不正确,每次最少0.01元！");
		log.info("=======输入金额不正确,每次最少0.01元！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(支付密码错误，充值，提现金额输入错误导致失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne6(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "输入金额不正确，每次最多50000元！");
		log.info("=======输入金额不正确，每次最多50000元！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(支付密码错误，充值，提现金额输入错误导致失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne7(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "输入金额不正确,每次最多200000元！");
		log.info("=======输入金额不正确,每次最多200000元！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(非第一次绑定，如需修改，请先点击修改支付密码！) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne1(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "非第一次绑定，如需修改，请先点击修改支付密码！");
		log.info("=======非第一次绑定，如需修改，请先点击修改支付密码！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(非第一次绑定，如需修改，请先点击修改支付密码！) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne3(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "密码输入有误！");
		log.info("=======密码输入有误！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(非第一次绑定，如需修改，请先点击修改支付密码！) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne4(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "支付密码未设置，请先设置支付密码！");
		log.info("=======支付密码未设置，请先设置支付密码！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: rechargeFailedOne 
	 * @Description: TODO(支付密码设置成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedOne2(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "支付密码设置成功！");
		log.info("=======支付密码设置成功！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedTwo 
	 * @Description: TODO(请先完成实名认证) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedTwo(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "请先完成实名认证！");
		log.info("=======请先完成实名认证！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedThree 
	 * @Description: TODO(这充值失败，请先绑定银行卡) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedThree(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "充值失败，请先绑定银行卡！");
		log.info("=======充值失败，请先绑定银行卡！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeFailedFour 
	 * @Description: TODO(充值账号有误) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeFailedFour(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "充值账号有误！");
		log.info("=======充值账号有误！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeOne 
	 * @Description: TODO(提现成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeOne(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "提现成功！");
		log.info("=======提现成功！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeTwo 
	 * @Description: TODO(余额不足，提现失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeTwo(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "余额不足，提现失败！");
		log.info("=======余额不足，提现失败！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: rechargeThree 
	 * @Description: TODO(支付密码错误) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> rechargeThree(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "支付密码错误！");
		log.info("=======支付密码错误！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: untieError 
	 * @Description: TODO(解绑银行卡失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> untieError(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "解绑银行卡失败，默认银行卡不可解绑！");
		log.info("=======解绑银行卡失败，默认银行卡不可解绑！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: untieError 
	 * @Description: TODO(解绑银行卡失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> untieError1(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "解绑银行卡失败，SQL执行异常！");
		log.info("=======解绑银行卡失败，SQL执行异常！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: untieError 
	 * @Description: TODO(解绑银行卡失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> untieError2(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "卡号为空！");
		log.info("=======卡号为空！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: untieError 
	 * @Description: TODO(解绑银行卡失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> untieError3(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "卡号不合法！");
		log.info("=======卡号不合法！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: untieError 
	 * @Description: TODO(解绑银行卡失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> untieError4(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "手机号不合法！");
		log.info("=======手机号不合法！==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: untieError 
	 * @Description: TODO(解绑银行卡失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> untieSuccess(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "解绑银行卡成功！");
		log.info("=======解绑银行卡成功！==========");
		return responseParam;
	}
	
	
	
	
	/**
	 * 
	 * @Title: verificationToken 
	 * @Description: TODO(登录状态验证失败) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> verificationToken(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "登录状态验证失败，请重新登录！");
		log.info("=======登录状态验证失败，请重新登录！==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: verificationToken 
	 * @Description: TODO(剩余投资额不足) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> investment(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "剩余投资额不足！");
		log.info("=======剩余投资额不足。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: verificationToken 
	 * @Description: TODO(投资计算利息开始时间小于当前时间，项目未开始正式回款，不能进行债券转让) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> claim(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "项目未开始回款或回款时间不足30天，不满足转让条件！");
		log.info("=======项目未开始回款或回款时间不足30天，不满足转让条件。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: verificationToken 
	 * @Description: TODO(余额不足，请充值) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> investment1(){
		Map<String , Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "余额不足，请充值！");
		log.info("=======余额不足，请充值。==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(录入项目信息成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry(){
		Map<String, Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "项目信息录入成功.");
		log.info("=======项目信息录入成功。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(查询投资信息成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry1(Object obj){
		LinkedHashMap<String, Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "投资信息查询成功.");
		responseParam.put("data", obj);
		log.info("=======投资信息查询成功。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(债权转让信息计算成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry1(Map<String, Object> paraMap){
		Map<String, Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "债权转让信息计算成功.");
		responseParam.put(ConstantParam.PARAM_DATA, paraMap);
		log.info("=======债权转让信息计算成功。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(债权转让信息计算成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry3(){
		Map<String, Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "债权转让成功，利息已同步提取到余额");
		log.info("=======债权转让成功，利息已同步提取到余额。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(债权转让信息计算成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry4(){
		Map<String, Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "债权转让失败，利息提取到账户失败。");
		log.info("=======债权转让失败，利息提取到账户失败。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(债权转让信息计算成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry5(){
		Map<String, Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "债权转让失败");
		log.info("=======债权转让失败。==========");
		return responseParam;
	}
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(录入项目信息成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry2(){
		Map<String, Object> responseParam = new LinkedHashMap<>();
		responseParam.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		responseParam.put(ConstantParam.RESPONSE_MASSAGE, "项目投资成功");
		log.info("=======项目投资成功。==========");
		return responseParam;
	}
	
	/**
	 * 
	 * @Title: projectEntry 
	 * @Description: TODO(录入项目信息成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> projectEntry(Object obj){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		map.put(ConstantParam.RESPONSE_MASSAGE, "项目信息查询成功.");
		map.put(ConstantParam.PARAM_DATA, obj);
		log.info("=======项目信息查询成功。==========");
		return map;
	}
	/**
	 * 
	 * @Title: LogoutFailure 
	 * @Description: TODO(注销账号失败，账号不存在) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> LogoutFailure(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		map.put(ConstantParam.RESPONSE_MASSAGE, "注销账号失败，账号不存在!");
		log.info("=======注销账号失败，账号不存在！==========");
		return map;
	}
	/**
	 * 
	 * @Title: LogoutFailure 
	 * @Description: TODO(密码错误) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> LogoutFailure1(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		map.put(ConstantParam.RESPONSE_MASSAGE, "密码错误!");
		log.info("=======密码错误！==========");
		return map;
	}
	/**
	 * 
	 * @Title: LogoutFailure 
	 * @Description: TODO(账号错误) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> LogoutFailure2(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_PHONE_ERROR_CODE);
		map.put(ConstantParam.RESPONSE_MASSAGE, "账号错误!");
		log.info("=======账号错误！==========");
		return map;
	}
	
	/**
	 * 
	 * @Title: LogoutFailure 
	 * @Description: TODO(账号注销成功) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> LogoutSuccess(){
		Map<String, Object> map = new LinkedHashMap<>();
		map.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		map.put(ConstantParam.RESPONSE_MASSAGE, "账号注销成功!");
		log.info("=======账号注销成功！==========");
		return map;
	}
	
	/**
	 * 
	 * @Title: LogoutFailure 
	 * @Description: TODO(查询绑定银行卡成功！) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 * @throws
	 */
	public static Map<String, Object> queryCardSuccess(List<BankCard> listArray){
		List<Map<String, Object>> list = new ArrayList<>();
		for (BankCard bankCard : listArray) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("bankCard", bankCard.getBankName());
			map.put("bankCardNumber", bankCard.getBankCardNumber());
			list.add(map);
		}
		Map<String, Object> map1 = new LinkedHashMap<>();
		map1.put(ConstantParam.STATUS_CODE, ConstantParam.MESSAGE_SESSION_CODE);
		map1.put(ConstantParam.RESPONSE_MASSAGE, "查询绑定银行卡成功!");
		map1.put(ConstantParam.PARAM_DATA, list);
		log.info("=======查询绑定银行卡成功！==========");
		return map1;
	}
	
	
}
