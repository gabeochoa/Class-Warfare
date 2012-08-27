package com.gabeochoa.engine;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Art {

	
	public static Image getImage(String filename)
	{
		try {
			return ImageIO.read(Art.class.getResourceAsStream((filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
