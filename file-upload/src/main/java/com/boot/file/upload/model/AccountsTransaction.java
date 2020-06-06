package com.boot.file.upload.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "accounts_transaction")
public class AccountsTransaction {

	@Id
	private String id;
	@Field(name = "trans_id")
	private String transId;
	@Field(name = "account_id")
	private String accountId;
	private String type;
	private String operation;
	private Double amount;
	@Field(name="k_symbol")
	private String kSymbol;
	private String bank;
	private String account;
	private Integer year;
	private Integer month;
	private Integer day;
	@Field(name = "fulldate")
	private String fullDate;
	@Field(name = "fulltime")
	private String fullTime;
	@Field(name = "fulldatewithtime")
	private String fullDateWithTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getkSymbol() {
		return kSymbol;
	}
	public void setkSymbol(String kSymbol) {
		this.kSymbol = kSymbol;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getFullDate() {
		return fullDate;
	}
	public void setFullDate(String fullDate) {
		this.fullDate = fullDate;
	}
	public String getFullTime() {
		return fullTime;
	}
	public void setFullTime(String fullTime) {
		this.fullTime = fullTime;
	}
	public String getFullDateWithTime() {
		return fullDateWithTime;
	}
	public void setFullDateWithTime(String fullDateWithTime) {
		this.fullDateWithTime = fullDateWithTime;
	}
	@Override
	public String toString() {
		return "AccountsTransaction [id=" + id + ", transId=" + transId + ", accountId=" + accountId + ", type=" + type
				+ ", operation=" + operation + ", amount=" + amount + ", kSymbol=" + kSymbol + ", bank=" + bank
				+ ", account=" + account + ", year=" + year + ", month=" + month + ", day=" + day + ", fullDate="
				+ fullDate + ", fullTime=" + fullTime + ", fullDateWithTime=" + fullDateWithTime + "]";
	}
	
	

}
