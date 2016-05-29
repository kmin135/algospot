package khs.algo.easy;

import java.util.Scanner;

/**
 * 시작 : 16-04-13 15:00
 * 종료 : 16-04-13 15:33
 * https://algospot.com/judge/problem/read/JUMPGAME
 * 
 * 
최악의 입력. 동적 계획법을 쓰자!
1
7
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 1
1 1 1 1 1 1 2
1 1 1 1 1 2 0 
 */
public class JUMPGAME {
	private static int[][] cache = new int[100][100];
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();

		while(tc-- > 0) {
			int n = sc.nextInt();
			int[][] map = new int[n][n];
			
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// start
			long st = System.nanoTime();

			boolean result = move(map, 0, 0);
			cache = new int[100][100];
			/* 걍 새로만드는게 빠르다...
			 * for(int i=0;i<cache.length;i++) {
				for(int j=0;j<cache[i].length;j++) {
					if(cache[i][j] == 1)
						cache[i][j] = 0;
				}
			}*/
			System.out.println("# elapsed " + (System.nanoTime() - st) + " ns ...");

			if(result) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			// end
		}
		
		sc.close();
	}
	
	static boolean move(int[][] map, int nx, int ny) {
		if(nx < 0 || nx > map.length-1 || ny < 0 || ny > map.length-1) {
			return false;
		} else if(nx == map.length-1 && ny == map.length-1) {
			return true;
		}
		
		if(cache[ny][nx] == 1) 
			return false;
		
		if(move(map, nx+map[ny][nx], ny) || move(map, nx, ny+map[ny][nx])) {
			return true;
		}
		cache[ny][nx] = 1;
		
		return false;
	}
}
