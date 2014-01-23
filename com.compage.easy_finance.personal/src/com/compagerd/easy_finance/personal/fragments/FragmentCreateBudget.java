package com.compagerd.easy_finance.personal.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbar.general.ActionBar;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.activities.CreateBudget;
import com.compagerd.easy_finance.personal.activities.Dashboard;
import com.compagerd.easy_finance.personal.adapters.AdapterGridView;
import com.compagerd.easy_finance.personal.model.Expense;
import com.compagerd.easy_finance.personal.model.Transaction;
import com.compagerd.easy_finance.personal.util.ActivitiesItems;
import com.compagerd.easy_finance.personal.util.Font;
import com.compagerd.easy_finance.personal.util.Item;
import com.compagerd.easy_finance.personal.util.Options;
import com.compagerd.easy_finance.personal.util.Settings;

public class FragmentCreateBudget extends Fragment implements OnClickListener, OnCheckedChangeListener
{
	
	public static TabHost				tabs;
	private TabSpec						spec;
	private LinearLayout				titleCategories, titleAmount, titleDate, titleRegularity;
	private GridView					gridViewTransactionCategories;
	public static LinearLayout			tabCategories		= null;
	public static Context				context;
	public static Transaction			transaction;
	public static TextView				textViewTitleChooseCategories, textViewAmount, textViewDate, textViewRegularity;
	public static ImageView				imageViewCategory;
	public static ArrayList<Item>		items;
	public static String				stringExpense;
	public static String				stringIncomes;
	public static ActivitiesItems		activitiesItems;
	private Settings					settings;
	private static AlertDialog.Builder	alertDialogBuilder;
	
	private View						viewExpenses;
	private TextView					titleIncomes, titleExpenses;
	
	public static String				selectedTab			= null;
	public static boolean				categorySelected	= false;
	public static boolean				amountSetted		= false;
	public static boolean				dateSetted			= false;
	public static boolean				regularitySetted	= false;
	
	private Options						options;
	private Item						selectedItem		= null;
	private Expense						budget;
	
	public FragmentCreateBudget()
	{
		super();
		options = new Options( context );
	}
	
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
	{
		View view = inflater.inflate( R.layout.fragment_create_budget, container, false );
		
		context = container.getContext();
		view = initComponents( view );
		configureTabHost();		
		dealWithEditAdd();
		configureGridView( activitiesItems.configureCategoryItems(), stringIncomes );
		configureTabChanges();
		configureComponents();
		return view;
	}
	
