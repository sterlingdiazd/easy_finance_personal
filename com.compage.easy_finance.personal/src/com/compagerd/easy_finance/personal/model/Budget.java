package com.compagerd.easy_finance.personal.model;

import java.io.Serializable;

public class Budget implements Serializable {


	private static final long serialVersionUID = 1L;
	int idBudget;
	String nameProdecedenceBudget;
	long idProdecedenceBudget;
	Double unitaryAmountBudget;	
	Double paymentsQuantityBudget;	
	Double totalAmountBudget;
	
	public Budget(int idBudget, String nameProdecedenceBudget,
			long idProdecedenceBudget, Double unitaryAmountBudget,
			Double paymentsQuantityBudget2, Double totalAmountBudget) {
		super();
		this.idBudget = idBudget;
		this.nameProdecedenceBudget = nameProdecedenceBudget;
		this.idProdecedenceBudget = idProdecedenceBudget;
		this.unitaryAmountBudget = unitaryAmountBudget;
		this.paymentsQuantityBudget = paymentsQuantityBudget2;
		this.totalAmountBudget = totalAmountBudget;
	}

	public int getIdBudget() {
		return idBudget;
	}

	public void setIdBudget(int idBudget) {
		this.idBudget = idBudget;
	}

	public String getNameProdecedenceBudget() {
		return nameProdecedenceBudget;
	}

	public void setNameProdecedenceBudget(String nameProdecedenceBudget) {
		this.nameProdecedenceBudget = nameProdecedenceBudget;
	}

	public long getIdProdecedenceBudget() {
		return idProdecedenceBudget;
	}

	public void setIdProdecedenceBudget(long idProdecedenceBudget) {
		this.idProdecedenceBudget = idProdecedenceBudget;
	}

	public Double getUnitaryAmountBudget() {
		return unitaryAmountBudget;
	}

	public void setUnitaryAmountBudget(Double unitaryAmountBudget) {
		this.unitaryAmountBudget = unitaryAmountBudget;
	}

	public Double getPaymentsQuantityBudget() {
		return paymentsQuantityBudget;
	}

	public void setPaymentsQuantityBudget(Double paymentsQuantityBudget) {
		this.paymentsQuantityBudget = paymentsQuantityBudget;
	}

	public Double getTotalAmountBudget() {
		return totalAmountBudget;
	}

	public void setTotalAmountBudget(Double totalAmountBudget) {
		this.totalAmountBudget = totalAmountBudget;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}