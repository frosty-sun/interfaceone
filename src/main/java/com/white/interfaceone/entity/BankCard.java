package com.white.interfaceone.entity;

/**
 * 
* @ClassName: BankCard 
* @Description: TODO(银行卡信息的实体类) 
* @author A18ccms a18ccms_gmail_com 
* @date 2018年7月12日 上午10:25:50 
*
 */
public class BankCard extends TokenOne {
	/**
	 * 账号
	 */
	private String phone;
	
	/**
	 * 身份证号
	 */
	private String idCard;
	
	/**
	 * 持卡人姓名
	 */
	private String cardholderName;
	
	/**
	 * 银行名称
	 */
	private String bankName;
	
	/**
	 * 银行卡号
	 */
	private String bankCardNumber;
	
	

	
	
	public String getPhone() {
		return phone;
	}





	public String getIdCard() {
		return idCard;
	}





	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}





	public String getCardholderName() {
		return cardholderName;
	}





	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}





	public String getBankName() {
		return bankName;
	}





	public void setBankName(String bankName) {
		this.bankName = bankName;
	}





	public String getBankCardNumber() {
		return bankCardNumber;
	}





	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}





	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
