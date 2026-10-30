package it.unibs.pajc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class TAApp01 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TAApp01 window = new TAApp01();
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
	public TAApp01() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PnlForme pnl = new PnlForme();
		frame.getContentPane().add(pnl, BorderLayout.CENTER);
	}

}
