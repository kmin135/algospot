package khs.algo.easy;

import java.util.Scanner;

// https://algospot.com/judge/problem/read/HELLOWORLD
public class HELLOWORLD {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			String name = sc.next();
			System.out.println("Hello, " + name + "!");
		}
		
		sc.close();
	}
}
