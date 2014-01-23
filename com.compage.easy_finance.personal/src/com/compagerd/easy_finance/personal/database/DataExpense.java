package com.compagerd.easy_finance.personal.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.compagerd.easy_finance.personal.model.Expense;

public class DataExpense extends SQLiteOpenHelper {

	public static final String TABLE_NAME_EXPENSE = "Expense";
	public static final String COLUMN_NAME_EXPENSE_ID = "idExpense";
	public static final String COLUMN_NAME_ACCOUNTING_CATEGORY = "accountingCategory";
	public static final String COLUMN_NAME_CATEGORY_NAME = "categoryExpense";
	public static final String COLUMN_NAME_AMOUNT = "amountExpense";
	public static final String COLUMN_NAME_DATE = "dateExpense";
	public static final String COLUMN_NAME_TIME = "timeExpense";
	public static final String COLUMN_NAME_NOTE = "noteExpense";
	public static final String COLUMN_NAME_REGULARITY = "regularityExpense";
	private static final  int DATABASE_VERSION = 1;
	
	private String createTable_expenses = "create table " + 
	TABLE_NAME_EXPENSE+" ( " + COLUMN_NAME_EXPENSE_ID + " integer primary key autoincrement , "+
	COLUMN_NAME_ACCOUNTING_CATEGORY+" TEXT , " + 
	COLUMN_NAME_CATEGORY_NAME+" TEXT , " + 
	COLUMN_NAME_AMOUNT + " REAL , " + 
	COLUMN_NAME_DATE + " TEXT, " + 
	COLUMN_NAME_TIME + " TEXT, " + 
	COLUMN_NAME_NOTE + " TEXT, " + 
	COLUMN_NAME_REGULARITY + " TEXT )";
	
	public DataExpense(Context context) {
		super(context, TABLE_NAME_EXPENSE, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(createTable_expenses);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EXPENSE);
		db.execSQL(createTable_expenses);
	}

