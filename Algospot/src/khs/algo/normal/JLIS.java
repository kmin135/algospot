package khs.algo.normal;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/JLIS
 * 2016-04-16 첫 시도 실패.
 * 2016-04-17 책(p239) 살짝 참고해서 맞추긴했는데 맞춰놓고도 잘 이해가 안 간다. 공부하자.
 * 2016-04-18 대강 이해. 완전히 이해가 간건 아니라 나중에 이 글을 읽으면 다시 풀어볼 것.
 */
public class JLIS {
	static int[][] cache;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-- > 0) {
			// 1 <= n,m <= 100
			// 각 값은 signed int의 모든 값
			int n = sc.nextInt();
			int[] narr = new int[n];
			int m = sc.nextInt();
			int[] marr = new int[m];
			cache = new int[n+1][m+1];
			
			for(int i=0;i<n;i++) 
				narr[i] = sc.nextInt();
			for(int i=0;i<m;i++) 
				marr[i] = sc.nextInt();
			
			System.out.println(jlis(narr,marr,-1,-1)-2);
		}
		sc.close();
	}
	
	/*
	 * ns, ms의 초기값은 -1이고 각 위치에는 임의의 -∞ 값이 들어있는걸 전제로 한다.
	 * 이렇게 하면 0번째부터 순회가 발생하므로 jlis(...) 바깥에서 반복적으로 호출해줄 필요가 없다.
	 * 단, 이렇게 하면 가상 값이 각 배열에 들어있었기 때문에 JLIS의 기본 길이값은 2가되므로 (narr, marr이 빈 배열일 때를 상정해보자)
	 * 호출부에서 -2를 해줘야한다.
	 */
	static int jlis(int[] narr, int[] marr, int ns, int ms) {
		if(cache[ns+1][ms+1] != 0) {
			return cache[ns+1][ms+1];
		}
		
		int result = 2;
		
		// narr에서 marr에 중복되는건 계산하지 않는다.
		for(int nn=ns+1;nn<narr.length;nn++) {
			if(ns == -1 || narr[nn] > narr[ns]) {
				boolean existMs = false;
				for(int i=0;i<marr.length;i++) {
					if(marr[i] == narr[nn]) {
						existMs = true;
						break;
					}
				}
				if(!existMs) {
					result = Math.max(result, jlis(narr, marr, nn, ms)+1);
				}
			}
		}
		for(int mn=ms+1;mn<marr.length;mn++) {
			if(ms == -1 || marr[mn] > marr[ms]) {
				result = Math.max(result, jlis(narr, marr, ns, mn)+1);
			}
		}
		cache[ns+1][ms+1] = result;
		return result;
	}
}
