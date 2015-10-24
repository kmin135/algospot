package khs.algo.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

// https://algospot.com/judge/problem/read/XHAENEUNG
public class XHAENEUNG {
	private static Map<String, Integer> string2intMap;
	private static Map<Integer, String> int2stringMap;
	static {
		string2intMap = new HashMap<>();
		string2intMap.put("zero", 0);
		string2intMap.put("one", 1);
		string2intMap.put("two", 2);
		string2intMap.put("three", 3);
		string2intMap.put("four", 4);
		string2intMap.put("five", 5);
		string2intMap.put("six", 6);
		string2intMap.put("seven", 7);
		string2intMap.put("eight", 8);
		string2intMap.put("nine", 9);
		string2intMap.put("ten", 10);
		
		int2stringMap = new HashMap<>();
		Iterator<String> iter = string2intMap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			int2stringMap.put(string2intMap.get(key), key);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		while(tc-- > 0) {
			String left = sc.next();
			String oper = sc.next();
			String right = sc.next();
			sc.next();	// skip "="
			String result = sc.next();

			int trueResultI = -1;
			switch(oper) {
			case "+":
				trueResultI = string2intMap.get(left) + string2intMap.get(right);
				break;
			case "-":
				trueResultI = string2intMap.get(left) - string2intMap.get(right);
				break;
			case "*":
				trueResultI = string2intMap.get(left) * string2intMap.get(right);
				break;
			}
			
			if(trueResultI < 0 || trueResultI > 10) {
				System.out.println("No");
			} else {
				String trueResultS = int2stringMap.get(trueResultI);
				
				if(compareStr(result, trueResultS)) {
					System.out.println("Yes");
				} else {
					System.out.println("No");
				}
			}			
		}
	}
	
	private static boolean compareStr(String left, String right) {
		if(left.length() != right.length()) {
			return false;
		}
		char[] leftCharArr = left.toCharArray();
		char[] rightCharArr = right.toCharArray();
		Arrays.sort(leftCharArr);
		Arrays.sort(rightCharArr);
		
		for(int i=0;i<leftCharArr.length;i++) {
			if(leftCharArr[i] != rightCharArr[i]) {
				return false;
			}
		}		
		return true;
	}
}
