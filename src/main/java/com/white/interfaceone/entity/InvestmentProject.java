package com.white.interfaceone.entity;

public class InvestmentProject extends Token {
	//投资ID
	private String investmentId;
	//投资金额
	private Double investmentAmount;
	//投资流水号
	private String investmentSerialNumber;
	//投资项目ID
	private String investmentProjectId;
	//关联账户
	private String linkedAccount;
	//投资时间
	private Long investmentTime;
	//投标状态
	private Integer biddingStatus;
	public String getInvestmentId() {
		return investmentId;
	}
	public void setInvestmentId(String investmentId) {
		this.investmentId = investmentId;
	}
	public Double getInvestmentAmount() {
		return investmentAmount;
	}
	public void setInvestmentAmount(Double investmentAmount) {
		this.investmentAmount = investmentAmount;
	}
	public String getInvestmentSerialNumber() {
		return investmentSerialNumber;
	}
	public void setInvestmentSerialNumber(String investmentSerialNumber) {
		this.investmentSerialNumber = investmentSerialNumber;
	}
	public String getInvestmentProjectId() {
		return investmentProjectId;
	}
	public void setInvestmentProjectId(String investmentProjectId) {
		this.investmentProjectId = investmentProjectId;
	}
	public String getLinkedAccount() {
		return linkedAccount;
	}
	public void setLinkedAccount(String linkedAccount) {
		this.linkedAccount = linkedAccount;
	}
	public Long getInvestmentTime() {
		return investmentTime;
	}
	public void setInvestmentTime(Long investmentTime) {
		this.investmentTime = investmentTime;
	}
	public Integer getBiddingStatus() {
		return biddingStatus;
	}
	public void setBiddingStatus(Integer biddingStatus) {
		this.biddingStatus = biddingStatus;
	}
	
	

}
