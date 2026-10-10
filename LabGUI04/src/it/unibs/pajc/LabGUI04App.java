package it.unibs.pajc;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;

public class LabGUI04App {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LabGUI04App window = new LabGUI04App();
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
	public LabGUI04App() {
		initialize();
	}
	
	static class ListItemRenderer extends DefaultListCellRenderer {
		private Map<String, Font> fontCache = new HashMap<String, Font>();
		
		@Override
		public Component getListCellRendererComponent(JList<?> list, 
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			//
			// DefaultListCellRenderer deriva da una JLabel, quindi se voglio
			// posso modificare quella come in questo esempio... 
			// oppure creare un nuovo componente... provare!!
			//
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			String fname = (String)value;
			
			Font font = fontCache.computeIfAbsent(
					fname, n -> new Font(n, Font.PLAIN, DIM_LISTA)
				);
			
			this.setFont(font);
			if(!isSelected)
				this.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
			return this;
		}
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	static final int DIM_LISTA = 24;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] fontFamily = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		
		JList lstFont = new JList(fontFamily);
		lstFont.setFixedCellHeight(DIM_LISTA + 12);
		lstFont.setFont(new Font("Lucida Grande", Font.PLAIN, DIM_LISTA));
		
		lstFont.setCellRenderer(new ListItemRenderer());
		
		JScrollPane lstScrollPane = new JScrollPane(lstFont);
		
		lstScrollPane.setPreferredSize(new Dimension(200, 0));
		
		frame.getContentPane().add(lstScrollPane, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 34));
		lblNewLabel.setBounds(6, 6, 241, 81);
		panel.add(lblNewLabel);
		
		lstFont.addListSelectionListener(e -> {
			String fname = ""+lstFont.getSelectedValue();
			Font f = new Font(fname, Font.PLAIN, 34);
			lblNewLabel.setFont(f); 
		});
	}

}
