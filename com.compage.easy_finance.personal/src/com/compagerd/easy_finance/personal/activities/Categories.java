package com.compagerd.easy_finance.personal.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.GridView;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.adapters.AdapterAccounts;
import com.compagerd.easy_finance.personal.adapters.AdapterGridView;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;
import com.compagerd.easy_finance.personal.fragments.FragmentCategory;
import com.compagerd.easy_finance.personal.util.ActivitiesItems;

public class Categories extends FragmentActivity {

	private ActionBar actionBar;
	private GridView gridViewcategories;
	private AdapterAccounts adapterAccounts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this.getBaseContext());
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_ad_category, fragmentAdvertising);
		fragmentTransaction.commit();
		
		initComponents();
		prepareActionBar();
		configureComponents();
	}

	public void initComponents() {
		actionBar = (ActionBar) findViewById(R.id.actionbar);
		gridViewcategories = (GridView) findViewById(R.id.gridViewcategories);		
		configureFragment();
		
	}
	
	public void prepareActionBar() {
		actionBar.setLogoAction(new ActionBar.IntentAction(this, Dashboard.createIntent(this),  R.drawable.icon_categories55x55));
		actionBar.setTitle(R.string.title_activity_categories);	
	}

	public void configureComponents() {		
		gridViewcategories.setAdapter(new AdapterGridView( this.getBaseContext(), new ActivitiesItems(this.getBaseContext()).configureCategoryItems() ));
	}

	private void configureFragment() {
		FragmentCategory fragmentCategory = new FragmentCategory(this);
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_category_bottom_panel, fragmentCategory);
		//fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}
}
