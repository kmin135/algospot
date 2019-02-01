package khs.algo.easy;

import java.util.Scanner;

// https://algospot.com/judge/problem/read/HOTSUMMER
public class HOTSUMMER {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			int threshold = sc.nextInt();
			int totalConsume = 0;
			for(int i=0,size=9;i<size;i++) {
				totalConsume += sc.nextInt();
			}
			System.out.println((totalConsume <= threshold) ? "YES" : "NO");
		}
		
		sc.close();
	}
}
