package it.unibs.pajc;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LabGUI01App {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGUI01App window = new LabGUI01App();
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
	public LabGUI01App() {
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
		
		JLabel lblInfo = new JLabel("--");
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblInfo.setBounds(35, 20, 393, 47);
		frame.getContentPane().add(lblInfo);
		
		JButton btnUno = new JButton("Btn Uno");
		btnUno.setBounds(28, 77, 117, 29);
		frame.getContentPane().add(btnUno);
		
		JButton btnDue = new JButton("Btn Due");
		btnDue.setBounds(192, 79, 117, 29);
		frame.getContentPane().add(btnDue);

		
		JButton btnDebug = new JButton("debug");
		btnDebug.setBounds(120, 144, 117, 29);
		frame.getContentPane().add(btnDebug);
		
		ActionListener btnListener = e -> {
			lblInfo.setText(e.getActionCommand());
			lblInfo.setForeground(Color.red);
		};
		
		ActionListener debugInfo = e -> System.out.println(e.getSource());
		
		btnUno.addActionListener(btnListener);
		btnDue.addActionListener(btnListener);
		
		btnDebug.addActionListener(e -> {
			btnUno.addActionListener(debugInfo);
			btnDue.addActionListener(debugInfo);
			// attivare e disattivare il debug!! removeActionListener
		});
	
		
	}
}
