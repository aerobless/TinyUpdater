package ch.theowinter.tinyupdater;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class TinyUI {

	private JFrame frmTinyupdater;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TinyUI window = new TinyUI();
					window.frmTinyupdater.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TinyUI() {
		initialize();
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
		
		JPanel panel = new JPanel();
		frmTinyupdater.getContentPane().add(panel, BorderLayout.WEST);
		
		JLabel label = new JLabel("    ");
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		frmTinyupdater.getContentPane().add(panel_1, BorderLayout.EAST);
		
		JLabel label_1 = new JLabel("    ");
		panel_1.add(label_1);
		
		JPanel centerMain = new JPanel();
		frmTinyupdater.getContentPane().add(centerMain, BorderLayout.CENTER);
		centerMain.setLayout(new BoxLayout(centerMain, BoxLayout.X_AXIS));
		
		JPanel infoPanel = new JPanel();
		centerMain.add(infoPanel);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Status: waiting for download..");
		infoPanel.add(lblNewLabel);
		
		JProgressBar progressBar = new JProgressBar();
		infoPanel.add(progressBar);
		progressBar.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		progressBar.setValue(45);
		progressBar.setStringPainted(true);
	}

}
