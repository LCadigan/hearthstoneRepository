package com.milton.hearthstone.Hearthstone;

import java.awt.FileDialog;


import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Transparency;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import java.rmi.server.LoaderHandler;

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

import java.awt.Canvas;
import java.awt.CardLayout; 
import java.awt.Color; 
import java.awt.FlowLayout; 
import java.awt.Graphics; 
import javax.swing.JButton; 
import javax.swing.JComponent; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App  extends Frame implements Runnable
{
	public  SaveLoad sl;
	public  JunkFile[] loader; // an array of junkFile
	
	public JunkFile[] saver;
	public int counter=0;
	
	public  ArrayList<MinionCard> minionCards= new ArrayList<MinionCard>();
	public ArrayList<SpellCard> spellCards = new ArrayList<SpellCard>();
	public ArrayList<WeaponCard> weaponCards = new ArrayList<WeaponCard>();
	public ArrayList<Card> allCards = new ArrayList<Card>();
	//public Image offImage= createImage(bounds().width,bounds().height);
	//public Graphics offScreen=offImage.getGraphics();
	   public boolean isTaunt =false; 
	   public int yourMana =0; 
	   public Card[] deck = new Card[30];
	   public Card[] cardArray = new Card[14];
	   public JButton[] cardButtons= new JButton[14];
	   public HearthCanvas canvas = new HearthCanvas();
	   Thread thread;
	  // public Image board= new Image
	 
    public static void main( String[] args )
    {
    	//new serverProgII();
        
    	App a= new App();
    	 a.show();	
    	 a.resize(600,600);
    	a.thread = new Thread(a);  
       a.thread.start();
    	/*a.addAllCards();
    	System.out.println("Cards made");
    	System.out.println(a.allCards.size());
    	JunkFile[] saveCards = new JunkFile[a.allCards.size()];
    	
        for(int x=0;x<a.allCards.size();x++)
        {
        	JunkFile card = new JunkFile(a.allCards.get(x).cardType, a.allCards.get(x).manaCost, a.allCards.get(x).text, a.allCards.get(x).playerClass, a.allCards.get(x).name, a.allCards.get(x).attack, a.allCards.get(x).health, a.allCards.get(x).durability, a.allCards.get(x).pic, a.allCards.get(x).rarity, a.allCards.get(x).race);
        	saveCards[x]= card;
        }
        System.out.println("Saving now");
        a.savearray(saveCards);
        
        System.out.println("Saved");*/
        
    	a.loadarray();
    	System.out.println(a.loader.length);
    	for(int i=0; i<a.loader.length; i++)
    	{
    		System.out.println(a.loader[i].cardType);
    		//Syste
    		if(a.loader[i].cardType.equals("Minion"))
    		{
    			//System.out.println("test");
    			MinionCard minion = new MinionCard(a.loader[i].mana, a.loader[i].attack, a.loader[i].health, a.loader[i].text, a.loader[i].pic, a.loader[i].playerClass, a.loader[i].name, a.loader[i].rarity, a.loader[i].race);
    			a.minionCards.add(minion);
    		}
    		if(a.loader[i].cardType.equals("Spell"))
    		{
    			SpellCard spell = new SpellCard(a.loader[i].mana, a.loader[i].text, a.loader[i].pic, a.loader[i].playerClass, a.loader[i].name, a.loader[i].rarity);
    			a.spellCards.add(spell);
    		}
    		if(a.loader[i].cardType.equals("Weapon"))
    		{
    			WeaponCard weapon = new WeaponCard(a.loader[i].mana, a.loader[i].attack, a.loader[i].durability, a.loader[i].text, a.loader[i].pic, a.loader[i].playerClass, a.loader[i].name, a.loader[i].rarity);
    			a.weaponCards.add(weapon);
    		}
    		
    	}
    	System.out.println("minions " + a.minionCards.size());
    	System.out.println("spells " + a.spellCards.size());
    	System.out.println("weapons " + a.weaponCards.size());
    	System.out.println(a.minionCards.get(5).name + ": "+ a.minionCards.get(5).race);
    	System.out.println(a.spellCards.get(101).name + ": " + a.spellCards.get(101).text);
    	System.out.println(a.weaponCards.get(16).name + ": " + a.weaponCards.get(16).text);
    	
       
    	
       
       
        //System.out.println("Cards: " +allCards.size());
        
    }
    public void createCanvas(){
        for(int i =0; i<14;i++){
	         cardButtons[i] = new JButton(i+"");
        }
   }
    
    public void playCard(int clickNum, Card playCard){
    	//note: must make sure we do not replay card, play card twice
    //note: break up effects by period
    if(yourMana>playCard.manaCost){
    cardArray[clickNum]=playCard;
    	if(cardArray[clickNum].text.contains("Battlecry")){
    		int startInt = cardArray[clickNum].text.indexOf("Battlecry"+10);
    doEffect(cardArray[clickNum].text.substring(startInt, cardArray[clickNum].text.indexOf(".",startInt)));
    
    	}
    	cardArray[clickNum].canAttack = cardArray[clickNum].text.contains("Charge") ? true: false; 

    }
    }
    public void doEffect(String pString){
    	
    	   System.out.println("doEffect: "+pString);
    	   
    	   sendString("attack(0,14)");
    	   if(pString.contains("attack")){
    		   int startInt=Integer.parseInt(pString.substring(pString.indexOf("(")+1,-1+pString.indexOf(",")));
      		 
    		   int endInt=Integer.parseInt(pString.substring(pString.indexOf(",")+1,-1+pString.indexOf(")")));
    		  attack(startInt,endInt);
    	   }
    	      if(pString.contains("all minions")){}
    	      else if(pString.contains("a friendly minion")){}
    	      	if(pString.contains("a minion")){}
    	      	//for effect
    	      		if(pString.contains("give")){}
    	      		if(pString.contains("Taunt")){}
    	      }

    public void attack(int aNum, int dNum){
    	cardArray[aNum].health-=cardArray[dNum].attack;
    	cardArray[dNum].health-=cardArray[aNum].attack;
    	if(cardArray[aNum].health<1){
    		cardArray[aNum]=null;
    	}
    	if(cardArray[dNum].health<1){
    		cardArray[dNum]=null;
    	}

    		//set num to -1, to indicate not to attack
    	int attackerNum=(-1);
    	}
    
    public void sendString(String pString){
    	System.out.println(pString);
    }
    
    public  void addAllCards(){
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
    
    
    public  void makeCard(String card)
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
				//System.out.println(object.get("name"));
				if(object.get("type").equals("Minion"))
						{
					//MinionCard mCard= new MinionCard((Integer) object.get("cost"), (Integer) object.get("attack"), (Integer) object.get("health"),(String) object.get("text"), (String) object.getString("img"), (String) object.get("playerClass"), (String) object.get("name"), (String) object.getString("rarity"), (String) object.getString("race"));
					MinionCard mCard = new MinionCard(i, i, i, card, card, card, card, card, card);
					mCard.attack=(Integer) object.get("attack");
					mCard.health=(Integer) object.get("health");
					try{
					mCard.playerClass=(String) object.get("playerClass");
					}catch(JSONException e){}
					
					
					mCard.name=(String) object.get("name");
					mCard.cardType=(String)object.get("type");
					mCard.manaCost=(Integer) object.get("cost");
					mCard.pic=(String) object.getString("img"); 
					try{
					mCard.race=(String) object.getString("race");
					}catch(JSONException e){}
					mCard.rarity=(String) object.getString("rarity");
					try{
					mCard.text= (String) object.get("text");
					}catch(JSONException e){}
					//System.out.println("IT WORKED, attack = " + mCard.attack);
						allCards.add(mCard);
						}
				
			    if(object.get("type").equals("Spell"))
			    {
			    	
			    	SpellCard sCard = new SpellCard(i, card, card, card, card, card);
			    		
			    	sCard.playerClass=(String) object.get("playerClass");
					
					sCard.name=(String) object.get("name");
					sCard.cardType=(String)object.get("type");
					sCard.manaCost=(Integer) object.get("cost");
					sCard.text= (String) object.get("text");	
					sCard.pic=(String) object.getString("img"); 
					try{
					sCard.rarity=(String) object.getString("rarity");
					}catch(JSONException e){}
					//System.out.println("IT WORKED, cost = " + sCard.manaCost);
					allCards.add(sCard);
			    }
			    if(object.get("type").equals("Weapon"))
			    		{
			    	try{
			    	//	
			    	WeaponCard wCard=new WeaponCard(i, i, i, card, card, card, card, card );
			    	wCard.attack=(Integer) object.get("attack");
					wCard.durability=(Integer) object.get("durability");
			    	wCard.playerClass=(String) object.get("playerClass");
					
					wCard.name=(String) object.get("name");
					wCard.cardType=(String)object.get("type");
					wCard.manaCost=(Integer) object.get("cost");
					wCard.pic=(String) object.getString("img"); 
					try{
					wCard.rarity=(String) object.getString("rarity");
					}catch(JSONException e){}
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
    public  void savearray(JunkFile s[])
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
    public  void loadarray()
	{
		/*String fileName = "";
		String dir = "C:/windows/desktop";
		FileDialog fdL = new FileDialog(this, "load me", FileDialog.LOAD);
		fdL.show();
		fileName = fdL.getFile();
		dir = fdL.getDirectory();*/

		FileInputStream fis;
		try
		{
			//System.out.println(dir + fileName);
			fis = new FileInputStream("/Users/Will/Desktop/HS Card");
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
    public void paint(Graphics g)
    {
    	
    }
    
    public void run(){
    	while(true){
    		
    		canvas.paint(getGraphics());
    		
    		
    		try {
                thread.sleep(50);
             }
             catch (Exception e){ }
          }//while
       
       }// run
    	
    }

