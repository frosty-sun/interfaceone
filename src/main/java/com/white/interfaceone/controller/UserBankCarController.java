package com.white.interfaceone.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.white.interfaceone.dao.BankCardDao;
import com.white.interfaceone.dao.BankDao;
import com.white.interfaceone.dao.InvestmentProjectDao;
import com.white.interfaceone.dao.ProjectDao;
import com.white.interfaceone.dao.ProjectreturnlogDao;
import com.white.interfaceone.dao.RechargeWaterDao;
import com.white.interfaceone.dao.UserDao;
import com.white.interfaceone.dao.UserLoginDao;
import com.white.interfaceone.entity.Bank;
import com.white.interfaceone.entity.BankCard;
import com.white.interfaceone.entity.BankCardRecharge;
import com.white.interfaceone.entity.InvestmentProject;
import com.white.interfaceone.entity.Project;
import com.white.interfaceone.entity.Projectreturnlog;
import com.white.interfaceone.entity.RechargeWater;
import com.white.interfaceone.entity.User;
import com.white.interfaceone.entity.UserBankCard;
import com.white.interfaceone.entity.UserLogin;
import com.white.interfaceone.service.ResponseMessage;
import com.white.interfaceone.service.UserLogService;
import com.white.interfaceone.util.ConstantParam;
import com.white.interfaceone.util.GetCheckBankCard;
import com.white.interfaceone.util.IdcardValidator;
import com.white.interfaceone.util.PropertyUtil;
import com.white.interfaceone.util.RequestCircuits;
import com.white.interfaceone.util.UtilMethod;
import com.white.interfaceone.util.ValidateUtils;

import net.sf.json.JSONObject;

@RestController
public class UserBankCarController {

    @Resource
    private BankCardDao bankCardDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserLoginDao userLoginDao;
    @Resource
    private BankDao bankDao;
    @Resource
    private RechargeWaterDao rechargeWaterDao;
    @Resource
    private ProjectDao projectDao;
    @Resource
    private InvestmentProjectDao investmentProjectDao;
    @Resource
    private ProjectreturnlogDao projectreturnlogDao;


    private static final Logger log = LoggerFactory.getLogger(UserBankCarController.class);

    /**
     * @RequestBody 返回的数据为对象
     * @RequestParam 可以接受map类型的参数
     */


