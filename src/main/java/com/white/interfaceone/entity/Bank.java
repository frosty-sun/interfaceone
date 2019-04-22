package com.white.interfaceone.entity;


public class Bank {
	//流水号
	private String serialNumber;
	//交易时间
	private String transactionTime;
	//银行卡号
	private String bankCardNumber;
	//交易金额
	private String amountOfTheTransaction;
	//订单号
	private String orderNumber;
	//交易状态  0交易失败 1交易成功
	private Integer tradingStatus;
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
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	public String getAmountOfTheTransaction() {
		return amountOfTheTransaction;
	}
	public void setAmountOfTheTransaction(String amountOfTheTransaction) {
		this.amountOfTheTransaction = amountOfTheTransaction;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getTradingStatus() {
		return tradingStatus;
	}
	public void setTradingStatus(Integer tradingStatus) {
		this.tradingStatus = tradingStatus;
	}
	
	

	
}
