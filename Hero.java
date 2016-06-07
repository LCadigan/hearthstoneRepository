package com.milton.hearthstone.Hearthstone;

public class Hero {
public String name;
public int health=30;
public int attack=0;
public int armor=0;
	public Hero(String pName) {
		
		name=pName;
	}

	public void heroPower(){
	
		if(name.equals("Paladin")){
			
		}
		if(name.equals("Warrior")){
			armor+=2;
		}
		if(name.equals("Priest")){
			
		}
		if(name.equals("Hunter")){
	
		}
		if(name.equals("Rogue")){
	
		}
		if(name.equals("Warlock")){
			health-=2;
		}
		if(name.equals("Druid")){
			armor+=1;
		}
		if(name.equals("Mage")){
	
		}
		if(name.equals("Shaman")){
	
		}
		
		
		
		
	}
	
	public void summonMinion(int mana, int attack, int health, String text, String pic, String playerClass, String name, String rarity, String race){
		
	}
	
}
