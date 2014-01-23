package com.compagerd.easy_finance.personal.util;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.adapters.AdapterAccounts;
import com.compagerd.easy_finance.personal.database.DataExpense;
import com.compagerd.easy_finance.personal.database.DataTransaction;
import com.compagerd.easy_finance.personal.fragments.FragmentBPAccounts;
import com.compagerd.easy_finance.personal.fragments.FragmentCreateBudget;
import com.compagerd.easy_finance.personal.model.Expense;
import com.compagerd.easy_finance.personal.model.Transaction;

public class Options implements OnClickListener {

	private static Context context;
	EditText editTextNumberScreen;
	Button Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, ButtonDot, ButtonZero, ButtonChangeDate, 
	ButtonRepeatExpense, ButtonDone;
	ImageButton imageButtonBackspace;
	//private String category;
	DataTransaction dataExpenses;
	private Transaction transaction;
	private static Settings settings;
	private DatePicker datePicker;
	
	public Options(Context context) {
		super();
		Options.context = context;	
		settings = Settings.getInstance();
		
	}

	private View initComponents(View view ) 
	{
		editTextNumberScreen = (EditText) view.findViewById(R.id.editTextNumberScreen);
		Button1 = (Button) view.findViewById(R.id.Button1);
		Button2 = (Button) view.findViewById(R.id.Button2);
		Button3 = (Button) view.findViewById(R.id.Button3);
		Button4 = (Button) view.findViewById(R.id.Button4);
		Button5 = (Button) view.findViewById(R.id.Button5);
		Button6 = (Button) view.findViewById(R.id.Button6);
		Button7 = (Button) view.findViewById(R.id.Button7);
		Button8 = (Button) view.findViewById(R.id.Button8);
		Button9 = (Button) view.findViewById(R.id.Button9);
		ButtonDot = (Button) view.findViewById(R.id.ButtonDot);
		ButtonZero = (Button) view.findViewById(R.id.ButtonZero);
		imageButtonBackspace = (ImageButton) view.findViewById(R.id.imageButtonBackspace);
		/*
		ButtonChangeDate = (Button) view.findViewById(R.id.ButtonChangeDate);
		ButtonRepeatExpense = (Button) view.findViewById(R.id.ButtonRepeatExpense);		
		ButtonDone = (Button) view.findViewById(R.id.ButtonDone);
		*/
		//editTextNumberScreen.set
		Button1.setOnClickListener(this);
		Button2.setOnClickListener(this);
		Button3.setOnClickListener(this);
		Button4.setOnClickListener(this);
		Button5.setOnClickListener(this);
		Button6.setOnClickListener(this);
		Button7.setOnClickListener(this);
		Button8.setOnClickListener(this);
		Button9.setOnClickListener(this);
		ButtonDot.setOnClickListener(this);
		ButtonZero.setOnClickListener(this);
		imageButtonBackspace.setOnClickListener(this);
		/*
		ButtonChangeDate.setOnClickListener(this);
		ButtonRepeatExpense.setOnClickListener(this);
		ButtonDone.setOnClickListener(this);
		*/
		return view;
	}
	
	public Dialog createTransactionDialog(final Item item, final String accountingCategory)
	{	
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(accountingCategory  + ": " + item.getItemTitle());
		alertDialogBuilder.setIcon(item.getResourceDrawable());
		//alertDialogBuilder.setMessage("¿Do you want to start session automatically?");
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_perform_transaction, null);
		initComponents(view);
		
