package com.milton.hearthstone.Hearthstone;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import javax.swing.tree.VariableHeightLayoutCache;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.CharacterData;

import java.awt.event.*;
//import java.io.*;
import java.applet.Applet;
import java.util.*;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Hello world!
 *
 */
public class App  extends Frame
{
	public  SaveLoad sl;
	public JunkFile[] loader; // an array of junkFile
	
	public JunkFile[] saver;
	public int counter=0;
	
	public static ArrayList<Card> allCards= new ArrayList<Card>();
	 
    public static void main( String[] args )
    {
    	new serverProgII();
        addAllCards();
        
       
    	//makeCard()
       
        //System.out.println(allCards.get(60).manaCost);
        //System.out.println("Cards: " +allCards.size());
        //System.out.println(cardNames.size());;
    }
    
    public static void addAllCards(){
    	try{
        	HttpResponse<JsonNode> response = Unirest.get("https://irythia-hs.p.mashape.com/cards")
        			.header("X-Mashape-Key", "9yOy2AJsEkmshk9xN3TLFYgjaY7zp1LBLOqjsnTCqv7f9fzvbM")
        			.header("Accept", "application/json")
        			.asJson();
        System.out.println(response.getBody().toString());
        String responseString = response.getBody().toString();
        int lastFind=1;
        //int counter=0;
        while(lastFind>0){
        	//counter++;
        	try{
        		//System.out.println(responseString.substring(lastFind+6,responseString.indexOf(",",lastFind+7)-1));
        		//makeCard("Aldor Peacekeeper");
        		String nextCard = responseString.substring(lastFind+6,responseString.indexOf(",",lastFind+6)).trim().replaceAll("\"","");
        		
        		makeCard(nextCard);
                

        	}catch(JSONException e){}
        	lastFind=responseString.indexOf("name",lastFind+1);
        	
        	//System.out.println(lastFind);
        }
    } catch (UnirestException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    
    public static void makeCard(String card)
    {
    	card.replaceAll(" ", "%");
    	//System.out.println("testing " +card);
    	try {
    		HttpResponse<JsonNode> response = Unirest.get("https://omgvamp-hearthstone-v1.p.mashape.com/cards/"+card)
    				.header("X-Mashape-Key", "9yOy2AJsEkmshk9xN3TLFYgjaY7zp1LBLOqjsnTCqv7f9fzvbM")
    				.header("Accept", "application/json")
    				.asJson();
        	//System.out.println(response.getBody());
			JSONObject obj = new JSONObject(response.getBody());
			//System.out.println(obj.keySet());
			JSONArray array = obj.getJSONArray("array");
			//System.out.println(array);
			
			
			
			for(int i=0;i<array.length(); i++){
				JSONObject object = array.getJSONObject(i);
				
				if(object.get("type").equals("Minion"))
						{
					
					MinionCard mCard = new MinionCard();
					mCard.attack=(Integer) object.get("attack");
					mCard.health=(Integer) object.get("health");
					try{
					mCard.playerClass=(String) object.get("playerClass");
					}catch(JSONException e){}
					
					//mCard.pic=(Image) object.get("img");
					mCard.name=(String) object.get("name");
					mCard.cardType=(String)object.get("type");
					mCard.manaCost=(Integer) object.get("cost");
					try{
					mCard.text= (String) object.get("text");
					}catch(JSONException e){}
					//System.out.println("IT WORKED, attack = " + mCard.attack);
						allCards.add(mCard);
						}
				
			    if(object.get("type").equals("Spell"))
			    {
			    	
			    	SpellCard sCard = new SpellCard();
			    		
			    	sCard.playerClass=(String) object.get("playerClass");
					//sCard.pic=(Image) object.get("img");
					sCard.name=(String) object.get("name");
					sCard.cardType=(String)object.get("type");
					sCard.manaCost=(Integer) object.get("cost");
					sCard.text= (String) object.get("text");				
					//System.out.println("IT WORKED, cost = " + sCard.manaCost);
					allCards.add(sCard);
			    }
			    if(object.get("type").equals("Weapon"))
			    		{
			    	try{
			    		
			    	WeaponCard wCard=new WeaponCard();
			    	wCard.attack=(Integer) object.get("attack");
					wCard.durability=(Integer) object.get("durability");
			    	wCard.playerClass=(String) object.get("playerClass");
					//wCard.pic=(Image) object.get("img");
					wCard.name=(String) object.get("name");
					wCard.cardType=(String)object.get("type");
					wCard.manaCost=(Integer) object.get("cost");
					try{
					wCard.text= (String) object.get("text");
			    	}catch(JSONException e){}
					//ystem.out.println("IT WORKED, attack = " + wCard.attack);
					allCards.add(wCard);
					}
			catch(JSONException e){
			
			    		}
			    		}
				
				
				
			}
			
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}