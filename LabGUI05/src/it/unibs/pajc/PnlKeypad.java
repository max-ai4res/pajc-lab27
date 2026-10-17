package it.unibs.pajc;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;

public class PnlKeypad extends JPanel {
	
	public PnlKeypad() {
		setBackground(new Color(148, 17, 0));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for(int i=0; i<10; i++) {
			int local_i = i;
			JButton btn = new JButton(""+i);
			btn.setPreferredSize(new Dimension(40, 40));
			
			btn.addActionListener(e -> this.fireActionEvent(e));
			this.add(btn);
		}
	}
	
	private ArrayList<ActionListener> listenerList = new ArrayList<ActionListener>();
	public void addActionListener(ActionListener l) {
		listenerList.add(l);
	}
	
	public void removeActionListener(ActionListener l) {
		listenerList.remove(l);
	}
	
	protected void fireActionEvent(ActionEvent e) {
		for(var l: listenerList)
			l.actionPerformed(e);
	}
	
	
	
}
