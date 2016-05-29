package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/PI
 * 2016-04-18 풀이시작. 20:00 ~ 22:00 실패.
 * 2016-04-19 풀이. 20:00 ~ 21:20 실패.
 * 2016-04-19 책 정말 살짝 참고해서 22:45 성공. 다시 분석이 필요하다.
 */
public class PI {
	static int[] cache;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			char[] arr = sc.next().toCharArray();
			int[] iarr = new int[arr.length];
			for(int i=0;i<arr.length;i++) {
				iarr[i] = arr[i] - 48;
			}
			cache = new int[iarr.length+5];
			System.out.println(pi(iarr, 0));
		}
		sc.close();
	}
	
	static int pi(int[] arr, int s) {
		if(arr.length == s) {
			return 0;
		}
		if(cache[s] != 0) {
			return cache[s];
		}
		int result = 987654321;
		for(int len=3;len<=Math.min(arr.length - s, 5);len++) {
			result = Math.min(result, nanido(arr, s, len) + pi(arr, s+len));
		}
		return cache[s] = result;
	}
	
	/*
	 * 한참 헤맨 틀린답. 
	 * static int pi(int[] arr, int s) {
		if(cache[s] != 0) {
			return cache[s];
		}
		int resultN = 987654321;
		int resultC = 987654321;
		for(int len=3;len<=Math.min(arr.length - s, 5);len++) {
			resultN = Math.min(resultN, pi(arr, s+len));
			resultC = Math.min(resultC, nanido(arr, s, len));
			
			if(resultN == 987654321 && resultC != 987654321) {
				resultN = 0;
			}
		}
		if(resultN == 987654321 && resultC != 987654321) {
			resultN = 0;
		}
		return cache[s] = resultC + resultN;
	}*/
	
	// len : 3 ~ 5
	static int nanido(int[] arr, int s, int len) {
		if(arr.length - (s+len) < 3 && arr.length - (s+len) != 0) {
			return 987654321;
		}
		int[] darr = new int[len-1];
		for(int i=s+1,j=0;i<s+len;i++,j++) {
			darr[j] = arr[i] - arr[i-1];
		}
		
		// 순서대로 조건 1,2,3,4.
		// 5는 모두 false일 때
		boolean[] flags = new boolean[4];
		for(int i=0;i<flags.length;i++) {
			flags[i] = true;
		}
		boolean allSame = true;
		boolean absSame = true;
		for(int i=1;i<darr.length;i++) {
			if(allSame && darr[i] != darr[0]) {
				allSame = false;
			}
			if(absSame && (darr[i] + darr[i-1] != 0)) {
				absSame = false;
			}
		}
		int result = 10;
		if(allSame) {
			switch(darr[0]) {
			case 0: result = 1; break;
			case 1: case -1: result = 2; break;
			default: result = (absSame) ? 4 : 5; break;
			}
		} else if(absSame) {
			result = 4;
		}
		
		/*System.out.print("[");
		for(int i=s;i<s+len;i++) {
			System.out.print(arr[i]+",");
		}
		System.out.println("] = " + result);*/
		return result;
	}
}
/**
1
139494639522473719070

답이 44인데 16-04-19 코드는 32로 나옴.
 */

/**
5 
12341234 
11111222 
12122222 
22222222 
12673939 

4
2
5
2
14
*/