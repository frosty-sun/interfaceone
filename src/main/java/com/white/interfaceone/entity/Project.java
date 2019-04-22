package com.white.interfaceone.entity;


public class Project extends Token {
	//项目名称
	private String projectName;
	//项目id
	private String projectId;
	//项目金额
	private Double totalProject;
	//剩余额度
	private Double remainingAmount;
	//项目期限
	private Long projectDeadline;
	//年利率
	private Double annualInterestRate;
	//额外利率
	private Double extraInterestRate;
	//有效期
	private Long validityPeriod;
	//发布时间
	private Long releaseTime;
	//录入时间
	private Long entryTime;
	//是否为一手项目
	private Long projectStatus;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Double getTotalProject() {
		return totalProject;
	}
	public void setTotalProject(Double totalProject) {
		this.totalProject = totalProject;
	}
	public Double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(Double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public Long getProjectDeadline() {
		return projectDeadline;
	}
	public void setProjectDeadline(Long projectDeadline) {
		this.projectDeadline = projectDeadline;
	}
	public Double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(Double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	public Double getExtraInterestRate() {
		return extraInterestRate;
	}
	public void setExtraInterestRate(Double extraInterestRate) {
		this.extraInterestRate = extraInterestRate;
	}
	public Long getValidityPeriod() {
		return validityPeriod;
	}
	public void setValidityPeriod(Long validityPeriod) {
		this.validityPeriod = validityPeriod;
	}
	public Long getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Long releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Long getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Long entryTime) {
		this.entryTime = entryTime;
	}
	public Long getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Long projectStatus) {
		this.projectStatus = projectStatus;
	}
	
	
	
	

}