		alertDialogBuilder.setView(view);
		alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.app_done), new DialogInterface.OnClickListener()
		{			
			public void onClick(DialogInterface dialog, int which) 
			{	
				transaction = (Transaction) createTransaction(accountingCategory, item.getItemTitle());
				new DataTransaction(context).addTransaction(transaction);
				
				if(transaction != null){
					dialog.cancel();
					Toast.makeText(context, context.getResources().getString(R.string.app_saved), Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(context, accountingCategory + " " + context.getResources().getString(R.string.app_is_null), Toast.LENGTH_SHORT).show();
				}					
				
			}
		});
		return alertDialogBuilder.create();
	}
	
	public Dialog createAmountDialog()
	{
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(context.getResources().getString(R.string.account_initial_value));
		alertDialogBuilder.setIcon(context.getResources().getDrawable(R.drawable.icon_freelance));
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_perform_transaction, null);
		initComponents(view);
		
		alertDialogBuilder.setView(view);
		alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.app_done), new DialogInterface.OnClickListener()
		{			
			public void onClick(DialogInterface dialog, int which) 
			{	
				FragmentBPAccounts.editTextInitialValue.setText(editTextNumberScreen.getText().toString());
				
			}
		});
		return alertDialogBuilder.create();
		
	}
	
	public boolean isTextViewEmpty(TextView textView)
	{	
		if( textView.getText().toString().length() < 1){
			return true;
		} else {
			return false;
		}
	}
	
	public Dialog createBudgetDialog(final Item item, final String accountingCategory)
	{	
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle( accountingCategory + ": " +  item.getItemTitle() );
		alertDialogBuilder.setIcon(item.getResourceDrawable());
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_perform_transaction, null);
		initComponents(view);
		
		alertDialogBuilder.setView(view);
		alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.app_done), new DialogInterface.OnClickListener() 
		{			
			public void onClick(DialogInterface dialog, int which) 
			{	
				FragmentCreateBudget.transaction = (Transaction) createBudgetTransaction(accountingCategory, item.getItemTitle());
				
				if(FragmentCreateBudget.transaction != null)
				{	
					FragmentCreateBudget.textViewTitleChooseCategories.setText(item.getItemTitle());
					FragmentCreateBudget.imageViewCategory.setImageResource(item.getResourceId());
					FragmentCreateBudget.textViewDate.setText(FragmentCreateBudget.transaction.getDateTransaction());
					FragmentCreateBudget.textViewAmount.setText(FragmentCreateBudget.transaction.getAmountTransaction()+"");
					dialog.cancel();					
				} else {
					Toast.makeText(context, context.getResources().getString(R.string.budgets_The_transaction_could_not_be_maded), Toast.LENGTH_SHORT).show();
				}					
				
			}
		});
		return alertDialogBuilder.create();
	}
	
	public Dialog setAmountDialog(final Item item, final String accountingCategory)
	{	
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(context.getResources().getString(R.string.app_changing_amount));
		alertDialogBuilder.setIcon(context.getResources().getDrawable(R.drawable.icon_coin));
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_perform_transaction, null);
		initComponents(view);
		
		alertDialogBuilder.setView(view);
		alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.app_done), new DialogInterface.OnClickListener() 
		{			
			public void onClick(DialogInterface dialog, int which) 
			{	
				if(changeAmount() != null)
				{
					FragmentCreateBudget.textViewAmount.setText(changeAmount() +"");
					Toast.makeText(context, context.getResources().getString(R.string.app_amount_changed), Toast.LENGTH_SHORT).show();
					dialog.cancel();	
				} 
				else 
				{
					Toast.makeText(context, context.getResources().getString(R.string.app_amount_cannot_be_null), Toast.LENGTH_SHORT).show();
				}
			}
		});
		return alertDialogBuilder.create();
	}
	
	public Dialog setDateDialog(final Item item)
	{	
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(context.getResources().getString(R.string.app_changing_date));
		alertDialogBuilder.setIcon(context.getResources().getDrawable(R.drawable.icon_budget_55x55));
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.dialog_date, null);
		initDateComponents(view);
		
		alertDialogBuilder.setView(view);
		alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.app_done), new DialogInterface.OnClickListener() 
		{			
			public void onClick(DialogInterface dialog, int which) 
			{	
				changeDate();
				Toast.makeText(context, context.getResources().getString(R.string.app_date_changed), Toast.LENGTH_SHORT).show();
				dialog.cancel();
			}			
		});
		return alertDialogBuilder.create();
	}

	private void initDateComponents(View view) 
	{
		datePicker = (DatePicker) view.findViewById(R.id.datePicker1);
	}

	public void changeDate() 
	{	
		FragmentCreateBudget.textViewDate.setText(createDate(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth()));
	}
	
	protected Double changeAmount() 
	{
		Double amount = null;

		try {
			amount = Double.valueOf( this.editTextNumberScreen.getText().toString() );
			return amount;
		} catch (Exception e) {
			return null;
		}
	}

	public void onClick(View view) 
	{
		switch (view.getId()) 
		{
		case R.id.Button1:
			this.addNumber(Button1.getText().toString());
			break;
		case R.id.Button2:
			this.addNumber(Button2.getText().toString());
			break;
		case R.id.Button3:
			this.addNumber(Button3.getText().toString());
			break;
		case R.id.Button4:
			this.addNumber(Button4.getText().toString());
			break;
		case R.id.Button5:
			this.addNumber(Button5.getText().toString());
			break;
		case R.id.Button6:
			this.addNumber(Button6.getText().toString());
			break;
		case R.id.Button7:
			this.addNumber(Button7.getText().toString());
			break;
		case R.id.Button8:
			this.addNumber(Button8.getText().toString());
			break;
		case R.id.Button9:
			this.addNumber(Button9.getText().toString());
			break;
		case R.id.ButtonDot:
			this.addNumber(ButtonDot.getText().toString());
			break;
		case R.id.ButtonZero:
			this.addNumber(ButtonZero.getText().toString());
			break;
		case R.id.imageButtonBackspace:
			char [] array = this.editTextNumberScreen.getText().toString().toCharArray();
			int lenght = array.length;
			String cadena = "";
			for(int x = 0; x < lenght - 1; x++){
				cadena = cadena + array[x];
			}
			this.editTextNumberScreen.setText(cadena);
			break;
			/*
		case R.id.ButtonChangeDate:
			this.addNumber(ButtonChangeDate.getText().toString());
			break;
		case R.id.ButtonRepeatExpense:
			this.addNumber(ButtonRepeatExpense.getText().toString());
			break;
			*/
		
		}
		
	}
	
	public String createDate(int year, int month, int day){
		String hyphen = "-";
		return year + hyphen + month + hyphen + day;
	}
	
	public String getCurrentDate()
	{
		Calendar calendar = Calendar.getInstance();
		return createDate(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1), calendar.get(Calendar.DAY_OF_MONTH));  
	}

	
	public String getCurrentTime()
	{
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR)+ ":" + calendar.get(Calendar.MINUTE);
	}
	
	private Object createTransaction(String accountabilityType, String category) 
	{	
		
		Double amount = changeAmount();
		String date =  getCurrentDate();
		
		String time = getCurrentTime();
		String note = null;	//CONFIGURE LATTER
		String regularity = null;	//CONFIGURE LATTER
		
		putTransactionInDashboard(accountabilityType, amount, Settings.TRANSACTION);
		
		return (Transaction) new Expense(0, accountabilityType, category, amount, date, time, note, regularity);
	}
	
	private Object createBudgetTransaction(String accountabilityType, String category) 
	{	
		final Calendar calendar = Calendar.getInstance();
		Double amount = null;
		
		if(changeAmount() != null)
		{
			amount = changeAmount();
			FragmentCreateBudget.textViewAmount.setText(amount + "");
		} 
		else 
		{
			return null;
		}
		
		String date = createDate(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1), calendar.get(Calendar.DAY_OF_MONTH));
		String time = calendar.get(Calendar.HOUR)+ ":" + calendar.get(Calendar.MINUTE);
		String note = null;	//CONFIGURE LATER
		String regularity = null;	//CONFIGURE LATTER
		
		return(Transaction) new Expense(0, accountabilityType, category, amount, date, time, note, regularity);
	}
	
	public static void putTransactionInDashboard(String accountabilityType, Double amount, String transactionType)
	{
		if(transactionType.equalsIgnoreCase(Settings.BUDGET))
		{
			applyAmountChange(accountabilityType, amount, Settings.BUDGET_EXPENSES, Settings.BUDGET_EARNINGS);
		} 
		else if(transactionType.equalsIgnoreCase(Settings.TRANSACTION)) 
		{
			applyAmountChange(accountabilityType, amount, Settings.DASHBOARD_EXPENSES, Settings.DASHBOARD_EARNINGS);
		} else {
			Log.e("CATEGORIA NO PUESTA AUN", ".");
		}
	}

	private static void applyAmountChange(String accountabilityType, Double amount, String expense, String income) 
	{
		if(accountabilityType.equalsIgnoreCase(Options.context.getResources().getString(R.string.accountant_category_expenses)))
		{
			try {
				String previousExpense = Settings.getInstance().getPrefs().getString(expense, "0");
				Double newBalance = Double.valueOf(previousExpense) + amount;
				settings.getPrefsEditor().putString(expense, newBalance.toString());
				settings.getPrefsEditor().commit();
			} catch (Exception e) {
				settings.getPrefsEditor().putString(expense, amount+"");
				settings.getPrefsEditor().commit();
			}
			
		} 
		else if(	accountabilityType.equalsIgnoreCase(Options.context.getResources().getString(R.string.accountant_category_incomes))	)
		{
			String previousEarnings = settings.getPrefs().getString(income, "0");
			Double newBalance = Double.valueOf(previousEarnings) + amount ;
			settings.getPrefsEditor().putString(income, newBalance.toString());
			settings.getPrefsEditor().commit();
		}
	}

	public static void adjustOneTabVisible(LinearLayout targetTab, ArrayList<LinearLayout> otherLayouts)
	{
		if(targetTab.getVisibility() == LinearLayout.VISIBLE){
			targetTab.setVisibility(LinearLayout.GONE);
		} 
		else 
		{
			targetTab.setVisibility(LinearLayout.VISIBLE);
			
			for(int x = 0, y = otherLayouts.size(); x < y; x++)
			{
				otherLayouts.get(x).setVisibility(LinearLayout.GONE);
			}
		}
	}
	
	public void addNumber(String number){
		editTextNumberScreen.setText( editTextNumberScreen.getText().toString() + number );
	}
	
	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	

}