    //密码加密测试接口
    @RequestMapping(value = "/encryption", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> responseIndex(@RequestBody Map<String, Object> paraMap) {
        String pwd = UtilMethod.md5Encryption(String.valueOf(paraMap.get("pwd")));
        log.info("\n****************************************************************************************\n" +
                 "*                   调用加密测试接口，此接口仅供测试调取明文加密操作                            *\n" +
                 "****************************************************************************************");
        log.info("明文：" + paraMap.get("pwd") + "加密加盐后为：" + pwd);
        paraMap.put("statusCode", "2000");
        paraMap.put("pwd", pwd);
        return paraMap;
    }


    /**
     * @param @return 设定文件
     * @return Map<String, String>    返回类型
     * @throws
     * @Title: insertUser
     * @Description: TODO(登录)
     */
    @RequestMapping(value = "/longin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertUser(@RequestBody User param) {
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        User user = new User();
        //进行密码账号输入为空判断
        if (param.getPhone() != null && param.getPhone().length() != 0
                && param.getPassWd() != null && param.getPassWd().length() != 0) {
            //对手机号进行正则判断
            if (ValidateUtils.isMobile(param.getPhone().replace(" ", ""))) {
                user = userDao.selectByLogIn(param.getPhone().replace(" ", ""));
                if (user == null) {
                    return ResponseMessage.loginFailed();
                }
                log.info("用户输入账号为：" + param.getPhone().replace(" ", ""));
                log.info("用户输入密码为：" + UtilMethod.md5Encryption(param.getPassWd()));
                log.info("用户真实密码为：" + param.getPassWd());
                log.info("查询到的加密密文密码为：" + user.getPassWd());
                //进行加密后的密文对比
                if (UtilMethod.md5Encryption(param.getPassWd()).equals(user.getPassWd())) {
                    //进行token生成，进行判断
                    /**
                     * 如需初测登录直接生成uuid需要更改此处方法
                     * 此处方法直接生成UUID会有安全风险
                     */
                    if (tokenUUIDLogin(param)) {
                        return ResponseMessage.loginSuccessful1(param);
                    }
                    //登录成功
                    return ResponseMessage.loginSuccessful();
                }
                return ResponseMessage.loginFailed();
            } else {
                return ResponseMessage.loginFailed();
            }
        } else {
            return ResponseMessage.loginFailed1();
        }
    }


    /*	*//**
     *
     * @Title: smsCode 注意参数对象，搞混
     * @Description: TODO(短信验证码请求方法)
     * @param @param phone
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     *//*
	@RequestMapping(value = "/SMSCode", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> smsCode(@RequestBody UserLogin paramUserLogin) {
		log.info("接收到的参数为：" + paramUserLogin.toString());
		Map<String, Object> paraMap = new HashMap<>();
		//验证手机号码是否正常
		if (ValidateUtils.isMobile(paramUserLogin.getPhone().replace(" ", ""))) {
			//已存在的获取验证码时间
			Long last = 0L;
			//当前时间
			Long curren = 0L;
			UserLogin userLogin = new UserLogin();
			try {
				userLogin = userLoginDao.selectByUserLogin(paramUserLogin);
			} catch (Exception e) {
				log.error(e + "数据库查询失败！");
			}
			int number = 0;
			if (userLogin != null) {
				number = userLogin.getNumber();
				last = userLogin.getCurrentUnix();
			}
			//获取到最后一次获取验证码的时间进行判断
			curren = UtilMethod.unixString();
			//进行用户验证，是否为等待24小时后获取的用户
			if (number >= 20) {
				if (curren >=  (last + ConstantParam.ROUND_THE_CLOCK_UNIX)) {
					userLoginDao.delUserLogin(paramUserLogin.getPhone());
				}else {
					// 针对超过20次的用户进行24小时时间限制
					paraMap.put(ConstantParam.SMS_VERIFICATION_PHONE, paramUserLogin.getPhone());
					paraMap.put(ConstantParam.PARAM_WATING_TIME, Math.abs(ConstantParam.ROUND_THE_CLOCK_UNIX) - (curren - last));
					paraMap.put(ConstantParam.PARAM_NUMBER, userLogin.getNumber());
					return UserLogService.responseMapFailure(paraMap,Math.abs(ConstantParam.ROUND_THE_CLOCK_UNIX) - (curren - last));
				}
			}
			//执行查询操作确认是否为第一次获取
			Integer index = userLoginDao.selectByUserLoginCount(paramUserLogin.getPhone());
			// 进行验证码限制取消，可不间断一直重复获取
			index = 0;
			if (index == ConstantParam.PARAM_ZERO) {
				//获取随机短信验证码
				UtilMethod.userLonginStatus(paramUserLogin);
				//获取当前的时间戳，set进对象，进行数据库更新
				paramUserLogin.setCurrentUnix(UtilMethod.unixString());
				//进行验证码次数计算
				paramUserLogin.setNumber(number);
				//进行数据插入操作
				userLoginDao.insertUserLogin(paramUserLogin);
				paraMap.put(ConstantParam.SMS_VERIFICATION_PHONE, paramUserLogin.getPhone());
				paraMap.put(ConstantParam.SMS_VERIFICATION_CODE, paramUserLogin.getSmsVerificationCode());
				paraMap.put(ConstantParam.PARAM_NUMBER, paramUserLogin.getNumber());
				return UserLogService.responseMapSuccess(paraMap);
								if (index == ConstantParam.PARAM_ZERO) {
					//获取随机短信验证码
					UtilMethod.userLonginStatus(paramUserLogin);
					//获取当前的时间戳，set进对象，进行数据库更新
					paramUserLogin.setCurrentUnix(UtilMethod.unixString());
					//进行验证码次数计算
					paramUserLogin.setNumber(number + 1);
					//进行数据插入操作
					userLoginDao.insertUserLogin(paramUserLogin);
					paraMap.put(ConstantParam.SMS_VERIFICATION_PHONE, paramUserLogin.getPhone());
					paraMap.put(ConstantParam.SMS_VERIFICATION_CODE, paramUserLogin.getSmsVerificationCode());
					paraMap.put(ConstantParam.PARAM_NUMBER, paramUserLogin.getNumber());
					return UserLogService.responseMapSuccess(paraMap);
				 			}else{
					 // 判断获取验证码的次数
					 if (number >= 1 && number < 20 ) {
						 //如果满足获取的要求进行获取验证码
						 if (curren - last >= ConstantParam.ONE_MINUTE) {
							 //获取随机短信验证码
							 UtilMethod.userLonginStatus(paramUserLogin);
							 //获取当前的时间戳，set进对象，进行数据库更新
							 paramUserLogin.setCurrentUnix(UtilMethod.unixString());
							 //进行number计算
							 paramUserLogin.setNumber(number + 1);
							 //进行数据更新操作
							 index = 0;
							 index = userLoginDao.updateUserLogin(paramUserLogin);
							 //成功后进行参数传递
							 if (index != ConstantParam.PARAM_ZERO) {
								 paraMap.put(ConstantParam.SMS_VERIFICATION_PHONE, paramUserLogin.getPhone());
								 paraMap.put(ConstantParam.SMS_VERIFICATION_CODE, paramUserLogin.getSmsVerificationCode());
								 paraMap.put(ConstantParam.PARAM_NUMBER, paramUserLogin.getNumber());
								 return UserLogService.responseMapSuccess(paraMap);
							 }else {
								 //更新失败，提示错误信息
								 return ResponseMessage.connectionRegistrationFailed();
							 }
						 }else {
							 paraMap.put(ConstantParam.SMS_VERIFICATION_PHONE, paramUserLogin.getPhone());
							 paraMap.put(ConstantParam.PARAM_WATING_TIME, Math.abs((curren - last) - ConstantParam.ONE_MINUTE));
							 paraMap.put(ConstantParam.PARAM_NUMBER, userLogin.getNumber());
							 return UserLogService.responseMapFailure(paraMap,Math.abs((curren - last) - ConstantParam.ONE_MINUTE));
						 }
					 }else {
						 //超过20次验证码
						 paraMap.put(ConstantParam.SMS_VERIFICATION_PHONE, paramUserLogin.getPhone());
						 paraMap.put(ConstantParam.PARAM_WATING_TIME, Math.abs(ConstantParam.ROUND_THE_CLOCK_UNIX) - (curren - last));
						 paraMap.put(ConstantParam.PARAM_NUMBER, userLogin.getNumber());
						 return UserLogService.responseMapFailure(paraMap,Math.abs(ConstantParam.ROUND_THE_CLOCK_UNIX) - (curren - last));
					 }
				 }
		}else {
			return UserLogService.phoneErrorMag();
		}
	}*/

    /**
     * @param @param  phone
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: smsCode 注意参数对象，搞混
     * @Description: TODO(短信验证码请求方法)
     */
    @RequestMapping(value = "/SMSCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> smsCode(@RequestBody UserLogin paramUserLogin) {
        log.info("接收到的参数为：" + JSONObject.fromObject(paramUserLogin));
        Map<String, Object> paraMap = new HashMap<>();
        // 验证手机号码是否正常
        if (ValidateUtils.isMobile(paramUserLogin.getPhone().replace(" ", ""))) {
            UserLogin userLogin = new UserLogin();
            try {
                userLogin = userLoginDao.selectByUserLogin(paramUserLogin);
            } catch (Exception e) {
                log.error(e + "数据库查询失败！");
            }
            int number = 0;
            if (userLogin != null) {
                number = userLogin.getNumber();
            }
            // 获取到最后一次获取验证码的时间进行判断
            // 获取随机短信验证码
            UtilMethod.userLonginStatus(paramUserLogin);
            // 获取当前的时间戳，set进对象，进行数据库更新
            paramUserLogin.setCurrentUnix(UtilMethod.unixString());
            // 进行验证码次数计算
            paramUserLogin.setNumber(number + 1);
            Integer index = userLoginDao.selectByUserLoginCount(paramUserLogin.getPhone());
            if (index == ConstantParam.PARAM_ZERO) {
                userLoginDao.insertUserLogin(paramUserLogin);
            } else {
                // 进行数据插入操作
                userLoginDao.updateUserLogin(paramUserLogin);
            }
            paraMap.put(ConstantParam.SMS_VERIFICATION_PHONE, paramUserLogin.getPhone());
            paraMap.put(ConstantParam.SMS_VERIFICATION_CODE, paramUserLogin.getSmsVerificationCode());
            paraMap.put(ConstantParam.PARAM_NUMBER, paramUserLogin.getNumber());
            return UserLogService.responseMapSuccess(paraMap);
        } else {
            return UserLogService.phoneErrorMag();
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: registerIndex
     * @Description: TODO(注册)
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerIndex(@RequestBody User param) {
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        // 进行手机号空格剔除
        param.setPhone(param.getPhone().replace(" ", ""));
        // 手机号码正则验证
        if (ValidateUtils.isMobile(param.getPhone())) {
            // 进行数据库账号比对，验证账号是否已存在
            Integer response = userDao.insertUserDetection(param);
            if (response == 0) {
                // 账号密码输入是否为空判断
                if (param.getPhone() != null && param.getPhone().length() != 0) {
                    //密码长度是否小于8位
                    if (param.getPassWd() != null && param.getPassWd().length() >= 8) {
                        //密码长度是否大于32位
                        if (param.getPassWd() != null && param.getPassWd().length() <= 32) {
                            //短信验证码是否输入
                            if (param.getSmsVerificationCode() != null
                                    && param.getSmsVerificationCode().length() != 0) {
                                //两次输入密码是否正确验证
                                if (param.getPassWd().equals(param.getPassWord())) {
                                    // 进行手机短信验证码验证
                                    UserLogin userLogin = new UserLogin();
                                    userLogin.setPhone(param.getPhone());
                                    userLogin = userLoginDao.selectByUserLogin(userLogin);
                                    // 判断输入的短信验证码是否有效
                                    if (param.getSmsVerificationCode().equals(userLogin.getSmsVerificationCode())
                                            && (UtilMethod.unixString()
                                            - userLogin.getCurrentUnix()) <= ConstantParam.FIVE_MINUTES_UNIX) {
                                        log.info("当前系统时间：" + UtilMethod.unixString());
                                        log.info("获取验证码的时间：" + userLogin.getCurrentUnix());
                                        log.info("验证码获取已：" + (UtilMethod.unixString() - userLogin.getCurrentUnix())
                                                + "秒过期");
                                        log.info("验证码过期还有："
                                                + (ConstantParam.FIVE_MINUTES_UNIX
                                                - (UtilMethod.unixString() - userLogin.getCurrentUnix()))
                                                + "秒");
                                        // 进行密码加密加盐
                                        param.setPassWd(UtilMethod.md5Encryption(param.getPassWd()));
                                        // 进行数据库注册信息插入
                                        Integer responsePaman = userDao.insertUser(param);
                                        if (responsePaman != 0) {
                                            return ResponseMessage.registrationSuccess();
                                        } else {
                                            return ResponseMessage.connectionRegistrationFailed();
                                        }
                                    } else if ((UtilMethod.unixString()
                                            - userLogin.getCurrentUnix()) > ConstantParam.FIVE_MINUTES_UNIX) {
                                        log.info("当前时间：" + UtilMethod.unixString());
                                        log.info("验证获取时间：" + userLogin.getCurrentUnix());
                                        log.info("验证码已过期：" + ((UtilMethod.unixString() - userLogin.getCurrentUnix())
                                                - ConstantParam.FIVE_MINUTES_UNIX) + "秒");
                                        // 短信验证码过期，请重新获取
                                        return ResponseMessage.loginFailed4();
                                    } else {
                                        // 短信验证码输入错误，请重新输入
                                        return ResponseMessage.loginFailed5();
                                    }
                                } else {
                                    return ResponseMessage.loginFailed3();
                                }
                            } else {
                                return ResponseMessage.informationErrorRegistrationFailed3();
                            }
                        } else {
                            return ResponseMessage.informationErrorRegistrationFailed2();
                        }
                    } else {
                        return ResponseMessage.informationErrorRegistrationFailed1();
                    }

                } else {
                    return ResponseMessage.informationErrorRegistrationFailed();
                }
            } else {
                return ResponseMessage.userRepetitionRegistrationFailed();
            }
        } else {
            return ResponseMessage.loginFailed2();
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: RealCertification
     * @Description: TODO(绑定身份证 、 手机号 、 姓名信息)
     */
    @RequestMapping(value = "/realcertification", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> realCertification(@RequestBody User param) {
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.getPhone());
        userToken.setStrUUID(param.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        IdcardValidator iv = new IdcardValidator();
        User user;
        //查询绑定信息
        if (ValidateUtils.isMobile(param.getPhone().replace(" ", ""))) {
            //因数据库中限制，查询 到的数据不可能为null
            user = userDao.selectByUserIdCard(param);
        } else {
            return ResponseMessage.verificationPhoneFailed();
        }
        if (!"".equals(user.getIdCard()) && user.getIdCard() != null) {
            return ResponseMessage.verificationUserNameFailed01(user);
        }
        //判断验证次数是否超3次
		if (user.getCertificationNumber() >= ConstantParam.PARAM_AUTHENTIATION_FAILD) {
			//验证次数超三次，提示24小时之后再来验证
			if (user.getCertificationTime() <= ConstantParam.ROUND_THE_CLOCK_UNIX) {
				User user1 = new User();
				user1.setPhone(param.getPhone());
				user1.setCertificationNumber(ConstantParam.PARAM_CODE_NOT_CERTIFID);
				userDao.updateUser(user1);
			}
			return ResponseMessage.verificationPhoneFailed1(user.getCertificationTime());
		}
        // 判断身份证号是否符合规范
        if (iv.isValidatedAllIdcard(param.getIdCard())) {
            // 判断姓名
            if (param.getUserName() != null && param.getUserName().length() >= 2) {
                // 进行验证次数和时间计算
                UtilMethod.getCationNumber(user, param, ConstantParam.PARAM_CODE_IN_CERTIFICATION);
                // 进行绑定身份证信息
                userDao.updateUser(param);
                // 绑定成功，返回绑定信息
                return ResponseMessage.bindingSuccess();
            } else {
                // 进行验证次数和时间计算
                UtilMethod.getCationNumber(user, param, ConstantParam.PARAM_CODE_NOT_CERTIFID);
                // 验证失败，数据更新
                userDao.updateUser(param);
                // 姓名为空或不正确，绑定失败
                return ResponseMessage.verificationUserNameFailed();
            }
        } else {
            // 进行验证次数和时间计算
            UtilMethod.getCationNumber(user, param, ConstantParam.PARAM_CODE_NOT_CERTIFID);
            // 验证失败，数据更新
            userDao.updateUser(param);
            // 身份证号为空或不正确，绑定失败
            return ResponseMessage.verificationIdCardFailed();
        }
    }

    public String getBankCardName(String bankCard) {
        if (bankCard.length() >= 6) {
            return GetCheckBankCard.getname(bankCard);
        } else {
            //银行归属查询失败
            return null;
        }
    }


    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: bindingBankCard
     * @Description: TODO(绑定银行卡)
     */
    @RequestMapping(value = "/bindingbankcard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> bindingBankCard(@RequestBody BankCardRecharge param) {
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.getPhone());
        userToken.setStrUUID(param.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        IdcardValidator iv = new IdcardValidator();
        User paramUser = new User();
        BankCard bankCard = new BankCard();
        paramUser.setPhone(param.getPhone());
        bankCard.setBankCardNumber(param.getBankCardNumber());
        Integer response = userDao.insertUserDetection(paramUser);
        Integer bankCardNumber = bankCardDao.selectByBankCardVerification(param);
        if (bankCardNumber == 0) {
            // 检查手机号是否为空、存在
            if (param.getPhone() != null && response != 0) {
                // 检查身份证号是否规范
                if (iv.isValidatedAllIdcard(param.getIdCard())) {
                    // 检查持卡人名称，只针对长度进行最低限制
                    if (param.getCardholderName() != null && param.getCardholderName().length() >= 2) {
                        // 检查银行卡号
                        if (GetCheckBankCard.checkBankCard(param.getBankCardNumber())) {
                            param.setBankName(getBankCardName(param.getBankCardNumber()));
                            // 检查输入的支付密码是否符合规范
                            if ((param.getPaymentPassword()).length() == 6) {
                                User user = new User();
                                User user2 = new User();
                                user2.setPhone(param.getPhone());
                                user = userDao.selectByPay(user2);
                                // 检查支付密码是否符合
                                try {
                                    user.getPaymentPassword();
                                } catch (Exception e) {
                                    return ResponseMessage.rechargeFailedOne4();
                                }
                                if (!"".equals(user.getPaymentPassword())) {
                                    if (UtilMethod.md5Encryption(param.getPaymentPassword()).equals(user.getPaymentPassword())) {
                                        // 绑定银行卡
                                        Integer index = bankCardDao.insertBankCard(param);
                                        if (index != 0) {
                                            return ResponseMessage.duccessfullyTiedTheCard();
                                        } else {
                                            return ResponseMessage.connectionRegistrationFailed();
                                        }
                                    } else {
                                        // 支付密码错误
                                        return ResponseMessage.rechargeFailedOne3();
                                    }
                                } else {
                                    // 支付密码错误
                                    return ResponseMessage.rechargeFailedOne4();
                                }
                            } else {
                                // 支付密码错误
                                return ResponseMessage.rechargeFailedOne3();
                            }
                        } else {
                            return ResponseMessage.cardingFailedFives();
                        }
                    } else {
                        return ResponseMessage.cardingFailedTwo();
                    }

                } else {
                    return ResponseMessage.cardingFailedOne();
                }
            } else {
                return ResponseMessage.cardingFailedFour();
            }
        } else {
            return ResponseMessage.bankCardRepeat();
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: rPaymentPassWord
     * @Description: TODO(修改 ， 设置支付密码)
     */
    @RequestMapping(value = "/newpaypd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> newPaymentPassWord(@RequestBody Map<String, String> param) {
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.get(ConstantParam.SMS_VERIFICATION_PHONE));
        userToken.setStrUUID(param.get(ConstantParam.PARAM_TOKEN));
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        if (UtilMethod.isNumeric(param.get(ConstantParam.PARAM_NEW_PAY_PASSWORD))
                && (param.get(ConstantParam.PARAM_NEW_PAY_PASSWORD)).length() == 6) {
            //参数必备：手机号、旧支付密码、新支付密码
            User user = new User();
            User user1 = new User();
            //将手机号传给user对象用于查询数据准备
            user.setPhone(param.get(ConstantParam.SMS_VERIFICATION_PHONE));
            //获取必要参数
            user1 = userDao.selectByUserPayPassWd(user);
            //进行支付密码加密处理
            try {
                if (user1.getPaymentPassword().length() == 0 && "".equals(user1.getPaymentPassword())) {
                    String passwd = UtilMethod.md5Encryption(param.get(ConstantParam.PARAM_NEW_PAY_PASSWORD));
                    user.setPaymentPassword(passwd);
                    //进行初次支付密码插入
                    userDao.updatePaymentPassword(user);
                    return ResponseMessage.rechargeFailedOne2();
                }
            } catch (Exception e) {
                String passwd = UtilMethod.md5Encryption(param.get(ConstantParam.PARAM_NEW_PAY_PASSWORD));
                user.setPaymentPassword(passwd);
                //进行初次支付密码插入
                userDao.updatePaymentPassword(user);
                return ResponseMessage.rechargeFailedOne2();
            }
            //非第一次新增密码，请点击修改密码
            return ResponseMessage.rechargeFailedOne1();
        }
        return ResponseMessage.rechargeFailedOne3();

    }


    /**
     * @param @param  phone
     * @param @param  backCard
     * @param @param  amount
     * @param @return 设定文件
     * @return Bank    返回类型
     * @throws
     * @Title: getBack
     * @Description: TODO(充值 、 提现调用的支付接口)
     */
    @RequestMapping(value = "/bank", method = RequestMethod.GET)
    @ResponseBody
    public Bank getBack(@RequestParam(ConstantParam.SMS_VERIFICATION_PHONE) String phone,
                        @RequestParam(ConstantParam.PARAM_BACK_CARD) String backCard,
                        @RequestParam(ConstantParam.PARAM_AMOUNT) String amount,
                        @RequestParam(ConstantParam.PARAM_RECHRGE_ORWITHDRAW) String rechargeOrWithdraw) {
        Bank bank = new Bank();
        String.valueOf(UUID.randomUUID());
        log.info("银行卡验证成功");
        String a = PropertyUtil.getPro(ConstantParam.PARAM_SEQUENCECONFIG_PROPERTIES, ConstantParam.PARAM_SEQUENCE);
        Integer ite = Integer.parseInt(a);
        a = String.valueOf(++ite);
        a.length();
        StringBuffer c = new StringBuffer();
        for (int i = 0; i < 9 - a.length(); i++) {
            c.append(ConstantParam.PARAM_ZERO);
        }
        if (PropertyUtil.updatePro(ConstantParam.PARAM_SEQUENCECONFIG_PROPERTIES, ConstantParam.PARAM_SEQUENCE, c + a)) {
            //流水号
            bank.setSerialNumber("1000000" + UtilMethod.getdingdan() + c + a);
            log.info("生成流水号：" + bank.getSerialNumber());
            //订单号
            bank.setOrderNumber(String.valueOf(UUID.randomUUID()));
            log.info("生成的订单号：" + bank.getOrderNumber());
            //充值金额
            if (" ".equals(rechargeOrWithdraw)) {
                rechargeOrWithdraw = "+";
            }
            bank.setAmountOfTheTransaction(rechargeOrWithdraw + amount);
            log.info("交易金额：" + rechargeOrWithdraw + amount);
            //生成数据库对应的时间格式
            bank.setTransactionTime(UtilMethod.getTime());
            log.info("交易时间：" + UtilMethod.getTime());
            //银行卡号
            bank.setBankCardNumber(backCard);
            log.info("银行卡号：" + backCard);
            //交易状态
            bank.setTradingStatus(1);
            log.info("交易成功！");
        }
        bankDao.insertAll(bank);
        return bank;
    }


    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: recharge
     * @Description: TODO(充值)
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> recharge(@RequestBody BankCardRecharge param) {
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.getPhone());
        userToken.setStrUUID(param.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        log.info("接收的参数为" + JSONObject.fromObject(param));
        //判断充值金额
        log.info("输入充值金额：" + param.getNumber());
        if (param.getNumber() > ConstantParam.PARAM_UPPER_LIMIT) {
            ResponseMessage.rechargeFailedOne6();
        }
        if (param.getNumber() < ConstantParam.PARAM_LOWER_LIMIT) {
            ResponseMessage.rechargeFailedOne5();
        }
        //需要账号，且完成实名认证，需要填写银行卡号，验证支付密码
        Map<String, Object> jsonMap;
        BankCard bankCard = new BankCard();
        bankCard.setBankCardNumber(param.getBankCardNumber());
        bankCard.setPhone(param.getPhone());
        Integer response = bankCardDao.selectByBankCardVerification(bankCard);
        User userParam;
        if (param.getPhone() != null) {
            // 判断账号是否正确
            userParam = userDao.selectByUser(param.getPhone());
            if (userParam.getPhone().equals(param.getPhone())) {
                // 判断银行卡是否已绑定
                if (response != 0) {
                    // 判断是否完成实名认证
                    if (userParam.getIdCard() != null) {
                        // 判断支付密码是否正确
                        User user1 = new User();
                        User user2 = new User();
                        user2.setPhone(param.getPhone());
                        user1 = userDao.selectByPay(user2);
                        if (UtilMethod.md5Encryption(param.getPaymentPassword()).equals(user1.getPaymentPassword())) {
                            // 执行充值操作，完成充值
                            User user = new User();
                            user.setPhone(param.getPhone());
                            user.setBalance(userParam.getBalance() + param.getNumber());
                            //调取银行充值接口
                            jsonMap = (Map<String, Object>) JSON.parse(String.valueOf(JSONObject.fromObject(RequestCircuits.sendGet(
                                    param.getPhone(), param.getBankCardNumber(), String.valueOf(param.getNumber()), "-"))));
                            //进行平台自己的充值流水生成
                            RechargeWater rw = new RechargeWater();
                            //手机号
                            rw.setPhone(param.getPhone());
                            //充值金额
                            rw.setAmountOfTheTransaction("+" + (String.valueOf(jsonMap.get(ConstantParam.PARAM_AMOUNT_TRANSACTION)).replace("-", "")));
                            //流水号
                            rw.setSerialNumber(String.valueOf(jsonMap.get(ConstantParam.PARAM_SERIAL_NUMBER)));
                            //充值状态
                            rw.setTradingStatus(Integer.valueOf(String.valueOf((jsonMap.get(ConstantParam.PARAM_TRADING_STATUS)))));
                            //订单时间
                            rw.setTransactionTime(String.valueOf(jsonMap.get(ConstantParam.PARAM_TRANSACTION_TIME)));
                            //进行平台订单记录生成
                            rechargeWaterDao.insertAll(rw);
                            //进行平台账户余额增加
                            userDao.updateAmount(user);
                            return ResponseMessage.rechargeSuccessful();
                        } else {
                            return ResponseMessage.rechargeFailedOne();
                        }
                    } else {
                        return ResponseMessage.rechargeFailedTwo();
                    }
                } else {
                    return ResponseMessage.rechargeFailedThree();
                }
            } else {
                return ResponseMessage.rechargeFailedFour();
            }
        } else {
            return ResponseMessage.rechargeFailedFour();
        }
    }


    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: recharge
     * @Description: TODO(提现)
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/consumption", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> consumption(@RequestBody UserBankCard param) {
        Map<String, Object> jsonMap;
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.getPhone());
        userToken.setStrUUID(param.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        //判断充值金额
        if (param.getBalance() > ConstantParam.PARAM_WITHDRAW_LOWER_LIMIT) {
            ResponseMessage.rechargeFailedOne7();
        }
        if (param.getBalance() < ConstantParam.PARAM_LOWER_LIMIT) {
            ResponseMessage.rechargeFailedOne5();
        }
        User userParam = new User();
        userParam = userDao.selectByUser(param.getPhone());
        User user = new User();
        if (UtilMethod.md5Encryption(param.getPaymentPassword()).equals(userParam.getPaymentPassword())) {
            //消费金额判断
            if (userParam.getBalance() >= param.getBalance()) {
                jsonMap = (Map<String, Object>) JSON.parse(String.valueOf(JSONObject.fromObject(RequestCircuits.sendGet(
                        param.getPhone(), param.getBankCardNumber(), String.valueOf(param.getBalance()), "+"))));
                //进行平台自己的充值流水生成
                RechargeWater rw = new RechargeWater();
                //手机号
                rw.setPhone(param.getPhone());
                //充值金额
                rw.setAmountOfTheTransaction("-" + (String.valueOf(jsonMap.get(ConstantParam.PARAM_AMOUNT_TRANSACTION)).replace("+", "")));
                //流水号
                rw.setSerialNumber(String.valueOf(jsonMap.get(ConstantParam.PARAM_SERIAL_NUMBER)));
                //充值状态
                rw.setTradingStatus(Integer.valueOf(String.valueOf((jsonMap.get(ConstantParam.PARAM_TRADING_STATUS)))));
                //订单时间
                rw.setTransactionTime(String.valueOf(jsonMap.get(ConstantParam.PARAM_TRANSACTION_TIME)));
                //进行平台订单记录生成
                rechargeWaterDao.insertAll(rw);
                //进行平台账户余额扣除
                user.setBalance(userParam.getBalance() - param.getBalance());
                user.setPhone(param.getPhone());
                userDao.updateAmount(user);
                return ResponseMessage.rechargeOne();
            } else {
                return ResponseMessage.rechargeTwo();
            }
        } else {
            return ResponseMessage.rechargeThree();
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: untiedBankCard
     * @Description: TODO(解绑银行卡)
     */
    @RequestMapping(value = "/ubcard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> untiedBankCard(@RequestBody BankCardRecharge param) {
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        //参数验证 卡号
        if (param.getBankCardNumber() == null && param.getBankCardNumber().length() == ConstantParam.PARAM_ZERO) {
            //卡号为空
            return ResponseMessage.untieError2();
        } else if (!GetCheckBankCard.checkBankCard(param.getBankCardNumber())) {
            //卡号不合法
            return ResponseMessage.untieError3();
        }
        //参数验证 手机号
        if (param.getPhone() == null && param.getPhone().length() == ConstantParam.PARAM_ZERO) {
            //卡号为空
            return ResponseMessage.untieError2();
        } else if (!ValidateUtils.isMobile(param.getPhone())) {
            //手机号不为真
            return ResponseMessage.untieError4();
        }
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.getPhone());
        userToken.setStrUUID(param.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        //进行支付密码验证
        User user = new User();
        user.setPhone(param.getPhone());
        user = userDao.selectByPay(user);
        if (!UtilMethod.md5Encryption(param.getPaymentPassword()).equals(user.getPaymentPassword())) {
            //支付密码错误
            return ResponseMessage.rechargeThree();
        }
        //查询是否为默认银行卡,默认银行卡不可解绑
        Integer index = bankCardDao.selectCardCount(param);
        if (index == 1) {
            return ResponseMessage.untieError();
        }
        //不为默认卡，可进行继续解绑
        Integer index1 = bankCardDao.delBankCard(param);
        if (index1 == 1) {
            //成功
            return ResponseMessage.untieSuccess();
        } else {
            //失败
            return ResponseMessage.untieError1();
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: Logout
     * @Description: TODO(注销账号)
     */
    @RequestMapping(value = "/Logout", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> logout(@RequestBody User param) {
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.getPhone());
        userToken.setStrUUID(param.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        if (ValidateUtils.isMobile(param.getPhone())) {
            Integer index = userDao.insertUserDetection(userToken);
            if (index == 0) {
                //账号不存在
                return ResponseMessage.LogoutFailure();
            }
            //查询密码
            userToken = userDao.selectByLogIn(userToken.getPhone());
            //密码验证
            if ((UtilMethod.md5Encryption(param.getPassWd())).equals(userToken.getPassWd())) {
                //密码验证成功，进行账户注销操作
                userDao.delUser(param.getPhone());
                log.info("账号销户成功！");
                return ResponseMessage.LogoutSuccess();
            } else {
                //密码错误
                return ResponseMessage.LogoutFailure1();
            }
        } else {
            //账号输入不合法
            return ResponseMessage.LogoutFailure2();
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryBankCard
     * @Description: TODO(查询绑定的银行卡)
     */
    @RequestMapping(value = "/querybankcard", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryBankCard(@RequestBody BankCardRecharge param) {
        log.info("接收到的参数为：" + JSONObject.fromObject(param));
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(param.getPhone());
        userToken.setStrUUID(param.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        if (!ValidateUtils.isMobile(param.getPhone())) {
            return ResponseMessage.LogoutFailure2();
        }
        List<BankCard> bankList = new ArrayList<>();
        bankList = bankCardDao.selectByBankCardAll(param.getPhone());
        return ResponseMessage.queryCardSuccess(bankList);
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: changePassword
     * @Description: TODO(修改密码)
     */
    @RequestMapping(value = "/chpwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changePassword(@RequestBody Map<String, Object> paraMap) {
        log.info("接收到的参数为：" + paraMap.toString());
        User user = new User();
        //验证token
        //检查登录状态
        User userToken = new User();
        userToken.setPhone(String.valueOf(paraMap.get(ConstantParam.PARAM_USER)));
        userToken.setStrUUID(String.valueOf(paraMap.get(ConstantParam.PARAM_TOKEN)));
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        //获取到输入的密码
        //paraMap.get(ConstantParam.PARAM_ORIGINAL_PASSWORD);
        //获取到需要修改的账号
        //paraMap.get(ConstantParam.PARAM_USER);
        //获取到需要修改的密码
        //paraMap.get(ConstantParam.PARAM_NEW_PASSWORD);
        //paraMap.get(ConstantParam.PARAM_REPEAT_PASSWORD);
        if (!ValidateUtils.isMobile(String.valueOf(paraMap.get(ConstantParam.PARAM_USER)))) {
            log.info(String.valueOf(paraMap.get(ConstantParam.PARAM_USER)) + " 手机号非法。");
            return ResponseMessage.loginFailed6();
        }
        user = userDao.selectByLogIn(String.valueOf(paraMap.get(ConstantParam.PARAM_USER)));
        User newUser = new User();
        if (String.valueOf(paraMap.get(ConstantParam.PARAM_ORIGINAL_PASSWORD)).length() == 6) {
            if (UtilMethod.md5Encryption(String.valueOf(paraMap.get(ConstantParam.PARAM_ORIGINAL_PASSWORD))).equals(user.getPaymentPassword())) {
                //修改支付密码
                if (paraMap.get(ConstantParam.PARAM_NEW_PASSWORD).equals(paraMap.get(ConstantParam.PARAM_REPEAT_PASSWORD))
                        && String.valueOf(paraMap.get(ConstantParam.PARAM_NEW_PASSWORD)).length() == 6) {
                    newUser.setPaymentPassword(UtilMethod.md5Encryption(String.valueOf(paraMap.get(ConstantParam.PARAM_NEW_PASSWORD))));
                    newUser.setPhone(String.valueOf(paraMap.get(ConstantParam.PARAM_USER)));
                    log.info("验证无误，支付密码修改开始...");
                    userDao.updatePaymentPassword(newUser);
                } else {
                    log.info("用户输入的密码为：" + paraMap.get(ConstantParam.PARAM_NEW_PASSWORD)
                            + "/n重复密码为：" + paraMap.get(ConstantParam.PARAM_REPEAT_PASSWORD));
                    return ResponseMessage.loginFailed3();
                }
            } else {
                log.info("用户输入的加密后密文为：" + UtilMethod.md5Encryption(String.valueOf(paraMap.get(ConstantParam.PARAM_ORIGINAL_PASSWORD)))
                        + "/n用户的真实密码密文为：" + user.getPaymentPassword());
                return ResponseMessage.rechargeFailedOne3();
            }
        } else {
            if (UtilMethod.md5Encryption(String.valueOf(paraMap.get(ConstantParam.PARAM_ORIGINAL_PASSWORD))).equals(user.getPassWd())) {
                //修改登录密码
                if (paraMap.get(ConstantParam.PARAM_NEW_PASSWORD).equals(paraMap.get(ConstantParam.PARAM_REPEAT_PASSWORD))) {
                    newUser.setPassWd(UtilMethod.md5Encryption(String.valueOf(paraMap.get(ConstantParam.PARAM_NEW_PASSWORD))));
                    newUser.setPhone(String.valueOf(paraMap.get(ConstantParam.PARAM_USER)));
                    log.info("登录密码验证无误，开始修改...");
                    userDao.updatePaymentPassword(newUser);
                } else {
                    log.info("用户输入的密码为：" + paraMap.get(ConstantParam.PARAM_NEW_PASSWORD)
                            + "/n重复密码为：" + paraMap.get(ConstantParam.PARAM_REPEAT_PASSWORD));
                    return ResponseMessage.loginFailed3();
                }
            } else {
                log.info("用户输入的加密后密文为：" + UtilMethod.md5Encryption(String.valueOf(paraMap.get(ConstantParam.PARAM_ORIGINAL_PASSWORD)))
                        + "/n用户的真实密码密文为：" + user.getPassWd());
                return ResponseMessage.rechargeFailedOne3();
            }
        }
        return ResponseMessage.loginFailed7();
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: modifyTheBoundPhone
     * @Description: TODO(修改绑定手机号)
     */
    @RequestMapping(value = "/modifytheboundphone", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyTheBoundPhone(@RequestBody BankCardRecharge param) {


        return null;

    }

    /**
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: projectRelease
     * @Description: TODO(查询项目信息)
     */
    @RequestMapping(value = "/queryPro", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> projectRelease() {
        List<Project> list = new ArrayList<>();
        List<Map<String, Object>> list1 = new ArrayList<>();
        //查询到所有数据
        list = projectDao.selectAll();
        //进行迭代
        for (Project project : list) {
            Map<String, Object> map1 = new LinkedHashMap<>();
            map1.put(ConstantParam.PARAM_PROJECT_NAME, project.getProjectName());
            map1.put(ConstantParam.PARAM_PROJECT_ID, project.getProjectId());
            map1.put(ConstantParam.PARAM_TOTAL_PROJECT, project.getTotalProject());
            map1.put(ConstantParam.PARAM_REMAININ_AMOUNT, project.getRemainingAmount());
            map1.put(ConstantParam.PARAM_PROJECT_DEADLINE, project.getProjectDeadline());
            map1.put(ConstantParam.PARAM_ANNUAL_INTEREST_RATE, project.getAnnualInterestRate());
            map1.put(ConstantParam.PARAM_EXTRA_INTEREST_RATE, project.getExtraInterestRate());
            map1.put(ConstantParam.PARAM_VALIDITY_PERIOD, UtilMethod.getTime(project.getValidityPeriod()));
            map1.put(ConstantParam.PARAM_RRELEASE_TIME, UtilMethod.getTime(project.getReleaseTime()));
            map1.put(ConstantParam.PARAM_ENTRY_TIME, UtilMethod.getTime(project.getEntryTime()));
            list1.add(map1);
        }
        //封装参数进行返回
        return ResponseMessage.projectEntry(list1);
    }

    /**
     * @param @param  project
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: projectRelease
     * @Description: TODO(根据ID查询)
     */
    @RequestMapping(value = "/queryProId", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> projectRelease(@RequestBody Project project) {
        log.info("接收到的参数为：" + JSONObject.fromObject(project));
        //验证token
        //检查登录状态,暂目前查询所有项目为公开方法，不进行token验证
        User user = new User();
        user.setPhone(project.getPhone());
        user.setStrUUID(project.getToken());
        if (!tokenUUID(user)) {
            return ResponseMessage.verificationToken();
        }
        List<Project> list = new ArrayList<>();
        List<Map<String, Object>> list1 = new ArrayList<>();
        //查询到所有数据
        list.add(projectDao.selectProjectId(project));
        //进行迭代
        for (Project project1 : list) {
            Map<String, Object> map1 = new LinkedHashMap<>();
            map1.put(ConstantParam.PARAM_PROJECT_NAME, project1.getProjectName());
            map1.put(ConstantParam.PARAM_PROJECT_ID, project1.getProjectId());
            map1.put(ConstantParam.PARAM_TOTAL_PROJECT, project1.getTotalProject());
            map1.put(ConstantParam.PARAM_REMAININ_AMOUNT, project1.getRemainingAmount());
            map1.put(ConstantParam.PARAM_PROJECT_DEADLINE, project1.getProjectDeadline());
            map1.put(ConstantParam.PARAM_ANNUAL_INTEREST_RATE, project1.getAnnualInterestRate());
            map1.put(ConstantParam.PARAM_EXTRA_INTEREST_RATE, project1.getExtraInterestRate());
            map1.put(ConstantParam.PARAM_VALIDITY_PERIOD, UtilMethod.getTime(project1.getValidityPeriod()));
            map1.put(ConstantParam.PARAM_RRELEASE_TIME, UtilMethod.getTime(project1.getReleaseTime()));
            map1.put(ConstantParam.PARAM_ENTRY_TIME, UtilMethod.getTime(project1.getEntryTime()));
            list1.add(map1);
        }
        //封装参数进行返回
        return ResponseMessage.projectEntry(list1);
    }

    /**
     * @param @param  project
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: projectRelease
     * @Description: TODO(项目投资)
     */
    @RequestMapping(value = "/investment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> projectInvestment(@RequestBody InvestmentProject investmentProject) {
        log.info("接收到的参数为：" + JSONObject.fromObject(investmentProject));
        Project pro = new Project();
        //检查登录状态
        User user = new User();
        user.setPhone(investmentProject.getLinkedAccount());
        user.setStrUUID(investmentProject.getToken());
        if (!tokenUUID(user)) {
            return ResponseMessage.verificationToken();
        }
        //判断是否剩余投资总额满足投资金额
        pro.setProjectId(investmentProject.getInvestmentProjectId());
        pro = projectDao.selectProjectId(pro);
        if (pro.getRemainingAmount() < investmentProject.getInvestmentAmount()) {
            return ResponseMessage.investment();
        }
        //判断余额是否满足投资
        User user1 = new User();
        user1 = userDao.selectByAmount(user);
        if (user1.getBalance() < investmentProject.getInvestmentAmount()) {
            return ResponseMessage.investment1();
        }
        //支付密码判断
        if (!user1.getPaymentPassword().equals(UtilMethod.md5Encryption(investmentProject.getPaymentPassword()))) {
            return ResponseMessage.rechargeFailedOne3();
        }
        //投资计算，生成投资订单
        investmentProject.setInvestmentId(String.valueOf(UUID.randomUUID()));
        //生成流水单号
        //100000020181011000001
        String a = PropertyUtil.getPro(ConstantParam.PARAM_SEQUENCECONFIG_PROPERTIES, ConstantParam.PARAM_SEQUENCEL);
        Integer ite = Integer.parseInt(a);
        a = String.valueOf(++ite);
        StringBuffer c = new StringBuffer();
        for (int i = 0; i < 9 - a.length(); i++) {
            c.append(ConstantParam.PARAM_ZERO);
        }
        PropertyUtil.updatePro(ConstantParam.PARAM_SEQUENCECONFIG_PROPERTIES, ConstantParam.PARAM_SEQUENCEL, c + a);
        investmentProject.setInvestmentSerialNumber(ConstantParam.PARAM_ONE_MILLION + UtilMethod.getdingdan() + c + a);
        //投资计算，同步扣除账户余额
        user.setBalance(user1.getBalance() - investmentProject.getInvestmentAmount());
        userDao.updateAmount(user);
        //投资计算，同步扣除项目金额
        pro.setRemainingAmount(pro.getRemainingAmount() - investmentProject.getInvestmentAmount());
        projectDao.updateAmount(pro);
        //生成投资ID
        investmentProject.setInvestmentId(String.valueOf(UUID.randomUUID()));
        //生成投资时间
        investmentProject.setInvestmentTime(UtilMethod.unixString());
        //进行投资信息插入
        investmentProjectDao.insertAll(investmentProject);
        //计算是否达到汇款标准，进行回款数据清算

        if (pro.getRemainingAmount() == ConstantParam.PARAM_ZERO) {
            log.info("****************************************************************************************\n" +
                    "*                       项目剩余投资额为0，开始同步更新回款时间                                                     *\n" +
                    "****************************************************************************************");
            //同步查询此项目所有投资
            List<InvestmentProject> list = new ArrayList<>();
            list = investmentProjectDao.selectProjectId(investmentProject);
            Project project1 = new Project();
            //pro
            project1.setReleaseTime(UtilMethod.unixString());
            project1.setProjectId(pro.getProjectId());
            //同步更新项目开始计算回款时间
            projectDao.updateAmount(project1);
            for (InvestmentProject investmentProject2 : list) {
                //investmentProject2.setBiddingStatus(ConstantParam.PARAM_ZERO);
                //开始更新每笔购买项目的回款状态
                investmentProject2.setBiddingStatus(ConstantParam.PARAM_ZERO);
                investmentProjectDao.updatePaymentStatus(investmentProject2);
            }
            log.info("****************************************************************************************\n" +
                    "*                      所有项目信息更新结束，同步开始进入回款期                                                     *\n" +
                    "****************************************************************************************");
        }
        //封装参数进行返回
        return ResponseMessage.projectEntry2();
    }

    @RequestMapping(value = "/queryinvestment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryInvestment(@RequestBody User user) {
        log.info("接收到的参数为：" + JSONObject.fromObject(user));
        // 验证token
        // 检查登录状态
        User userToken = new User();
        userToken.setPhone(user.getPhone());
        userToken.setStrUUID(user.getToken());
        if (!tokenUUID(userToken)) {
            return ResponseMessage.verificationToken();
        }
        InvestmentProject ipt = new InvestmentProject();
        ipt.setLinkedAccount(user.getPhone());
        List<InvestmentProject> iptList = new ArrayList<>();
        List<Project> pjtList = new ArrayList<>();
        iptList = investmentProjectDao.selectAccount(ipt);
        pjtList = projectDao.selectAll();
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (InvestmentProject investmentProject : iptList) {
            for (Project project : pjtList) {
                if (investmentProject.getInvestmentProjectId().equals(project.getProjectId())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("investmentId", investmentProject.getInvestmentId());
                    map.put("investmentProject", project.getProjectName());
                    map.put("investmentAmount", investmentProject.getInvestmentAmount());
                    map.put("investmentSerialNumber", investmentProject.getInvestmentSerialNumber());
                    map.put("investmentTime", UtilMethod.getTime(investmentProject.getInvestmentTime()));
                    map.put("biddingStatus", investmentProject.getBiddingStatus());
                    listMap.add(map);
                }
            }
        }
        return ResponseMessage.projectEntry1(listMap);
    }

    /**
     * @param @param  project
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: queryProject
     * @Description: TODO(项目录入)
     */
    @RequestMapping(value = "/proRelease", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> projectEntry(@RequestBody Project project) {
        log.info("接收到的参数为：" + JSONObject.fromObject(project));
        //验证token
        //检查登录状态
        User user = new User();
        user.setPhone(project.getPhone());
        user.setStrUUID(project.getToken());
        if (!tokenUUID(user)) {
            return ResponseMessage.verificationToken();
        }
        //生成项目ID
        project.setProjectId(String.valueOf(UUID.randomUUID()));
        //获取当前系统时间，进行录入时间计算
        project.setEntryTime(UtilMethod.unixString());
        //进行剩余额度同步赋值
        project.setRemainingAmount(project.getTotalProject());
        //进行项目有效时间的计算
        project.setValidityPeriod(project.getReleaseTime()
                + (project.getProjectDeadline() * ConstantParam.ROUND_THE_CLOCK_UNIX));
        projectDao.insertAll(project);
        return ResponseMessage.projectEntry();
    }


    /**
     * @param @param  investmentProject
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: claimTransfer
     * @Description: TODO(债权转让信息计算)
     */
    @RequestMapping(value = "/transferCalculation", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> projectRelease(@RequestBody Map<String, Object> pataMap) {
        log.info("接收到的参数为：" + JSONObject.fromObject(pataMap));
        InvestmentProject investmentProject = new InvestmentProject();
        investmentProject.setInvestmentId(String.valueOf(pataMap.get(ConstantParam.PARAM_INVESTMENT_ID)));
        //token信息
        investmentProject.setToken(String.valueOf(pataMap.get(ConstantParam.PARAM_TOKEN)));
        Map<String, Object> map = new HashMap<>();
        //ID返还
        map.put(ConstantParam.PARAM_INVESTMENT_ID, pataMap.get(ConstantParam.PARAM_INVESTMENT_ID));
        InvestmentProject inPro = new InvestmentProject();
        Project pro = new Project();
        //查询到投资信息
        inPro = investmentProjectDao.selectInvestmentId(investmentProject);
        //验证token
        //检查登录状态
        User user = new User();
        user.setPhone(inPro.getLinkedAccount());
        user.setStrUUID(investmentProject.getToken());
        if (!tokenUUID(user)) {
            return ResponseMessage.verificationToken();
        }
        //进行投资项目查询
        pro.setProjectId(inPro.getInvestmentProjectId());
        pro = projectDao.selectProjectId(pro);
        //获取系统时间,测试
        log.info("当前系统unix时间为：" + UtilMethod.unixString());
        if (pro.getReleaseTime() > UtilMethod.unixString()
                || (UtilMethod.unixString() - pro.getReleaseTime()) < ConstantParam.PARAM_MONTH_UNIX) {
            //投资计算利息开始时间小于当前时间，项目未开始正式回款，不能进行债券转让
            log.info("结算开始时间：" + pro.getReleaseTime());
            log.info("时间差：" + (UtilMethod.unixString() - pro.getReleaseTime()));
            return ResponseMessage.claim();
        }
        //满足转让条件，开始计算利息，手续费等
        //计算投资天数
        Double day = Double.valueOf((UtilMethod.unixString() - pro.getReleaseTime()) / ConstantParam.ROUND_THE_CLOCK_UNIX);
        log.info("当前系统unix时间为：" + UtilMethod.unixString());
        //利率
        Double interestRate1 = pro.getAnnualInterestRate();
        //额外
        Double interestRate2 = pro.getExtraInterestRate();
        //本金
        Double principal1 = inPro.getInvestmentAmount();
        //额外
        Double principal2 = inPro.getInvestmentAmount();
        //计算利息
        pro.getAnnualInterestRate();
        principal1 = (interestRate1 * principal1 * (day / ConstantParam.PARAM_TEAR)) + principal1;
        if (interestRate2 > ConstantParam.PARAM_ZERO) {
            principal2 = (interestRate2 * principal2 * (day / ConstantParam.PARAM_TEAR)) + principal2;
        }
        //对计算后的数据进行整理并返回给页面确认
        //本金
        map.put(ConstantParam.PARAM_PRINCIPAL, inPro.getInvestmentAmount());
        //利息
        Double interest = 0.0;
        if (interestRate2 > ConstantParam.PARAM_ZERO) {
            interest = (principal1 - inPro.getInvestmentAmount()) + (principal2 - inPro.getInvestmentAmount());
        } else {
            interest = principal1 - inPro.getInvestmentAmount();
        }
        map.put(ConstantParam.PARAM_INTEREST, UtilMethod.getTwoDecimal(interest));
        //天数
        map.put(ConstantParam.PARAM_DAY, day);
        //全部本所得
        map.put(ConstantParam.PARAM_INCOME, UtilMethod.getTwoDecimal(interest + inPro.getInvestmentAmount()));
        //计算转让费
        return ResponseMessage.projectEntry1(map);
    }

    /**
     * @param @param  investmentProject
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: claimTransfer
     * @Description: TODO(进行债券转让)
     */
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> claimTransfer(@RequestBody Map<String, String> paraMap) {
        log.info("接收到的参数为：" + paraMap.toString());
        InvestmentProject investmentProject = new InvestmentProject();
        BankCardRecharge bcr = new BankCardRecharge();
        InvestmentProject iPro = new InvestmentProject();
        Project pro = new Project();
        investmentProject.setInvestmentId(paraMap.get(ConstantParam.PARAM_INVESTMENT_ID));
        iPro = investmentProjectDao.selectInvestmentId(investmentProject);
        //验证token
        //检查登录状态
        User user = new User();
        user.setPhone(iPro.getLinkedAccount());
        user.setStrUUID(paraMap.get(ConstantParam.PARAM_TOKEN));
        if (!tokenUUID(user)) {
            return ResponseMessage.verificationToken();
        }
        //将投资ID作为银行卡号
        bcr.setBankCardNumber(iPro.getInvestmentProjectId());
        //关联账号
        bcr.setPhone(iPro.getLinkedAccount());
        //进行利息的同步余额充值
        bcr.setNumber(Double.valueOf(paraMap.get(ConstantParam.PARAM_INTEREST)));
        if (!recharge1(bcr)) {
            return ResponseMessage.projectEntry4();
        }
        //进行项目发布
        pro.setProjectId(iPro.getInvestmentProjectId());
        pro = projectDao.selectProjectId(pro);
        //投资ID作为项目ID
        pro.setProjectId(paraMap.get(ConstantParam.PARAM_INVESTMENT_ID));
        pro.setTotalProject(Double.valueOf(paraMap.get(ConstantParam.PARAM_PRINCIPAL)));
        pro.setProjectStatus(ConstantParam.PARAM_ONE);
        if (!projectEntry1(pro)) {
            return ResponseMessage.projectEntry5();
        }
        return ResponseMessage.projectEntry3();
    }


    /**
     * @param @param
     * @return @return
     * @throws
     * @Title: projectPayment
     * @Description: TODO(自动核查回款项目)
     * @author sunbo@pgytesting.cn
     * @date 2019/4/22 17:51
     */
    //@Scheduled(cron = "0 0 0 * * ?")//每天凌晨0点执行一次
    @Scheduled(cron = "0 */3 * * * ?")//每十分钟执行一次，为了测试方便
    public void projectPayment() {
        log.info("回款检查方法调用启动...");
        //查询状态为回款，剩余额度为0的项目进行回款条件过滤
        List<Project> list = new ArrayList<>();
        List<Project> listEnd = new ArrayList<>();
        list = projectDao.selectPaybackAll();
        Long currentTime = UtilMethod.unixString();
        if (list.size() == ConstantParam.PARAM_CODE_NOT_CERTIFID) {
            log.info("所有项目均未开始回款，等待下次调用检查...");
        }
        for (Project project : list) {
            log.info("判断数据是否满足回款条件：" + String.valueOf(project));
            if (currentTime >= (project.getReleaseTime() +
                    (project.getProjectDeadline() * ConstantParam.ROUND_THE_CLOCK_UNIX))) {
                log.info("数据满足回款条件，进行全局投资查询，回款计算开始...");
                InvestmentProject iPro = new InvestmentProject();
                List<InvestmentProject> iProList = new ArrayList<>();
                //查询到该项目的所有投资
                iPro.setInvestmentProjectId(project.getProjectId());
                iProList = investmentProjectDao.selectProjectId(iPro);
                for (InvestmentProject investmentProject : iProList) {
                    //进行每笔项目的自动回款
                    log.info("进行自动回款...");
                    BankCardRecharge BankCardRecharge = new BankCardRecharge();
                    //回款账号
                    BankCardRecharge.setPhone(investmentProject.getLinkedAccount());
                    //回款金额计算
                    BankCardRecharge.setNumber(investmentProject.getInvestmentAmount()
                            + (investmentProject.getInvestmentAmount() * project.getAnnualInterestRate() * (project.getProjectDeadline() / ConstantParam.PARAM_TEAR)));
                    //计算额外利率
                    if (project.getExtraInterestRate() > ConstantParam.PARAM_ZERO) {
                        BankCardRecharge.setNumber(investmentProject.getInvestmentAmount()
                                + (investmentProject.getInvestmentAmount() * project.getExtraInterestRate() * (project.getProjectDeadline() / ConstantParam.PARAM_TEAR)));
                    }
                    BankCardRecharge.setBankCardNumber(investmentProject.getInvestmentId());
                    //进行接口调用充值
                    recharge1(BankCardRecharge);
                    log.info("账号：" + investmentProject.getLinkedAccount() + "回款结束！");
                }
                //进行项目回款标注ProjectStatus = 4 则为已完成回款
                project.setProjectStatus(ConstantParam.PARAM_FOUR);
                listEnd.add(project);
            }
            projectDao.updateProjectStatus(project);
            if ("".equals(project.getProjectName())) {
                log.info("所有项目均为开始回款，等待下次调用检查...");
            } else {
                log.info("项目：" + project.getProjectName() + ",项目ID为：" + project.getProjectId() + "  回款结束！");
            }
        }
    }


    /**
     * @param @param  param
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: tokenUUID
     * @Description: TODO(判断token是否有效 ， 进行存库 ， 比对 ， 更新)
     */
    public boolean tokenUUID(User param) {//uuidMd5Encryption
        User user1 = new User();
        //验证手机号码是否为空
        if (param.getPhone() != null && ValidateUtils.isMobile(param.getPhone())) {
            User user = userDao.selectByUserUUID(param);
            //验证UUID是否一致
            if (param.getStrUUID().equals(user.getStrUUID())) {
                log.info("token验证一致，加密后的token为：" + param.getStrUUID());
                //验证token的时间
                //获取当前时间
                if (UtilMethod.unixString() - user.getCertificationUUIDTime() <= ConstantParam.PARAM_THIRTY_MINUTES) {
                    //更新数据库的UUID和时间
                    user1.setPhone(param.getPhone());
                    //UUID时间
                    user1.setCertificationUUIDTime(UtilMethod.unixString());
                    userDao.updateUserUUIDtime(user1);
                    return true;
                } else {

                    log.info("token已过期，请重新登录获取token，过期时间：" + ((UtilMethod.unixString() - user.getCertificationUUIDTime()) - ConstantParam.PARAM_THIRTY_MINUTES));
                    return false;
                }

            } else {
                //UUID不一致
                log.info("token验证不一致，存储的加密后的token为：" + user.getStrUUID());
                log.info("token验证不一致，传递的加密后的token为：" + param.getStrUUID());
                return false;
            }
        } else {
            //手机号为空
            log.info("======手机号码为空，token无法验证======");
            return false;
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: tokenUUID
     * @Description: TODO(登录生成UUID)
     */
    public boolean tokenUUIDLogin(User param) {
        //验证手机号码是否为空
        if (param.getPhone() != null && param.getPhone().length() != 0) {
            //获取UUID
            param.setStrUUID(UtilMethod.uuidMd5Encryption());
            //获取系统时间
            param.setCertificationUUIDTime(UtilMethod.unixString());
            log.info("token验证一致，加密后的token为：" + param.getStrUUID());
            //更新数据库的UUID和时间
            userDao.updateUserUUIDtime(param);
            return true;
        } else {
            //手机号为空
            log.info("======手机号码为空，token无法验证======");
            return false;
        }
    }

    /**
     * @param @param  param
     * @param @return 设定文件
     * @return Map<String, Object>    返回类型
     * @throws
     * @Title: recharge1
     * @Description: TODO(内部调用充值接口)
     */
    public boolean recharge1(BankCardRecharge param) {
        // 需要账号，且完成实名认证，需要填写银行卡号，验证支付密码
        BankCard bankCard = new BankCard();
        bankCard.setBankCardNumber(param.getBankCardNumber());
        User userParam = new User();
        // 执行充值操作，完成充值
        User user = new User();
        try {
            userParam = userDao.selectByUser(param.getPhone());
            user.setPhone(param.getPhone());
            user.setBalance(userParam.getBalance() + param.getNumber());
            // 进行平台自己的充值流水生成
            RechargeWater rw = new RechargeWater();
            // 手机号
            rw.setPhone(param.getPhone());
            // 充值金额
            rw.setAmountOfTheTransaction(
                    "+" + (String.valueOf(param.getNumber())));
            // 流水号
            String a = PropertyUtil.getPro(ConstantParam.PARAM_SEQUENCECONFIG_PROPERTIES, ConstantParam.PARAM_SEQUENCE);
            Integer ite = Integer.parseInt(a);
            a = String.valueOf(++ite);
            a.length();
            StringBuffer c = new StringBuffer();
            for (int i = 0; i < 9 - a.length(); i++) {
                c.append(ConstantParam.PARAM_ZERO);
            }
            if (PropertyUtil.updatePro(ConstantParam.PARAM_SEQUENCECONFIG_PROPERTIES, ConstantParam.PARAM_SEQUENCE, c + a)) {
                //流水号
                rw.setSerialNumber(ConstantParam.PARAM_ONE_MILLION + UtilMethod.getdingdan() + c + a);
                log.info("生成流水号：" + rw.getSerialNumber());
                //订单
            }
            // 充值状态
            rw.setTradingStatus(ConstantParam.PARAM_CODE_IN_CERTIFICATION);
            // 订单时间
            rw.setTransactionTime(UtilMethod.getTime());
            // 进行平台订单记录生成
            rechargeWaterDao.insertAll(rw);
            //进行日志库填充
            Projectreturnlog projectreturnlog = new Projectreturnlog();
            //获取日志库关联投资ID
            projectreturnlog.setBankCardNumber(param.getBankCardNumber());
            //获取金额
            projectreturnlog.setAmountOfTheTransaction("+" + param.getNumber());
            //获取投资ID
            projectreturnlog.setSerialNumber(param.getBankCardNumber());
            //获取当前系统时间
            projectreturnlog.setTransactionTime(UtilMethod.getTime());
            //生成唯一查询ID
            projectreturnlog.setOrderNumber(String.valueOf(UUID.randomUUID()));
            //获取充值状态
            projectreturnlog.setTradingStatus(ConstantParam.PARAM_CODE_IN_CERTIFICATION);
            //进行日志库填充
            projectreturnlogDao.insertAll(projectreturnlog);
            // 进行平台账户余额增加
            userDao.updateAmount(user);
            return true;
        } catch (Exception e) {
            log.error("=================平台自调用充值接口失败=================");
            return false;
        }
    }


    /**
     * @param @param  project
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: projectEntry1
     * @Description: TODO(内部项目转出调用)
     */
    public boolean projectEntry1(Project project) {
        try {
            //获取当前系统时间，进行录入时间计算
            project.setEntryTime(UtilMethod.unixString());
            //进行剩余额度同步赋值
            project.setRemainingAmount(project.getTotalProject());
            //进行项目有效时间的计算
            project.setValidityPeriod(project.getReleaseTime()
                    + (project.getProjectDeadline() * ConstantParam.ROUND_THE_CLOCK_UNIX));
            projectDao.insertAll(project);
            return true;
        } catch (Exception e) {
            log.error("===================项目转出失败===================");
            return false;
        }
    }
}
