
package com.compagerd.easy_finance.personal.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.model.Account;

public class AdapterAccounts extends BaseAdapter {

	private Context context;
	private List<Account> accounts;

	public AdapterAccounts(Context context, List<Account> accounts) {
		super();
		this.context = context;
		this.accounts = accounts;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View adapter_accounts = layoutInflater.inflate(
				R.layout.adapter_expense, null);

		ImageView imageViewExpenseAccountantCategory = (ImageView) adapter_accounts
				.findViewById(R.id.imageViewExpenseAccountantCategory);
		TextView textViewAccountName = (TextView) adapter_accounts
				.findViewById(R.id.textViewExpenseCategory);
		TextView textViewInitialValue = (TextView) adapter_accounts
				.findViewById(R.id.textViewExpenseAmount);
		textViewInitialValue.setTextColor(this.context.getResources().getColor(
				R.color.font_color_green));
		TextView textViewAditionalInfo = (TextView) adapter_accounts
				.findViewById(R.id.textViewExpenseRegularity);
		TextView textViewAccountType = (TextView) adapter_accounts
				.findViewById(R.id.textViewExpenseDate);

		Account account = accounts.get(position);

		if (account.getAccountType().equalsIgnoreCase(
				context.getResources().getString(R.string.account_type_card))) {
			imageViewExpenseAccountantCategory
					.setImageResource(R.drawable.icon_card);
		} else {
			imageViewExpenseAccountantCategory
					.setImageResource(R.drawable.icon_cash);
		}
		textViewAccountName.setText(account.getAccountName());
		textViewInitialValue.setText(String.valueOf(account.getInitialValue()));
		textViewAccountType.setText(account.getAccountType());
		textViewAditionalInfo.setText(account.getAditionalInfo());

		return adapter_accounts;
	}

	public AdapterAccounts(Context context) {
		super();
		this.context = context;

	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public int getCount() {
		return accounts.size();
	}

	public Object getItem(int position) {
		return accounts.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

}
