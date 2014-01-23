package com.compagerd.easy_finance.personal.util;

import java.util.ArrayList;

import com.compagerd.easy_finance.personal.R;

import android.content.Context;
import android.util.Log;

public class ActivitiesItems {

	private Context context;

	public ActivitiesItems(Context context) {
		super();
		this.context = context;
	}
	
	public ArrayList<String> configureRegularityItems() 
	{
		ArrayList<String> regularityItems = new ArrayList<String>();
		regularityItems.add(context.getResources().getString(R.string.regularity_never));
		regularityItems.add(context.getResources().getString(R.string.regularity_daily));
		regularityItems.add(context.getResources().getString(R.string.regularity_weekly));
		regularityItems.add(context.getResources().getString(R.string.regularity_biweekly));
		regularityItems.add(context.getResources().getString(R.string.regularity_monthly));
		regularityItems.add(context.getResources().getString(R.string.regularity_trimestral));
		regularityItems.add(context.getResources().getString(R.string.regularity_quarterly));
		regularityItems.add(context.getResources().getString(R.string.regularity_biyearly));
		regularityItems.add(context.getResources().getString(R.string.regularity_yearly));
		return regularityItems;
	}
	public ArrayList<Item> configureTransactionItems() 
	{
		ArrayList<Item> transactionItems = new ArrayList<Item>();
		transactionItems.add(new Item(this.context, R.string.income_type_salary, R.drawable.icon_salary));
		transactionItems.add(new Item(this.context, R.string.income_type_work, R.drawable.icon_freelance));
		
		transactionItems.add(new Item(this.context, R.string.category_bank, R.drawable.icon_bank));
		transactionItems.add(new Item(this.context, R.string.category_bus, R.drawable.icon_bus));
		transactionItems.add(new Item(this.context, R.string.category_car, R.drawable.icon_car));
		transactionItems.add(new Item(this.context, R.string.category_church, R.drawable.icon_church));
		transactionItems.add(new Item(this.context, R.string.category_cloth, R.drawable.icon_clothes));
		transactionItems.add(new Item(this.context, R.string.category_drink, R.drawable.icon_drinks));
		transactionItems.add(new Item(this.context, R.string.category_food, R.drawable.icon_foods));
		transactionItems.add(new Item(this.context, R.string.category_friends, R.drawable.icon_friends));
		transactionItems.add(new Item(this.context, R.string.category_games, R.drawable.icon_games));
		transactionItems.add(new Item(this.context, R.string.category_gifts, R.drawable.icon_gifts));
		transactionItems.add(new Item(this.context, R.string.category_groceries, R.drawable.icon_groceries));
		transactionItems.add(new Item(this.context, R.string.category_home, R.drawable.icon_home));
		transactionItems.add(new Item(this.context, R.string.category_medicine, R.drawable.icon_medicines));
		transactionItems.add(new Item(this.context, R.string.category_relationships, R.drawable.icon_relationships));
		transactionItems.add(new Item(this.context, R.string.category_reparations, R.drawable.icon_reparations));
		transactionItems.add(new Item(this.context, R.string.category_sports, R.drawable.icon_sports));
		transactionItems.add(new Item(this.context, R.string.category_studies, R.drawable.icon_studies));
		transactionItems.add(new Item(this.context, R.string.category_trips, R.drawable.icon_trips));
		return transactionItems;
		
	}
	
	public ArrayList<Item> configureBillsItems() {
		ArrayList<Item> transactionItems = new ArrayList<Item>();
		//Set bills items
		return transactionItems;
		
	}
	
	public ArrayList<Item> configureIncomesItems() {
		ArrayList<Item> incomesItems = new ArrayList<Item>();
		incomesItems.add(new Item(this.context, R.string.income_type_salary, R.drawable.icon_salary));
		incomesItems.add(new Item(this.context, R.string.income_type_work, R.drawable.icon_freelance));
		return incomesItems;
		
	}

