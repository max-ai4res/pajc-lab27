package it.unibs.pajc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;

public class MandelbrotApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MandelbrotApp window = new MandelbrotApp();
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
	
	private MandelbrotCntrl cntrl;
	private MandelbrotModel model;
	public MandelbrotApp() {
		model = new MandelbrotModel();
		cntrl = new MandelbrotCntrl(model);
		
		initialize();
		model.addChangeListener(this::modelUpdated);				
	}

	private void modelUpdated(ChangeEvent e) {
		if(pnl == null)
			return;
		
		Runnable task = () -> pnl.setMandelbrotData(model.getData());
		
		if(EventQueue.isDispatchThread()) {
			task.run();
		} else {
			SwingUtilities.invokeLater(task);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	PnlMandelbrot pnl;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pnl = new PnlMandelbrot(cntrl);
		frame.getContentPane().add(pnl, BorderLayout.CENTER);
	}

}
