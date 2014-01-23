package com.compagerd.easy_finance.personal.database;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.compagerd.easy_finance.personal.model.Budget;

public class DataBudget extends SQLiteOpenHelper {

	public static final String TABLE_NAME_BUDGET = "Budget";
	public static final String COLUMN_NAME_BUDGET_ID = "idBudget";
	public static final String COLUMN_NAME_BUDGET_PROCEDENCE_NAME = "nameProdecedenceBudget";
	public static final String COLUMN_NAME_BUDGET_PROCEDENCE_ID = "idProdecedenceBudget";
	public static final String COLUMN_NAME_UNITARY_AMOUNT = "unitaryAmountBudget";
	public static final String COLUMN_NAME_PAYMENTS_QUANTITY = "paymentsQuantityBudget";
	public static final String COLUMN_NAME_TOTAL_AMOUNT = "totalAmountBudget";
	private static final  int DATABASE_VERSION = 1;
	
	private String createTable_transactions = "create table " + 
	TABLE_NAME_BUDGET+" ( " + 
	COLUMN_NAME_BUDGET_ID + " integer primary key autoincrement , "+
	COLUMN_NAME_BUDGET_PROCEDENCE_NAME +" TEXT , " + 
	COLUMN_NAME_BUDGET_PROCEDENCE_ID +" TEXT , " + 
	COLUMN_NAME_UNITARY_AMOUNT + " REAL , " + 
	COLUMN_NAME_PAYMENTS_QUANTITY + " REAL, " + 
	COLUMN_NAME_TOTAL_AMOUNT + " REAL )";
	
	public DataBudget(Context context) {
		super(context, TABLE_NAME_BUDGET, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(createTable_transactions);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BUDGET);
		db.execSQL(createTable_transactions);
	}
	