	public ArrayList<Item> configureCategoryItems() 
	{	
		ArrayList<Item> categoryItems = new ArrayList<Item>();
		categoryItems.add(new Item(this.context, R.string.category_bank, R.drawable.icon_bank));
		categoryItems.add(new Item(this.context, R.string.category_bus, R.drawable.icon_bus));
		categoryItems.add(new Item(this.context, R.string.category_car, R.drawable.icon_car));
		categoryItems.add(new Item(this.context, R.string.category_church, R.drawable.icon_church));
		categoryItems.add(new Item(this.context, R.string.category_cloth, R.drawable.icon_clothes));
		categoryItems.add(new Item(this.context, R.string.category_drink, R.drawable.icon_drinks));
		categoryItems.add(new Item(this.context, R.string.category_food, R.drawable.icon_foods));
		categoryItems.add(new Item(this.context, R.string.category_friends, R.drawable.icon_friends));
		categoryItems.add(new Item(this.context, R.string.category_games, R.drawable.icon_games));
		categoryItems.add(new Item(this.context, R.string.category_gifts, R.drawable.icon_gifts));
		categoryItems.add(new Item(this.context, R.string.category_groceries, R.drawable.icon_groceries));
		categoryItems.add(new Item(this.context, R.string.category_home, R.drawable.icon_home));
		categoryItems.add(new Item(this.context, R.string.category_medicine, R.drawable.icon_medicines));
		categoryItems.add(new Item(this.context, R.string.category_relationships, R.drawable.icon_relationships));
		categoryItems.add(new Item(this.context, R.string.category_reparations, R.drawable.icon_reparations));
		categoryItems.add(new Item(this.context, R.string.category_sports, R.drawable.icon_sports));
		categoryItems.add(new Item(this.context, R.string.category_studies, R.drawable.icon_studies));
		categoryItems.add(new Item(this.context, R.string.category_trips, R.drawable.icon_trips));
		
		return categoryItems;
	}
		
	public ArrayList<String> configureActivitiesStrings() 
	{
		Log.e("account", context.getResources().getString(R.string.activity_accounts));
		
		ArrayList<String> dashboardItems = new ArrayList<String>();
		dashboardItems.add(context.getResources().getString(R.string.activity_accounts));
		dashboardItems.add(context.getResources().getString(R.string.activity_budget));
		dashboardItems.add(context.getResources().getString(R.string.activity_history));
		dashboardItems.add(context.getResources().getString(R.string.activity_transactions));
		
		return dashboardItems;
	}
	
	public ArrayList<Item> configureDashboardItems() {
		
		ArrayList<Item> dashboardItems = new ArrayList<Item>();
		dashboardItems.add(new Item(this.context, R.string.title_activity_accounts, R.drawable.icon_accounts));
		//dashboardItems.add(new Item(this.context, R.string.title_activity_categories, R.drawable.icon_category));
		//dashboardItems.add(new Item(this.context, R.string.title_activity_settings, R.drawable.icon_settings));
		dashboardItems.add(new Item(this.context, R.string.title_activity_budget, R.drawable.icon_budget));
		//dashboardItems.add(new Item(this.context, R.string.title_activity_reports, R.drawable.icon_reports));
		dashboardItems.add(new Item(this.context, R.string.title_activity_history, R.drawable.icon_history));
		dashboardItems.add(new Item(this.context, R.string.title_activity_transactions, R.drawable.icon_transaction));
		
		return dashboardItems;
	}
	
	
	public Item getItem(ArrayList<Item> itemList, String itemTitle)
	{
		Item lookedItem = null;
		for(int x = 0, y = itemList.size(); x < y; x++)
		{
			if(itemList.get(x).getItemTitle().equalsIgnoreCase(itemTitle)){
				lookedItem = itemList.get(x);
				break;
			}
		}
		return lookedItem;
	}
	
	public String getMonth(int monthNumber){
		ArrayList<String> months = configureMonthList();
		String searchedMonth = "";
		for(int x = 0, y = months.size(); x < y; x++){
			if(x == monthNumber){
				searchedMonth = months.get(x);
				break;
			}
		}
		return searchedMonth;
	}

	private ArrayList<String> configureMonthList() {
		ArrayList<String> months = new ArrayList<String>();
		months.add(this.context.getResources().getString(R.string.month_january));
		months.add(this.context.getResources().getString(R.string.month_february));
		months.add(this.context.getResources().getString(R.string.month_march));
		months.add(this.context.getResources().getString(R.string.month_may));
		months.add(this.context.getResources().getString(R.string.month_june));
		months.add(this.context.getResources().getString(R.string.month_july));
		months.add(this.context.getResources().getString(R.string.month_august));
		months.add(this.context.getResources().getString(R.string.month_september));
		months.add(this.context.getResources().getString(R.string.month_october));
		months.add(this.context.getResources().getString(R.string.month_november));
		months.add(this.context.getResources().getString(R.string.month_december));
		return months;
	}
}
