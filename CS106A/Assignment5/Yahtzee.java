/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game from Assignment #5.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
/*
 * The run method uses the IODialog class from the acm.io package to initialize
 * the game.  It first allows the user to choose the number of players and then
 * reads in the name of each player.  Once the initialization is complete, the
 * run method initializes the Yahtzee display and calls the playGame method.
 */
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players", 1, MAX_PLAYERS);
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
		ui = new YahtzeeUI(playerNames);
		playGame();
	}

/*
 * This method plays a single game of Yahtzee.
 */
	private void playGame() {
		// You fill this in
		scoreArray = new int[nPlayers][N_CATEGORIES];
		selectFlag = new int[nPlayers][N_CATEGORIES];
		for(int i = 0; i < nPlayers; i++) {  // initial the total value to zero, and selectFlag
			scoreArray[i][TOTAL] = 0;
			for(int j = 0; j < N_CATEGORIES; j++) {
				selectFlag[i][j] = 0;
			}
		}
		
		//play dice for every player, for N_SCORING_CATEGORIES turns
		for(int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for(int j = 0; j < nPlayers; j++) {
				rollDice(j);
			}
		}

		// calculate upperscore, upper bonus, lowerscore, and update them to the screen
		calcFinal();

		//find out the winner. and print the result.
		int winner = 0;
		for(int i = 1; i < nPlayers; i++) {  // initial the total value to zero
			if(scoreArray[i][TOTAL] > scoreArray[winner][TOTAL])
				winner = i;
		}		
		ui.printMessage(playerNames[winner] + "win the game with score" 
				+ scoreArray[winner][TOTAL] + " !");
	}
	
	private void calcFinal() {
		for(int i = 0; i < nPlayers; i++) {  // initial the total value to zero
			scoreArray[i][UPPER_SCORE] = 0;
			scoreArray[i][LOWER_SCORE] = 0;
			
			for(int j = 0; j < UPPER_SCORE; j++)
				scoreArray[i][UPPER_SCORE] += scoreArray[i][j];

			if(scoreArray[i][UPPER_SCORE] > UPPER_BONUS_THREADHOLD) {
				scoreArray[i][UPPER_BONUS] = UPPER_BONUS_SCORE;
				scoreArray[i][TOTAL] += scoreArray[i][UPPER_BONUS];
			}else {
				scoreArray[i][UPPER_BONUS] = 0;
			}
			
			for(int j = THREE_OF_A_KIND; j < LOWER_SCORE; j++)
				scoreArray[i][LOWER_SCORE] += scoreArray[i][j];
	
			ui.updateScorecard(UPPER_SCORE, i, scoreArray[i][UPPER_SCORE]);
			ui.updateScorecard(UPPER_BONUS, i, scoreArray[i][UPPER_BONUS]);
			ui.updateScorecard(TOTAL, i, scoreArray[i][TOTAL]);
			ui.updateScorecard(LOWER_SCORE, i, scoreArray[i][LOWER_SCORE]);
		}		
	}
	
	private void rollDice(int player) {
		ui.printMessage(playerNames[player] + "'s turn! " +
			"Click the \"Roll Dice\" button to roll the dice.");
		ui.waitForPlayerToClickRoll(player);		
		int[] dice = new int[N_DICE];
		for(int i = 0; i < N_DICE; i++) {
				dice[i] = rgen.nextInt(DICE_MIN, DICE_MAX);
		}
		ui.displayDice(dice);		

		for(int j = 0; j < 2; j++) {				
			ui.printMessage("Select the dice you wish to re-roll and " +
												"click \"Roll Again\"");
			ui.waitForPlayerToSelectDice();
			for(int i = 0; i < N_DICE; i++) {
				if(ui.isDieSelected(i))
			dice[i] = rgen.nextInt(DICE_MIN, DICE_MAX);
			}
			ui.displayDice(dice);
		}		
		ui.printMessage("Select a category for this roll");	
		int category;
		while(true) {
			category = ui.waitForPlayerToSelectCategory();
			if (selectFlag[player][category] == 1)
				ui.printMessage("The category has already been selected! " +
						"Select another category.");
			else {
				selectFlag[player][category] = 1;
				break;
			}			
		}		
		if(YahtzeeMagicStub.checkCategory(dice, category)) { //TODO: implement checkCategory
			int score = calcSum(dice, category);
			scoreArray[player][category] = score;
			scoreArray[player][TOTAL] += score;
			ui.updateScorecard(category, player, scoreArray[player][category]);
			ui.updateScorecard(TOTAL, player, scoreArray[player][TOTAL]);			
			
		}else
			ui.updateScorecard(category, player, 0);		
	}
	
	private int calcSum(int[] dice, int category) {
		int score = 0;
		switch(category) {
		case ONES: case TWOS: case THREES: case FOURS: case FIVES: case SIXES:
			for(int i = 0; i < N_DICE; i++) {
				if(dice[i] == (category + 1)) {
					score += (category + 1);
				}
			}
			break;
		case THREE_OF_A_KIND: case FOUR_OF_A_KIND: case CHANCE:
			for(int i = 0; i < N_DICE; i++) {
				score += dice[i];
			}
			break;
		case FULL_HOUSE:
			score = FULL_HOUSE_SCORE;
			break;			
		case SMALL_STRAIGHT:
			score = SMALL_STRAIGHT_SCORE;
			break;
		case LARGE_STRAIGHT:
			score = LARGE_STRAIGHT_SCORE;
			break;
		case YAHTZEE:
			score = YAHTZEE_SCORE;
			break;
		default:
			break;
		}
		return score;
	}
	

/* Set the window dimensions */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 500;

/* Private instance variables */

	private int nPlayers;
	private String[] playerNames;
	private YahtzeeUI ui;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private final int DICE_MIN = 1;
	private final int DICE_MAX = 6;
	private final int FULL_HOUSE_SCORE = 25;
	private final int SMALL_STRAIGHT_SCORE = 30;
	private final int LARGE_STRAIGHT_SCORE = 40;
	private final int YAHTZEE_SCORE = 50;
	private final int UPPER_BONUS_SCORE = 35;
	private int[][] scoreArray;
	private int[][] selectFlag;
	private final int UPPER_BONUS_THREADHOLD = 63;
}
