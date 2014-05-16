package ch.theowinter.tinyupdater;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class TinyUI implements Runnable, Observer {

	private JFrame frmTinyupdater;
	private TinyProgressStatus tinyProgress;
	private JLabel lblStatus;
	private JProgressBar progressBar;

	/**
	 * Create the application.
	 */
	public TinyUI(TinyProgressStatus tinyProgress) {
		this.tinyProgress = tinyProgress;
		initialize();
	}

	/**
	 * Launch the application.
	 */
	@Override
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TinyUI window = new TinyUI(tinyProgress);
					window.frmTinyupdater.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTinyupdater = new JFrame();
		frmTinyupdater.setTitle("TinyUpdater");
		frmTinyupdater.setBounds(100, 100, 445, 103);
		frmTinyupdater.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTinyupdater.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//We don't want the user to quit during an update.
		frmTinyupdater.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmTinyupdater.getContentPane().add(panel, BorderLayout.WEST);
		
		JLabel sideSpacer = new JLabel("    ");
		panel.add(sideSpacer);
		
		JPanel panel_1 = new JPanel();
		frmTinyupdater.getContentPane().add(panel_1, BorderLayout.EAST);
		
		panel_1.add(sideSpacer);
		
		JPanel centerMain = new JPanel();
		frmTinyupdater.getContentPane().add(centerMain, BorderLayout.CENTER);
		centerMain.setLayout(new BoxLayout(centerMain, BoxLayout.X_AXIS));
		
		JPanel infoPanel = new JPanel();
		centerMain.add(infoPanel);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		lblStatus = new JLabel("Status: waiting for download..");
		infoPanel.add(lblStatus);
		
		progressBar = new JProgressBar();
		infoPanel.add(progressBar);
		progressBar.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		progressBar.setValue(45);
		progressBar.setStringPainted(true);
	}

	@Override
	public void update(Observable aO, Object aArg) {
		System.out.println("UPDATING DUE TO OBSERVER");
		lblStatus.setText(tinyProgress.getCurrentTask());
		progressBar.setValue(tinyProgress.getOverallProgress());
		
	}

}
