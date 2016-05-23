package com.milton.hearthstone.Hearthstone;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class JunkFile implements java.io.Serializable
{
	public int rx,ry,DX=3,DY=3;
	public Rectangle junkRec;
	public int size=10;
	public int canvX,canvY, myNum;
	public int fontSize=10;
	public int mana, attack, health, durability;
	public String cardType, text, playerClass, name, pic,rarity, race;


public JunkFile(int x, int y)
	{
		rx=x;
		ry=y;
	
	}

	public JunkFile(int x, int y,int cx,int cy ,int mn)
	{
		rx=x;
		ry=y;
		canvX=cx;
		canvY=cy;
		myNum=mn;

		junkRec= new Rectangle(rx-cx,ry-cy-fontSize,size,size);

	}
	public JunkFile(String tCardType, int tMana, String tText, String tPlayerClass, String tName, int tAttack, int tHealth, int tDurability,String tPic, String tRarity, String tRace){
		cardType=tCardType;
		mana=tMana;
		text=tText;
		playerClass=tPlayerClass;
		name=tName;
		attack=tAttack;
		health=tHealth;
		durability=tDurability;
		pic=tPic;
		rarity=tRarity;
		race= tRace;
		
	}
}
