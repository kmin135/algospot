package khs.algo.normal;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/NUMB3RS
 */
public class NUMB3RS {
	private static int[][] map;	// 주어진 마을 지도
	private static int[] towns;	// 확률을 계산할 마을 번호들
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();	// 마을 수
			int d = sc.nextInt();	// 탈출 후 경과 일수
			int p = sc.nextInt();	// 교도소가 있는 마을 번호
			map = new int[n][n];
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			int t = sc.nextInt();	// 확률을 계산할 마을의 수
			towns = new int[t];
			for(int i=0;i<towns.length;i++) {
				towns[i] = sc.nextInt();
			}
			
			
		}
		sc.close();
	}
}
