package com.compagerd.easy_finance.personal.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;
import com.compagerd.easy_finance.personal.fragments.FragmentHistory;

public class History extends FragmentActivity{

	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this.getBaseContext());
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_ad_history, fragmentAdvertising);
		fragmentTransaction.commit();
		
		initComponents();
		prepareActionBar();
		configureComponents();
	}

	public void initComponents() {
		actionBar = (ActionBar) findViewById(R.id.actionbar);		
	}

	public void configureComponents() {
		FragmentHistory fragmentHistory = new FragmentHistory(this.getBaseContext());
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_history, fragmentHistory);
		fragmentTransaction.commit();
	}
	
	public void prepareActionBar() {
		actionBar.setLogoAction(new ActionBar.IntentAction(this, Dashboard.createIntent(this),  R.drawable.icon_history_55x55));
		actionBar.setTitle(R.string.title_activity_history);	
	}

	
}
