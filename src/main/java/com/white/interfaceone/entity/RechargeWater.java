package com.white.interfaceone.entity;

public class RechargeWater {
	
	//手机号
	private String phone;
	//流水号
	private String serialNumber;
	//交易时间
	private String transactionTime;
	//交易金额
	private String amountOfTheTransaction;
	//交易状态
	private Integer tradingStatus;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getAmountOfTheTransaction() {
		return amountOfTheTransaction;
	}
	public void setAmountOfTheTransaction(String amountOfTheTransaction) {
		this.amountOfTheTransaction = amountOfTheTransaction;
	}
	public Integer getTradingStatus() {
		return tradingStatus;
	}
	public void setTradingStatus(Integer tradingStatus) {
		this.tradingStatus = tradingStatus;
	}
	

}
