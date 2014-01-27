/*
 * File: GraphicsHierarchy.java
 * ----------------------------
 * This program is a stub for the GraphicsHierarchy problem, which
 * draws a partial diagram of the acm.graphics hierarchy.
 */

import javax.swing.JMenuItem;

import acm.program.*;
import acm.graphics.*;

public class GraphicsHierarchy extends GraphicsProgram {
	private static final int BOX_WIDTH = 100;
	private static final int BOX_HEIGHT = 40;
	
	
	public void run() {
		// You fill this in
		
		//define box distance in x way
		int box_dis = BOX_WIDTH / 4;
		double x;
		double y;
		double xAdd;
		double yAdd;

		
		// add the GObject box in the graphic
		GRect boxGobject = new GRect((getWidth() - BOX_WIDTH) / 2, getHeight() / 2 - BOX_HEIGHT * 2,
				BOX_WIDTH, BOX_HEIGHT);
		add(boxGobject);		
		GLabel labelGobject = new GLabel("GObject");
		labelGobject.setFont("Times-18");
		xAdd = (BOX_WIDTH  - labelGobject.getWidth()) / 2;
		yAdd = (BOX_HEIGHT + labelGobject.getAscent()) / 2;
		add(labelGobject, (getWidth() - BOX_WIDTH ) / 2 + xAdd, getHeight() / 2 - BOX_HEIGHT * 2 + yAdd);
		
		// add the GLabel box in the graphic
		x = (getWidth() - BOX_WIDTH * 4 - box_dis * 3 ) / 2;
		y = getHeight() / 2 + BOX_HEIGHT * 2;
		GRect boxGLabel = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(boxGLabel);		
		GLabel labelGLabel = new GLabel("GLabel");
		labelGLabel.setFont("Times-18");
		xAdd = (BOX_WIDTH  - labelGLabel.getWidth()) / 2;
		yAdd = (BOX_HEIGHT + labelGLabel.getAscent()) / 2;
		add(labelGLabel, x + xAdd, y + yAdd);
		
		GLine Gline1 = new GLine((getWidth() - BOX_WIDTH) / 2 + BOX_WIDTH / 2, getHeight() / 2 - BOX_HEIGHT * 2 + BOX_HEIGHT,
				x + BOX_WIDTH / 2, y);
		add(Gline1);
		
		// add the GLine box in the graphic
		x += (BOX_WIDTH + box_dis);
		y = getHeight() / 2 + BOX_HEIGHT * 2;
		GRect boxGLine = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(boxGLine);		
		GLabel labelGLine = new GLabel("GLine");
		labelGLine.setFont("Times-18");
		xAdd = (BOX_WIDTH  - labelGLine.getWidth()) / 2;
		yAdd = (BOX_HEIGHT + labelGLine.getAscent()) / 2;
		add(labelGLine, x + xAdd, y + yAdd);	
		
		GLine Gline2 = new GLine((getWidth() - BOX_WIDTH) / 2 + BOX_WIDTH / 2, getHeight() / 2 - BOX_HEIGHT * 2 + BOX_HEIGHT,
				x + BOX_WIDTH / 2, y);
		add(Gline2);
		
		// add the GOval box in the graphic
		x += (BOX_WIDTH + box_dis);
		y = getHeight() / 2 + BOX_HEIGHT * 2;
		GRect boxGOval = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(boxGOval);
		GLabel labelGOval = new GLabel("GOval");
		labelGOval.setFont("Times-18");
		xAdd = (BOX_WIDTH  - labelGLine.getWidth()) / 2;
		yAdd = (BOX_HEIGHT + labelGLine.getAscent()) / 2;
		add(labelGOval, x + xAdd, y + yAdd);	
		
		GLine Gline3 = new GLine((getWidth() - BOX_WIDTH) / 2 + BOX_WIDTH / 2, getHeight() / 2 - BOX_HEIGHT * 2 + BOX_HEIGHT,
				x + BOX_WIDTH / 2, y);
		add(Gline3);

		// add the GRect box in the graphic
		x += (BOX_WIDTH + box_dis);
		y = getHeight() / 2 + BOX_HEIGHT * 2;
		GRect boxGRect = new GRect(x, y, BOX_WIDTH, BOX_HEIGHT);
		add(boxGRect);
		GLabel labelGRect = new GLabel("GOval");
		labelGRect.setFont("Times-18");
		xAdd = (BOX_WIDTH  - labelGLine.getWidth()) / 2;
		yAdd = (BOX_HEIGHT + labelGLine.getAscent()) / 2;
		add(labelGRect, x + xAdd, y + yAdd);
		
		GLine Gline4 = new GLine((getWidth() - BOX_WIDTH) / 2 + BOX_WIDTH / 2, getHeight() / 2 - BOX_HEIGHT * 2 + BOX_HEIGHT,
				x + BOX_WIDTH / 2, y);
		add(Gline4);		
	}

}
