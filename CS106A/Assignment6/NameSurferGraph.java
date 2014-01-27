/*
 * File: NameSurferGraph.java
 * --------------------------
 * This class is responsible for updating the graph whenever the
 * list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.util.RandomGenerator;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class NameSurferGraph extends ResizableGCanvas
                             implements NameSurferConstants {

/**
 * Creates a new NameSurferGraph object that displays the data.
 */
	public NameSurferGraph() {
		// You fill in the details of the constructor
		entryList = new ArrayList<NameSurferEntry>();


	}

/**
 * Clears the list of name surfer entries stored inside this class.
 */
	public void clear() {
		// You fill this in
		removeAll();
		entryList.clear();
	}

/**
 * Adds a new NameSurferEntry to the list of entries on the display.
 * Note that this method does not actually draw the graph, but
 * simply stores the entry; the graph is drawn by calling update.
 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in
		for(int i = 0; i < entryList.size(); i++) {
			if(entryList.get(i).getName().equals(entry.getName()))
				return;
		}
		entryList.add(entry);

	}

/**
 * Updates the display image by deleting all the graphical objects from
 * the canvas and then reassembling the display according to the list
 * of entries.  Your application must call update after calling either
 * clear or addEntry; update is also called automatically whenever the
 * size of the canvas changes.
 */
	public void update() {
		// You fill this in
		removeAll();
		addGrid();
		

		for(int i = 0; i < entryList.size(); i++) {
			//System.out.println("entryList's size" + entryList.size());
			NameSurferEntry entry = entryList.get(i);
			String name = entry.getName();
			rgen.setSeed(i);
			Color c = rgen.nextColor();
			for(int j = 0; j < NDECADES - 1; j++) {
				int rank1 = entry.getRank(1900 + j * 10);
				int rank2 = entry.getRank(1900 + (j + 1) * 10);
				
				if(rank1 == 0)
					rank1 = MAX_RANK;
				if(rank2 == 0)
					rank2 = MAX_RANK;
				
				GLine Gline = new GLine(getWidth() / 12 * j,
						getHeight() / 20 * (1 + 18 * rank1 / MAX_RANK),
						getWidth() / 12 * (j + 1),
						getHeight() / 20 * (1 +  18 * rank2 / MAX_RANK));
				Gline.setColor(c);
				add(Gline);
				
				GLabel Glabel = new GLabel(name + " " + rank1 % MAX_RANK);
				Glabel.setColor(c);
				add(Glabel, getWidth() / 12 * j,
						getHeight() / 20 * (1 + 18 * rank1 / MAX_RANK));
				
				if(j == NDECADES - 2) {
					GLabel Glabel2010 = new GLabel(name + " " + rank2 % MAX_RANK);
					Glabel2010.setColor(c);
					add(Glabel2010, getWidth() / 12 * (j + 1),
							getHeight() / 20 * (1 + 18 * rank2 / MAX_RANK));					
				}
					
			}

		}
		
	}
	
	private void addGrid() {
		for(int i = 0; i < NDECADES - 1; i++) {
			GLine Gline = new GLine(getWidth() / 12 * (i + 1) , 0, 
					getWidth() / 12 * (i + 1), getHeight());
			add(Gline);
			GLabel Glabel = new GLabel("" + (1900 + i * 10));//TODO: 1900 final variable
			add(Glabel, getWidth() / 12 * i + Glabel.getWidth() / 2,
					getHeight() * 19 / 20 + Glabel.getHeight());
		}
		GLabel Glabel = new GLabel("" + (1900 + (NDECADES - 1) * 10));
		add(Glabel, getWidth() / 12 * (NDECADES - 1) + Glabel.getWidth() / 2,
				getHeight() * 19 / 20 + Glabel.getHeight());
		
		GLine Gline1 = new GLine(0, getHeight() / 20,
				getWidth(), getHeight() / 20);
		add(Gline1);
		GLine Gline2 = new GLine(0, getHeight() * 19 / 20,
				getWidth(), getHeight() * 19 / 20);
		add(Gline2);

	}
	
	private ArrayList<NameSurferEntry> entryList;
	private RandomGenerator rgen = RandomGenerator.getInstance();

}