	/*
	public void addBudget(Budget budget)
	{	
		boolean isInTheTable = checkBudgetExistence(budget.getIdBudget());
		if(!isInTheTable){
			SQLiteDatabase db = null;	
			db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_NAME_BUDGET_PROCEDENCE_NAME, budget.getNameProdecedenceBudget());
			contentValues.put(COLUMN_NAME_BUDGET_PROCEDENCE_ID, budget.getIdProdecedenceBudget());
			contentValues.put(COLUMN_NAME_UNITARY_AMOUNT, budget.getUnitaryAmountBudget());
			contentValues.put(COLUMN_NAME_PAYMENTS_QUANTITY, String.valueOf(budget.getPaymentsQuantityBudget()) );
			contentValues.put(COLUMN_NAME_TOTAL_AMOUNT, String.valueOf(budget.getTotalAmountBudget()) );
			db.insert(TABLE_NAME_BUDGET, null, contentValues);		
			db.close();
		} 
		
	}
	*/
	public int updateBudget(Budget budget)
	{
		boolean isInTheTable = checkBudgetExistence(budget.getIdBudget());
		
		if(!isInTheTable)
		{
			SQLiteDatabase db = null;	
			db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_NAME_BUDGET_PROCEDENCE_NAME, budget.getNameProdecedenceBudget());
			contentValues.put(COLUMN_NAME_BUDGET_PROCEDENCE_ID, budget.getIdProdecedenceBudget());
			contentValues.put(COLUMN_NAME_UNITARY_AMOUNT, budget.getUnitaryAmountBudget());
			contentValues.put(COLUMN_NAME_PAYMENTS_QUANTITY, String.valueOf(budget.getPaymentsQuantityBudget()) );
			contentValues.put(COLUMN_NAME_TOTAL_AMOUNT, String.valueOf(budget.getTotalAmountBudget()) );
			int result = (int) db.insert(TABLE_NAME_BUDGET, null, contentValues);		
			db.close();
			return result;
		} 
		else 
		{
			SQLiteDatabase db = null;	
			db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_NAME_BUDGET_PROCEDENCE_NAME, budget.getNameProdecedenceBudget());
			contentValues.put(COLUMN_NAME_BUDGET_PROCEDENCE_ID, budget.getIdProdecedenceBudget());
			contentValues.put(COLUMN_NAME_UNITARY_AMOUNT, budget.getUnitaryAmountBudget());
			contentValues.put(COLUMN_NAME_PAYMENTS_QUANTITY, String.valueOf(budget.getPaymentsQuantityBudget()) );
			contentValues.put(COLUMN_NAME_TOTAL_AMOUNT, String.valueOf(budget.getTotalAmountBudget()) );
			int ret = db.update(TABLE_NAME_BUDGET, contentValues, COLUMN_NAME_BUDGET_ID + " = " + budget.getIdBudget(), null);		
			db.close();
			return ret;
		}
		
		
	}
	
	public boolean checkBudgetExistence(int id)
	{
		boolean isInTheList = false;
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		
		Cursor cursor =  db.query(TABLE_NAME_BUDGET,  
				new String[]{
				COLUMN_NAME_BUDGET_ID, 
				COLUMN_NAME_BUDGET_PROCEDENCE_NAME, 
				COLUMN_NAME_BUDGET_PROCEDENCE_ID, 
				COLUMN_NAME_UNITARY_AMOUNT, 
				COLUMN_NAME_PAYMENTS_QUANTITY, 
				COLUMN_NAME_TOTAL_AMOUNT}, 
				COLUMN_NAME_BUDGET_ID + "= ?", 
				new String[]{ String.valueOf(id) }, 
				null, 
				null, 
				COLUMN_NAME_BUDGET_ID);
		
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

	public ArrayList<Budget> getAllBudgets(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_BUDGET, new String[]{COLUMN_NAME_BUDGET_ID, COLUMN_NAME_BUDGET_PROCEDENCE_NAME, COLUMN_NAME_BUDGET_PROCEDENCE_ID, 
				COLUMN_NAME_UNITARY_AMOUNT, COLUMN_NAME_PAYMENTS_QUANTITY, COLUMN_NAME_TOTAL_AMOUNT}, null, null, null, null, COLUMN_NAME_BUDGET_ID);
		
		ArrayList<Budget> transactions = new ArrayList<Budget>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idBudget = cursor.getInt(0);
						String nameProdecedenceBudget = cursor.getString(1);
						int idProdecedenceBudget = cursor.getInt(2);
						double unitaryAmountBudget = cursor.getDouble(3);						
						Double paymentsQuantityBudget = cursor.getDouble(4);
						double totalAmountBudget = cursor.getDouble(5);
						transactions.add(new Budget(idBudget, nameProdecedenceBudget, idProdecedenceBudget, unitaryAmountBudget, paymentsQuantityBudget, totalAmountBudget));
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
	
	public List<Budget> getAllDates(){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_BUDGET, new String[]{COLUMN_NAME_BUDGET_ID, COLUMN_NAME_BUDGET_PROCEDENCE_NAME, COLUMN_NAME_BUDGET_PROCEDENCE_ID, 
				COLUMN_NAME_UNITARY_AMOUNT, COLUMN_NAME_PAYMENTS_QUANTITY, COLUMN_NAME_TOTAL_AMOUNT}, null, null, COLUMN_NAME_PAYMENTS_QUANTITY, null, COLUMN_NAME_BUDGET_ID, null);
		
		List<Budget> transactions = new ArrayList<Budget>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idBudget = cursor.getInt(0);
						String nameProdecedenceBudget = cursor.getString(1);
						int idProdecedenceBudget = cursor.getInt(2);
						double unitaryAmountBudget = cursor.getDouble(3);						
						Double paymentsQuantityBudget = cursor.getDouble(4);
						double totalAmountBudget = cursor.getDouble(5);
						transactions.add(new Budget(idBudget, nameProdecedenceBudget, idProdecedenceBudget, unitaryAmountBudget, paymentsQuantityBudget, totalAmountBudget));
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
	
	public List<Budget> getBudgetsByDate(String date){		
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(TABLE_NAME_BUDGET, new String[]{COLUMN_NAME_BUDGET_ID, COLUMN_NAME_BUDGET_PROCEDENCE_NAME, COLUMN_NAME_BUDGET_PROCEDENCE_ID, 
				COLUMN_NAME_UNITARY_AMOUNT, COLUMN_NAME_PAYMENTS_QUANTITY, COLUMN_NAME_TOTAL_AMOUNT}, 
				COLUMN_NAME_PAYMENTS_QUANTITY + "= ?", new String[]{date}, null, null, COLUMN_NAME_BUDGET_ID);
		
		List<Budget> budgets = new ArrayList<Budget>();
		
		if(cursor != null && cursor.getCount() != 0)
		{	
			if(cursor.moveToFirst()){
				try{
					
					do {
						int idBudget = cursor.getInt(0);
						String nameProdecedenceBudget = cursor.getString(1);
						int idProdecedenceBudget = cursor.getInt(2);
						double unitaryAmountBudget = cursor.getDouble(3);						
						Double paymentsQuantityBudget = cursor.getDouble(4);
						double totalAmountBudget = cursor.getDouble(5);
						budgets.add(new Budget(idBudget, nameProdecedenceBudget, idProdecedenceBudget, unitaryAmountBudget, paymentsQuantityBudget, totalAmountBudget));
					} while(cursor.moveToNext());
					
					
				} catch(Exception e ){
					e.printStackTrace();
				}
			} 			
		}
		cursor.close();
		db.close();
		return budgets;
	}
	
	public int deleteBudget(int l){
		SQLiteDatabase db = null;	
		db = this.getWritableDatabase();
		int ret = db.delete(TABLE_NAME_BUDGET, COLUMN_NAME_BUDGET_ID + " = " + l,  null );
		db.close();
		return ret;
	}
	
}
