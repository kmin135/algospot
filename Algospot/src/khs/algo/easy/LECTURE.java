package khs.algo.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// https://algospot.com/judge/problem/read/LECTURE
public class LECTURE {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			String input = sc.next();
			
			List<String> splitList = new ArrayList<>();
			for(int i=0,size=input.length();i<size;i+=2) {
				splitList.add(input.substring(i, i+2));
			}
			
			Collections.sort(splitList);
			
			for(int i=0,size=splitList.size();i<size;i++) {
				System.out.print(splitList.get(i));
			}
			System.out.println();
		}
		
		sc.close();
	}
}
