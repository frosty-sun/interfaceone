package com.white.interfaceone.entity;

public class BankCardRecharge extends BankCard {
	/**
	 * ½ð¶î
	 */
	private double number;
	/**
	 * Ö§¸¶ÃÜÂë
	 */
	private String paymentPassword;
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getPaymentPassword() {
		return paymentPassword;
	}
	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

}
