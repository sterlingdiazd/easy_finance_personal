package com.compagerd.easy_finance.personal.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class Item {

	private Context context;
	private int itemTitle;
	private int resourceId;

	public Item(Context context, int itemTitle, int resourceId) {	
		this.context = context;
		this.itemTitle = itemTitle;
		this.resourceId = resourceId;
	}
	
	public String getItemTitle() {
		return context.getResources().getString(this.itemTitle);
	}

	public void setItemTitle(int itemTitle) {
		this.itemTitle = itemTitle;
	}

	public int getResourceId() {
		return resourceId;
	}
	
	public Drawable getResourceDrawable() {
		return context.getResources().getDrawable(this.resourceId);
	}


	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	
	
	
}
