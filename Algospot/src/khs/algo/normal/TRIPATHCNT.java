package khs.algo.normal;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/TRIPATHCNT
 * 2016-05-09 20:30 ~ 21:30 하다보니 TRIANGLEPATH부터 품...
 * 2016-05-10 20:20 ~ 21:30 교재 참고해서 성공.
 * 	path(y, x)에서  얻은 최대값으로 삼각형을 재구성 했을 때 최대 경로 수를 구할 수 있는 새로운 재귀 함수 count(y, x)를 구한게 핵심 아이디어였다.
 * 	메모이제이션도 각각 써주는게 핵심. 마치 두 문제가 한 문제에 섞인 듯한 문제다..
 */
public class TRIPATHCNT {
	static int[][] map;
	static int[][] cache;
	static int[][] cntCache;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();
			map = new int[n][n];
			cache = new int[n][n];
			cntCache = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<=i;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 풀이
			System.out.println(count(0, 0));
			
			/*for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(cache[i][j] + ", ");
				}
				System.out.println();
			}*/
		}
		sc.close();
	}
	
	/*
	 * (y, x) 을 시작점으로 할 때 구간최대값을 가지는 경로의 수
	 */
	static int count(int y, int x) {
		if(y == map.length-1)
			return 1;
		
		if(cntCache[y][x] != 0)
			return cntCache[y][x];
		
		int lPath = path(y+1, x);
		int rPath = path(y+1, x+1);
		
		if(lPath > rPath) {
			return cntCache[y][x] = count(y+1 ,x);
		} else if(lPath < rPath) {
			return cntCache[y][x] = count(y+1 ,x+1);
		} else {
			return cntCache[y][x] = count(y+1 ,x) + count(y+1 ,x+1);
		}
	}
	
	/*
	 * (y, x) 을 시작점으로 할 때의 구간최대값
	 */
	static int path(int y, int x) {
		if(y == map.length - 1)
			return map[y][x];
		
		if(cache[y][x] != 0) {
			//System.out.println("ca " + y + ", " + x);
			return cache[y][x];
		}
			
		//System.out.println(cnt++);
		return cache[y][x] = Math.max(path(y+1, x) + map[y][x], path(y+1, x+1) + map[y][x]);
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