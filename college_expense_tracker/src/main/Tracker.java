package main;

import java.util.ArrayList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Tracker {
	public enum ChangeEvent { EXPENSE_ADDED, EXPENSE_DELETED };
	private static Tracker theInstance;
	
	private ArrayList<Expense> expenseLst;
	private FileManager f;
	private PropertyChangeSupport support;
	
	private Tracker(){
		this.f = new FileManager("data.bin");
		this.expenseLst = f.read();
		this.support = new PropertyChangeSupport(this);
	}
	
	public static Tracker getInstance() {
		if (theInstance == null) {
			theInstance = new Tracker();
		} 
		return theInstance;
	}
	
	// Method to add an observer.
    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        this.support.addPropertyChangeListener(pcl);
    }
    
    // Method to remove an observer.
    public void removePropertyChangeListener(final PropertyChangeListener pcl) {
        this.support.removePropertyChangeListener(pcl);
    }
    
	public ArrayList<Expense> getExpenseList(){
		return expenseLst;
	}
	
	public void addExpense(Expense e) {
		expenseLst.add(e);
		this.f.save(expenseLst);
		support.firePropertyChange(ChangeEvent.EXPENSE_ADDED.toString(), null, e);
		
	}
	
	public void deleteExpense(Expense e) {
	    if (expenseLst.remove(e)) { // returns true if removed successfully
	        f.save(expenseLst);
	        support.firePropertyChange(ChangeEvent.EXPENSE_DELETED.toString(), e, null);
	    }
	}
}
