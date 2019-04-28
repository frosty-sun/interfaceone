package com.white.interfaceone.util;

public interface ConstantParam {
	//param：身份证
	public  static final String ID_CARD = "idCard";
	//param:银行卡号
	public  static final String BANK_CAR_NUMBER = "bankCardNumber";
	//param：账号
	public  static final String LOG_NAME = "name";
	//param:密码
	public  static final String LOG_PASSWD = "passWd";
	//param：姓名
	public  static final String USER_NAME = "userName";
	//param：余额
	public  static final String BALANCE = "balance";
	//param：电话
	public  static final String PHONE = "phone";
	//参数 成功
	public static final String MESSAGE_SESSION = "success";
	//状态
	public static final String STATUS_CODE = "statusCode";
	//成功
	public static final String MESSAGE_SESSION_CODE = "2000";
	//状态码错误
	public static final String MESSAGE_SESSION_ERROR_CODE = "3000";
	//状态码异常
	public static final String MESSAGE_PHONE_ERROR_CODE = "5000";
	//参数消息
	public static final String RESPONSE_MASSAGE = "message";
	//参数 手机号
	public static final String SMS_VERIFICATION_PHONE = "phone";
	//短信验证码
	public static final String SMS_VERIFICATION_CODE = "smsVerificationCode";
	//1000毫秒
	public static final long ONE_THOUSAND_UNIX = 1000;
	//参数 60秒
	public static final long ONE_MINUTE = 60;
	//5分钟
	public static final long FIVE_MINUTES_UNIX = 300;
	//24小时
	public static final long ROUND_THE_CLOCK_UNIX = 86400;
	//时间戳
	public static final String PARAM_UNIX = "currentUnix";
	//参数：0
	public static final int PARAM_ZERO = 0;
	//参数 验证码获取次数
	public static final String PARAM_NUMBER = "number";
	//参数 data
	public static final String PARAM_DATA = "data";
	//等待时间
	public static final String PARAM_WATING_TIME = "waitingTime";
	//token
	public static final String PARAM_TOKEN = "token";
	//未认证
	public static final int PARAM_CODE_NOT_CERTIFID = 0;
	//认证中,认证次数
	public static final int PARAM_CODE_IN_CERTIFICATION = 1;
	//认证成功，认证次数
	public static final int PARAM_SUCCESSFUL_CERTIFICATION = 2;
	//认证失败，认证次数
	public static final int PARAM_AUTHENTIATION_FAILD = 3;
	//参数 认证状态
	public static final String PARAM_CERTIFICATION_STATUS = "certificationCode";
	// 参数 英文
	public static final String PARAM_CHINESE = "EN";
	// 参数 中文
	public static final String PARAM_ENGLISH  = "ZH";
	// 参数 银行卡归属
	public static final String PARAM_BANK_NAME  = "banknName";
	//参数 银行卡号
	public static final String PARAM_BANK_CARD  = "banknCard";
	// 参数旧支付密码
	public static final String PARAM_ORIGUNAL_PAY_PASSWORD  = "originalPaymentPassword";
	// 参数 新支付密码
	public static final String PARAM_NEW_PAY_PASSWORD  = "newPaymentPassword";
	//参数 支付密码
	public static final String PARAM_PAYMENT_PASSWORD = "paymentPassword";
	//充值金额
	public static final String PARAM_AMOUNT_TRANSACTION = "amountOfTheTransaction";
	//流水号
	public static final String PARAM_SERIAL_NUMBER = "serialNumber";
	//充值状态
	public static final String PARAM_TRADING_STATUS = "tradingStatus";
	//交易完成时间
	public static final String PARAM_TRANSACTION_TIME = "transactionTime";
	//金额上限
	public static final Double PARAM_UPPER_LIMIT = 50000.00;
	//金额下限
	public static final Double PARAM_LOWER_LIMIT = 0.01;
	//下限
	public static final Double PARAM_WITHDRAW_LOWER_LIMIT = 0.01;
	//月粒度时间戳
	public static final Long  PARAM_MONTH_UNIX = 2592000L;
	//投资ID
	public static final String PARAM_INVESTMENT_ID = "investmentId";
	//年
	public static final Double PARAM_TEAR = 365.0;
	// 参数Long 1
	public static final Long PARAM_ONE = 1L;
	// 参数 状态
	public static final Long PARAM_FOUR = 4L;
	// 参数 旧密码
	public static final String PARAM_ORIGINAL_PASSWORD = "originalPasswd";
	//用户名
	public static final String PARAM_USER = "user";
	//新密码
	public static final String PARAM_NEW_PASSWORD = "newPasswd";
	//重复密码
	public static final String PARAM_REPEAT_PASSWORD = "repeatPasswd";
	// 参数backCard
	public static final String PARAM_BACK_CARD = "backCard";
	// 参数amount
	public static final String PARAM_AMOUNT = "amount";
	// 参数rechargeOrWithdraw
	public static final String PARAM_RECHRGE_ORWITHDRAW = "rechargeOrWithdraw";
	// 参数sequenceConfig.properties
	public static final String PARAM_SEQUENCECONFIG_PROPERTIES = "sequenceConfig.properties";
	// 参数sequence
	public static final String PARAM_SEQUENCE = "sequence";
	// 参数projectName
	public static final String PARAM_PROJECT_NAME = "projectName";
	// 参数projectId
	public static final String PARAM_PROJECT_ID = "projectId";
	// 参数totalProject
	public static final String PARAM_TOTAL_PROJECT = "totalProject";
	// 参数remainingAmount
	public static final String PARAM_REMAININ_AMOUNT = "remainingAmount";
	// 参数projectDeadline
	public static final String PARAM_PROJECT_DEADLINE = "projectDeadline";
	// 参数annualInterestRate
	public static final String PARAM_ANNUAL_INTEREST_RATE = "annualInterestRate";
	// 参数extraInterestRate
	public static final String PARAM_EXTRA_INTEREST_RATE = "extraInterestRate";
	// 参数validityPeriod
	public static final String PARAM_VALIDITY_PERIOD = "validityPeriod";
	// 参数releaseTime
	public static final String PARAM_RRELEASE_TIME = "releaseTime";
	// 参数entryTime
	public static final String PARAM_ENTRY_TIME = "entryTime";
	// 参数sequence1
	public static final String PARAM_SEQUENCEL = "sequence1";
	// 参数1000000
	public static final String PARAM_ONE_MILLION = "1000000";
	// 参数principal
	public static final String PARAM_PRINCIPAL = "principal";
	// 参数interest
	public static final String PARAM_INTEREST = "interest";
	// 参数day
	public static final String PARAM_DAY = "day";
	// 参数income
	public static final String PARAM_INCOME = "income";
	//参数 半小时
	public static final Long PARAM_THIRTY_MINUTES = 1800L;
	
}
