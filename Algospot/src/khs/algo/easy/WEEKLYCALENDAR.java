package khs.algo.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//https://algospot.com/judge/problem/read/WEEKLYCALENDAR
public class WEEKLYCALENDAR {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Map<String, Integer> day2intMap = new HashMap<>();
		day2intMap.put("Sunday", 0);
		day2intMap.put("Monday", 1);
		day2intMap.put("Tuesday", 2);
		day2intMap.put("Wednesday", 3);
		day2intMap.put("Thursday", 4);
		day2intMap.put("Friday", 5);
		day2intMap.put("Saturday", 6);
		
		int tc = sc.nextInt();
		while(tc-- > 0) {
			int m = sc.nextInt();
			int d = sc.nextInt();
			String day = sc.next();
			
			int beforeFinalDay = getFinalDayFromMonth((m - 1 != 0) ? m - 1 : 12);
			int thisFinalDay = getFinalDayFromMonth(m);
			
			int[] resultDays = new int[7];
			int standardIdx = day2intMap.get(day);
			resultDays[standardIdx] = d;
			for(int i=0,size=resultDays.length;i<size;i++) {
				if(i == standardIdx) {
					continue;
				}
				int iValue = resultDays[standardIdx] - (standardIdx - i);
				if(iValue < 1) {
					iValue = beforeFinalDay + iValue;
				} else if(iValue > thisFinalDay) {
					iValue = iValue - thisFinalDay;
				}
				resultDays[i] = iValue;
			}
			for(int i=0,size=resultDays.length;i<size;i++) {
				System.out.print(resultDays[i] + ((i < size-1) ? " " : ""));
			}
			System.out.println();
		}
	}
	
	private static int getFinalDayFromMonth(int m) {
		int finalDay = 0;
		switch(m) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			finalDay = 31;
			break;
		case 2:
			finalDay = 28;
			break;
		case 4: case 6: case 9: case 11:
			finalDay = 30;
			break;
		}
		return finalDay;
	}
}
