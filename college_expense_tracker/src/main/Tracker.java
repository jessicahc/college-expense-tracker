package main;

import java.util.ArrayList;

public class Tracker {
	private ArrayList<Expense> expenseLst;
	private FileManager f;
	
	public Tracker(){
		this.f = new FileManager("data.bin");
		this.expenseLst = f.read();
	}
	
	public ArrayList<Expense> getExpenseList(){
		return expenseLst;
	}
	
	public void addExpense(Expense e) {
		expenseLst.add(e);
		this.f.save(expenseLst);
	}
	
	public void deleteExpense(int id) {
		expenseLst.remove(id);
		f.save(expenseLst);
	}
	
	public ArrayList<Expense> filter(String category){
		if (category.equals("All")) {
			return expenseLst;
		}
		
		ArrayList<Expense> filteredLst = new ArrayList<>();
		for (Expense e: expenseLst) {
			if (e.getCategory().equals(category)) {
				filteredLst.add(e);
			}
		}
		
		return filteredLst;
	}
	
	public double calculateTotal(ArrayList<Expense> lst) {
		double total = 0;
		
		for (Expense e: lst) {
			total += e.getAmount();
		}
		return total;
	}
	
	
	
}
