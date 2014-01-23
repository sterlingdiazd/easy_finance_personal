package com.compagerd.easy_finance.personal.activities;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.fragments.FragmentAdvertising;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateCategory extends FragmentActivity implements OnClickListener  {

	private EditText editTextCategoryName;
	private Button buttonCreateCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_category);
		
		FragmentAdvertising fragmentAdvertising = new FragmentAdvertising(this.getBaseContext());
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.frame_ad_create_category, fragmentAdvertising);
		fragmentTransaction.commit();
		
		editTextCategoryName = (EditText) findViewById(R.id.editTextCategoryName);
		buttonCreateCategory = (Button) findViewById(R.id.buttonCreateCategory);
		buttonCreateCategory.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch(v.getId()){
			case R.id.buttonCreateCategory:
				editTextCategoryName.getText().toString();
				this.finish();
				break;
		}
		
	}

}
