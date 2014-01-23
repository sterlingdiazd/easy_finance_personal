package com.compagerd.easy_finance.personal.model;

import java.io.Serializable;

import android.content.Context;

public class Account implements Serializable {

	private String accountName, aditionalInfo, accountType;
	private int idAccount;
	private Double initialValue;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int idAccount, String accountName, Double initialValue, 
			String accountType, String aditionalInfo ) {
		super();
		this.accountName = accountName;
		this.aditionalInfo = aditionalInfo;
		this.accountType = accountType;
		this.idAccount = idAccount;
		this.initialValue = initialValue;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAditionalInfo() {
		return aditionalInfo;
	}

	public void setAditionalInfo(String aditionalInfo) {
		this.aditionalInfo = aditionalInfo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public Double getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(Double initialValue) {
		this.initialValue = initialValue;
	}
	
	
	

	
}
