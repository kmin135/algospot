package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/ASYMTILING
 * 16-05-16 20:40 ~ 22:30 실패
 * 	find2와 같이 그럴듯한 점화식은 찾았는데 실패. 생각해보니 전체 타일링 수, 대칭 타일링 수를 따로 구해버리니까 이미 MOD 된 수 끼리 뺄셈이 발생해서 문제가 생긴다.
 * 	find3를 만들어서 함께 연산할 수 있는 방법을 찾아야할듯. 예를 들면 전체 타일링 수 - 대칭 타일링 수를 재귀적으로 구하는 함수라던가?
 * 16-05-17 20:30 ~ 21:30 교재 답보고 풀이
 *  find2는 운 좋게 얻어걸린 점화식. 계산해보면 n이 80이상일 때 짝수번째마다 값이 음수가 되는 경우가 생기더라. 원인은 find2내에서 (n-1), (n-2)일 때 한 값만 MOD를 넘을 경우 음수가 발생하기 때문.
 *  반면 교재 코드인 asym은 그런 문제가 없다. 사실 find2도 MOD연산을 잘 해주면 될 것 같기도 한데 솔직히 정확한 문제점을 모르겠다.
 *  또 asym은 예를 들면 홀수 일 때 왼쪽의 n/2만큼의 타일링 수가 곧 나머지 n/2 (홀수니까 가운데 1은 남기고)부분을 포함한 대칭 타일링수임을 파악하고 작성한 코드이다.
 *  이런 아이디어 내는게 어려울듯... ㅠㅠ 
 *  어쨋든 난이도가 '하' 라는데 명확하게 이해가 안 간 문제다.  
 */
public class ASYMTILING {
	static final int MOD = 1000000007;
	static int[] cache;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();
			cache = new int[n+1];
			System.out.println(asym(n));
			cache = new int[n+1];
			int r1 = find(n);
			
			cache = new int[n+1];
			int r2 = find2(n);
			System.out.println(r2);
			System.out.println(r1 - r2);
		}
		sc.close();
	}
	
	static int find2(int n) {
		if(n <= 2)
			return (n > 0) ? n : 0;
		
		if(cache[n] != 0)
			return cache[n];
		
		// find2(n - 1) > find(n -2)인데 find2(n-1)만 MOD를 넘고 find2(n-2)는 아닌 경우 res는 음수가 되버린다.
		int res = ((n % 2 == 1) ? find2(n - 1) - find2(n - 2) + MOD: find2(n - 1) + find2(n - 2)) % MOD;
		if(res < 0) {
			System.out.println("음수가 되는 타이밍이 있다.");
		}
		return cache[n] = res;
	}
	
	/*
	 * n칸 있을 때 타일링 수.
	 * TILING2.java 그대로 가져온 것.
	 */
	static int find(int n) {
		if(n <= 1)
			return 1;
		
		if(cache[n] != 0)
			return cache[n];
		
		return cache[n] = (find(n - 2) + find(n - 1)) % MOD;
	}
	
	static int asym(int n) {
		if(n % 2 == 1)
			return (find(n) - find(n/2) + MOD) % MOD;
		
		int res = find(n);
		res = (res - find(n/2-1) + MOD) % MOD;
		System.out.println(find(n/2-1));
		res = (res - find(n/2) + MOD) % MOD;
		System.out.println(find(n/2));
		return res;
	}
}

/*
3
2
4
92

0
2
913227494
*/