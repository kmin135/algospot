package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/TRIPATHCNT
 * 2016-05-09 20:30 ~ 21:10
 */
public class TRIANGLEPATH {
	static int[][] map;
	static int[][] cache;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();
			map = new int[n][n];
			cache = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<=i;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 풀이
			System.out.println(dp(0, 0));
		}
		sc.close();
	}
	
	static int dp(int y, int x) {
		if(y >= map.length)
			return 0;
		
		if(cache[y][x] != 0)
			return cache[y][x];
		
		return cache[y][x] = Math.max(dp(y+1, x) + map[y][x], dp(y+1, x+1) + map[y][x]);
	}
}

/*
2
4
1
1 1 
1 1 1 
1 1 1 1 
4
9
5 7
1 3 2
3 5 5 6

8
3
*/