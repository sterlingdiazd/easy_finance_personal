package com.compagerd.easy_finance.personal.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.database.DataTransaction;
import com.compagerd.easy_finance.personal.model.Transaction;
import com.compagerd.easy_finance.personal.util.ActivitiesItems;
import com.compagerd.easy_finance.personal.util.Font;
import com.compagerd.easy_finance.personal.util.Item;

public class FragmentHistory extends Fragment implements OnClickListener {

	private Context context;
	
	public FragmentHistory(Context context) {
		super();
		this.context = context;
	}
	
	public FragmentHistory() {
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentHistory = inflater.inflate(R.layout.fragment_history, container, false);

		DataTransaction dataTransaction = new DataTransaction(context);
		List<Transaction> allDatesTransactions = dataTransaction.getAllDates();
		
		LinearLayout mainLayout = (LinearLayout) fragmentHistory.findViewById(R.id.main);	
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		
		LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//	Group transactions by date
		for(int x = 0, y = allDatesTransactions.size(); x < y; x++)
		{
			Transaction transactionDate = allDatesTransactions.get(x);
			
			LinearLayout linearLayoutDate = new LinearLayout(context);
			linearLayoutDate.setOrientation(LinearLayout.VERTICAL);
			
			View view = layoutInflater.inflate(R.layout.layout_title, null);	
			TextView text = Font.useHandelGotic(this.context, (TextView) view.findViewById(R.id.textViewTitleDate));
			text.setText(transactionDate.getDateTransaction());	
			
			linearLayoutDate.addView(view);
			
			ArrayList<Transaction> transactionsByDate = (ArrayList<Transaction>) dataTransaction.getTransactionsByDate(transactionDate.getDateTransaction());
			
			for(int m = 0, n = transactionsByDate.size(); m < n; m++)
			{
				Transaction tran = transactionsByDate.get(m);
				
				View adapter_transaction = layoutInflater.inflate(R.layout.adapter_transaction, null);	
				
				LinearLayout linear = new LinearLayout(context);
				linear.setOrientation(LinearLayout.HORIZONTAL);
				
				linear.setBackgroundResource(R.drawable.adapter_background_list_item);
				linear.setPadding(10, 10, 10, 10);
				
				//	imageViewAdapterCategory
				ImageView imageViewAdapterCategory = (ImageView) adapter_transaction.findViewById(R.id.imageViewAdapterCategory);
				ActivitiesItems activitiesItems = new ActivitiesItems(this.context);
				ArrayList<Item> items = activitiesItems.configureTransactionItems();
				Item item = activitiesItems.getItem(items, tran.getCategoryTransaction());
				imageViewAdapterCategory.setImageResource(item.getResourceId());
				(	(ViewGroup)	imageViewAdapterCategory.getParent()	).removeView(imageViewAdapterCategory);
				linear.addView(imageViewAdapterCategory);
				
				//	textViewAdapterCategory
				TextView textViewAdapterCategory = (TextView) adapter_transaction.findViewById(R.id.textViewAdapterCategory);
				String optionalTitle;				
				if(tran.getNoteTransaction() != null)
				{
					optionalTitle = tran.getNoteTransaction();
				} 
				else 
				{
					optionalTitle = tran.getCategoryTransaction();
				}
				textViewAdapterCategory.setText(optionalTitle);
				(	(ViewGroup)	textViewAdapterCategory.getParent()	).removeView(textViewAdapterCategory);
				linear.addView(textViewAdapterCategory);
				
				//	textViewAdapterAmount
				TextView textViewAdapterAmount = (TextView) adapter_transaction.findViewById(R.id.textViewAdapterAmount);
				if(tran.getAccountingCategory().equalsIgnoreCase(this.context.getResources().getString(R.string.accountant_category_incomes)))
				{
					textViewAdapterAmount.setTextColor(this.context.getResources().getColor(R.color.font_color_green));
				}
				
				textViewAdapterAmount.setText(	String.valueOf(tran.getAmountTransaction())	);
				(	(ViewGroup)	textViewAdapterAmount.getParent()	).removeView(textViewAdapterAmount);
				linear.addView(textViewAdapterAmount);
				
				
				linearLayoutDate.addView(linear);
				
			}
			
			mainLayout.addView(linearLayoutDate);
		}		
		return fragmentHistory;
	}

	public void onClick(View v)
	{	
	}

}
