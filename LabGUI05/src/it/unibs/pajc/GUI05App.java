package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

public class GUI05App {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI05App window = new GUI05App();
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
	public GUI05App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setText("0");
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JPanel pnlMain = new JPanel();
		pnlMain.setBackground(new Color(114, 252, 213));
		frame.getContentPane().add(pnlMain, BorderLayout.CENTER);
		pnlMain.setLayout(null);
		
		PnlKeypad pnlKeypad = new PnlKeypad();
		pnlKeypad.setBounds(275, 27, 141, 182);
		pnlMain.add(pnlKeypad);
		
	
		pnlKeypad.addActionListener(e -> 
			textField.setText(e.getActionCommand()));
	
		
	}
}
