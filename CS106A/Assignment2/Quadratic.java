/*
 * File: Quadratic.java
 * --------------------
 * This program is a stub for the Quadratic problem, which finds the
 * roots of the quadratic equation.
 */

import acm.program.*;

public class Quadratic extends ConsoleProgram {

	public void run() {
		// You fill this in
		println("Enter coefficients for the quadratic equation:");
		double a;
		while((a = readDouble("a: ")) == 0) {
			println("a should not be zero, please reentre");
		}
			
		double b = readDouble("b: ");
		double c = readDouble("c: ");
		
		double x = b * b - 4 * a * c;
		
		if( x < 0) {
			println("There is no solution for this equation!");
			return;
		}
		
		println("The first solution is " + ((-b + Math.sqrt(x)) / (2 * a)));
		println("The second solution is " + ((-b - Math.sqrt(x)) / (2 * a)));
	}

}

