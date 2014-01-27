/*
 * File: Pyramid.java
 * ------------------
 * This program is a stub for the Pyramid problem, which draws
 * a brick pyramid.
 */

import acm.program.*;
import acm.graphics.*;

public class Pyramid extends GraphicsProgram {
	
	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICK_IN_BASE = 12;

	public void run() {
		// You fill this in
		//int width = ;
		//int height = ;
		//int num = BRICK_IN_BASE;
		for(int num = BRICK_IN_BASE; num > 0; num--) {
			for(int i = 0; i < num; i++) {			
				GRect sq = new GRect(getWidth() / 2 - ((float)num / 2 - i) * BRICK_WIDTH, getHeight() - (BRICK_IN_BASE - num + 1) * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
				add(sq);
			}
		}
	}
}
