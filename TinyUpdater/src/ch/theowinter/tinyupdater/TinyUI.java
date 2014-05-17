package ch.theowinter.tinyupdater;

import java.awt.BorderLayout;
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
	public TinyUI(TinyProgressStatus tinyProgress, String titleOfUpdatedApplication) {
		this.tinyProgress = tinyProgress;
		initialize(titleOfUpdatedApplication);
		main.frmTinyupdater.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String titleOfUpdatedApplication) {
		frmTinyupdater = new JFrame();
		frmTinyupdater.setTitle("TinyUpdater: "+titleOfUpdatedApplication);
		frmTinyupdater.setBounds(100, 100, 445, 103);
		frmTinyupdater.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//We don't want the user to quit during an update.
		frmTinyupdater.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel leftPanel = new JPanel();
		frmTinyupdater.getContentPane().add(leftPanel, BorderLayout.WEST);
		
		JLabel sideSpacer = new JLabel("    ");
		leftPanel.add(sideSpacer);
		
		JPanel rightPanel = new JPanel();
		frmTinyupdater.getContentPane().add(rightPanel, BorderLayout.EAST);
		
		rightPanel.add(sideSpacer);
		
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
		progressBar.setValue(tinyProgress.getOverallProgress());
		lblStatus.setText(tinyProgress.getCurrentTask());
	}
}
