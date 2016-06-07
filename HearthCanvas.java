package com.milton.hearthstone.Hearthstone;
import java.awt.*;

import javax.swing.ImageIcon;

import java.applet.*;

public class HearthCanvas extends Canvas 
{
	//public Image board = Toolkit.getDefaultToolkit().getImage(img);
	public Card[] drawCards= new Card[14];
	

	public void paint(Graphics g)
	{
		//System.out.println("paint");
		//g.drawImage(board, 0, 0, 949, 593, this);\
		g.drawString("asdfa", 3, 43);
		drawCard(g,drawCards);
	}

	public void drawCard(Graphics g, Card[] cards)
	{
		for(Card p: cards){
			Image card= Toolkit.getDefaultToolkit().getImage(p.name+".jpg");
			g.drawImage(card, 0, 0,this);
		}
				
	}


}