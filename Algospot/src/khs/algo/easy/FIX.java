package khs.algo.easy;

import java.util.Scanner;

// https://algospot.com/judge/problem/read/FIX
public class FIX {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int numCnt = sc.nextInt();
			int resultCnt = 0;

			for(int i=1,size=numCnt;i<=size;i++) {
				resultCnt += (i == sc.nextInt()) ? 1 : 0;
			}
			
			System.out.println(resultCnt);
		}
		
		sc.close();
	}
}
