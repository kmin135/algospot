package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/LIS
 * 연관문제 : https://algospot.com/judge/problem/read/JLIS
 *
 * 2016-04-16 결국 시간초과되서 책 보고 풀었는데도 1700ms로 턱걸이 (2000ms제한)
 * 2016-04-17 책(1권P234) 보고 getLongest3로 하니까 630ms정도 나온다. 캐시 초기화에 시간을 많이 소모해서 그런듯.
 */
public class LIS {
	static int[] cache = new int[500];
	static int[] cache2 = new int[501];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			// N <= 500
			int n = sc.nextInt();
			int[] arr = new int[n];
			for(int i=0;i<arr.length;i++) {
				// 각 정수는 1 이상 100,000 이하
				arr[i] = sc.nextInt();
			}
			
			/*
			 * getLongest2를 사용하면 외부에서도 시작 지점별로 순회해야하고 cache도 계속 초기화해야하는 단점이 있다.
			 * 예를 들어 n이 500이면 이 tc에서만 500번 cache를 초기화해야한다.!!!
			 * int max = getLongest2(arr, 0);
			for(int i=1;i<arr.length;i++) {
				cache = new int[500];
				int newVal = getLongest2(arr, i);

				if(newVal > max)
					max = newVal;
				
			}*/
			
			/*
			 * getLongest3는 -1이라는 가상의 포인트를 시작점으로 해서 함수 내에서 0부터 재귀가 일어나도록 한 함수이다.
			 * 코드가 깔끔할 뿐만 아니라 cache초기화를 각 tc에서 한 번씩만 하면된다.
			 *  -1이라는 가상의 지점을 카운트했으므로 최종결과에서 -1 해준다.
			 */
			int max = getLongest3(arr, -1)-1;
			System.out.println(max);
			cache2 = new int[501];
		}
		sc.close();
	}
	
	static int getLongest3(int[] arr, int i) {
		int result = cache2[i+1];
		if(result != 0) {
			return result;
		}
		
		result = 1;
		for(int j=i+1;j<arr.length;j++) {
			if(i==-1 || arr[j] > arr[i]) {
				cache2[i+1] = result = Math.max(result, getLongest3(arr, j) + 1);
			}
		}
		return result;
	}
	
	/**
	 * i번째부터 탐색했을 때의 순증가 순열의 최대 길이를 얻는다.
	 */
	static int getLongest2(int[] arr, int i) {
		int result = cache[i];
		if(result != 0) {
			return result;
		}
		
		result = 1;
		for(int j=i+1;j<arr.length;j++) {
			if(arr[j] > arr[i]) {
				cache[i] = result = Math.max(result, getLongest2(arr, j) + 1);
			}
		}
		return result;
	}
	
	/*
	 * 16-04-16 시간초과
	 * static int getLongest(int[] arr, int i, int depth, int max) {
		if(arr.length-1 == i) {
			return depth;
		}
		
		int maxDepth = depth;
		for(int j=i+1;j<arr.length;j++) {
			int newDepth = (cache[i][j] == 0) ? getLongest(arr, j, depth+(arr[j] > max ? 1 : 0), (arr[j] > max) ? arr[j] : max) : cache[i][j];
			if(newDepth > maxDepth) 
				maxDepth = newDepth;
			cache[i][j] = maxDepth;
		}
		return maxDepth;
	}*/
}
