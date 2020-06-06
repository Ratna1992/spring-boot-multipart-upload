package com.boot.file.upload.to;

import com.opencsv.bean.CsvBindByName;

public class AccountTransactionCSVTo {

	@CsvBindByName
	private String id;
	@CsvBindByName(column="trans_id")
	private String transId;
	@CsvBindByName(column="account_id")
	private String accountId;
	@CsvBindByName
	private String type;
	@CsvBindByName
	private String operation;
	@CsvBindByName
	private Double amount;
	@CsvBindByName(column="k_symbol")
	private String kSymbol;
	@CsvBindByName
	private String bank;
	@CsvBindByName
	private String account;
	@CsvBindByName
	private Integer year;
	@CsvBindByName
	private Integer month;
	@CsvBindByName
	private Integer day;
	@CsvBindByName(column="fulldate")
	private String fullDate;
	@CsvBindByName(column="fulltime")
	private String fullTime;
	@CsvBindByName(column="fulldatewithtime")
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

}
