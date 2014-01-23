package com.compagerd.easy_finance.personal.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.compagerd.easy_finance.personal.model.Income;

public class DataIncomes extends SQLiteOpenHelper {

	public static final String TABLE_NAME_INCOME = "Incomes";
	public static final String COLUMN_NAME_INCOME_ID = "idIncome";
	public static final String COLUMN_NAME_CATEGORY_NAME = "categoryIncome";
	public static final String COLUMN_NAME_AMOUNT = "amountIncome";
	public static final String COLUMN_NAME_DATE = "dateIncome";
	public static final String COLUMN_NAME_TIME = "timeIncome";
	public static final String COLUMN_NAME_NOTE = "noteIncome";
	private static final  int DATABASE_VERSION = 1;
	
	private String createTable_incomes = "create table " + 
	TABLE_NAME_INCOME+" ( " + COLUMN_NAME_INCOME_ID + " integer primary key autoincrement , "+
	COLUMN_NAME_CATEGORY_NAME+" TEXT , " + 
	COLUMN_NAME_AMOUNT + " REAL , " + 
	COLUMN_NAME_DATE + " TEXT, " + 
	COLUMN_NAME_TIME + " TEXT, " + 
	COLUMN_NAME_NOTE + " TEXT )";
	
	public DataIncomes(Context context) {
		super(context, TABLE_NAME_INCOME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable_incomes);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_INCOME);
		db.execSQL(createTable_incomes);
	}

	public void addIncome(Income Income)
	{	
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_NAME_CATEGORY_NAME, Income.getCategoryIncome());
		contentValues.put(COLUMN_NAME_AMOUNT, Income.getAmountIncome());
		contentValues.put(COLUMN_NAME_DATE, String.valueOf(Income.getDateIncome()) );
		contentValues.put(COLUMN_NAME_TIME, String.valueOf(Income.getTimeIncome()) );
		contentValues.put(COLUMN_NAME_NOTE, Income.getNoteIncome());
		db.insert(TABLE_NAME_INCOME, null, contentValues);		
		db.close();
	}
	
	public int updateIncome(Income income)
	{
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_NAME_CATEGORY_NAME, income.getCategoryIncome());
		contentValues.put(COLUMN_NAME_AMOUNT, income.getAmountIncome());
		contentValues.put(COLUMN_NAME_DATE, String.valueOf(income.getDateIncome()) );
		contentValues.put(COLUMN_NAME_TIME, String.valueOf(income.getTimeIncome()) );
		contentValues.put(COLUMN_NAME_NOTE, income.getNoteIncome());
		int ret = db.update(TABLE_NAME_INCOME, contentValues, COLUMN_NAME_INCOME_ID + " = " + income.getIdIncome(), null);		
		db.close();
		return ret;
	}

	public List<Income> getAllIncomes(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_INCOME, new String[]{COLUMN_NAME_INCOME_ID, COLUMN_NAME_CATEGORY_NAME, COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, 
				COLUMN_NAME_TIME, COLUMN_NAME_NOTE}, null, null, null, null, COLUMN_NAME_INCOME_ID);
		
		List<Income> Incomes = new ArrayList<Income>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idIncome = cursor.getInt(0);
						String categoryIncome = cursor.getString(1);
						double amountIncome = cursor.getDouble(2);						
						String dateIncome = cursor.getString(3);
						String timeIncome = cursor.getString(4);
						String noteIncome = cursor.getString(5);
						Incomes.add(new Income(idIncome, categoryIncome, amountIncome, dateIncome, timeIncome, noteIncome));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 			
		}
		cursor.close();
		db.close();
		return Incomes;
	}
	
	public List<Income> getAllDates(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_INCOME, new String[]{COLUMN_NAME_INCOME_ID, COLUMN_NAME_CATEGORY_NAME, COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME,
				COLUMN_NAME_NOTE}, null, null, COLUMN_NAME_DATE, null, COLUMN_NAME_INCOME_ID, null);
		
		List<Income> incomes = new ArrayList<Income>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idIncome = cursor.getInt(0);
						String categoryIncome = cursor.getString(1);
						double amountIncome = cursor.getDouble(2);						
						String dateIncome = cursor.getString(3);
						String timeIncome = cursor.getString(4);
						String noteIncome = cursor.getString(5);
						//Log.e("getAllDates. dateIncome", "date "+dateIncome + "time " + timeIncome);
						incomes.add(new Income(idIncome, categoryIncome, amountIncome, dateIncome, timeIncome, noteIncome));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 
		}
		cursor.close();
		db.close();
		return incomes;
	}
	
	public List<Income> getIncomesByDate(String date){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_INCOME, new String[]{COLUMN_NAME_INCOME_ID, COLUMN_NAME_CATEGORY_NAME, COLUMN_NAME_AMOUNT, COLUMN_NAME_DATE, COLUMN_NAME_TIME, 
				COLUMN_NAME_NOTE}, COLUMN_NAME_DATE + "= ?", new String[]{date}, null, null, COLUMN_NAME_INCOME_ID);
		
		List<Income> Incomes = new ArrayList<Income>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idIncome = cursor.getInt(0);
						String categoryIncome = cursor.getString(1);
						double amountIncome = cursor.getDouble(2);						
						String dateIncome = cursor.getString(3);
						String timeIncome = cursor.getString(4);
						String noteIncome = cursor.getString(5);
						Incomes.add(new Income(idIncome, categoryIncome, amountIncome, dateIncome, timeIncome, noteIncome ));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 
		}
		cursor.close();
		db.close();
		return Incomes;
	}
	
	public int deleteIncome(int idIncome){
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		int ret = db.delete(TABLE_NAME_INCOME, COLUMN_NAME_INCOME_ID + " = " + idIncome,  null );
		db.close();
		return ret;
	}

}