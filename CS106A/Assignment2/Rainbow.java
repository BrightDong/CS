/*
 * File: Rainbow.java
 * ------------------
 * This program is a stub for the Rainbow problem, which displays
 * a rainbow by adding consecutively smaller circles to the canvas.
 */

import java.awt.Color;

import acm.program.*;
import acm.graphics.*;

public class Rainbow extends GraphicsProgram {

	public void run() {
		// You fill this in
		GRect rect = new GRect(0, 0, getWidth(), getHeight());
		rect.setFilled(true);
		rect.setFillColor(Color.CYAN);
		add(rect);
		
		GOval oval = new GOval(-90, 10, getWidth() + 180, getWidth() + 180);
		oval.setFilled(true);
		oval.setFillColor(Color.RED);
		add(oval);

		GOval ovalO = new GOval(-90 + 10, 10 + 10, getWidth() + 180 - 20, getWidth() + 180 - 20);
		ovalO.setFilled(true);
		ovalO.setFillColor(Color.ORANGE);
		add(ovalO);

		GOval ovalY = new GOval(-90 + 20, 10 + 20, getWidth() + 180 - 40, getWidth() + 180 - 40);
		ovalY.setFilled(true);
		ovalY.setFillColor(Color.YELLOW);
		add(ovalY);
		
		GOval ovalG = new GOval(-90 + 30, 10 + 30, getWidth() + 180 - 60, getWidth() + 180 - 60);
		ovalG.setFilled(true);
		ovalG.setFillColor(Color.GREEN);
		add(ovalG);		
		
		GOval ovalB = new GOval(-90 + 40, 10 + 40, getWidth() + 180 - 80, getWidth() + 180 - 80);
		ovalB.setFilled(true);
		ovalB.setFillColor(Color.BLUE);
		add(ovalB);	
		
		GOval ovalM = new GOval(-90 + 50, 10 + 50, getWidth() + 180 - 100, getWidth() + 180 - 100);
		ovalM.setFilled(true);
		ovalM.setFillColor(Color.MAGENTA);
		add(ovalM);	
		
		GOval ovalC = new GOval(-90 + 60, 10 + 60, getWidth() + 180 - 120, getWidth() + 180 - 120);
		ovalC.setFilled(true);
		ovalC.setFillColor(Color.CYAN);
		add(ovalC);	
		
	}

}