	public void dealWithEditAdd()
	{
		try
		{
			if( settings.getPrefs().getBoolean( Settings.IS_FOR_EDITION, false ) )
			{
				Bundle bundle = this.getArguments();
				if( ( Expense ) bundle.getSerializable( "serialBudget" ) != null )
				{
					budget = ( Expense ) bundle.getSerializable( "serialBudget" );
					CreateBudget.actionBar.setTitle( getResources().getString( R.string.budgets_editing_budget ) );
					CreateBudget.actionBar.setLogoAction( new ActionBar.IntentAction( context, Dashboard.createIntent( context ), R.drawable.editar64x64 ) );
					ActivitiesItems iconos = new ActivitiesItems( context );
					
					this.selectedItem = ( Item ) iconos.getItem( iconos.configureTransactionItems(), budget.getCategoryTransaction() );
					
					if( budget.getAccountingCategory().equalsIgnoreCase( stringExpense ) )
					{
						titleIncomes.setTextColor( getResources().getColor( R.color.font_color_gris_666 ) );
						titleExpenses.setTextColor( getResources().getColor( R.color.font_color_orange ) );
						configureGridView( iconos.configureCategoryItems(), stringExpense );
						tabs.setCurrentTab( 0 );
					}
					else
					{
						titleExpenses.setTextColor( getResources().getColor( R.color.font_color_gris_666 ) );
						titleIncomes.setTextColor( getResources().getColor( R.color.font_color_green ) );
						configureGridView( iconos.configureCategoryItems(), stringIncomes );
						tabs.setCurrentTab( 1 );
					}
					
					textViewTitleChooseCategories.setText( budget.getCategoryTransaction() );
					textViewAmount.setText( budget.getAmountTransaction() + "" );
					textViewDate.setText( budget.getDateTransaction() );
					textViewRegularity.setText( budget.getRegularityTransaction() );
					imageViewCategory.setImageResource( selectedItem.getResourceId() );
					
					// transaction = new Transaction(budget.getIdExpense()+1,
					// imageViewCategory.getTag().toString(),
					// budget.getCategoryExpense(), budget.getAmountExpense(),
					// budget.getDateExpense(), budget.getTimeExpense(),
					// budget.getNoteExpense());
					
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	public void configureComponents()
	{
		titleCategories.setOnClickListener( this );
		titleAmount.setOnClickListener( this );
		titleDate.setOnClickListener( this );
		titleRegularity.setOnClickListener( this );
		
	}
	
	private View initComponents( View view )
	{
		settings = Settings.getInstance();
		settings.configureSharePreference( context );
		
		tabs = ( TabHost ) view.findViewById( android.R.id.tabhost );
		tabs.setBackgroundResource( R.drawable.adapter_background_list_item );
		
		stringExpense = context.getResources().getString( R.string.accountant_category_expenses );
		stringIncomes = context.getResources().getString( R.string.accountant_category_incomes );
		
		activitiesItems = new ActivitiesItems( context );
		
		gridViewTransactionCategories = ( GridView ) view.findViewById( R.id.gridViewTransactionCategories );
		items = activitiesItems.configureCategoryItems();
		
		textViewTitleChooseCategories = Font.useHandelGotic( context, ( TextView ) view.findViewById( R.id.textViewTitleChooseCategories ) );
		textViewAmount = Font.useHandelGotic( context, ( TextView ) view.findViewById( R.id.textViewAmount ) );
		textViewDate = Font.useHandelGotic( context, ( TextView ) view.findViewById( R.id.textViewDate ) );
		textViewRegularity = Font.useHandelGotic( context, ( TextView ) view.findViewById( R.id.textViewRepeat ) );
		
		imageViewCategory = ( ImageView ) view.findViewById( R.id.imageViewCategory );
		
		titleCategories = ( LinearLayout ) view.findViewById( R.id.titleCategories );
		tabCategories = ( LinearLayout ) view.findViewById( R.id.tabCategories );
		
		titleAmount = ( LinearLayout ) view.findViewById( R.id.titleAmount );
		titleDate = ( LinearLayout ) view.findViewById( R.id.titleDate );
		titleRegularity = ( LinearLayout ) view.findViewById( R.id.titleRegularity );
		
		return view;
	}
	
	private void configureTabHost()
	{
		tabs.setup();
		
		tabs.setBackgroundDrawable( this.getResources().getDrawable( R.drawable.transparent_background ) );
		spec = tabs.newTabSpec( stringExpense );
		spec.setContent( R.id.tab1 );
		
		LayoutInflater inflaterExpenses = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		viewExpenses = inflaterExpenses.inflate( R.layout.view_tabhost, null );
		titleExpenses = ( TextView ) viewExpenses.findViewById( R.id.textViewTitleTab );
		titleExpenses.setText( stringExpense );
		
		titleExpenses.setTextColor( getResources().getColor( R.color.font_color_orange ) );
		FrameLayout frameExpenses = ( FrameLayout ) viewExpenses.findViewById( R.id.frame_image_tabhost );
		frameExpenses.setBackgroundDrawable( getResources().getDrawable( R.drawable.selector_icon_expenses ) );
		viewExpenses.setOnClickListener( this );
		spec.setIndicator( viewExpenses );
		
		tabs.addTab( spec );
		
		spec = tabs.newTabSpec( stringIncomes );
		spec.setContent( R.id.tab1 );
		
		LayoutInflater inflaterIncomes = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View viewIncomes = inflaterIncomes.inflate( R.layout.view_tabhost, null );
		titleIncomes = ( TextView ) viewIncomes.findViewById( R.id.textViewTitleTab );
		titleIncomes.setText( stringIncomes );
		titleIncomes.setTextColor( getResources().getColor( R.color.font_color_gris_666 ) );
		FrameLayout frameIncomes = ( FrameLayout ) viewIncomes.findViewById( R.id.frame_image_tabhost );
		frameIncomes.setBackgroundDrawable( getResources().getDrawable( R.drawable.selector_icon_incomes ) );
		
		spec.setIndicator( viewIncomes );
		tabs.addTab( spec );
		
		tabs.setCurrentTab( 0 );
		FragmentCreateBudget.selectedTab = tabs.getCurrentTabTag();
	}
	
	private void configureGridView( ArrayList<Item> items, final String accountantCategory )
	{
		gridViewTransactionCategories.setAdapter( new AdapterGridView( context, items ) );
		gridViewTransactionCategories.setOnItemClickListener( new OnItemClickListener()
		{
			public void onItemClick( AdapterView<?> adapter, View view, int position, long arg3 )
			{
				tabCategories.setVisibility( LinearLayout.GONE );
				selectedItem = ( Item ) adapter.getItemAtPosition( position );
				options.createBudgetDialog( selectedItem, accountantCategory ).show();
			}
		} );
	}
	
	private void configureTabChanges()
	{
		tabs.setOnTabChangedListener( new OnTabChangeListener()
		{
			public void onTabChanged( String tabId )
			{
				selectedTab = tabId;
				if( tabId.equalsIgnoreCase( stringExpense ) )
				{
					
					titleIncomes.setTextColor( getResources().getColor( R.color.font_color_gris_666 ) );
					titleExpenses.setTextColor( getResources().getColor( R.color.font_color_orange ) );
					configureGridView( activitiesItems.configureCategoryItems(), stringExpense );
				}
				else if( tabId.equalsIgnoreCase( stringIncomes ) )
				{
					titleExpenses.setTextColor( getResources().getColor( R.color.font_color_gris_666 ) );
					titleIncomes.setTextColor( getResources().getColor( R.color.font_color_green ) );
					configureGridView( activitiesItems.configureIncomesItems(), stringIncomes );
				}
			}
		} );
	}
	
	public void onClick( View v )
	{
		switch ( v.getId() ) {
			case R.id.titleCategories:
				Options.adjustOneTabVisible( FragmentCreateBudget.tabCategories, new ArrayList<LinearLayout>() );
				break;
			
			case R.id.titleAmount:
				
				if( selectedItem == null )
				{
					Toast.makeText( context, context.getResources().getString( R.string.app_select_item ), Toast.LENGTH_SHORT ).show();
				}
				else
				{
					options.setAmountDialog( selectedItem, FragmentCreateBudget.selectedTab ).show();
				}
				break;
			
			case R.id.titleDate:
				
				if( selectedItem == null )
				{
					Toast.makeText( context, context.getResources().getString( R.string.app_select_item ), Toast.LENGTH_SHORT ).show();
				}
				else
				{
					options.setDateDialog( selectedItem ).show();
				}
				break;
			
			case R.id.titleRegularity:
				dialogSelectRegularity().show();
				break;
		
		}
	}
	
	private Dialog dialogSelectRegularity()
	{
		alertDialogBuilder = new AlertDialog.Builder( context );
		alertDialogBuilder.setTitle( context.getResources().getString( R.string.budgets_select_regularity ) );
		alertDialogBuilder.setIcon( context.getResources().getDrawable( R.drawable.repeat ) );
		
		LayoutInflater inflater = LayoutInflater.from( context );
		View view = inflater.inflate( R.layout.dialog_select_regularity, null );
		RadioGroup radioGroupRegularity = ( RadioGroup ) view.findViewById( R.id.radioGroupRegularity );
		radioGroupRegularity.setOnCheckedChangeListener( this );
		
		alertDialogBuilder.setView( view );
		alertDialogBuilder.setPositiveButton( context.getResources().getString( R.string.app_done ), new DialogInterface.OnClickListener()
		{
			public void onClick( DialogInterface dialog, int which )
			{
				dialog.cancel();
			}
		} );
		
		return alertDialogBuilder.create();
	}
	
	public void onCheckedChanged( RadioGroup group, int checkedId )
	{
		RadioButton button;
		button = ( RadioButton ) group.findViewById( checkedId );
		
		switch ( checkedId ) {
		
			default:
				textViewRegularity.setText( button.getText().toString() );
				break;
		
		}
		
	}
	
}
