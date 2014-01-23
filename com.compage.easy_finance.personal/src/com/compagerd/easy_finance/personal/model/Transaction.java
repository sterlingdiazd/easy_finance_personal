package com.compagerd.easy_finance.personal.model;

import java.io.Serializable;

public abstract class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int idTransaction;
	String accountingCategory;
	String categoryTransaction;
	Double amountTransaction;	
	String dateTransaction;	
	String timeTransaction;
	String noteTransaction;
	String regularityTransaction;

	public Transaction(int idTransaction, String accountingCategory,
			String categoryTransaction, Double amountTransaction,
			String dateTransaction, String timeTransaction,
			String noteTransaction, String regularityTransaction) {
		super();
		this.idTransaction = idTransaction;
		this.accountingCategory = accountingCategory;
		this.categoryTransaction = categoryTransaction;
		this.amountTransaction = amountTransaction;
		this.dateTransaction = dateTransaction;
		this.timeTransaction = timeTransaction;
		this.noteTransaction = noteTransaction;
		this.regularityTransaction = regularityTransaction;
	}

	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public String getAccountingCategory() {
		return accountingCategory;
	}

	public void setAccountingCategory(String accountingCategory) {
		this.accountingCategory = accountingCategory;
	}

	public String getCategoryTransaction() {
		return categoryTransaction;
	}

	public void setCategoryTransaction(String categoryTransaction) {
		this.categoryTransaction = categoryTransaction;
	}

	public Double getAmountTransaction() {
		return amountTransaction;
	}

	public void setAmountTransaction(Double amountTransaction) {
		this.amountTransaction = amountTransaction;
	}

	public String getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(String dateTransaction) {
		this.dateTransaction = dateTransaction;
	}

	public String getTimeTransaction() {
		return timeTransaction;
	}

	public void setTimeTransaction(String timeTransaction) {
		this.timeTransaction = timeTransaction;
	}

	public String getNoteTransaction() {
		return noteTransaction;
	}

	public void setNoteTransaction(String noteTransaction) {
		this.noteTransaction = noteTransaction;
	}

	public String getRegularityTransaction() {
		return regularityTransaction;
	}

	public void setRegularityTransaction(String regularityTransaction) {
		this.regularityTransaction = regularityTransaction;
	}

	
}
