package com.compagerd.easy_finance.personal.fragments;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.adapters.AdapterAccounts;
import com.compagerd.easy_finance.personal.database.DataAccount;
import com.compagerd.easy_finance.personal.interfaces.FragmentList;
import com.compagerd.easy_finance.personal.model.Account;
import com.compagerd.easy_finance.personal.util.ListManager;
import com.compagerd.easy_finance.personal.util.Settings;

public class FragmentListViewAccount extends Fragment implements OnItemClickListener {

	public static ListView list;
	private static DataAccount accountData;
	private static AdapterAccounts adapterAccounts;
	public static int selectedItem = -1;
	private static List<Account> accounts;	
	public static Account account;
	private Settings settings;
	public static Context context;
	private static ListManager listManager;
	
	public FragmentListViewAccount(Context context) 
	{
		super();
		FragmentListViewAccount.context = context;
		listManager = new ListManager();
	}
	
	public FragmentListViewAccount() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{		
		View view = inflater.inflate(R.layout.fragment_list_view, container, false);
		initComponents(view);
		configureComponents();
		
		ArrayList<Object> objects = new ArrayList<Object>();
		for(Account account : accountData.getAllAccounts() )
		{
			objects.add((Object) account);
		}
		
		AdapterAccounts adapterAccounts =  new AdapterAccounts(context, accountData.getAllAccounts());
		list = listManager.fillList(objects, (BaseAdapter) adapterAccounts, list);
		
		return view;
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();

		ArrayList<Object> objects = new ArrayList<Object>();
		for(Account account : accountData.getAllAccounts() )
		{
			objects.add((Object) account);
		}
		
		AdapterAccounts adapterAccounts =  new AdapterAccounts(context, accountData.getAllAccounts());
		list = listManager.fillList(objects, (BaseAdapter) adapterAccounts, list);
		
	}

	public void initComponents(View view) 
	{
		list = (ListView) view.findViewById(android.R.id.list);			
		accountData = new DataAccount(context);
		settings = Settings.getInstance();
		settings.configureSharePreference(context);
	}
	
	public void configureComponents() {
		list.setOnItemClickListener(this);
	}
	
	/*
	public static boolean fillList() 
	{
		accounts = accountData.getAllAccounts();	
		if(accounts.size() > 0)
		{
			adapterAccounts = new AdapterAccounts(context, accounts);
			list.setAdapter(adapterAccounts);	
			list.refreshDrawableState();
			return true;
		} 
		else 
		{
			return false;
		}	
	}
	*/
	
	public static void updateList() 
	{
		if(!(accountData.getAllAccounts().size() > 0))
		{
			Toast.makeText(context, "Empty accounts", Toast.LENGTH_SHORT).show();
			TextView view = new TextView(context);
			view.setText("No account yet");
			view.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.background_new));
			list.setEmptyView(view);
		} 
		else 
		{
			ArrayList<Object> objects = new ArrayList<Object>();
			for(Account account : accountData.getAllAccounts() )
			{
				objects.add((Object) account);
			}
			
			AdapterAccounts adapterAccounts =  new AdapterAccounts(context, accountData.getAllAccounts());
			list = listManager.fillList(objects, (BaseAdapter) adapterAccounts, list);
		}
	}
	
	public void onItemClick(AdapterView<?> v, View view, int pos, long arg3) 
	{	
		account = (Account) v.getItemAtPosition(pos);
				
		TextView textViewAccountName = (TextView) view.findViewById(R.id.textViewExpenseCategory);
		textViewAccountName.setTextColor(getResources().getColor(R.color.font_color_gris_claro));	
		TextView textViewInitialValue = (TextView) view.findViewById(R.id.textViewExpenseAmount);
		textViewInitialValue.setTextColor(getResources().getColor(R.color.font_color_gris_claro));	
		
		settings.setAccount(null);
		settings.setAccount(account);
		
		settings.getPrefsEditor().putInt(Settings.ACCOUNT_SELECTED, account.getIdAccount());		
		settings.getPrefsEditor().commit();
	}

	




}