package com.white.interfaceone.entity;

/**
 * 
* @ClassName: User 
* @Description: TODO(用户信息实体类) 
* @author A18ccms a18ccms_gmail_com 
* @date 2018年7月12日 上午10:25:25 
*
 */
public class User extends TokenOne {
	// 密碼
	private String passWd;
	//用户姓名
	private String userName;
	//身份证号
	private String idCard;
	//余额
	private Double balance;
	//手机号
	private String phone;
	//支付密码
	private String paymentPassword;
	//重复密码
	private String passWord;
	//短信验证码
	private String smsVerificationCode;
	//token
	private String strUUID;
	//获取时间
	private Long currentUnix;
	//绑定状态
	private Integer certificationCode;
	//绑定次数
	private Integer certificationNumber;
	//绑定操作时间
	private Long certificationTime;
	//UUID操作时间
	private Long certificationUUIDTime;
	
	
	
	public String getPaymentPassword() {
		return paymentPassword;
	}
	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}
	public Long getCertificationUUIDTime() {
		return certificationUUIDTime;
	}
	public void setCertificationUUIDTime(Long certificationUUIDTime) {
		this.certificationUUIDTime = certificationUUIDTime;
	}
	public Long getCertificationTime() {
		return certificationTime;
	}
	public void setCertificationTime(Long certificationTime) {
		this.certificationTime = certificationTime;
	}
	public Integer getCertificationCode() {
		return certificationCode;
	}
	public void setCertificationCode(Integer certificationCode) {
		this.certificationCode = certificationCode;
	}
	public Integer getCertificationNumber() {
		return certificationNumber;
	}
	public void setCertificationNumber(Integer certificationNumber) {
		this.certificationNumber = certificationNumber;
	}
	public String getStrUUID() {
		return strUUID;
	}
	public void setStrUUID(String strUUID) {
		this.strUUID = strUUID;
	}
	public Long getCurrentUnix() {
		return currentUnix;
	}
	public void setCurrentUnix(Long currentUnix) {
		this.currentUnix = currentUnix;
	}
	public String getSmsVerificationCode() {
		return smsVerificationCode;
	}
	public void setSmsVerificationCode(String smsVerificationCode) {
		this.smsVerificationCode = smsVerificationCode;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	

}
