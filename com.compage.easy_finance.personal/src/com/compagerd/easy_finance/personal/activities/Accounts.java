package com.compagerd.easy_finance.personal.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;
import com.compagerd.easy_finance.personal.fragments.FragmentBPAccounts;
import com.compagerd.easy_finance.personal.fragments.FragmentListViewAccount;
import com.compagerd.easy_finance.personal.interfaces.Activities;

public class Accounts extends FragmentActivity implements Activities {

	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		actionBar.setLogoAction(new ActionBar.IntentAction(this, Dashboard.createIntent(this),  R.drawable.icon_accounts_55x55));
		actionBar.setTitle(R.string.title_activity_accounts);			
	}

	public void configureFragments() 
	{	
		FragmentListViewAccount fragmentListView = new FragmentListViewAccount(this);		
		FragmentBPAccounts fragmentBPAccounts = new FragmentBPAccounts(this);		
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this);		
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_account_bottom_panel, fragmentBPAccounts);
		fragmentTransaction.add(R.id.frame_listview_accounts, fragmentListView);
		fragmentTransaction.add(R.id.frame_ad_account, fragmentAdvertising);
		fragmentTransaction.commit();
	}
}