	public void addExpense(Expense expense)
	{	
		boolean isInTheTable = checkExpenseExistence(expense.getIdTransaction());
		if(!isInTheTable){
			SQLiteDatabase db = null;	
			db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_NAME_ACCOUNTING_CATEGORY, expense.getAccountingCategory());
			contentValues.put(COLUMN_NAME_CATEGORY_NAME, expense.getCategoryTransaction());
			contentValues.put(COLUMN_NAME_AMOUNT, expense.getAmountTransaction());
			contentValues.put(COLUMN_NAME_DATE, String.valueOf(expense.getDateTransaction()) );
			contentValues.put(COLUMN_NAME_TIME, String.valueOf(expense.getTimeTransaction()) );
			contentValues.put(COLUMN_NAME_NOTE, expense.getNoteTransaction());
			contentValues.put(COLUMN_NAME_REGULARITY, expense.getRegularityTransaction());
			db.insert(TABLE_NAME_EXPENSE, null, contentValues);		
			db.close();
		} 
		
	}
	
	public long updateExpense(Expense expense)
	{
		boolean isInTheTable = checkExpenseExistence(expense.getIdTransaction());
		
		if(!isInTheTable)
		{
			SQLiteDatabase db = null;	
			db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_NAME_ACCOUNTING_CATEGORY, expense.getAccountingCategory());
			contentValues.put(COLUMN_NAME_CATEGORY_NAME, expense.getCategoryTransaction());
			contentValues.put(COLUMN_NAME_AMOUNT, expense.getAmountTransaction());
			contentValues.put(COLUMN_NAME_DATE, String.valueOf(expense.getDateTransaction()) );
			contentValues.put(COLUMN_NAME_TIME, String.valueOf(expense.getTimeTransaction()) );
			contentValues.put(COLUMN_NAME_NOTE, expense.getNoteTransaction());
			contentValues.put(COLUMN_NAME_REGULARITY, expense.getRegularityTransaction());
			long result = db.insert(TABLE_NAME_EXPENSE, null, contentValues);		
			db.close();
			return result;
		} 
		else 
		{
			SQLiteDatabase db = null;	
			db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_NAME_ACCOUNTING_CATEGORY, expense.getAccountingCategory());
			contentValues.put(COLUMN_NAME_CATEGORY_NAME, expense.getCategoryTransaction());
			contentValues.put(COLUMN_NAME_AMOUNT, expense.getAmountTransaction());
			contentValues.put(COLUMN_NAME_DATE, String.valueOf(expense.getDateTransaction()) );
			contentValues.put(COLUMN_NAME_TIME, String.valueOf(expense.getTimeTransaction()) );
			contentValues.put(COLUMN_NAME_NOTE, expense.getNoteTransaction());
			contentValues.put(COLUMN_NAME_REGULARITY, expense.getRegularityTransaction());
			long ret = db.update(TABLE_NAME_EXPENSE, contentValues, COLUMN_NAME_EXPENSE_ID + " = " + expense.getIdTransaction(), null);		
			db.close();
			return ret;
		}
		
		
	}
	
	public boolean checkExpenseExistence(long id)
	{
		boolean isInTheList = false;
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		
		Cursor cursor =  db.query(TABLE_NAME_EXPENSE,  
				new String[]{
				COLUMN_NAME_EXPENSE_ID, 
				COLUMN_NAME_ACCOUNTING_CATEGORY, 
				COLUMN_NAME_CATEGORY_NAME, 
				COLUMN_NAME_AMOUNT, 
				COLUMN_NAME_DATE, 
				COLUMN_NAME_TIME, 
				COLUMN_NAME_NOTE, 
				COLUMN_NAME_REGULARITY}, 
				COLUMN_NAME_EXPENSE_ID + "= ?", 
				new String[]{ String.valueOf(id) }, 
				null, 
				null, 
				COLUMN_NAME_EXPENSE_ID);
		
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

	public ArrayList<Expense> getAllExpenses(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_EXPENSE, new String[]{COLUMN_NAME_EXPENSE_ID, COLUMN_NAME_ACCOUNTING_CATEGORY, COLUMN_NAME_CATEGORY_NAME, 
				COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME, COLUMN_NAME_NOTE, COLUMN_NAME_REGULARITY}, null, null, null, null, COLUMN_NAME_EXPENSE_ID);
		
		ArrayList<Expense> transactions = new ArrayList<Expense>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idExpense = cursor.getInt(0);
						String accountingCategory = cursor.getString(1);
						String categoryExpense = cursor.getString(2);
						double amountExpense = cursor.getDouble(3);						
						String dateExpense = cursor.getString(4);
						String timeExpense = cursor.getString(5);
						String noteExpense = cursor.getString(6);
						String regularityExpense = cursor.getString(7);
						//Log.e("getAllDates. dateExpense", "date "+dateExpense + "time " + timeExpense);
						transactions.add(new Expense(idExpense, accountingCategory, categoryExpense, amountExpense, dateExpense, timeExpense, noteExpense, regularityExpense));
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
	
	public List<Expense> getAllDates(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_EXPENSE, new String[]{COLUMN_NAME_EXPENSE_ID, COLUMN_NAME_ACCOUNTING_CATEGORY, COLUMN_NAME_CATEGORY_NAME, 
				COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME, COLUMN_NAME_NOTE, COLUMN_NAME_REGULARITY}, null, null, COLUMN_NAME_DATE, null, COLUMN_NAME_EXPENSE_ID, null);
		
		List<Expense> transactions = new ArrayList<Expense>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idExpense = cursor.getInt(0);
						String accountingCategory = cursor.getString(1);
						String categoryExpense = cursor.getString(2);
						double amountExpense = cursor.getDouble(3);						
						String dateExpense = cursor.getString(4);
						String timeExpense = cursor.getString(5);
						String noteExpense = cursor.getString(6);
						String regularityExpense = cursor.getString(7);
						//Log.e("getAllDates. dateExpense", "date "+dateExpense + "time " + timeExpense);
						transactions.add(new Expense(idExpense, accountingCategory, categoryExpense, amountExpense, dateExpense, timeExpense, noteExpense, regularityExpense));
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
	
	public List<Expense> getExpensesByDate(String date){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_EXPENSE, new String[]{COLUMN_NAME_EXPENSE_ID, COLUMN_NAME_ACCOUNTING_CATEGORY, COLUMN_NAME_CATEGORY_NAME, COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME, 
				COLUMN_NAME_NOTE, COLUMN_NAME_REGULARITY}, COLUMN_NAME_DATE + "= ?", new String[]{date}, null, null, COLUMN_NAME_EXPENSE_ID);
		
		List<Expense> expenses = new ArrayList<Expense>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idExpense = cursor.getInt(0);
						String accountingCategory = cursor.getString(1);
						String categoryExpense = cursor.getString(2);
						double amountExpense = cursor.getDouble(3);						
						String dateExpense = cursor.getString(4);
						String timeExpense = cursor.getString(5);
						String noteExpense = cursor.getString(6);
						String regularityExpense = cursor.getString(7);
						expenses.add(new Expense(idExpense, accountingCategory, categoryExpense, amountExpense, dateExpense, timeExpense, noteExpense, regularityExpense));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 			
		}
		cursor.close();
		db.close();
		return expenses;
	}
	
	public int deleteExpense(long id){
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		
		int ret = db.delete(TABLE_NAME_EXPENSE, COLUMN_NAME_EXPENSE_ID + " = " + id,  null );
		db.close();
		return ret;
	}

}