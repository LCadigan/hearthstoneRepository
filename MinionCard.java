package com.milton.hearthstone.Hearthstone;

//import java.awt.Image;

public class MinionCard extends Card {



	public MinionCard(int pMana, int pAttack, int pHealth, String pText, String pPic, String pPlayerClass, String pName, String pRarity, String pRace) {
		attack=pAttack;
		health=pHealth;
		manaCost=pMana;
		text=pText;
		pic=pPic;
		playerClass=pPlayerClass;
		name=pName;
		rarity= pRarity;
		race= pRace;
		
		
		cardType="minion";
	}

}
