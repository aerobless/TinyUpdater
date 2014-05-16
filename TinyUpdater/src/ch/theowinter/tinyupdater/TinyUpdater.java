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
	private static int waitTime;

	/*
	 * We expect args in the form of a waitTime-Count (seconds) and one http download-URL 
	 * that ends with a ".jar" file. This jar file has the same name as the file
	 * we're supposed to be updating.
	 * TinyUpdater waits until his waitTime is over and then tries to download and write the file.
	 * The original application needs to be shutdown at that point otherwise the update fails.
	 */
	public static void main(String[] args) {
		/*
		 * --CLI version--
		 * arg0 = time to wait before attempting to overwrite
		 * arg1 = updateURL
		 * 
		 * --GUI version--
		 * Specify 3 or more args and we assume you want to use the GUI version.
		 * arg2 = application title
		 */
		if(args.length<2){
			System.out.println("You didn't specify enough arguments to run TinyUpdater.");
		} else if(args.length<3){
			System.out.println("TinyUpdater:");
			System.out.print("  Initalizing");
			updateURL = args[1];
			System.out.print(".");
			waitTime = Integer.parseInt(args[0]);
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
			downloadFile(updateURL, downloadPath);
			System.out.println(".....");
			System.out.println("  Update complete..");
		} else {
			log("Launching GUI version of TinyUpdater...");
			updateURL = args[1];
			waitTime = Integer.parseInt(args[0]);
			Thread tinyUI = new Thread(new TinyUI());
			tinyUI.setDaemon(true);
			tinyUI.start();
			
		}
	}


	public static void downloadFile(String downloadURL, String filenameAndPath) {
		try {
			URL url = new URL(downloadURL);
			URLConnection con = url.openConnection(); 
			DataInputStream dis = new DataInputStream(con.getInputStream());
			byte[] fileData = new byte[con.getContentLength()];
			for (int x = 0; x < fileData.length; x++) {
				fileData[x] = dis.readByte();
			}
			dis.close(); 
			FileOutputStream fos = new FileOutputStream(new File(filenameAndPath));
			fos.write(fileData); 
			fos.close();
		} catch (MalformedURLException m) {
			System.out.println(m);
		} catch (IOException io) {
			System.out.println(io);
		}
	}

	public static String getJarDirectory(String filename){
		URL jarLocation = TinyUpdater.class.getProtectionDomain().getCodeSource().getLocation();
		URL dataXML = null;
		try {
			dataXML = new URL(jarLocation, filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
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