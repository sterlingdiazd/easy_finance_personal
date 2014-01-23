package com.compagerd.easy_finance.personal.util;

import java.util.ArrayList;

import com.compagerd.easy_finance.personal.adapters.AdapterAccounts;
import com.compagerd.easy_finance.personal.interfaces.FragmentList;
import com.compagerd.easy_finance.personal.model.Account;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class ListManager implements FragmentList {

	private ArrayList<Object> items;
	private SQLiteOpenHelper databaseHandler;
	
	public ListView fillList(ArrayList<Object> items, BaseAdapter adapter, ListView list) {
		
		list.setAdapter(adapter);
		list.refreshDrawableState();
		return list;
	}
	
}
