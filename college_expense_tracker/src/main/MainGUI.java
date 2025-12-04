package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lblMainTitle;
	private JComboBox comboBox;
	private JLabel lblTotalTitle;
	private JLabel lblTotalAmount;
	private JButton btnAddExpense;
	private JButton btnDeleteExpense;
	private JScrollPane scrollPane;
	private JTableHeader header;
	private String[] tblColumnNames = {"Date", "Category", "Description", "Amount"};
	private DefaultTableModel tableModel;

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMainTitle = new JLabel("College Expense Tracker");
		lblMainTitle.setBounds(166, 6, 159, 16);
		contentPane.add(lblMainTitle);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All", "Food", "Entertainment", "Transportation", "Tuition and Fees", "Housing", "Books/Materials/Electronics"}));
		comboBox.setBounds(328, 30, 129, 27);
		contentPane.add(comboBox);
		
		lblTotalTitle = new JLabel("Total:");
		lblTotalTitle.setBounds(343, 220, 41, 16);
		contentPane.add(lblTotalTitle);
		
		lblTotalAmount = new JLabel("");
		lblTotalAmount.setBounds(386, 220, 71, 16);
		contentPane.add(lblTotalAmount);
		
		btnAddExpense = new JButton("Add");
		btnAddExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddExpense.setBounds(124, 248, 117, 29);
		contentPane.add(btnAddExpense);
		
		btnDeleteExpense = new JButton("Delete");
		btnDeleteExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteExpense.setBounds(241, 248, 117, 29);
		contentPane.add(btnDeleteExpense);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(27, 60, 430, 152);
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
		
		tableModel = new DefaultTableModel(tblColumnNames, 0);
		table.setModel(tableModel);

	}
}
