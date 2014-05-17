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

	/**
	 * We expect args in the form of a waitTime-Count (seconds) and one http download-URL 
	 * that ends with a ".jar" file. This jar file has the same name as the file
	 * we're supposed to be updating.
	 * 
	 * TinyUpdater waits until its waitTime is over and then tries to download and overwrite the old file
	 * with the new one. Trying to overwrite the .jar of a program that's still open may result in
	 * strange behavior depending on your operating system, so be sure to close your old application asap.
	 * 
	 *  + arg0 = time to wait before attempting to download & overwrite
	 *  + arg1 = the URL to your updated .jar file
	 *  + arg2 = the application title (if you specify 3 args we assume you want to use the GUI version)
	 * 
	 */
	public static void main(String[] args) {
		
		//Test String: Uncomment to test TinyUpdater
		//args = new String[]{"10","http://jenkins.w1nter.net/job/ToxicTodo/lastSuccessfulBuild/artifact/ToxicTodo/dist/ToxicTodoClient.jar","Toxic Test"};
		
		if(args.length<2){
			System.out.println("You didn't specify enough arguments to run TinyUpdater.");
		} else if (args.length == 2){
			System.out.println("TinyUpdater CLI:");
			updateURL = args[1];
			cliUpdater(Integer.parseInt(args[0]));
		} else if (args.length == 3){
			log("Launching GUI version of TinyUpdater...");
			updateURL = args[1];
			guiUpdater(args[2], Integer.parseInt(args[0]));
		} else{
			log("TinyUpdater only supports up to 3arguments.");
			log("Checkout the documentation on: https://github.com/aerobless/TinyUpdater");
		}
	}
	
	private static void guiUpdater(String applicationTitel, int waitTime){
		TinyProgressStatus tinyProgress = new TinyProgressStatus("Initalizing updater..", 0);
		TinyUI tinyUI = new TinyUI(tinyProgress, applicationTitel);
		tinyProgress.activateObserver(tinyUI);
		
		String[] updateArray  = updateURL.split("/");
		String downloadPath = getJarDirectory(updateArray[updateArray.length-1]);

		tinyProgress.updateStatus("Preparing to download..", 0);
		
		waitTime *=10;
		for(int i = 0; i<waitTime; i++){
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
		
		//Show success:
		for(int i = 0; i<5; i++){
			try {
				Thread.sleep(i*100);
			} catch (InterruptedException e) {
				System.out.println("Error - Can't sleep properly.");
			}
		}
		
		//Launch downloaded app:
		System.out.println(updateArray[updateArray.length-1]);
		try {
			Runtime.getRuntime().exec(new String[]{"java","-jar",downloadPath});
		} catch (IOException e) {
			log("IOException while trying to launch the downloaded update..", e);
		}
		System.exit(0);
	}

	private static void cliUpdater(int waitTime){
		System.out.print("  Initalizing");
		System.out.print(".");
		System.out.print(".");
		String[] updateArray  = updateURL.split("/");
		String downloadPath = getJarDirectory(updateArray[updateArray.length-1]);
		System.out.println(".");
		System.out.print("  Preparing for update");
		for(int i = 0; i<waitTime; i++){
			try {
				Thread.sleep(i*100);
				System.out.print(".");
			} catch (InterruptedException e) {
				System.out.println("Error - Can't sleep properly.");
			}
		}
		System.out.println(".");
		System.out.print("  Downloading update..");
		downloadFile(updateURL, downloadPath, null);
		System.out.println(".....");
		System.out.println("  Update complete..");
	}

	public static void downloadFile(String downloadURL, String filenameAndPath, TinyProgressStatus tinyProgress) {
		try {
			URL url = new URL(downloadURL);
			URLConnection con = url.openConnection(); 
			DataInputStream dis = new DataInputStream(con.getInputStream());
			byte[] fileData = new byte[con.getContentLength()];
			for (int x = 0; x < fileData.length; x++) {
				fileData[x] = dis.readByte();
				if(tinyProgress != null){
					tinyProgress.setOverallProgress(x/(fileData.length/100));
				}
			}
			dis.close(); 
			FileOutputStream fos = new FileOutputStream(new File(filenameAndPath));
			fos.write(fileData); 
			fos.close();
		} catch (MalformedURLException m) {
			log("MalformedURLException while trying to download a file.", m);
		} catch (IOException io) {
			log("IOException while trying to download a file.", io);
		}
	}

	public static String getJarDirectory(String filename){
		URL jarLocation = TinyUpdater.class.getProtectionDomain().getCodeSource().getLocation();
		URL dataXML = null;
		try {
			dataXML = new URL(jarLocation, filename);
		} catch (MalformedURLException e) {
			log("MalformedURLException while trying to get the jar's directory.", e);
		}
		return dataXML.getPath();
	}
	
	public static void log(String input){
		log(input, null);
	}
	
	public static void log(String input, Exception e){
		System.out.println(input);
	}
}