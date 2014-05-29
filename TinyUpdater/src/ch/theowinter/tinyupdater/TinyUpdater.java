package ch.theowinter.tinyupdater;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TinyUpdater {
	private static String updateURL;

	private TinyUpdater() {
		super();
	}

	/**
	 * We expect args in the form of a waitTime-Count (seconds) and one http
	 * download-URL that ends with a ".jar" file. This jar file has the same
	 * name as the file we're supposed to be updating.
	 * 
	 * TinyUpdater waits until its waitTime is over and then tries to download
	 * and overwrite the old file with the new one. Trying to overwrite the .jar
	 * of a program that's still open may result in strange behavior depending
	 * on your operating system, so be sure to close your old application asap.
	 * 
	 * + arg0 = time to wait before attempting to download & overwrite + arg1 =
	 * the URL to your updated .jar file + arg2 = the application title (if you
	 * specify 3 args we assume you want to use the GUI version)
	 * 
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			log("You didn't specify enough arguments to run TinyUpdater.");
			log("Checkout the documentation on: https://github.com/aerobless/TinyUpdater");
		} else if (args.length == 2) {
			log("TinyUpdater CLI:");
			updateURL = args[1];
			cliUpdater(Integer.parseInt(args[0]));
		} else if (args.length == 3) {
			log("Launching GUI version of TinyUpdater...");
			updateURL = args[1];
			guiUpdater(args[2], Integer.parseInt(args[0]));
		} else {
			log("TinyUpdater only supports up to 3arguments.");
			log("Checkout the documentation on: https://github.com/aerobless/TinyUpdater");
		}
	}

	/**
	 * The CLI-based Updater
	 * 
	 * @param waitTime
	 */
	private static void cliUpdater(int waitTime) {
		logN("  Initalizing");
		logN(".");
		logN(".");
		String[] updateArray = updateURL.split("/");
		String downloadPath = getJarDirectory(updateArray[updateArray.length - 1]);
		log(".");
		logN("  Preparing for update");
		for (int i = 0; i < waitTime; i++) {
			try {
				Thread.sleep(i * 100);
				logN(".");
			} catch (InterruptedException e) {
				log("Error - Can't sleep properly.", e);
			}
		}
		log(".");
		logN("  Downloading update..");
		downloadFile(updateURL, downloadPath, null);
		log(".....");
		log("  Update complete..");
	}

	/**
	 * The GUI-based Updater
	 * 
	 * @param applicationTitel
	 * @param waitTime
	 */
	private static void guiUpdater(String applicationTitel, int waitTime) {
		TinyProgressStatus tinyProgress = new TinyProgressStatus(
				"Initalizing updater..", 0);
		TinyUI tinyUI = new TinyUI(tinyProgress, applicationTitel);
		tinyProgress.activateObserver(tinyUI);

		String[] updateArray = updateURL.split("/");
		String downloadPath = getJarDirectory(updateArray[updateArray.length - 1]);

		tinyProgress.updateStatus("Preparing to download..", 0);

		int localWaitTime = waitTime*10;
		for (int i = 0; i < localWaitTime; i++) {
			try {
				Thread.sleep(50);
				tinyProgress.setOverallProgress(i);
			} catch (InterruptedException e) {
				log("Error - Can't sleep properly.", e);
			}
		}
		tinyProgress.updateStatus("Downloading..");
		downloadFile(updateURL, downloadPath, tinyProgress);
		tinyProgress.updateStatus("Download complete.", 100);

		// Show success:
		for (int i = 0; i < 5; i++) {
			try {
				Thread.sleep(i * 100);
			} catch (InterruptedException e) {
				log("InterruptedException while trying to sleep.", e);
			}
		}

		// Launch new jar:
		try {
			if("windows".equals(getOS())){
				Runtime.getRuntime().exec(
						new String[] { "java", "-jar", downloadPath.substring(1) });
			}else{
				Runtime.getRuntime().exec(
						new String[] { "java", "-jar", downloadPath });
			}
		} catch (IOException e) {
			log("IOException while trying to launch the downloaded update..", e);
		}

		// Disposing of GUI and graceful exit:
		tinyUI.shutdownGUI();
	}

	public static void downloadFile(String downloadURL, String filenameAndPath,
			TinyProgressStatus tinyProgress) {
		try {
			URL url = new URL(downloadURL);
			URLConnection con = url.openConnection();
			DataInputStream dis = new DataInputStream(con.getInputStream());
			byte[] fileData = new byte[con.getContentLength()];
			for (int x = 0; x < fileData.length; x++) {
				fileData[x] = dis.readByte();
				if (tinyProgress != null) {
					tinyProgress
							.setOverallProgress(x / (fileData.length / 100));
				}
			}
			dis.close();
			FileOutputStream fos = new FileOutputStream(new File(
					filenameAndPath));
			fos.write(fileData);
			fos.close();
		} catch (MalformedURLException m) {
			log("MalformedURLException while trying to download a file.", m);
		} catch (IOException io) {
			log("IOException while trying to download a file.", io);
		}
	}

	public static String getJarDirectory(String filename) {
		URL jarLocation = TinyUpdater.class.getProtectionDomain()
				.getCodeSource().getLocation();
		URL dataXML = null;
		try {
			dataXML = new URL(jarLocation, filename);
		} catch (MalformedURLException e) {
			log("MalformedURLException while trying to get the jar's directory.",
					e);
		}
		return dataXML.getPath();
	}

	/**
	 * Print without newline
	 * 
	 * @param input
	 */
	public static void logN(String input) {
		System.out.print(input); //NOSONAR
	}

	/**
	 * Print with newline
	 * 
	 * @param input
	 */
	public static void log(String input) {
		log(input, null);
	}

	public static void log(String input, Exception e) {
		System.out.println(input); //NOSONAR
	}
	
	private static String getOS(){
		String operatingSystemRaw = System.getProperty("os.name");
		String output;
		if(operatingSystemRaw.contains("Windows")){
			output = "windows";
		} else if(operatingSystemRaw.contains("Mac")){
			output = "osx";
		} else if("Linux".equals(operatingSystemRaw)){
			output = "linux";
		} else {
			output = "unkown";
			log("Operating System not recognized...");
			log("Raw Data: "+operatingSystemRaw);
		}
		return output;
	}
}