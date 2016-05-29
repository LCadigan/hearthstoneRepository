package com.milton.hearthstone.Hearthstone;

import java.awt.Image;

public abstract class Card {
	
public int manaCost;
public String pic;
public String text;
public String playerClass; 
public String name;
public String cardType;
public int attack;
public int health;
public int durability;
public String rarity;
public String race;
public boolean canAttack=false;

public void playCard()
{
}

}
