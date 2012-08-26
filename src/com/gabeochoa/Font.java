package com.gabeochoa;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.gabeochoa.engine.Art;

public class Font {

	BufferedImage mainImage;
	
	String[] alphabet = 
		{
			"a","b","c","d","e","f","g","h",
	    	 "i","j","k","l","m","n","o","p",
	    	 "q","r","s","t","u","v","w","x",
	    	 "y","z","!","?","'",",",">","<",
	    	 "0","1","2","3","4","5","6","7",
	    	 "8","9","-","_",".","*"
	    };
	
	ArrayList <Letter> letters = new ArrayList();
	int columns = 8; 
	int imageSize = 6;
	
	public Font()
	{
		this.mainImage = Letter.toBufferedImage(Art.getImage("./res/alphabetWhiteNumbers.png"));
		
		BufferedImage temp = null;
		Letter tempLetter;
		int row,col;
		row = col = 0;
		
		for(String current: alphabet)
		{
			temp = mainImage.getSubimage(col*imageSize, row*imageSize, imageSize, imageSize);
			tempLetter = new Letter(current, temp);
			letters.add(tempLetter);
			col++;
			if(col >= columns)
			{
				col = 0; 
				row++;
			}
		}
	}
	
	public void printLine(String s, int textSize, int xPos, int yPos, Graphics g)
	{
		String tS;
		Letter tL;
		
		for(int i = 0; i < s.length(); i++)
        {
            tS = s.substring(i,i+1);
            for(int x = 0; x < letters.size(); x++)
            {
                tL = letters.get(x);
                if(tS.equalsIgnoreCase(tL.ID))
                {
                    g.drawImage(tL.letterValue,xPos+(i*textSize),yPos,textSize,textSize,null);
                    break;
                }
            }
        }
	}
	
	
}
