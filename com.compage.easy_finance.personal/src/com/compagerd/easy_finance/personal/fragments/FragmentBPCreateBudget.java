package com.compagerd.easy_finance.personal.fragments;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.activities.Budgets;
import com.compagerd.easy_finance.personal.database.DataAccount;
import com.compagerd.easy_finance.personal.database.DataBudget;
import com.compagerd.easy_finance.personal.database.DataExpense;
import com.compagerd.easy_finance.personal.model.Budget;
import com.compagerd.easy_finance.personal.model.Expense;
import com.compagerd.easy_finance.personal.model.Transaction;
import com.compagerd.easy_finance.personal.util.Options;
import com.compagerd.easy_finance.personal.util.Settings;

public class FragmentBPCreateBudget extends Fragment implements OnClickListener {

	private LinearLayout ll_create, ll_done;
	private Context context;
	private DataAccount accountData;
	private Settings settings;
	private Button ButtonRepeatExpense, ButtonDone;
	private boolean selectedTab; 
	private String message = "";
	private Expense expense;
	private Options options;

	public FragmentBPCreateBudget(Context context) {
		super();
		this.context = context;
		accountData = new DataAccount(context);
		options = new Options(context);
	}

	public FragmentBPCreateBudget() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_bottom_panel_create_expense, container, false);
		initComponents(view);
		configureComponent();
		
		Bundle bundle = this.getArguments();

		try
		{
			if(	settings.getPrefs().getBoolean(Settings.IS_FOR_EDITION, false)	)
			{
				if((Expense) bundle.getSerializable("serialBudget") != null)
			     {
					 expense =  (Expense) bundle.getSerializable("serialBudget");		       
			    }
			}
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return view;
	}

	public View initComponents(View view) 
	{
		settings = Settings.getInstance();
		settings.configureSharePreference(context);
		ll_create = (LinearLayout) view.findViewById(R.id.ll_create);
		ll_done = (LinearLayout) view.findViewById(R.id.ll_done);
		return view;
	}

	private void configureComponent() {
		ll_create.setOnClickListener(this);
		ll_done.setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.ll_create:
				if(validateToSaveExpense())
					clearFields();
				break;
	
			case R.id.ll_done:
				
				if(validateToSaveExpense())
				{
					this.getActivity().finish();
					startActivity(new Intent(this.context, Budgets.class));
				}
				break;
		}
	}
	
	private void clearFields() 
	{
		FragmentCreateBudget.textViewTitleChooseCategories.setText(getResources().getString(R.string.choose_category));
		FragmentCreateBudget.textViewAmount.setText(getResources().getString(R.string.change_amount));
		FragmentCreateBudget.textViewDate.setText(getResources().getString(R.string.change_date));
		FragmentCreateBudget.textViewRegularity.setText(getResources().getString(R.string.change_regularity));
	}

	private boolean validateToSaveExpense() 
	{
		boolean validated = false;
		
		if(FragmentCreateBudget.selectedTab != null){
			selectedTab = true;
		} else {					
			selectedTab = false;
		}
		
		if(
		selectedTab == false |
		checkIfSetted(FragmentCreateBudget.textViewTitleChooseCategories.getText().toString(), R.string.choose_category, this.message, FragmentCreateBudget.categorySelected) == false | 
		checkIfSetted(FragmentCreateBudget.textViewAmount.getText().toString(), R.string.change_amount, this.message, FragmentCreateBudget.amountSetted) == false | 
		checkIfSetted(FragmentCreateBudget.textViewDate.getText().toString(), R.string.change_date, this.message, FragmentCreateBudget.dateSetted) == false | 
		checkIfSetted(FragmentCreateBudget.textViewRegularity.getText().toString(), R.string.change_regularity, this.message, FragmentCreateBudget.regularitySetted) == false)
		{	
			Toast.makeText(getActivity(), this.message, Toast.LENGTH_LONG).show();
			return validated;
		} 
		else 
		{
			Expense editedExpense;
			
			if(expense == null){
				 editedExpense = new Expense(0, FragmentCreateBudget.tabs.getCurrentTabTag(), 
			    		 FragmentCreateBudget.textViewTitleChooseCategories.getText().toString(), 
			    		 Double.valueOf(FragmentCreateBudget.textViewAmount.getText().toString()), FragmentCreateBudget.textViewDate.getText().toString(), 
			    		 options.getCurrentTime(), "", FragmentCreateBudget.textViewRegularity.getText().toString());
			} else {
				 editedExpense = new Expense(expense.getIdTransaction(), FragmentCreateBudget.tabs.getCurrentTabTag(), 
			    		 FragmentCreateBudget.textViewTitleChooseCategories.getText().toString(), 
			    		 Double.valueOf(FragmentCreateBudget.textViewAmount.getText().toString()), FragmentCreateBudget.textViewDate.getText().toString(), 
			    		 options.getCurrentTime(), "", FragmentCreateBudget.textViewRegularity.getText().toString());
			}
		    
			DataExpense dataExpense = new DataExpense(context);
			long idExpense = dataExpense.updateExpense(editedExpense);
		  
			Double paymentsQuantityBudget = calculatePaymentsQuantity(editedExpense.getAmountTransaction(), editedExpense.getRegularityTransaction());  
			
		    Double totalAmount = editedExpense.getAmountTransaction() * paymentsQuantityBudget;
		    Budget budget = new Budget(0, editedExpense.getAccountingCategory(), idExpense, editedExpense.getAmountTransaction(), paymentsQuantityBudget, totalAmount);
		   
		    DataBudget dataBudget = new DataBudget(context);
		    long idBudget = dataBudget.updateBudget(budget);
		    
		    
			Options.putTransactionInDashboard(budget.getNameProdecedenceBudget(), totalAmount, Settings.BUDGET);
			
			Toast.makeText(getActivity(), getResources().getString(R.string.app_saved), Toast.LENGTH_LONG).show();
			
			return true;
		}
		
	}
	
	public Double calculatePaymentsQuantity(Double amount, String regularity)
	{
		Double paymentsQuantity = 0.0; 
		
		if(getResources().getString(R.string.regularity_never).equalsIgnoreCase(regularity))
		{
			// amount stays the same
		}
		if( getResources().getString(R.string.regularity_daily).equalsIgnoreCase(regularity))
		{			
			int quantityOfMonthDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
			int currentDayNumberOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			paymentsQuantity = Double.valueOf(quantityOfMonthDays - currentDayNumberOfMonth);
			
		}
		if(getResources().getString(R.string.regularity_weekly).equalsIgnoreCase(regularity))
		{
			int quantityOfMonthWeeks= Calendar.getInstance().getActualMaximum(Calendar.WEEK_OF_MONTH);	Log.e("quantityOfMonthWeeks", quantityOfMonthWeeks+"");
			int currentWeekNumberOfMonth = Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);			Log.e("currentWeekNumberOfMonth", currentWeekNumberOfMonth+"");
			paymentsQuantity = Double.valueOf(quantityOfMonthWeeks - currentWeekNumberOfMonth);
			//amount *= paymentsQuantity;
		}
		if(getResources().getString(R.string.regularity_monthly).equalsIgnoreCase(regularity))
		{
			paymentsQuantity = Double.valueOf(1.0);
		}
		if(getResources().getString(R.string.regularity_trimestral).equalsIgnoreCase(regularity))
		{
			paymentsQuantity = Double.valueOf(1.0/3.0);
		}
		if(getResources().getString(R.string.regularity_quarterly).equalsIgnoreCase(regularity))
		{
			
			paymentsQuantity = (double) (1.0/4.0);
			Log.e("paymentsQuantity", paymentsQuantity+"");
		}
		if(getResources().getString(R.string.regularity_biyearly).equalsIgnoreCase(regularity))
		{
			paymentsQuantity = Double.valueOf(1.0/6.0);
		}
		if(getResources().getString(R.string.regularity_yearly).equalsIgnoreCase(regularity))
		{
			paymentsQuantity = Double.valueOf(1.0/12.0);
		}
		
		return paymentsQuantity;
	}

	public String concatStringProperly(String lackingItems, String message)
	{
		if(lackingItems != "")
		{
			if(lackingItems.contains("Category") && lackingItems.contains("Amount") && lackingItems.contains("Date") && lackingItems.contains("Repeat"))
			{
				return lackingItems;
			} else {
				lackingItems = lackingItems + ". " + message;
			}
			
		} else {
			lackingItems = message;
		}
		return lackingItems;
	}
	
	public boolean checkIfSetted(String textview, int resourceId, String lackingItems, boolean item)
	{
		if(textview.equalsIgnoreCase(getResources().getString(resourceId)))
		{
			message = concatStringProperly(lackingItems, this.context.getResources().getString(resourceId));
		} else {
			item = true;
		}
		return item;
	}
}
