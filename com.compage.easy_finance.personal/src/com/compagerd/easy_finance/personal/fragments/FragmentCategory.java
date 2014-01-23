package com.compagerd.easy_finance.personal.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.activities.CreateCategory;

public class FragmentCategory extends Fragment implements OnClickListener {

	private LinearLayout ll_create;
	private Context context;
	
	public FragmentCategory(Context context) {
		super();
		this.context = context;
	}
	
	public FragmentCategory() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 
		View view = inflater.inflate(R.layout.fragment_bottom_panel, container, false);
		ll_create = (LinearLayout) view.findViewById(R.id.ll_create);
		
		configureComponent();
		
		return view;
	}
	
	public void onClick(View view) {

		switch(view.getId()){		
			case R.id.ll_create:
				startActivity(new Intent(this.context, CreateCategory.class));
				break;
		}
		
	}
	
	private void configureComponent() {
		ll_create.setOnClickListener(this);		
	}
	
}