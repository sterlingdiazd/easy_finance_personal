package com.compagerd.easy_finance.personal.fragments;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.activities.Accounts;
import com.compagerd.easy_finance.personal.database.DataAccount;
import com.compagerd.easy_finance.personal.model.Account;
import com.compagerd.easy_finance.personal.util.Options;
import com.compagerd.easy_finance.personal.util.Settings;

public class FragmentBPAccounts extends Fragment implements View.OnClickListener {

	private ImageView imageView, imageViewAdd, imageViewDelete, imageViewEdit;
	private Context context;
	private DataAccount accountData;
	private Settings settings;
	private Options options;
	private Spinner spinnerAccountType;
	private EditText editTextAccountName, /*editTextInitialValue,*/ editTextAditionalInfo;
	private Button buttonCreateAccount;
	public static Double initialValue;
	public static EditText editTextInitialValue;
	
	public FragmentBPAccounts(Context context) {
		super();
		this.context = context;
		accountData = new DataAccount(context);
		settings = Settings.getInstance();
		settings.configureSharePreference(context);
	}
	
	public FragmentBPAccounts() {
		super();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{	 
		View view = inflater.inflate(R.layout.fragment_bottom_panel, container, false);
		initComponents(view);
		configureComponent();		
		return view;
	}
	
	private View initComponents(View view) 
	{
		imageViewAdd = (ImageView) view.findViewById(R.id.imageViewAdd);
		imageViewDelete = (ImageView) view.findViewById(R.id.imageViewDelete);
		imageViewEdit = (ImageView) view.findViewById(R.id.imageViewEdit);
		options = new Options(this.context);
		return view;
		
	}

	private void configureComponent() 
	{
		imageViewAdd.setOnClickListener(this);	
		imageViewDelete.setOnClickListener(this);	
		imageViewEdit.setOnClickListener(this);	
	}
	
	public Dialog createDialogAccount()
	{
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(context.getResources().getString(R.string.account_add_account));
		alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.icon_add));
		//alertDialogBuilder.setMessage("¿Do you want to start session automatically?");
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.activity_create_account, null);
		
		editTextInitialValue = (EditText) view.findViewById(R.id.editTextInitialValue);
		editTextInitialValue.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				options.createAmountDialog().show();
			}
		});
		
		initDialogComponents(view);
		configureSpinner();
		
		alertDialogBuilder.setView(view);
		alertDialogBuilder.setPositiveButton(this.context.getResources().getString(R.string.account_add_account), new DialogInterface.OnClickListener() 
		{			
			public void onClick(DialogInterface dialog, int which) 
			{	
				Options options = new Options(context);
				
				if(options.isTextViewEmpty(editTextAccountName) | options.isTextViewEmpty(editTextInitialValue))
				{
					Toast.makeText(context, context.getResources().getString(R.string.account_empty_fields), Toast.LENGTH_SHORT).show();
					
				} else {
					Toast.makeText(context,  context.getResources().getString(R.string.app_saved), Toast.LENGTH_SHORT).show();
					Account account = new Account();
					account.setAccountName( editTextAccountName.getText().toString() ); 
					account.setInitialValue(Double.valueOf(editTextInitialValue.getText().toString()));
					account.setAccountType(spinnerAccountType.getSelectedItem().toString());
					account.setAditionalInfo(editTextAditionalInfo.getText().toString());
					accountData.registerAccount(account);
					dialog.cancel();
					
					FragmentListViewAccount.updateList();
				}
			}
		});
		return alertDialogBuilder.create();
	}

	public Dialog editDialogAccount(final Account account)
	{
		
		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle(getResources().getString(R.string.account_edit_account));
		alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.edit48x48));
		//alertDialogBuilder.setMessage("¿Do you want to start session automatically?");
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.activity_create_account, null);
		initDialogEditComponents(view, account);
		configureSpinner();
		
		alertDialogBuilder.setView(view);
		alertDialogBuilder.setPositiveButton(getResources().getString(R.string.account_edit_account), new DialogInterface.OnClickListener() 
		{			
			public void onClick(DialogInterface dialog, int which) 
			{	
				Options options = new Options(context);
				
				if(options.isTextViewEmpty(editTextAccountName) | options.isTextViewEmpty(editTextInitialValue))
				{
					Toast.makeText(context, context.getResources().getString(R.string.account_empty_fields), Toast.LENGTH_SHORT).show();
					
				} else {					
					account.setAccountName( editTextAccountName.getText().toString() ); 
					account.setInitialValue(Double.valueOf(editTextInitialValue.getText().toString()));
					account.setAccountType(spinnerAccountType.getSelectedItem().toString());
					account.setAditionalInfo(editTextAditionalInfo.getText().toString());
					accountData.registerAccount(account);
					dialog.cancel();
					FragmentListViewAccount.updateList();
				}
			}
		});
		return alertDialogBuilder.create();
	}

	private void initDialogComponents(View view) 
	{		
		editTextAccountName = (EditText) view.findViewById(R.id.editTextAccountName);
		editTextInitialValue = (EditText) view.findViewById(R.id.editTextInitialValue);
		editTextAditionalInfo = (EditText) view.findViewById(R.id.editTextAditionalInfo);
		spinnerAccountType = (Spinner) view.findViewById(R.id.spinnerAccountType);	
	}
	
	private void initDialogEditComponents(View view, Account account) 
	{		
		editTextAccountName = (EditText) view.findViewById(R.id.editTextAccountName);
		editTextInitialValue = (EditText) view.findViewById(R.id.editTextInitialValue);
		editTextAditionalInfo = (EditText) view.findViewById(R.id.editTextAditionalInfo);
		spinnerAccountType = (Spinner) view.findViewById(R.id.spinnerAccountType);		
		
		editTextAccountName.setText(account.getAccountName());
		editTextInitialValue.setText(account.getInitialValue()+"");
		editTextAditionalInfo.setText(account.getAditionalInfo());
		spinnerAccountType.setSelection(0);
	}
	
	private void configureSpinner() 
	{
		ArrayList<String> accountTypes = new ArrayList<String>();
		accountTypes.add(this.getResources().getString(R.string.account_type_cash));
		accountTypes.add(this.getResources().getString(R.string.account_type_card));				
		ArrayAdapter<String> arrayAdapterAccountTypes = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_dropdown_item, accountTypes);
		spinnerAccountType.setAdapter(arrayAdapterAccountTypes);		
	}

	public void onClick(View v) {
		
		switch(v.getId()){
			
			case R.id.imageViewAdd:
				createDialogAccount().show();
				break;
				
			case R.id.imageViewDelete:
				if(settings.getPrefs().getInt(Settings.ACCOUNT_SELECTED, 9999) != 9999)
				{	
					int ret = accountData.deleteAccount(settings.getPrefs().getInt(Settings.ACCOUNT_SELECTED, 9999));
					
					settings.getPrefsEditor().putInt(Settings.ACCOUNT_SELECTED, 9999);
					settings.getPrefsEditor().commit();
					
					this.getActivity().finish();
					startActivity(new Intent(this.context, Accounts.class));
					
				} else{
					Toast.makeText(context, context.getResources().getString(R.string.app_select_item), Toast.LENGTH_SHORT).show();
				}
				break;
				
			case R.id.imageViewEdit:	
				
				if(settings.getPrefs().getInt(Settings.ACCOUNT_SELECTED, 9999) != 9999){
					
					editDialogAccount(Settings.getInstance().getAccount()).show();
					
					settings.getPrefsEditor().putInt(Settings.ACCOUNT_SELECTED, 9999);
					settings.getPrefsEditor().commit();
								
					
					//startActivity(new Intent(this.context, EditAccount.class));
					
				} else {
					Toast.makeText(context, context.getResources().getString(R.string.app_select_item), Toast.LENGTH_SHORT).show();
				}
				
				break;
		}
		
	}

}
