package com.milton.hearthstone.Hearthstone;

import java.awt.Image;

public class SpellCard extends Card {

	public SpellCard(int pMana, String pText, String pPic, String pPlayerClass, String pName, String pRarity, String pRace) {
	manaCost=pMana;
	text=pText;
	pic=pPic;
	playerClass=pPlayerClass;
	name=pName;
	cardType="spell";
	rarity= pRarity;
	race= pRace;
	
	}

}
