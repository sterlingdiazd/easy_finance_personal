package com.compagerd.easy_finance.personal.fragments;

import java.io.Serializable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.activities.Budgets;
import com.compagerd.easy_finance.personal.activities.CreateBudget;
import com.compagerd.easy_finance.personal.database.DataAccount;
import com.compagerd.easy_finance.personal.database.DataBudget;
import com.compagerd.easy_finance.personal.database.DataExpense;
import com.compagerd.easy_finance.personal.model.Expense;
import com.compagerd.easy_finance.personal.model.Transaction;
import com.compagerd.easy_finance.personal.util.Settings;

public class FragmentBPBudget extends Fragment implements View.OnClickListener
{
	
	private ImageView	imageViewAdd, imageViewDelete, imageViewEdit;
	private Context		context;
	private Settings	settings;
	EditText			editTextNumberScreen;
	Button				Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, ButtonDot, ButtonZero, ButtonChangeDate, ButtonRepeatExpense, ButtonDone;
	ImageButton			imageButtonBackspace;
	
	public FragmentBPBudget()
	{
		super();
	}
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
	{
		
		View view = inflater.inflate( R.layout.fragment_bottom_panel, container, false );
		initComponents( view );
		configureComponent();
		return view;
	}
	
	public View initComponents( View view )
	{
		context = view.getContext();
		settings = Settings.getInstance();
		settings.configureSharePreference( context );
		
		imageViewAdd = ( ImageView ) view.findViewById( R.id.imageViewAdd );
		imageViewDelete = ( ImageView ) view.findViewById( R.id.imageViewDelete );
		imageViewEdit = ( ImageView ) view.findViewById( R.id.imageViewEdit );
		return view;
	}
	
	private void configureComponent()
	{
		imageViewAdd.setOnClickListener( this );
		imageViewDelete.setOnClickListener( this );
		imageViewEdit.setOnClickListener( this );
	}
	
	public void onClick( View v )
	{
		switch ( v.getId() ) {
			case R.id.imageViewAdd:
				settings.getPrefsEditor().putBoolean( Settings.IS_FOR_EDITION, false );
				settings.getPrefsEditor().commit();
				
				startActivityForResult( new Intent( this.context, CreateBudget.class ), 0 );
				break;
			
			case R.id.imageViewDelete:
				
				if( settings.getPrefs().getInt( Settings.SELECTED_BUDGET, 9999 ) != 9999 )
				{
					int id = settings.getPrefs().getInt( Settings.SELECTED_BUDGET, 9999 );
					int result = new DataExpense( context ).deleteExpense( settings.getPrefs().getInt( Settings.SELECTED_BUDGET, 9999 ) );
					int resultBudget = new DataBudget( context ).deleteBudget( settings.getPrefs().getInt( Settings.SELECTED_BUDGET, 9999 ) );
					
					settings.getPrefsEditor().putInt( Settings.SELECTED_BUDGET, 9999 );
					settings.getPrefsEditor().commit();
					
					getActivity().finish();
					startActivity( new Intent( context, Budgets.class ) );
					
				}
				else
				{
					Toast.makeText( context, context.getResources().getString( R.string.app_select_item ), Toast.LENGTH_SHORT ).show();
				}
				
				break;
			
			case R.id.imageViewEdit:
				
				if( settings.getPrefs().getInt( Settings.SELECTED_BUDGET, 9999 ) != 9999 )
				{
					Intent intent = new Intent( this.context, CreateBudget.class );
					Bundle bundle = new Bundle();
					/*
					 * if( FragmentListViewBudget.budgets.size() > 0 ) // BORRAR
					 * // VALIDACION// INNCESARIA {
					 * settings.getPrefsEditor().putBoolean(
					 * Settings.IS_FOR_EDITION, true );
					 * settings.getPrefsEditor().commit();
					 * 
					 * Transaction transaction =
					 * FragmentListViewBudget.budgets.get(
					 * settings.getPrefs().getInt( Settings.SELECTED_BUDGET,
					 * 9999 ) - 1 ); // intent.putExtra("expense", expense); //
					 * Log.e("expense",expense.getAccountingCategory()+"");
					 * bundle.putSerializable( "editExpense", ( Serializable )
					 * transaction ); // this.getActivity().finish();
					 * intent.putExtras( bundle ); startActivity( intent );
					 * 
					 * } else { Log.e( "vacio", "budgets" ); }
					 */
				}
				else
				{
					Toast.makeText( context, this.context.getResources().getString( R.string.app_select_item ), Toast.LENGTH_SHORT ).show();
				}
				
				break;
		}
		
	}
}
