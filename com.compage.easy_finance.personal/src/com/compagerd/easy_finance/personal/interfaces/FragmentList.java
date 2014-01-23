package com.compagerd.easy_finance.personal.interfaces;

import java.util.ArrayList;

import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

public interface FragmentList {

	/*
	public void initComponents(View view);
	public void configureComponents();
	*/
	public ListView fillList(ArrayList<Object> items, BaseAdapter adapter, ListView list);
}
