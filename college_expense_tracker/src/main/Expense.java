package main;

import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable{
	private LocalDate date;
	private String category;
	private String description;
	private double amount;
	
	public Expense(java.time.LocalDate date, String category, double amount, String description) {
		this.date = date;
		this.category = category;
		this.amount = amount;
		this.description = description;
	}
	
	public java.time.LocalDate getDate() {
		return date;
	}
	
	public String getCategory() {
		return category;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public String getDescription() {
		return description;
	}
	
}
