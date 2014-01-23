package com.compagerd.easy_finance.personal.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.compagerd.easy_finance.personal.model.Expense;
import com.compagerd.easy_finance.personal.model.Transaction;

public class DataTransaction extends SQLiteOpenHelper {

	public static final String TABLE_NAME_TRANSACTION = "Transactions";
	public static final String COLUMN_NAME_TRANSACTION_ID = "idTransaction";
	public static final String COLUMN_NAME_ACCOUNTING_CATEGORY = "accountingCategory";
	public static final String COLUMN_NAME_CATEGORY_NAME = "categoryTransaction";
	public static final String COLUMN_NAME_AMOUNT = "amountTransaction";
	public static final String COLUMN_NAME_DATE = "dateTransaction";
	public static final String COLUMN_NAME_TIME = "timeTransaction";
	public static final String COLUMN_NAME_NOTE = "noteTransaction";
	public static final String COLUMN_NAME_REGULARITY = "regularityTransaction";
	private static final  int DATABASE_VERSION = 1;
	
	private String createTable_transactions = "create table " + 
	TABLE_NAME_TRANSACTION+" ( " + COLUMN_NAME_TRANSACTION_ID + " integer primary key autoincrement , "+
	COLUMN_NAME_ACCOUNTING_CATEGORY+" TEXT , " + 
	COLUMN_NAME_CATEGORY_NAME+" TEXT , " + 
	COLUMN_NAME_AMOUNT + " REAL , " + 
	COLUMN_NAME_DATE + " TEXT, " + 
	COLUMN_NAME_TIME + " TEXT, " + 
	COLUMN_NAME_NOTE + " TEXT, " + 
	COLUMN_NAME_REGULARITY + " TEXT )";
	
	public DataTransaction(Context context) {
		super(context, TABLE_NAME_TRANSACTION, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable_transactions);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRANSACTION);
		db.execSQL(createTable_transactions);
	}

	public void addTransaction(Transaction transaction)
	{	
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_NAME_ACCOUNTING_CATEGORY, transaction.getAccountingCategory());
		contentValues.put(COLUMN_NAME_CATEGORY_NAME, transaction.getCategoryTransaction());
		contentValues.put(COLUMN_NAME_AMOUNT, transaction.getAmountTransaction());
		contentValues.put(COLUMN_NAME_DATE, String.valueOf(transaction.getDateTransaction()) );
		contentValues.put(COLUMN_NAME_TIME, String.valueOf(transaction.getTimeTransaction()) );
		contentValues.put(COLUMN_NAME_NOTE, transaction.getNoteTransaction());
		contentValues.put(COLUMN_NAME_REGULARITY, transaction.getRegularityTransaction());
		db.insert(TABLE_NAME_TRANSACTION, null, contentValues);		
		db.close();
	}
	
	public int updateTransaction(Transaction transaction)
	{
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_NAME_ACCOUNTING_CATEGORY, transaction.getAccountingCategory());
		contentValues.put(COLUMN_NAME_CATEGORY_NAME, transaction.getCategoryTransaction());
		contentValues.put(COLUMN_NAME_AMOUNT, transaction.getAmountTransaction());
		contentValues.put(COLUMN_NAME_DATE, String.valueOf(transaction.getDateTransaction()) );
		contentValues.put(COLUMN_NAME_TIME, String.valueOf(transaction.getTimeTransaction()) );
		contentValues.put(COLUMN_NAME_NOTE, transaction.getNoteTransaction());
		contentValues.put(COLUMN_NAME_REGULARITY, transaction.getRegularityTransaction());
		int ret = db.update(TABLE_NAME_TRANSACTION, contentValues, COLUMN_NAME_TRANSACTION_ID + " = " + transaction.getIdTransaction(), null);		
		db.close();
		return ret;
	}

	public List<Transaction> getAllTransactions(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_TRANSACTION, new String[]{COLUMN_NAME_TRANSACTION_ID, COLUMN_NAME_ACCOUNTING_CATEGORY, COLUMN_NAME_CATEGORY_NAME, 
				COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME, COLUMN_NAME_NOTE, COLUMN_NAME_REGULARITY}, null, null, null, null, COLUMN_NAME_TRANSACTION_ID);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idTransaction = cursor.getInt(0);
						String accountingCategory = cursor.getString(1);
						String categoryTransaction = cursor.getString(2);
						double amountTransaction = cursor.getDouble(3);						
						String dateTransaction = cursor.getString(4);
						String timeTransaction = cursor.getString(5);
						String noteTransaction = cursor.getString(6);
						String regularityTransaction = cursor.getString(7);
						transactions.add( (Transaction) new Expense(idTransaction, accountingCategory, categoryTransaction, amountTransaction, dateTransaction, timeTransaction, 
								noteTransaction, regularityTransaction));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 
		}
		cursor.close();
		db.close();
		return transactions;
	}
	
	public List<Transaction> getAllDates(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_TRANSACTION, new String[]{COLUMN_NAME_TRANSACTION_ID, COLUMN_NAME_ACCOUNTING_CATEGORY, COLUMN_NAME_CATEGORY_NAME, 
				COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME, COLUMN_NAME_NOTE, COLUMN_NAME_REGULARITY}, null, null, COLUMN_NAME_DATE, null, COLUMN_NAME_TRANSACTION_ID, null);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idTransaction = cursor.getInt(0);
						String accountingCategory = cursor.getString(1);
						String categoryTransaction = cursor.getString(2);
						double amountTransaction = cursor.getDouble(3);						
						String dateTransaction = cursor.getString(4);
						String timeTransaction = cursor.getString(5);
						String noteTransaction = cursor.getString(6);
						String regularityTransaction = cursor.getString(7);
						transactions.add( (Transaction) new Expense(idTransaction, accountingCategory, categoryTransaction, amountTransaction, dateTransaction, timeTransaction, 
								noteTransaction, regularityTransaction));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 
		}
		cursor.close();
		db.close();
		return transactions;
	}
	
	public List<Transaction> getTransactionsByDate(String date){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_TRANSACTION, new String[]{COLUMN_NAME_TRANSACTION_ID, COLUMN_NAME_ACCOUNTING_CATEGORY, COLUMN_NAME_CATEGORY_NAME, COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME, 
				COLUMN_NAME_NOTE, COLUMN_NAME_REGULARITY}, COLUMN_NAME_DATE + "= ?", new String[]{date}, null, null, COLUMN_NAME_TRANSACTION_ID);
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idTransaction = cursor.getInt(0);
						String accountingCategory = cursor.getString(1);
						String categoryTransaction = cursor.getString(2);
						double amountTransaction = cursor.getDouble(3);						
						String dateTransaction = cursor.getString(4);
						String timeTransaction = cursor.getString(5);
						String noteTransaction = cursor.getString(6);
						String regularityTransaction = cursor.getString(7);
						transactions.add( (Transaction) new Expense(idTransaction, accountingCategory, categoryTransaction, amountTransaction, dateTransaction, timeTransaction, 
								noteTransaction, regularityTransaction));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 
		}
		cursor.close();
		db.close();
		return transactions;
	}
	
	public int deleteTransaction(int idTransaction){
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		int ret = db.delete(TABLE_NAME_TRANSACTION, COLUMN_NAME_TRANSACTION_ID + " = " + idTransaction,  null );
		db.close();
		return ret;
	}

}
