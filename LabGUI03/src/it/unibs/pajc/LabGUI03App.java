package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;

public class LabGUI03App {

	private JFrame frame;
	private JSlider slRed;
	private JSlider slGreen;
	private JSlider slBlu;
	private JPanel pnlColor;
	private JLabel lblInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGUI03App window = new LabGUI03App();
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
	public LabGUI03App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(121, 121, 121));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		slRed = new JSlider();
		slRed.setMaximum(255);
		slRed.setBounds(27, 21, 190, 29);
		frame.getContentPane().add(slRed);
		
		lblInfo = new JLabel("New label");
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblInfo.setBounds(6, 237, 315, 29);
		frame.getContentPane().add(lblInfo);
		
		slGreen = new JSlider();
		slGreen.setMaximum(255);
		slGreen.setBounds(27, 62, 190, 29);
		frame.getContentPane().add(slGreen);
		
		slBlu = new JSlider();
		slBlu.setMaximum(255);
		slBlu.setBounds(27, 103, 190, 29);
		frame.getContentPane().add(slBlu);
		
		pnlColor = new JPanel();
		pnlColor.setBounds(313, 21, 96, 86);
		frame.getContentPane().add(pnlColor);
		
		slRed.addChangeListener(e -> aggiorna());
		slGreen.addChangeListener(e -> aggiorna());
		slBlu.addChangeListener(e -> aggiorna());
		
	}
	
	private void aggiorna() {
		String cdesc = String.format("#%02X%02X%02X", slRed.getValue(),
				slGreen.getValue(), slBlu.getValue());
		lblInfo.setText(cdesc);
		pnlColor.setBackground(new Color(slRed.getValue(),
				slGreen.getValue(), slBlu.getValue()));
	}
}
