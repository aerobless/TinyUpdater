package ch.theowinter.tinyupdater;

import java.util.Observable;
import java.util.Observer;

public class TinyProgressStatus extends Observable{
	private String currentTask; //Length 300
	private int overallProgress; //0-100
	
	/**
	 * Creates a new instance of this class.
	 *
	 * @param aCurrentTask
	 * @param aOverallProgress
	 */
	TinyProgressStatus(String aCurrentTask, int aOverallProgress) {
		super();
		currentTask = aCurrentTask;
		overallProgress = aOverallProgress;
	}

	/**
	 * @return the currentTask
	 */
	public final String getCurrentTask() {
		return currentTask;
	}

	/**
	 * @param aCurrentTask the currentTask to set
	 */
	public void updateStatus(String aCurrentTask, int processPercentage) {
		currentTask = aCurrentTask;
		overallProgress = processPercentage;
		notifyObservers();
		setChanged();
	}

	/**
	 * @return the overallProgress
	 */
	public int getOverallProgress() {
		return overallProgress;
	}

	/**
	 * @param aOverallProgress the overallProgress to set
	 */
	public void setOverallProgress(int processPercentage) {
		overallProgress = processPercentage;
		setChanged();
		notifyObservers();
	}
	
	public void activateObserver(Observer o){
		setChanged();
		this.addObserver(o);
	}
}
