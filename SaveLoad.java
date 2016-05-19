package com.milton.hearthstone.Hearthstone;

import java.awt.*;

import java.awt.event.*;
import java.io.*;
import java.applet.Applet;
import java.util.*;

public class SaveLoad extends Frame implements ActionListener // this extends frame
{

	public  SaveLoad sl;
	public JunkFile[] loader; // an array of junkFile
	public Button save,load;
	public Canvas c;
	public JunkFile[] saver;
	public int counter=0;

	// this is the main wich is run when you run an application


	// this is the consturctor for saveload and calls initialize

	public SaveLoad()
	{
		//initialize();
		resize(400,300);
		show();
	}

	

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("save array"))
		{
			// call save array and send it saver
			savearray(saver);
			for(int x=0; x<15; x++)
			{
				System.out.println("rx=" +saver[x].rx);
			}
		}
		if(e.getActionCommand().equals("load array"))
		{
			loadarray();
			for(int x=0; x<15; x++)
			{
				System.out.println("rx=" +loader[x].rx);
			}
		}
	}




	public void loadarray()
	{
		String fileName = "";
		String dir = "C:/windows/desktop";
		FileDialog fdL = new FileDialog(this, "load me", FileDialog.LOAD);
		fdL.show();
		fileName = fdL.getFile();
		dir = fdL.getDirectory();

		FileInputStream fis;
		try
		{
			fis = new FileInputStream(dir + fileName);
			ObjectInputStream ois=new ObjectInputStream(fis);
			JunkFile b[] = (JunkFile[])ois.readObject();
			loader=new JunkFile[b.length];
			loader=b;
			fis.close();

		}
		catch(IOException e)
		{
			System.out.println("IOException");
			System.out.println(e);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class not found");
		}


	}
	public void savearray(JunkFile s[])
	{
		String fileName = "";
		String dir = "C:/windows/desktop";


		FileDialog fdS = new FileDialog(this, "SAVE", FileDialog.SAVE);
		fdS.show();
		fileName = fdS.getFile();
		dir = fdS.getDirectory();


		try
		{
			FileOutputStream fos=new FileOutputStream(dir + fileName);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(s);
			oos.flush();

			fos.close();
		}
		catch(IOException e)
		{
			System.out.println("did not save");

		}


	}


}