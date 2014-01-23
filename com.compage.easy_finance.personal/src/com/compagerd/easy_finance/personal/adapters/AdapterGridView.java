package com.compagerd.easy_finance.personal.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.compagerd.easy_finance.personal.R;
import com.compagerd.easy_finance.personal.util.Item;

public class AdapterGridView extends BaseAdapter {

	private Context context;
	private ArrayList<Item> items;
	
	
	public AdapterGridView(Context context, ArrayList<Item> items) {		
		this.context = context;
		this.items = items;		
	}

	public int getCount() {
		return items.size();
	}

	public Object getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int id) {
		return id;
	}

	public View getView(int position, View view, ViewGroup viewGroup) {
		LayoutInflater layoutInflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View gridviewItem = layoutInflater.inflate(R.layout.gridview_item, null);
		
		ImageView imageView = (ImageView) gridviewItem.findViewById(R.id.imageViewIcon);
		TextView textViewIconTitle = (TextView) gridviewItem.findViewById(R.id.textViewIconTitle);
		textViewIconTitle.setText(items.get(position).getItemTitle());
		imageView.setImageResource( items.get(position).getResourceId() );
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		gridviewItem.setLayoutParams(new GridView.LayoutParams(130, 160));
		imageView.setPadding(10, 10, 10, 10);
		return gridviewItem;
	}

	

	
}
