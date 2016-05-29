package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/TILING2
 * 교재 1권 253
 * 16-05-05 16:00 ~ 16:40 1차 자체 풀이. (solve()) 규칙성을 찾아내지 않고 단순하게 완전탐색 -> DP로 바꾼거라 코드가 장황하다.
 * 	2차는 교재의 모범 코드(solve2()). 타일링하는 방법을 2가지로 단순화해서 코드도 확 짧아지고 속도도 10배 이상 빠르고 메모리도 훨씬 덜 먹는다.
 */
public class TILING2 {
	static int MOD = 1000000007;
	static int[][] square;
	static int[][] cache;
	static int[] cache2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();
			// 풀이 1
			cache = new int[2][n];
			for(int i=0;i<cache.length;i++){
				for(int j=0;j<cache[i].length;j++) {
					cache[i][j] = -1;
				}
			}
			square = new int[2][n];
			long st = System.nanoTime();
			System.out.println(solve());
			System.out.println("풀이 1 : " + (System.nanoTime() - st) + " ns...");
			
			// 풀이 2
			cache2 = new int[n+1];
			for(int i=0;i<cache2.length;i++){
				cache2[i] = -1;
			}
			st = System.nanoTime();
			System.out.println(solve2(n));
			System.out.println("풀이 2 : " + (System.nanoTime() - st) + " ns...");
		}
		sc.close();
	}
	
	/*
	 * 자르는 방법은
	 * 1. 
	 * O
	 * O
	 * 
	 * 2.
	 * OO
	 * OO
	 * 
	 * 일 수 밖에 없는 점을 이용했다.
	 * 예를 들어
	 * OO
	 *  OO
	 * 같은 타일링은 불가능하다!
	 * 
	 * y는 어차피 2로 고정되어있으므로 문제를 선으로 단순화하고 (2차원 -> 1차원)
	 * w만 1 또는 2씩 줄이는 코드다.
	 */
	static int solve2(int w) {
		if(cache2[w] != -1) {
			return cache2[w];
		}
		if(w <= 1) {
			return 1;
		}
		
		return cache2[w] = (solve2(w-2) + solve2(w-1)) % MOD;
	}
	
	/*
	 * 2 * n 크기의 사각형을 2 * 1 크기의 타일로 채우는 방법의 수를 구한다.
	 * 0 :
	 * OO
	 * 
	 * 1 :
	 * O
	 * O
	 */
	static int solve() {
		int x = -1;
		int y = -1;
		for(int i=0;i<square.length && y==-1;i++) {
			for(int j=0;j<square[i].length && x==-1;j++) {
				if(square[i][j] == 0) {
					x = j;
					y = i;
				}
			}
		}
		
		if(x == -1 && y == -1) {
			return 1;
		}
		if(cache[y][x] != -1) {
			return cache[y][x];
		}
		
		int res = 0;
		for(int i=0;i<2;i++) {
			if(fill(x, y, i, true)) {
				res = ((res%1000000007) + (solve()%1000000007))%1000000007;
				fill(x, y, i, false);
			}
		}
		cache[y][x] = res;
		return res;
	}
	
	/*
	 * shape
	 * 	0 : OO
	 * 	1 : O
	 * 		O
	 * wise
	 *  mode : 채우기
	 *  false : 비우기
	 *  
	 * return
	 * 	true : 정상
	 * 	false : index out
	 */
	static boolean fill(int x, int y, int shape, boolean mode) {
		if(mode && shape == 0 && (x+1) >= square[0].length) {
			return false;
		} else if(mode && shape == 1 && (y+1) >= square.length) {
			return false;
		}
		
		if(shape == 0) {
			square[y][x] = (mode) ? 1 : 0;
			square[y][x+1] = (mode) ? 1 : 0;
		} else if(shape == 1) {
			square[y][x] = (mode) ? 1 : 0;
			square[y+1][x] = (mode) ? 1 : 0;
		}
		return true;
	}
}
