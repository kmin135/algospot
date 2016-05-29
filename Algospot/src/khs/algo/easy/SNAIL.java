package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/SNAIL
 * 16-05-15 13:00 ~ 14:30
 * 
 * 책에서 기본 코드를 보고 풀어서 생각보다 금방 품.
 * 근데 처음으로 memory limit를 먹은 문제. 캐쉬도 최소한의 크기를 사용하도록 신경써야겠다.
 * 실수 출력도 잘 봐두고. %.8f !
 */
public class SNAIL {
	static int n;
	static int m;
	static double[][] cache;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			// 우물 깊이
			n = sc.nextInt();
			// 장마 기간 길이
			m = sc.nextInt();
			/*
			 * new double[n*2+1][m+1]; 로 초기화하니까 Memory Limit에 걸리더라...
			 * 어차피 m*2넘게 올라가지 않으니까 m*2로 바꿔버림.
			 */
			cache = new double[m*2+1][m+1];
			for(int i=0;i<cache.length;i++) {
				for(int j=0;j<cache[i].length;j++) {
					cache[i][j] = -1.0;
				}
			}
			
			System.out.printf("%.8f\n", climb(0, 0));
		}
		sc.close();
	}
	
	static double climb(int sDepth, int sDay) {
		if(sDay == m)
			return sDepth >= n ? 1 : 0;
			
		if(cache[sDepth][sDay] >= 0.0) {
			return cache[sDepth][sDay];
		}
		
		return cache[sDepth][sDay] = ((double)3/4) * climb(sDepth+2, sDay+1) + ((double)1/4) * climb(sDepth+1, sDay+1);
	}
}

/*
4
5 4
5 3
4 2
3 2

0.9960937500
0.8437500000
0.5625000000
0.9375000000

10^−7 이하의 상대/절대 오차가 있는 답은 정답으로 인정됩니다.
*/