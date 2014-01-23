package com.compagerd.easy_finance.personal.model;

public class Income {

	int idIncome;
	String categoryIncome;
	Double amountIncome;	
	String dateIncome;	
	String timeIncome;
	String noteIncome;
	
	public Income(int idIncome, String categoryIncome, Double amountIncome,
			String dateIncome, String timeIncome, String noteIncome) {
		super();
		this.idIncome = idIncome;
		this.categoryIncome = categoryIncome;
		this.amountIncome = amountIncome;
		this.dateIncome = dateIncome;
		this.timeIncome = timeIncome;
		this.noteIncome = noteIncome;
	}

	public int getIdIncome() {
		return idIncome;
	}

	public void setIdIncome(int idIncome) {
		this.idIncome = idIncome;
	}

	public String getCategoryIncome() {
		return categoryIncome;
	}

	public void setCategoryIncome(String categoryIncome) {
		this.categoryIncome = categoryIncome;
	}

	public Double getAmountIncome() {
		return amountIncome;
	}

	public void setAmountIncome(Double amountIncome) {
		this.amountIncome = amountIncome;
	}

	public String getDateIncome() {
		return dateIncome;
	}

	public void setDateIncome(String dateIncome) {
		this.dateIncome = dateIncome;
	}

	public String getTimeIncome() {
		return timeIncome;
	}

	public void setTimeIncome(String timeIncome) {
		this.timeIncome = timeIncome;
	}

	public String getNoteIncome() {
		return noteIncome;
	}

	public void setNoteIncome(String noteIncome) {
		this.noteIncome = noteIncome;
	}
	
	
	
}
