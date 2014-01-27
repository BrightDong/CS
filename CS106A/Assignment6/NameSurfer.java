/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	       // You fill this in, along with any helper methods
		graph = new NameSurferGraph();
		add(graph, CENTER);
		add(new JLabel("Name"), NORTH);
		nameText = new JTextField(JTEXT_FIELD_SIZE);
		nameText.addActionListener(this);
		add(nameText, NORTH);
		add(new JButton("Graph"), NORTH);
		add(new JButton("Clear"), NORTH);
		addActionListeners();

		database = new NameSurferDataBase(DATAFILENAME);

	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in
		if(e.getActionCommand().equals("Graph")) {
			String name = nameText.getText();
			if(name != null) {
				NameSurferEntry entry;
				name = name.substring(0, 1).toUpperCase() + 
							name.substring(1).toLowerCase();  //Upper lower case process
				entry = database.findEntry(name);
				if(entry != null) {
					graph.addEntry(entry);
					//String str = entry.toString();
					//System.out.println("Graph: " + str);
					graph.update();
				}

			}
		}else if(e.getActionCommand().equals("Clear")) {
			//println("Clear");
			graph.clear();
		}
		
	}
	private JTextField nameText;
	private final String DATAFILENAME = "NameSurferData.txt";
	private NameSurferDataBase database;
	private NameSurferGraph graph;
}
