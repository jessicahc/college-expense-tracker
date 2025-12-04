package main;

import java.io.*;
import javax.swing.DefaultListModel;

public class FileManager {
	private String fileName;
	
	FileManager(String fileName){
		this.fileName = fileName;
	}
	
	public DefaultListModel<Expense> read(){
		DefaultListModel<Expense> lst = new DefaultListModel<>();
		
		try{
			FileInputStream f = new FileInputStream(this.fileName);
			ObjectInputStream is = new ObjectInputStream(f);
			
			while (true) {
				try{
					Expense expense = (Expense) is.readObject();
					lst.addElement(expense);
				}
				catch(Exception e) {
					break;
				}
			}
			is.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return lst;
	}
	
	public void save(DefaultListModel<Expense> lst){
		try{
			FileOutputStream f = new FileOutputStream(this.fileName);
			ObjectOutputStream os = new ObjectOutputStream(f);
			
			for (int i = 0; i < lst.getSize(); i++) {
				Expense expense = lst.getElementAt(i);
				os.writeObject(expense);
			}
			os.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
