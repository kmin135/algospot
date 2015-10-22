package khs.algo.easy;

import java.util.Scanner;

// https://algospot.com/judge/problem/read/CONVERT
public class CONVERT {
	private static int printCnt = 1;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		while(tc-- > 0)	{
			double val = sc.nextDouble();
			String unit = sc.next();
			convert(val, unit);
		}
	}
	
	private static void convert(double targetVal, String unit) {
		final double kg2lb = 2.2046;
		final double lb2kg = 0.4536;
		final double l2g = 0.2642;
		final double g2l = 3.7854;
		
		double resultVal = -1.0;
		String resultUnit = "";
		
		switch(unit) {
		case "kg":
			resultVal = roundPlace(targetVal * kg2lb, 4);
			resultUnit = "lb";
			break;
		case "lb":
			resultVal = roundPlace(targetVal * lb2kg, 4);
			resultUnit = "kg";
			break;
		case "l":
			resultVal = roundPlace(targetVal * l2g, 4);
			resultUnit = "g";
			break;
		case "g":
			resultVal = roundPlace(targetVal * g2l, 4);
			resultUnit = "l";
			break;
		}
		
		System.out.printf("%d %.4f %s\n", printCnt++, resultVal, resultUnit);
	}
	
	private static double roundPlace(double val, int place) {
		double placeVal = (int)Math.pow(10, place);
		return Math.round(val * placeVal)/placeVal;
	}
}
