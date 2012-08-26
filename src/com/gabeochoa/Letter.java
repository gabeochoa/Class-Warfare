package com.gabeochoa;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Letter {

	BufferedImage letterValue;
	String ID;
	
	public Letter(String ID, BufferedImage LV)
	{
		this.ID = ID;
		this.letterValue = LV;
	}

	public static BufferedImage toBufferedImage(Image image) {

		//http://www.exampledepot.com/egs/java.awt.image/Image2Buf.html
		 if (image instanceof BufferedImage) {
		        return (BufferedImage)image;
		    }

		    // This code ensures that all the pixels in the image are loaded
		    image = new ImageIcon(image).getImage();
		    // Determine if the image has transparent pixels; for this method's
		    // implementation, see Determining If an Image Has Transparent Pixels

		    // Create a buffered image with a format that's compatible with the screen
		    BufferedImage bimage = null;
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    try {
		        // Determine the type of transparency of the new buffered image
		        int transparency = Transparency.BITMASK;
		       
		        // Create the buffered image
		        GraphicsDevice gs = ge.getDefaultScreenDevice();
		        GraphicsConfiguration gc = gs.getDefaultConfiguration();
		        bimage = gc.createCompatibleImage(
		            image.getWidth(null), image.getHeight(null), transparency);
		    } catch (HeadlessException e) {
		        // The system does not have a screen
		    }

		    return bimage;
	}
}
