package it.unibs.pajc;

import java.util.ArrayList;
import javax.swing.event.*;

public class BaseModel {
	private ArrayList<ChangeListener> listenerList = new ArrayList<>();
	
	public void addChangeListener(ChangeListener l) { listenerList.add(l); }
	
	public void removeChangeListener(ChangeListener l) { listenerList.remove(l); }
	
	protected void fireValuesChange(ChangeEvent e) {
		for(ChangeListener l: listenerList)
			l.stateChanged(e);
	}
	
}
