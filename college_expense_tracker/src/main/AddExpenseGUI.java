package main;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddExpenseGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitle;
	private JLabel lblDate;
	private DatePicker datePicker;
	private JLabel lblCategory;
	private JComboBox<String> comboBoxCategory;
	private JLabel lblAmount;
	private JTextField txtFieldAmount;
	private JLabel lblDescription;
	private JTextArea txtFieldDescription;
	private JScrollPane descriptionScrollPane;
	private JButton btnSaveExpense;
	private JButton btnExitAddGUI;
	private Tracker t;

	public AddExpenseGUI() {
		
		this.t = Tracker.getInstance();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("Add New Expense");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTitle.setBounds(143, 6, 143, 16);
		contentPane.add(lblTitle);
		
		lblDate = new JLabel("Date:");
		lblDate.setBounds(27, 47, 82, 16);
		contentPane.add(lblDate);
		
		DatePickerSettings dateSettings = new DatePickerSettings();
		dateSettings.setAllowEmptyDates(false); // user must choose a date
		datePicker = new DatePicker(dateSettings);
		datePicker.getComponentToggleCalendarButton().setText("Calendar");
		datePicker.setBounds(121, 42, 288, 26);
		contentPane.add(datePicker);
		
		lblCategory = new JLabel("Category:");
		lblCategory.setBounds(27, 89, 61, 16);
		contentPane.add(lblCategory);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(121, 85, 296, 27);
		comboBoxCategory.setModel(new DefaultComboBoxModel<String>(
								  new String[] {"Food", "Entertainment", "Transportation", 
										  		"Tuition and Fees", "Housing", 
										  		"Books/Materials/Electronics", "Other"}));
		contentPane.add(comboBoxCategory);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(27, 127, 61, 16);
		contentPane.add(lblAmount);
		
		txtFieldAmount = new JTextField();
		txtFieldAmount.setBounds(121, 122, 296, 26);
		contentPane.add(txtFieldAmount);
		txtFieldAmount.setColumns(10);
		
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(27, 167, 82, 16);
		contentPane.add(lblDescription);
		
		
		txtFieldDescription = new JTextArea();
		txtFieldDescription.setBounds(121, 160, 296, 112);
		contentPane.add(txtFieldDescription);
		txtFieldDescription.setColumns(10);
		
		descriptionScrollPane = new JScrollPane(txtFieldDescription);
		descriptionScrollPane.setBounds(121, 160, 296, 112);
		contentPane.add(descriptionScrollPane);
		
		
		btnSaveExpense = new JButton("Save");
		btnSaveExpense.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        double amount;

		        try {
		            amount = Double.parseDouble(txtFieldAmount.getText());
		        } 
		        catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, 
		                "Please enter a valid numeric amount.",
		                "Invalid Input",
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        save(t,
		            datePicker.getDate(),
		            comboBoxCategory.getSelectedItem().toString(),
		            amount,
		            txtFieldDescription.getText()
		        );

		        dispose();
		    }
		});

		btnSaveExpense.setBounds(68, 300, 117, 29);
		contentPane.add(btnSaveExpense);
		
		btnExitAddGUI = new JButton("Exit");
		btnExitAddGUI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExitAddGUI.setBounds(253, 300, 117, 29);
		contentPane.add(btnExitAddGUI);

	}
	
	public void save(Tracker t, java.time.LocalDate date, String category, double amount, String description) {
		Expense newExpense = new Expense(date, category, amount, description);
		t.addExpense(newExpense);
	}
}
