package main;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
	private String fileName;
	
	FileManager(String fileName){
		this.fileName = fileName;
	}
	
	public ArrayList<Expense> read(){
		try{
			FileInputStream f = new FileInputStream(this.fileName);
			ObjectInputStream is = new ObjectInputStream(f);
			
			ArrayList<Expense> lst = (ArrayList<Expense>) is.readObject();
			is.close();
			return lst;
			}
		//empty file
		catch(EOFException e) { 
			return new ArrayList<>();
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public void save(ArrayList<Expense> lst){
		try{
			FileOutputStream f = new FileOutputStream(this.fileName);
			ObjectOutputStream os = new ObjectOutputStream(f);
			os.writeObject(lst);
			os.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
