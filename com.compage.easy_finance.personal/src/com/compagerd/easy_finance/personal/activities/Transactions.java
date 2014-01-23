package com.compagerd.easy_finance.personal.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.adapters.AdapterGridView;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;
import com.compagerd.easy_finance.personal.util.ActivitiesItems;
import com.compagerd.easy_finance.personal.util.Font;
import com.compagerd.easy_finance.personal.util.Item;
import com.compagerd.easy_finance.personal.util.Options;

public class Transactions extends FragmentActivity implements OnClickListener{

	private ActionBar actionBar;
	private Context context;	
	private LinearLayout titleIncomes, titleExpenses, /*titleBills,*/ tabIncomes, tabExpenses, tabBills;
	private GridView gridViewIncomes, gridViewTransactionCategories, gridViewTransactionBills;
	private TextView textViewTitleIncomes, textViewTitleExpenses, textViewTitleBills;
	private Options options;
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this.getBaseContext());
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_ad_transactions, fragmentAdvertising);
		fragmentTransaction.commit();
		
		initComponents();
		prepareActionBar();		
		configureComponents();
	}

	private void initComponents()
	{
		this.context = Transactions.this;
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		
		titleIncomes = (LinearLayout) findViewById(R.id.titleIncomes);
		titleExpenses = (LinearLayout) findViewById(R.id.titleExpenses);
		//titleBills = (LinearLayout) findViewById(R.id.titleBills);
		tabIncomes = (LinearLayout) findViewById(R.id.tabIncomes);
		tabExpenses = (LinearLayout) findViewById(R.id.tabExpenses);
		tabBills = (LinearLayout) findViewById(R.id.tabBills);
		
		textViewTitleIncomes = Font.useHandelGotic(this.context, (TextView) findViewById(R.id.textViewTitleIncomes));
		textViewTitleExpenses = Font.useHandelGotic(this.context, (TextView) findViewById(R.id.textViewTitleExpenses));
		//textViewTitleBills = Font.useHandelGotic(this.context, (TextView) findViewById(R.id.textViewTitleBills));
		
		gridViewIncomes = (GridView) findViewById(R.id.gridViewIncomes);
		gridViewTransactionCategories = (GridView) findViewById(R.id.gridViewTransactionCategories);
		gridViewTransactionBills = (GridView) findViewById(R.id.gridViewTransactionBills);
		
		options = new Options(context);	
	}
	
	private void configureComponents()
	{
		titleIncomes.setOnClickListener(this);
		titleExpenses.setOnClickListener(this);
		//titleBills.setOnClickListener(this);
		
		tabIncomes.setVisibility(LinearLayout.GONE);
		tabExpenses.setVisibility(LinearLayout.GONE);
		tabBills.setVisibility(LinearLayout.GONE);
		
		gridViewIncomes.setAdapter( 				new AdapterGridView(	this.context, new ActivitiesItems(context).configureIncomesItems()		) 	);
		gridViewTransactionCategories.setAdapter(	new AdapterGridView(	this.context, new ActivitiesItems(context).configureCategoryItems() 	)	);
		gridViewTransactionBills.setAdapter( 		new AdapterGridView(	this.context, new ActivitiesItems(context).configureBillsItems()		) 	);
		
		final Context context = Transactions.this;
		gridViewTransactionCategories.setOnItemClickListener( new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) 
			{
				Item item = (Item) adapter.getItemAtPosition(position);
				options.createTransactionDialog(item, getResources().getString(R.string.accountant_category_expenses)).show();			
			}			
		});	
		
		gridViewIncomes.setOnItemClickListener( new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) 
			{
				Item item = (Item) adapter.getItemAtPosition(position);
				options.createTransactionDialog(item, getResources().getString(R.string.accountant_category_incomes)).show();			
			}			
		});	
	}

	private void prepareActionBar() 
	{
		actionBar.setLogoAction(new ActionBar.IntentAction(this, Dashboard.createIntent(this),  R.drawable.icon_transaction55x55));
		actionBar.setTitle(R.string.title_activity_transactions);	
	}

	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.titleIncomes:
			
			ArrayList<LinearLayout> otherLayoutsThanTabIncomes = new ArrayList<LinearLayout>();
			otherLayoutsThanTabIncomes.add(tabExpenses);
			otherLayoutsThanTabIncomes.add(tabBills);
			Options.adjustOneTabVisible(tabIncomes, otherLayoutsThanTabIncomes);
			break;
			
		case R.id.titleExpenses:
			
			ArrayList<LinearLayout> otherLayoutsThanTabExpenses = new ArrayList<LinearLayout>();
			otherLayoutsThanTabExpenses.add(tabIncomes);
			otherLayoutsThanTabExpenses.add(tabBills);
			Options.adjustOneTabVisible(tabExpenses, otherLayoutsThanTabExpenses);
			break;
			/*
		case R.id.titleBills:
			
			ArrayList<LinearLayout> otherLayoutsThanTabBills = new ArrayList<LinearLayout>();
			otherLayoutsThanTabBills.add(tabExpenses);
			otherLayoutsThanTabBills.add(tabIncomes);
			Options.adjustOneTabVisible(tabBills, otherLayoutsThanTabBills);
			break;
			*/
		}
	}
}
