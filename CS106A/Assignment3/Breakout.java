/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/* Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/* Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/* Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/* Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/* Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/* Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/* Separation between bricks */
	private static final int BRICK_SEP = 4;

/* Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/* Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/* Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/* Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/* Number of turns */
	private static final int NTURNS = 3;
	
	// Number of bricks
	private static final int NBRICKS = NBRICK_ROWS * NBRICKS_PER_ROW;

/* Runs the Breakout program. */
	public void run() {
		// You fill this in, along with any subsidiary methods
		
		//stage 1, setup the bricks
		setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		
		for(int j = 0; j < NBRICK_ROWS; j++) {
			for(int i = 0; i < NBRICKS_PER_ROW; i++) {
				GRect rect = new GRect(BRICK_SEP / 2 + i * (BRICK_WIDTH + BRICK_SEP),
						BRICK_Y_OFFSET + j * (BRICK_HEIGHT + BRICK_SEP), 
						BRICK_WIDTH, BRICK_HEIGHT);
				rect.setFilled(true);
				switch(j) {
				case 0: 
				case 1:
					rect.setFillColor(Color.red);
					break;
				case 2:
				case 3:
					rect.setFillColor(Color.orange);
					break;	
				case 4: 
				case 5:
					rect.setFillColor(Color.yellow);
					break;
				case 6:
				case 7:
					rect.setFillColor(Color.green);
					break;	
				case 8: 
				case 9:
					rect.setFillColor(Color.cyan);
					break;
				default:
					rect.setFillColor(Color.black);
				}
				add(rect);
			}
		}
		
		// stage 2, add paddle
		paddle = new GRect(0,HEIGHT - PADDLE_Y_OFFSET, 
				PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.black);
		add(paddle);
		addMouseListeners();
		
		//step 3, create a ball, get it bounce off the walls
		ball = new GOval(WIDTH / 2, HEIGHT / 2, BALL_RADIUS * 2, BALL_RADIUS * 2);
		ball.setFilled(true);
		ball.setFillColor(Color.black);
		add(ball);

		vx = rgen.nextDouble(MIN_X_VELOCITY, MAX_X_VELOCITY);
		if (rgen.nextBoolean()) vx = - vx;
		vy = 3.0;
		waitForClick();
		int nbricks = NBRICKS;
		int nturns = NTURNS;
		while(true) {
			ball.move(vx, vy);
			double x = ball.getX();
			double y = ball.getY();
			if(x < 0 || x > WIDTH - BALL_RADIUS * 2)
				vx *= -1;
			if (y < 0)
				vy *= -1;
			if (y > HEIGHT) {   // failed	
				//vy *= -1;
				nturns--;
				if(nturns <=0) {
					GLabel label = new GLabel("GAME OVER! YOU LOST THE GAME!");
					label.setFont("Times-18");
					add(label, (WIDTH - label.getWidth()) / 2, (HEIGHT + label.getHeight()) / 2);	
					break;
				} else {
					GLabel label = new GLabel("FAILED! YOU STILL HAVE " + nturns + " TURNS!");
					label.setFont("Times-18");
					add(label, (WIDTH - label.getWidth()) / 2, (HEIGHT + label.getHeight()) / 2);
					waitForClick();
					remove(label);

					ball.setLocation(WIDTH / 2, HEIGHT / 2);
					vx = rgen.nextDouble(MIN_X_VELOCITY, MAX_X_VELOCITY);
					if (rgen.nextBoolean()) vx = - vx;
					vy = 3.0;
					waitForClick();

					continue;					
				}
			}

			//step 4: checking for collisions
			GObject collider = getCollidingObject();
			if(collider == paddle) {
				vy *= -1;
				ball.setLocation(ball.getX(), HEIGHT - PADDLE_Y_OFFSET - BALL_RADIUS * 2);
			}
			else if(collider != null) {
				vy *= -1;
				remove(collider);
				nbricks --;
				if(nbricks <= 0) {
					ball.setVisible(false);
					GLabel label = new GLabel("YOU WIN THE GAME!");
					label.setFont("Times-18");
					add(label, (WIDTH - label.getWidth()) / 2, (HEIGHT + label.getHeight()) / 2);	
					break;
				}					
			}			
			pause(PAUSE_TIME);
		}
		
	}
	
	public void mouseMoved(MouseEvent e) {
		float x = e.getX();
		if(x > WIDTH - PADDLE_WIDTH / 2)
			x = WIDTH - PADDLE_WIDTH / 2;
		else if(x < PADDLE_WIDTH / 2)
			x = PADDLE_WIDTH / 2;		
		// avoid ball glued to the paddle
		paddle.setLocation(x - PADDLE_WIDTH / 2, HEIGHT - PADDLE_Y_OFFSET);	
	}

	private GObject getCollidingObject() {
		double x = ball.getX();
		double y = ball.getY();
		GObject collider;
		
		if((collider = getElementAt(x, y)) != null)
			return collider;
		else if((collider = getElementAt(x + BALL_RADIUS * 2, y)) != null)
			return collider;
		else if((collider = getElementAt(x, y + BALL_RADIUS * 2)) != null)
			return collider;
		else if((collider = getElementAt(x + BALL_RADIUS * 2, y + BALL_RADIUS * 2)) != null)
			return collider;
		else
			return null;		
	}
	
	private GRect paddle;
	private GOval ball;
	private double vx;
	private double vy;
	
	private static final double PAUSE_TIME = 10;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private static final double MIN_X_VELOCITY = 1.0;
	private static final double MAX_X_VELOCITY = 3.0;

}
