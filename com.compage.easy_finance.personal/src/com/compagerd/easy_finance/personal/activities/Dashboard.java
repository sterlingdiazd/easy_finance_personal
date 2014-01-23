package com.compagerd.easy_finance.personal.activities;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.adapters.AdapterGridView;
import com.compagerd.easy_finance.personal.database.DataBudget;
import com.compagerd.easy_finance.personal.database.DataTransaction;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;
import com.compagerd.easy_finance.personal.interfaces.Activities;
import com.compagerd.easy_finance.personal.model.Budget;
import com.compagerd.easy_finance.personal.model.Movement;
import com.compagerd.easy_finance.personal.model.MovementsBudget;
import com.compagerd.easy_finance.personal.model.MovementsTransaction;
import com.compagerd.easy_finance.personal.model.Transaction;
import com.compagerd.easy_finance.personal.util.ActivitiesItems;
import com.compagerd.easy_finance.personal.util.Item;
import com.compagerd.easy_finance.personal.util.Settings;

public class Dashboard extends FragmentActivity implements Activities {

	public static ActionBar actionBar;
	private GridView gridViewDashboardOptions;
	public static final String ACTIVITY_NAME = "ACTIVITY_NAME";
	private Settings settings;
	private Context context;
	private ProgressBar progressBarBalance, progressBarBudget, progressBarAvailable;
	private TextView textViewAmountBudget, textViewAmountBalance, textViewAmountAvailable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this.getBaseContext());
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_ad_dashboard, fragmentAdvertising);
		fragmentTransaction.commit();
		
		initComponents();
		Settings.getInstance().configureSharePreference(context);
		
		configureComponents();
		prepareActionBar( this );
	}

	public void initComponents() 
	{
		this.context = this.getBaseContext();
				
		settings = Settings.getInstance();
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		gridViewDashboardOptions = (GridView) findViewById(R.id.gridViewDashboardOptions);
		
		progressBarBalance = (ProgressBar) findViewById(R.id.progressBarBalance);
		progressBarBudget = (ProgressBar) findViewById(R.id.progressBarBudget);
		
		
		
		
		//progressBarAvailable = (ProgressBar) findViewById(R.id.progressBarAvailable);
		
		textViewAmountBudget  = (TextView) findViewById(R.id.textViewAmountBudget);
		textViewAmountBalance = (TextView) findViewById(R.id.textViewAmountBalance);
		//textViewAmountAvailable = (TextView) findViewById(R.id.textViewAmountAvailable);	
	}
	
	private void prepareActionBar(Context context) 
	{
		actionBar.setLogoAction(new ActionBar.IntentAction(context, Dashboard.createIntent(context), R.drawable.logo));
		actionBar.setTitle(R.string.title_activity_dashboard);			
	}

	private void configureComponents() {
		
		settings.configureSharePreference(this.context);
		
		prepareItemsToUpdate();

		ActivitiesItems activitiesItems = new ActivitiesItems(this.getBaseContext());
		final ArrayList<Item> items = activitiesItems.configureDashboardItems();
		final ArrayList<String> titles = activitiesItems.configureActivitiesStrings();

		gridViewDashboardOptions.setAdapter(new AdapterGridView(this.getBaseContext(), items));
		gridViewDashboardOptions.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				String actitivyName = titles.get(position);
				try {
					Class ourClass = Class.forName("com.compagerd.easy_finance.personal.activities."+ actitivyName);
					Intent intent = new Intent(Dashboard.this, ourClass);
					intent.putExtra(ACTIVITY_NAME, actitivyName);
					startActivity(intent);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onResume() 
	{
		super.onResume();
		prepareItemsToUpdate();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	private void prepareItemsToUpdate() 
	{
		ArrayList<Movement> itemsToUpdate = new ArrayList<Movement>();
		MovementsBudget movementsBudget = new MovementsBudget(Settings.BUDGET, progressBarBudget, textViewAmountBudget, (SQLiteOpenHelper) new DataBudget(this.context));
		MovementsTransaction movementsTransaction = new MovementsTransaction(Settings.TRANSACTION, progressBarBalance, textViewAmountBalance, (SQLiteOpenHelper) new DataTransaction(this.context));
		
		itemsToUpdate.add( (Movement) movementsBudget);
		itemsToUpdate.add( (Movement) movementsTransaction);		
		updateAmounts(itemsToUpdate);
	}

	public void updateAmounts(ArrayList<Movement> itemsToUpdate)
	{
		Double incomes = 0.0;
		Double expenses = 0.0;
		
		for(int x = 0, y = itemsToUpdate.size(); x < y; x++)
		{	
			Movement movement = itemsToUpdate.get(x);
			calculateIncomes(movement);
			
			/*
			if(movement.getAccountantCategory().equalsIgnoreCase(Settings.BUDGET))
			{
				movement.getProgressBar().set
			}
			*/
			
			//Log.e("ingresos", ingresos+"");
			/*
			int ingresosInt = ingresos.intValue();
			
			String gastos = calculateExpense(movement);
			Double gastosDouble = Double.parseDouble(gastos);
			int gastosInt = gastosDouble.intValue();
			
			int balance = ingresosInt - gastosInt;
			
			movement.getProgressBar().setMax(ingresosInt);
			movement.getProgressBar().setProgress( gastosInt );
			movement.getTextViewAmount().setText( String.valueOf(balance));
			*/
		}
	}

	/*
	public void calculateBalance(SQLiteOpenHelper dataHandler, String type)
	{
		Double incomes = 0.0;
		Double expenses = 0.0;
		if(type.equalsIgnoreCase(Settings.BUDGET))
		{
			DataBudget dataBudget = (DataBudget) dataHandler;

			for(Budget budget : dataBudget.getAllBudgets())
			{
				//if()
			}
		}
		
	}
	*/
	private void calculateIncomes(Movement movement) 
	{
		Double incomes = 0.0;
		Double expenses = 0.0;
		
		if(movement.getAccountantCategory().equalsIgnoreCase(Settings.BUDGET))
		{			
			DataBudget dataBudget = (DataBudget) movement.getDataHandler();
			
			for(Budget budget : dataBudget.getAllBudgets())
			{	
				if(budget.getNameProdecedenceBudget().equalsIgnoreCase(getResources().getString(R.string.accountant_category_incomes)))
				{
					incomes += budget.getTotalAmountBudget();
				} 
				else if(budget.getNameProdecedenceBudget().equalsIgnoreCase(getResources().getString(R.string.accountant_category_expenses)))
				{					
					expenses += budget.getTotalAmountBudget();
				}
			}
			
			movement.getProgressBar().setMax(incomes.intValue());
			movement.getProgressBar().setProgress( expenses.intValue() );
			movement.getTextViewAmount().setText( String.valueOf(incomes - expenses));
					
		}
		else if(movement.getAccountantCategory().equals(Settings.TRANSACTION))
		{			
			DataTransaction dataTransaction = (DataTransaction) movement.getDataHandler();
			
			for(Transaction transaction : dataTransaction.getAllTransactions())
			{
				if(transaction.getAccountingCategory().equalsIgnoreCase(getResources().getString(R.string.accountant_category_incomes)))
				{					
					incomes += transaction.getAmountTransaction();
				} 
				else if(transaction.getAccountingCategory().equalsIgnoreCase(getResources().getString(R.string.accountant_category_expenses)))
				{
					expenses += transaction.getAmountTransaction();
				}
			}
			movement.getProgressBar().setMax(incomes.intValue());
			movement.getProgressBar().setProgress( expenses.intValue() );
			movement.getTextViewAmount().setText( String.valueOf(incomes - expenses));
		}
	}
	
	public static Intent createIntent(Context context) {
		Intent intent = new Intent();
		intent.setClass(context, Dashboard.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return intent;
	}

	public void prepareActionBar() {
		// TODO Auto-generated method stub
		
	}

	public void configureFragments() {
		// TODO Auto-generated method stub
		
	}
	
}
