package com.milton.hearthstone.Hearthstone;

import java.awt.Image;
import java.util.StringTokenizer;


public class WeaponCard extends Card {
	

	public WeaponCard(int pMana, int pAttack, int pDurability, String pText, String pPic, String pPlayerClass, String pName, String pRarity, String pRace) {
	manaCost=pMana;
	attack=pAttack;
	durability=pDurability;
	text=pText;
	pic=pPic;
	playerClass=pPlayerClass;
	name=pName;
	cardType="weapon";
	rarity= pRarity;
	race= pRace;
	
	
		
	}

}
