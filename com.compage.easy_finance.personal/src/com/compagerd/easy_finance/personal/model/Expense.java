package com.compagerd.easy_finance.personal.model;

import java.io.Serializable;

public class Expense extends Transaction implements Serializable {

	private static final long serialVersionUID = 1L;
	int idExpense;
	String accountingCategory;
	String categoryExpense;
	Double amountExpense;	
	String dateExpense;	
	String timeExpense;
	String noteExpense;
	String regularityExpense;
	
	public Expense(int idTransaction, String accountingCategory,
			String categoryTransaction, Double amountTransaction,
			String dateTransaction, String timeTransaction,
			String noteTransaction, String regularityTransaction) {
		super(idTransaction, accountingCategory, categoryTransaction,
				amountTransaction, dateTransaction, timeTransaction,
				noteTransaction, regularityTransaction);
	}

	
	
	
	
}
