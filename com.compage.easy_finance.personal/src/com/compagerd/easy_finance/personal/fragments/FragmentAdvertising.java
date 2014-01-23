package com.compagerd.easy_finance.personal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.compagerd.easy_finance.personal.R;

public class FragmentAdvertising extends Fragment {

	private Context context;
	
	public FragmentAdvertising(Context context) 
	{
		super();
		this.context = context;
	}
	
	public FragmentAdvertising() {
		super();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		return inflater.inflate(R.layout.ad_admob, container, false);
	}
	

}