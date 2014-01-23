package com.compagerd.easy_finance.personal.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.compagerd.easy_finance.personal.adapters.AdapterTransaction;
import com.compagerd.easy_finance.personal.database.DataTransaction;
import com.compagerd.easy_finance.personal.model.Account;
import com.compagerd.easy_finance.personal.model.Expense;
import com.compagerd.easy_finance.personal.model.Transaction;
import com.compagerd.easy_finance.personal.util.Settings;
import com.google.ads.v;

public class FragmentListViewBudget extends Fragment implements OnItemClickListener
{
	
	public ListView						list;
	public static DataTransaction		dataTransactions;
	public static AdapterTransaction	adapterTransaction;
	public static int					selectedItem	= -1;
	public ArrayList<Transaction>		budgets;
	private Settings					settings;
	public Context						context;
	private OnListViewListener			mCallback;
	
	public FragmentListViewBudget()
	{
		super();
	}
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
	{
		View view = inflater.inflate( R.layout.fragment_list_view, container, false );
		initComponents( view );
		configureComponents();
		fillListView();
		return view;
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		updateListView();
	}
	
	private void initComponents( View view )
	{
		context = view.getContext();
		list = ( ListView ) view.findViewById( android.R.id.list );
		dataTransactions = new DataTransaction( context );
		settings = Settings.getInstance();
		settings.configureSharePreference( context );
	}
	
	public interface OnListViewListener
	{
		public void onListViewOperation( ArrayList<Transaction> items, BaseAdapter adapter, ListView listView, int position );
	}
	
	@Override
	public void onAttach( Activity activity )
	{
		super.onAttach( activity );
		
		try
		{
			mCallback = ( OnListViewListener ) activity;
		}
		catch ( ClassCastException e )
		{
			throw new ClassCastException( activity.toString() + " OnListViewListener" );
		}
	}
	
	public void configureComponents()
	{
		list.setOnItemClickListener( this );
	}
	
	public boolean fillListView()
	{
		budgets = ( ArrayList<Transaction> ) dataTransactions.getAllTransactions();
		
		if( budgets.size() > 0 )
		{
			adapterTransaction = new AdapterTransaction( context, budgets );
			list.setAdapter( adapterTransaction );
			list.refreshDrawableState();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	 * public ListView fillList( ArrayList<Object> items, BaseAdapter adapter,
	 * ListView list ) {
	 * 
	 * list.setAdapter( adapter ); list.refreshDrawableState(); return list; }
	 */
	public void updateListView()
	{
		if( !fillListView() )
		{
			Toast.makeText( context, context.getResources().getString( R.string.budgets_empty_budget ), Toast.LENGTH_SHORT ).show();
		}
	}
	
	public void onItemClick( AdapterView<?> v, View view, int pos, long arg3 )
	{
		Expense budget = ( Expense ) v.getItemAtPosition( pos );
		Expense expense = ( Expense ) list.getAdapter().getItem( pos );
		mCallback.onListViewOperation( budgets, adapterTransaction, list, pos );
		
		/*
		 * 
		 * settings.getPrefsEditor().putInt( Settings.SELECTED_BUDGET,
		 * budget.getIdTransaction() ); settings.getPrefsEditor().commit();
		 */
	}
	
}
