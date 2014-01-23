package com.compagerd.easy_finance.personal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.database.DataTransaction;

public class TransactionFragment  extends Fragment implements OnClickListener {

	private Context context;
	private DataTransaction dataExpenses;
	
	public TransactionFragment(Context context) {
		super();
		this.context = context;
		dataExpenses = new DataTransaction(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.dialog_perform_transaction, container, false);
	
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public void onClick(View v) {
		
	}
	
}
