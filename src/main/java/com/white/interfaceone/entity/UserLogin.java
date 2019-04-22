package com.white.interfaceone.entity;

public class UserLogin {
	
	//短信验证码
	private String smsVerificationCode;
	//当前系统的时间戳
	private Long currentUnix;
	//验证码次数
	private int number;
	//手机号
	private String phone;
	
	
	
	

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	
	
	
	
	
	
	

}
