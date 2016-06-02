package com.milton.hearthstone.Hearthstone;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class SaveURL{
	public static int counter = 1;
	public static String baseFolder = "hearthstone_images/";
	
	public static void main(String[] args) throws Exception {
		//String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
		
		//saveImage(imageUrl, baseFolder);
	}

	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(baseFolder+destinationFile+".jpg");
		counter++;
		byte[] b = new byte[2048];
		int length;
		//while you can read a b, continue to read b 
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

}