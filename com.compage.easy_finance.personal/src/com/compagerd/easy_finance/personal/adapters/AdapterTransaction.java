package com.compagerd.easy_finance.personal.adapters;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.model.Transaction;

public class AdapterTransaction extends BaseAdapter {

	private Context context;
	private List<Transaction> transactions;
	
	public AdapterTransaction(Context context, ArrayList<Transaction> budgets) 
	{
		super();
		this.context = context;
		this.transactions = budgets;
	}

	public View getView(int position, View convertView, ViewGroup parent) 
	{	
		LayoutInflater layoutInflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View adapter_expenses = layoutInflater.inflate(R.layout.adapter_expense, null);
		
		ImageView imageViewExpenseAccountantCategory = (ImageView) adapter_expenses.findViewById(R.id.imageViewExpenseAccountantCategory);
		TextView textViewExpenseCategory = (TextView) adapter_expenses.findViewById(R.id.textViewExpenseCategory);
		TextView textViewExpenseAmount = (TextView) adapter_expenses.findViewById(R.id.textViewExpenseAmount);
		TextView textViewExpenseRegularity = (TextView) adapter_expenses.findViewById(R.id.textViewExpenseRegularity);
		TextView textViewExpenseDate = (TextView) adapter_expenses.findViewById(R.id.textViewExpenseDate);
		
		Transaction expense = transactions.get(position);
		
		if(expense.getAccountingCategory().equalsIgnoreCase(context.getResources().getString(R.string.accountant_category_expenses))){
			imageViewExpenseAccountantCategory.setImageResource(R.drawable.icon_expenses);
			textViewExpenseAmount.setTextColor(context.getResources().getColor(R.color.font_color_orange));
		} else {
			imageViewExpenseAccountantCategory.setImageResource(R.drawable.icon_incomes);
			textViewExpenseAmount.setTextColor(context.getResources().getColor(R.color.font_color_green));
		}
		textViewExpenseCategory.setText(expense.getCategoryTransaction());
		textViewExpenseAmount.setText(String.valueOf( expense.getAmountTransaction() ));
		textViewExpenseRegularity.setText(expense.getRegularityTransaction());
		textViewExpenseDate.setText(expense.getDateTransaction());
		
		return adapter_expenses;
	}
	
	public int getCount() {
		return transactions.size();
	}

	public Object getItem(int position) {
		return transactions.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

}
