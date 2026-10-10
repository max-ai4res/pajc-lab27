package it.unibs.pajc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;

public class LabGUI02App {
	
	/*class Colore {
		String nome;
		Color colore;
		
		public Colore(String nome, Color colore) {
			this.colore = colore;
			this.nome = nome;
		}
		
		public String toString() { return nome; }
	}*/
	
	record Colore(String nome, Color colore) {
		public String toString() { return nome; }
	}
	
	final Colore[] COLORI = {
			new Colore("Rosso", Color.red),
			new Colore("Verde", Color.green),
			new Colore("Blue", Color.blue),
			new Colore("Giallino", new Color(255,250, 200))
	};

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGUI02App window = new LabGUI02App();
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
	public LabGUI02App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(66, 66, 66));
		frame.setBounds(100, 100, 648, 378);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblInfo = new JLabel("New label");
		lblInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		frame.getContentPane().add(lblInfo, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JComboBox comboBox = new JComboBox(COLORI);
		comboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		panel.add(comboBox);
		
		JLabel lblCombo = new JLabel("--");
		lblCombo.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		panel.add(lblCombo);
		
		
		comboBox.addActionListener(e ->
			lblCombo.setText(comboBox.getSelectedItem().toString()));
		
		comboBox.addActionListener(e -> {
			lblInfo.setText(String.format("Selected index: %d", 
					comboBox.getSelectedIndex()));
			
			Colore c = (Colore) comboBox.getSelectedObjects()[0];
			
			lblInfo.setForeground(c.colore);
			});
		
		
	}

}
