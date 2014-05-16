package ch.theowinter.tinyupdater;

import java.util.Observable;

public class TinyProgressStatus extends Observable{
	String currentTask; //Length 300
	int overallProgress; //0-100
	
	/**
	 * Creates a new instance of this class.
	 *
	 * @param aCurrentTask
	 * @param aOverallProgress
	 */
	private TinyProgressStatus(String aCurrentTask, int aOverallProgress) {
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
	public final void setCurrentTask(String aCurrentTask) {
		currentTask = aCurrentTask;
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
	public final void setOverallProgress(int aOverallProgress) {
		overallProgress = aOverallProgress;
	}

	
	
}
