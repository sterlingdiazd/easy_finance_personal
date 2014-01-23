package com.compagerd.easy_finance.personal.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MovementsTransaction extends Movement {

	public MovementsTransaction(String accountantCategory, ProgressBar progressBarBalance, TextView textViewAmountBalance, SQLiteOpenHelper dataHandler) {
		super(accountantCategory, progressBarBalance, textViewAmountBalance, dataHandler);
	}

}
