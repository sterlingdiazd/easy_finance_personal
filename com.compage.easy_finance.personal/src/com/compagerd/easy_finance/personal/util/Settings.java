package com.compagerd.easy_finance.personal.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.compagerd.easy_finance.personal.model.Account;

public class Settings {

	private static Settings settings = null;
	public static String ACCOUNT;
	public static String ACCOUNT_ID;
	public static String SELECTED_BUDGET;
	public static String PREFERENCES;
	public static String FIRST_ACCOUNT;
	public static String ACCOUNT_SELECTED;
	public static String DASHBOARD_EARNINGS;
	public static String DASHBOARD_EXPENSES;
	public static String DASHBOARD_BALANCE;
	public static String BUDGET_EARNINGS;
	public static String BUDGET_EXPENSES;
	public static String BUDGET_BALANCE;
	public static String BUDGET_REGULARITY;
	public static String BUDGET;
	public static String TRANSACTION;
	public static String IS_FOR_EDITION;
	public boolean firstAccount;
	private boolean isForEdition;
	private Account account;
	private SharedPreferences prefs;
	private SharedPreferences.Editor prefsEditor;

	private Settings() 
	{
		ACCOUNT = "ACCOUNT";
		ACCOUNT_ID = "ACCOUNT_ID";
		PREFERENCES = "PREFERENCES";
		FIRST_ACCOUNT = "FIRST_ACCOUNT";
		ACCOUNT_SELECTED = "ACCOUNT_SELECTED";
		SELECTED_BUDGET = "SELECTED_BUDGET";
		
		DASHBOARD_EARNINGS = "DASHBOARD_EARNINGS";
		DASHBOARD_EXPENSES = "DASHBOARD_EXPENSES";
		DASHBOARD_BALANCE = "DASHBOARD_BALANCE";
		
		BUDGET_EARNINGS = "BUDGET_EARNINGS";
		BUDGET_EXPENSES = "BUDGET_EXPENSES";
		BUDGET_BALANCE = "BUDGET_BALANCE";
		
		BUDGET_REGULARITY = "BUDGET_REGULARITY";
		
		BUDGET = "BUDGET";
		TRANSACTION = "TRANSACTION";
		firstAccount = true;
		isForEdition = false;
	}

	public static synchronized Settings getInstance() {
		if (settings == null) {
			settings = new Settings();
		}
		return settings;
	}
	
	public SharedPreferences getPrefs() {
		return prefs;
	}

	public void setPrefs(SharedPreferences prefs) {
		this.prefs = prefs;
	}

	public SharedPreferences.Editor getPrefsEditor() {
		return prefsEditor;
	}

	public void setPrefsEditor(SharedPreferences.Editor prefsEditor) {
		this.prefsEditor = prefsEditor;
	}

	public void configureSharePreference(Context context){
		this.setPrefs(context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE));
		this.setPrefsEditor(this.getPrefs().edit());
	}
	
	public boolean isFirstAccount() {
		return firstAccount;
	}

	public void setFirstAccount(boolean firstAccount) {
		this.firstAccount = firstAccount;
	}
	
	public boolean isForEdition() {
		return isForEdition;
	}

	public void setForEdition(boolean isForEdition) {
		this.isForEdition = isForEdition;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
