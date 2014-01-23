package com.compagerd.easy_finance.personal.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.activities.Dashboard;
import com.compagerd.easy_finance.personal.adapters.AdapterAccounts;
import com.compagerd.easy_finance.personal.adapters.AdapterTransaction;
import com.compagerd.easy_finance.personal.database.DataExpense;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;
import com.compagerd.easy_finance.personal.fragments.FragmentBPBudget;
import com.compagerd.easy_finance.personal.fragments.FragmentListViewBudget;
import com.compagerd.easy_finance.personal.fragments.FragmentListViewBudget.OnListViewListener;
import com.compagerd.easy_finance.personal.interfaces.Activities;
import com.compagerd.easy_finance.personal.model.Expense;
import com.compagerd.easy_finance.personal.model.Transaction;
import com.compagerd.easy_finance.personal.util.Item;
import com.compagerd.easy_finance.personal.util.Settings;

public class Budgets extends FragmentActivity  implements Activities, OnListViewListener {

	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);		
		initComponents();
		prepareActionBar();
		configureFragments();
	}

	public void initComponents()
	{
		actionBar = (ActionBar) findViewById(R.id.actionbar);		
	}

	public void prepareActionBar() 
	{
		actionBar.setLogoAction(new ActionBar.IntentAction(this, Dashboard.createIntent(this),  R.drawable.icon_budget_55x55));
		actionBar.setTitle(R.string.title_activity_budget);	
	}
	
	public void configureFragments() 
	{
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		FragmentListViewBudget fragmentListViewBudget = new FragmentListViewBudget();
		FragmentBPBudget fragmentBudget = new FragmentBPBudget();
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this.getBaseContext());
		fragmentTransaction.add(R.id.frame_listview_accounts, fragmentListViewBudget);
		fragmentTransaction.add(R.id.frame_account_bottom_panel, fragmentBudget);
		fragmentTransaction.add(R.id.frame_ad_account, fragmentAdvertising);
		//fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	public void onListViewOperation( ArrayList<Transaction> items, BaseAdapter adapter, ListView listView, int position )
	{
		Expense expense = (Expense) listView.getAdapter().getItem( position);
		

		TextView textViewExpenseCategory = ( TextView ) listView.getRootView().findViewById( R.id.textViewExpenseCategory );
		textViewExpenseCategory.setTextColor( getResources().getColor( R.color.font_color_gris_claro ) );
		
		TextView textViewExpenseAmount = ( TextView ) listView.getRootView().findViewById( R.id.textViewExpenseAmount );
		textViewExpenseAmount.setTextColor( getResources().getColor( R.color.font_color_gris_claro ) );
		
	}



}
