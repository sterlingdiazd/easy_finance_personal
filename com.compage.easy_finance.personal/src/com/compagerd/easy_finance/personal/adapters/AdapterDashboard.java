package com.compagerd.easy_finance.personal.adapters;

import java.util.ArrayList;

import com.compagerd.easy_finance.personal.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterDashboard extends BaseAdapter {

	private Context context;
	public static ArrayList<String> dashboardIconsTitle = new ArrayList<String>();
	public ArrayList<Integer> dashboardIcons = new ArrayList<Integer>();
	
	
	public AdapterDashboard(Context context) {
		
		this.context = context;
		
		//	NAMES
		dashboardIconsTitle.add(this.context.getResources().getString(R.string.title_activity_accounts));
		//dashboardIconsTitle.add("Categories");
		//dashboardIconsTitle.add("Settings");
		dashboardIconsTitle.add(this.context.getResources().getString(R.string.title_activity_budget));
		//dashboardIconsTitle.add("Reports");
		dashboardIconsTitle.add(this.context.getResources().getString(R.string.title_activity_history));
		dashboardIconsTitle.add(this.context.getResources().getString(R.string.title_activity_transactions));
		
		//	ICONS
		dashboardIcons.add(R.drawable.icon_accounts);
		//dashboardIcons.add(R.drawable.icon_category);
		//dashboardIcons.add(R.drawable.icon_settings);
		dashboardIcons.add(R.drawable.icon_budget);
		//dashboardIcons.add(R.drawable.icon_reports);
		dashboardIcons.add(R.drawable.icon_history);
		dashboardIcons.add(R.drawable.icon_transaction);		
		
	}

	public int getCount() {
		return dashboardIcons.size();
	}

	public Object getItem(int position) {
		return dashboardIcons.get(position);
	}

	public long getItemId(int arg0) {
		return 0;
	}

	public View getView(int position, View view, ViewGroup viewGroup) {
		LayoutInflater layoutInflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridviewItem = layoutInflater.inflate(R.layout.gridview_item, null);
		
		ImageView imageView = (ImageView) gridviewItem.findViewById(R.id.imageViewIcon);
		TextView textViewIconTitle = (TextView) gridviewItem.findViewById(R.id.textViewIconTitle);
		textViewIconTitle.setText(dashboardIconsTitle.get(position));
		imageView.setImageResource( dashboardIcons.get(position) );
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		gridviewItem.setLayoutParams(new GridView.LayoutParams(130, 160));
		imageView.setPadding(10, 10, 10, 10);
		return gridviewItem;
	}
	

}
