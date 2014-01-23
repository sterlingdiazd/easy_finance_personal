package com.compagerd.easy_finance.personal.activities;

import java.io.Serializable;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.GridView;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;
import com.compagerd.easy_finance.personal.fragments.FragmentBPCreateBudget;
import com.compagerd.easy_finance.personal.fragments.FragmentCreateBudget;
import com.compagerd.easy_finance.personal.model.Expense;

public class CreateBudget extends FragmentActivity {

	public static ActionBar actionBar;
	private GridView gridViewcategories;
	private Expense budget;
	private Serializable ser;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_budget);	
		initComponents();
		prepareActionBar();
		configureComponents();
	}


	private void initComponents() 
	{
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		ser = (Serializable) this.getIntent().getSerializableExtra("editExpense");
	}

	private void prepareActionBar() 
	{
		actionBar.setLogoAction(new ActionBar.IntentAction(this, Dashboard.createIntent(this),  R.drawable.icon_add));
		actionBar.setTitle(this.getBaseContext().getResources().getString(R.string.budgets_add_budget));
	}
	
	private void configureComponents() 
	{		
		FragmentCreateBudget fragmentCreateBudget = new FragmentCreateBudget();
		FragmentBPCreateBudget fragmentBPCreateBudget = new FragmentBPCreateBudget(this);
		if(ser != null)
		{
			Bundle bundle = new Bundle();
			bundle.putSerializable("serialBudget", (Serializable) ser);
			fragmentCreateBudget.setArguments(bundle);
			fragmentBPCreateBudget.setArguments(bundle);
		}
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this.getBaseContext());
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_create_budget, fragmentCreateBudget);
		fragmentTransaction.add(R.id.frame_bottom_panel_create_budget, fragmentBPCreateBudget);
		fragmentTransaction.add(R.id.frame_ad_create_budget, fragmentAdvertising);
		fragmentTransaction.commit();
	}
	
}
