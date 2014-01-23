package com.compagerd.easy_finance.personal.model;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MovementsBudget extends Movement {

	public MovementsBudget(String accountantCategory, ProgressBar progressBar, TextView textViewAmount, SQLiteOpenHelper dataHandler) {
		super(accountantCategory, progressBar, textViewAmount, dataHandler);
	}

}
