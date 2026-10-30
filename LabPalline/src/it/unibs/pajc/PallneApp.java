package it.unibs.pajc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class PallneApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PallneApp window = new PallneApp();
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
	public PallneApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PnlPallineView pnl = new PnlPallineView();
		frame.getContentPane().add(pnl, BorderLayout.CENTER);
	}

}
