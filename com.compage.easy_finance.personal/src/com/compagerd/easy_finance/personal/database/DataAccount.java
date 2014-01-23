package com.compagerd.easy_finance.personal.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.compagerd.easy_finance.personal.model.Account;

public class DataAccount extends SQLiteOpenHelper {

	public static final String TABLE_NAME_ACCOUNT = "Account";
	public static final String COLUMN_NAME_ACCOUNT_ID = "idAccount";
	public static final String COLUMN_NAME_ACCOUNT_NAME = "accountName";
	public static final String COLUMN_NAME_INITIAL_VALUE = "initialValue";
	public static final String COLUMN_NAME_ACCOUNT_TYPE = "accountType";
	public static final String COLUMN_NAME_ADITIONAL_INFO = "aditionalInfo";
	private static final  int DATABASE_VERSION = 1;
	
	private String createTable_account = "create table "+TABLE_NAME_ACCOUNT+" ( " + 
	COLUMN_NAME_ACCOUNT_ID + " integer primary key autoincrement , "+
	COLUMN_NAME_ACCOUNT_NAME+" String , " + 
	COLUMN_NAME_INITIAL_VALUE + " integer , " + 
	COLUMN_NAME_ACCOUNT_TYPE + " String, " + 
	COLUMN_NAME_ADITIONAL_INFO + " String )";
	
	public DataAccount(Context context) {
		super(context, TABLE_NAME_ACCOUNT, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable_account);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNT);
		db.execSQL(createTable_account);
	}

	public boolean checkBudgetExistence(int id)
	{
		Log.e("id", id+"");
		boolean isInTheList = false;
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		
		Cursor cursor = db.query(TABLE_NAME_ACCOUNT, 
				new String[]{
				COLUMN_NAME_ACCOUNT_ID, 
				COLUMN_NAME_ACCOUNT_NAME, 
				COLUMN_NAME_INITIAL_VALUE, 
				COLUMN_NAME_ACCOUNT_TYPE, 
				COLUMN_NAME_ADITIONAL_INFO}, 
				COLUMN_NAME_ACCOUNT_ID + "= ?", 
				new String[]{ String.valueOf(id) }, 
				null, null, COLUMN_NAME_ACCOUNT_ID);


		if(cursor != null && cursor.getCount() != 0)
		{	
			cursor.moveToFirst();	
			try
			{
				
				if(id == cursor.getInt(0))
				{
					isInTheList = true;
				} 
				else 
				{
					isInTheList = false;
				}
			}
			catch(CursorIndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
		} 

		cursor.close();
		db.close();

		return isInTheList;	
	}
	public void registerAccount(Account account)
	{	
		Log.e("id", account.getIdAccount()+"");
		Log.e("getAccountName", account.getAccountName()+"");
		Log.e("getAccountType", account.getAccountType()+"");
		Log.e("getInitialValue", account.getInitialValue()+"");
		Log.e("getAditionalInfo", account.getAditionalInfo()+"");
		
		boolean isInTheTable = checkBudgetExistence(account.getIdAccount());
		if(!isInTheTable)
		{
			SQLiteDatabase db = null;	
			db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			Log.e("e", account.getAccountName() + " " +  account.getInitialValue()   + " " +   account.getAccountType()    + " " +     account.getAditionalInfo()  );
			contentValues.put(COLUMN_NAME_ACCOUNT_NAME, account.getAccountName());
			contentValues.put(COLUMN_NAME_INITIAL_VALUE, account.getInitialValue());
			contentValues.put(COLUMN_NAME_ACCOUNT_TYPE, account.getAccountType());
			contentValues.put(COLUMN_NAME_ADITIONAL_INFO, account.getAditionalInfo());
			db.insert(TABLE_NAME_ACCOUNT, null, contentValues);		
			db.close();
		}
		else 
		{
			SQLiteDatabase dbUpdate = null;	
			dbUpdate = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			//Log.e("e", account.getAccountName() + " " +  account.getInitialValue()   + " " +   account.getAccountType()    + " " +     account.getAditionalInfo()  );
			contentValues.put(COLUMN_NAME_ACCOUNT_NAME, account.getAccountName());
			contentValues.put(COLUMN_NAME_INITIAL_VALUE, account.getInitialValue());
			contentValues.put(COLUMN_NAME_ACCOUNT_TYPE, account.getAccountType());
			contentValues.put(COLUMN_NAME_ADITIONAL_INFO, account.getAditionalInfo());
			dbUpdate.update(TABLE_NAME_ACCOUNT, contentValues, COLUMN_NAME_ACCOUNT_ID + " = " + account.getIdAccount(), null);		
			dbUpdate.close();
		}
		
	}
		/*
	public int updateAccount(Account account)
	{
		
	}
	*/
	public List<Account> getAllAccounts(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_ACCOUNT, 
				new String[]{
				COLUMN_NAME_ACCOUNT_ID, 
				COLUMN_NAME_ACCOUNT_NAME, 
				COLUMN_NAME_INITIAL_VALUE, 
				COLUMN_NAME_ACCOUNT_TYPE, 
				COLUMN_NAME_ADITIONAL_INFO}, 
				null, null, null, null, COLUMN_NAME_ACCOUNT_ID);
		
		List<Account> accounts = new ArrayList<Account>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int id = cursor.getInt(0);
						String accountName = cursor.getString(1);
						double initialValue = cursor.getDouble(2);
						String accountType = cursor.getString(3);
						String aditionalInfo = cursor.getString(4);
						accounts.add(new Account(id, accountName, initialValue, accountType, aditionalInfo));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 
		}
		cursor.close();
		db.close();
		return accounts;
	}
	
	public int deleteAccount(int id){
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		int ret = db.delete(TABLE_NAME_ACCOUNT, COLUMN_NAME_ACCOUNT_ID + " = " + id,  null );
		db.close();
		return ret;
	}
	
}
