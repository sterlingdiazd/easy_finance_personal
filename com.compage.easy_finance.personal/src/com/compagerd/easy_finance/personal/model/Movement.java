package com.compagerd.easy_finance.personal.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class  Movement {

	String accountantCategory;
	ProgressBar ProgressBar;
	TextView TextViewAmount;
	SQLiteOpenHelper dataHandler;
	
	public Movement(String accountantCategory, ProgressBar progressBar, TextView textViewAmount, SQLiteOpenHelper dataHandler) {
		super();
		this.accountantCategory = accountantCategory;
		ProgressBar = progressBar;
		TextViewAmount = textViewAmount;
		this.dataHandler = dataHandler;
	}

	public String getAccountantCategory() {
		return accountantCategory;
	}

	public void setAccountantCategory(String accountantCategory) {
		this.accountantCategory = accountantCategory;
	}

	public ProgressBar getProgressBar() {
		return ProgressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		ProgressBar = progressBar;
	}

	public TextView getTextViewAmount() {
		return TextViewAmount;
	}

	public void setTextViewAmount(TextView textViewAmount) {
		TextViewAmount = textViewAmount;
	}

	public SQLiteOpenHelper getDataHandler() {
		return dataHandler;
	}

	public void setDataHandler(SQLiteOpenHelper dataHandler) {
		this.dataHandler = dataHandler;
	}

	
	
}
