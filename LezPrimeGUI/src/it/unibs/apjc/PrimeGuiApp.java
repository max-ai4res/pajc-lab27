package it.unibs.apjc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PrimeGuiApp {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeGuiApp window = new PrimeGuiApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrimeGuiApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblResult = new JLabel("--");
		lblResult.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblResult.setBounds(26, 19, 135, 46);
		frame.getContentPane().add(lblResult);
		
		JButton btnCalcola = new JButton("Calcola");
		btnCalcola.setBounds(208, 162, 117, 29);
		frame.getContentPane().add(btnCalcola);
		
		textField = new JTextField();
		textField.setText("1000000");
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		textField.setBounds(26, 145, 170, 53);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		btnCalcola.addActionListener(e -> {
			long nmax = Long.parseLong(textField.getText());
			lblResult.setText("avvio calcolo...");
			
			btnCalcola.setEnabled(false);
			
			new Thread(() -> {
				
				long n = new PrimeTester(1, nmax).call();
				System.out.println("primi: " + n);
				
				EventQueue.invokeLater(() -> {
					lblResult.setText("primi: " + n);
					btnCalcola.setEnabled(true);}
				);
			
			}).start();
	
			
			
			
			
		});
	}
}
