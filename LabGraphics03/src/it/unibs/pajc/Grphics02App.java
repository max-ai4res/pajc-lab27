package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Grphics02App {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grphics02App window = new Grphics02App();
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
	public Grphics02App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PnlGraphics pnlGraphics = new PnlGraphics();
		frame.getContentPane().add(pnlGraphics, BorderLayout.CENTER);
	}

}
