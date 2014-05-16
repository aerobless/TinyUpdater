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
	public final void updateStatus(String aCurrentTask, int processPercentage) {
		currentTask = aCurrentTask;
		overallProgress = processPercentage;
		notifyObservers();
		setChanged();
		System.out.println("would notify");
	}

	/**
	 * @return the overallProgress
	 */
	public final int getOverallProgress() {
		return overallProgress;
	}

	/**
	 * @param aOverallProgress the overallProgress to set
	 */
	public final void setOverallProgress(int processPercentage) {
		overallProgress = processPercentage;
		setChanged();
		notifyObservers();
	}
	
	public final void activateObserver(Observer o){
		System.out.println("ddd");
		setChanged();
		this.addObserver(o);
	}
}
