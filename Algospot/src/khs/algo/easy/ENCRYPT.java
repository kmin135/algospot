package khs.algo.easy;

import java.util.Scanner;

// https://algospot.com/judge/problem/read/ENCRYPT
public class ENCRYPT {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			String targetStr = sc.next();
			long startTime = System.currentTimeMillis();
			char[] targetCharArr = targetStr.toCharArray();
			char[] resultCharArr = new char[targetCharArr.length];
			
			int evenIdx = 0;
			int oddIdx = Math.round(targetStr.length() / 2f);
			for(int i=0;i<targetCharArr.length;i++) {
				if(i % 2 == 0) {
					resultCharArr[evenIdx++] = targetCharArr[i];
				} else {
					resultCharArr[oddIdx++] = targetCharArr[i];
				}
			}
			
			System.out.println(String.valueOf(resultCharArr));
			long endTime = System.currentTimeMillis();
			System.out.println((endTime - startTime) + " ms 경과");
		}
		sc.close();
	}
}
