package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;


public class MainGUI extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblMainTitle;
	private JComboBox<String> comboBox;
	private JLabel lblTotalTitle;
	private JLabel lblTotalAmount;
	private JButton btnAddExpense;
	private JButton btnDeleteExpense;
	private JScrollPane scrollPane;
	private JTableHeader header;
	private String[] tblColumnNames = {"Date", "Category", "Description", "Amount", "Expense"};

	private DefaultTableModel tableModel;
	private Tracker t;

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMainTitle = new JLabel("College Expense Tracker");
		lblMainTitle.setBounds(225, 16, 159, 16);
		contentPane.add(lblMainTitle);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Food", "Entertainment", "Transportation", "Tuition and Fees", "Housing", "Books/Materials/Electronics", "Other"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filter();
			}
		});
		
		comboBox.setBounds(442, 33, 129, 27);
		contentPane.add(comboBox);
		
		lblTotalTitle = new JLabel("Total:");
		lblTotalTitle.setBounds(432, 220, 41, 16);
		contentPane.add(lblTotalTitle);
		
		lblTotalAmount = new JLabel("");
		lblTotalAmount.setBounds(474, 220, 71, 16);
		contentPane.add(lblTotalAmount);
		
		btnAddExpense = new JButton("Add");
		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show_addGUI();
			}
		});
		btnAddExpense.setBounds(161, 248, 117, 29);
		contentPane.add(btnAddExpense);
		
		btnDeleteExpense = new JButton("Delete");
		btnDeleteExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnDeleteExpense.setBounds(311, 248, 117, 29);
		contentPane.add(btnDeleteExpense);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 60, 544, 152);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setSelectionForeground(Color.LIGHT_GRAY);    
		table.setSelectionBackground(Color.BLUE);      
		
		header = table.getTableHeader();
		header.setBackground(Color.LIGHT_GRAY);
		header.setForeground(Color.BLACK);
		header.setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		tableModel = new DefaultTableModel(tblColumnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(tableModel);
		
		// Hide Expense column that holds Expense objects
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setWidth(0);
		
		t = Tracker.getInstance();
		ArrayList<Expense> expenseLst = t.getExpenseList();
		loadTable(expenseLst);
		
		t.addPropertyChangeListener(this);
	}
	
	void show_addGUI() {
		AddExpenseGUI addGUI = new AddExpenseGUI();
		addGUI.setLocationRelativeTo(this);
	    addGUI.setVisible(true); 
	}
	
	void delete() {
	    int row = table.getSelectedRow();

	    if (row == -1) {
	    	JOptionPane.showMessageDialog(
	                this,
	                "Please select a row to delete.",
	                "No Selection",
	                JOptionPane.INFORMATION_MESSAGE
	        );
	    	return;
	    }

	    // retrieve hidden Expense object
	    Expense e = (Expense) table.getModel().getValueAt(row, 4);  
	    t.deleteExpense(e);
	}
	
	void filter() {
		String category = comboBox.getSelectedItem().toString();
	    ArrayList<Expense> expenses = t.getExpenseList();

	    if ("All".equals(category)) {
	        loadTable(expenses);
	        return;
	    }

	    ArrayList<Expense> filtered = new ArrayList<>();
	    for (Expense e : expenses) {
	        if (category.equals(e.getCategory())) {
	            filtered.add(e);
	        }
	    }
	    loadTable(filtered);
	}

	private void loadTable(ArrayList<Expense> lst) {
	    tableModel.setRowCount(0);

	    for (Expense e : lst) {
	        Object[] row = new Object[] {
	                e.getDate(),
	                e.getCategory(),
	                e.getDescription(),
	                e.getAmount(),
	                e  // store the expense object in the table
	        };
	        tableModel.addRow(row);
	    }

	    double totalAmount = calculateTotal(lst);
	    lblTotalAmount.setText("$" + String.format("%.2f", totalAmount));
	}

	private double calculateTotal(ArrayList<Expense> lst) {
		double total = 0;
		
		for (Expense e: lst) {
			total += e.getAmount();
		}
		
		return total;
	}
	
	@Override
    public void propertyChange(final PropertyChangeEvent evt) {
		String ce = evt.getPropertyName();
		System.out.println("MainGUI.propertyChange: " + ce + " detected");
		if (ce.equals(Tracker.ChangeEvent.EXPENSE_ADDED.toString()) || 
			ce.equals(Tracker.ChangeEvent.EXPENSE_DELETED.toString())) {
			comboBox.setSelectedItem("All");
			filter();  // reload table with current filter applied
			this.setVisible(true);
		}
    }
}
