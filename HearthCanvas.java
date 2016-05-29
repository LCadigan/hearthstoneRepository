package com.milton.hearthstone.Hearthstone;
import java.awt.*;

public class HearthCanvas extends Canvas
{
	public Image board = getImage()
	

	public void paint(Graphics g)
	{
		//System.out.println("paint");
		g.drawImage(board, 0, 0, 300, 949, 593, this);
	}



}