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

public class TinyUI implements Observer {

	private JFrame frmTinyupdater;
	private TinyProgressStatus tinyProgress;
	private JLabel lblStatus;
	private JProgressBar progressBar;
	private TinyUI main = this;

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
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main = new TinyUI(tinyProgress);
					main.frmTinyupdater.setVisible(true);
				} catch (Exception e) {
				//TODO:
					System.out.println("error here");
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
		
		lblStatus = new JLabel(tinyProgress.getCurrentTask());
		infoPanel.add(lblStatus);
		
		progressBar = new JProgressBar();
		infoPanel.add(progressBar);
		progressBar.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		progressBar.setValue(tinyProgress.getOverallProgress());
		progressBar.setStringPainted(true);
	}

	@Override
	public void update(Observable aO, Object aArg) {
		//main.frmTinyupdater.setVisible(false);
		System.out.println("UPDATING DUE TO OBSERVER");
		main.lblStatus.setText(tinyProgress.getCurrentTask());
		System.out.println(tinyProgress.getCurrentTask());
		main.lblStatus.setVisible(false);

		progressBar.setValue(tinyProgress.getOverallProgress());
		System.out.println(tinyProgress.getOverallProgress());
	}
}
