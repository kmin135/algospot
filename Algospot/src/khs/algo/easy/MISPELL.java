package khs.algo.easy;

import java.io.IOException;
import java.util.Scanner;

// https://algospot.com/judge/problem/read/MISPELL
public class MISPELL {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		sc.nextLine();
		int printIdx = 1;
		
		while(tc-- > 0) {
			String[] inputs = sc.nextLine().split(" ");
			int misIdx = Integer.parseInt(inputs[0]);
			String misStr = inputs[1];
			
			String left = misStr.substring(0, misIdx-1);
			String right = misStr.substring(misIdx, misStr.length());
			
			System.out.println(printIdx++ + " " + (left+right));
		}
		
		sc.close();
	}
}
