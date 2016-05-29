package khs.algo.normal;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/POLY
 * 2016-05-19 모르겠어서 책 풀이 읽어보고 품. 포인트는 다음과 같은데 비슷한 유형 나오면 이걸 생각해낼 수 있을까...
 * 	P1. 첫 줄을 정해진 숫자만큼 정사각형을 채우고 남은 정사각형에서 그 다음줄을 채우고... 를 반복하면 우선 가로줄에 정사각형을 배치하는 경우의 수를 셀 수 있다. (세로로 단조기 때문에 가능한 방법!)
 *	P2. 첫 줄과 그 다음줄만 한정해서 생각해보면 두 줄을 섞는 방법의 수는 (first + second - 1)이다.
 *	아래는 first가 3, second가 2인 경우니까 4개 임을 알 수 있다.
 *	 OOO
 *	OO
 *
 *	OOO
 *	OO
 *
 *	OOO
 *	 OO
 *	
 *	OOO
 *	  OO
 *	P3. (가로줄에 배치하는 수 * 그 배치를 섞는 방법) 을 모든 가로줄에 적용하면... = 폴리오미노의 수 
 */
public class POLY {
	private static int[][] cache;
	private static final int MOD = 10000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();
			cache = new int[n+1][n+1];
			int outres = 0;
			for(int i=1;i<=n;i++) {
				outres = (outres + poly(n, i)) % MOD;
			}
			System.out.println(outres);
		}
		sc.close();
	}
	private static int poly(int n, int f) {
		if(n == f)
			return 1;
		
		if(cache[n][f] != 0)
			return cache[n][f];
		
		int res = 0;
		for(int s = 1, end = n - f; s <= end; s++) {
			int subres = ((f + s -1) * poly(n - f, s)) % MOD;
			res = (res + subres) % MOD;
		}
		return cache[n][f] = res;
	}
}

/*
3
2
4
92

2
19
4841817
*/