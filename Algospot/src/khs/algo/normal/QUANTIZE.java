package khs.algo.normal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/QUANTIZE
 * 160501 15:20 ~ 17:10 완전탐색으로 답은 맞춘 것 같은데 시간초과.
 * 	19:30 ~ 20:10 책 좀 봤음. 파라미터가 리스트 형태라 동적계획법 적용이 어려움. int 형 변수로 바꿀 수 있을까?
 * 160505 14:30 ~ 15:30 결국 int형으로 바꿀 아이디어는 책에서 얻음. 어찌어찌 정답... ㅠㅠ
 */
public class QUANTIZE {
	static int[] arr;
	static int[][] cache;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			//dp(4,2,pList);
			// 양자화할 대상의 수열 개수 [1, 100]
			int n = sc.nextInt();
			// 양자화 종류의 수 [1, 10]
			int s = sc.nextInt();
			arr = new int[n];
			// 1000이하의 자연수
			for(int i=0;i<arr.length;i++) {
				arr[i] = sc.nextInt();
			}
			Arrays.sort(arr);
			cache = new int[n+1][s+1];
			for(int i=0;i<cache.length;i++){
				for(int j=0;j<cache[i].length;j++) {
					cache[i][j] = -1;
				}
			}
			
			/*
			 * 아이디어 정리
			 * 1. 배열을 오름차순 정렬
			 * 2. s개로 배열을 쪼개는데 각 부분의 평균(각 부분 평균의 반올림한 자연수)과의 차의 제곱의 합이 최소가 되는 것.
			 */
			//List<Integer> pList = new ArrayList<Integer>();
			//pList.add(0);
			//System.out.println(comb(n, s-1, pList));
			System.out.println(dp(0, s));
		}
		sc.close();
	}
	
	/*
	 * (160502) 1차 구현이 dp적용이 힘든 건 구간을 모두 완성한 다음에 결과값을 계산하기 때문.
	 * 2차 구현에서는 앞에 확정한 구간의 결과값에 앞으로 만들 구간의 결과값을 더하는 형태로 해보자.
	 * 예를 들면... (이번 순회에서 정해진 구간의 결과값) + (앞으로 정해야할 구간의 결과값) 을 최소화 하는 형태의 함수.
	 * 
	 * (160505) 구현완료. 한 번의 for문이 끝나면 from에서 part개를 골라야할 때의 값을 알 수 있고 이를 통해 dp적용이 가능하다.
	 */
	static int dp(int from, int part) {
		if(cache[from][part] != -1) {
			return cache[from][part];
		}
		if(part >= arr.length) {
			return 0;
		}
		if(from >= arr.length) {
			return 0;
		}
		
		int res = 987654321;
		
		for(int partSize = (part == 1) ? (arr.length - from) : 1; from + partSize <= arr.length - (part - 1);partSize++) {
			res = Math.min(res, quantize(from, partSize) + dp(from+partSize, part-1));
		}
		cache[from][part] = res;
		return res;
	}
	
	static int quantize(int st, int size) {
		int et = st + size;
		int avg = 0;
		for(int i = st; i<et; i++) {
			avg += arr[i];
		}
		avg = (int)Math.round(avg / (double)size);
		
		int oneres = 0;
		for(int i=st;i<et;i++) {
			int sub = Math.abs(avg - arr[i]);
			oneres += sub * sub;
		}
		return oneres;
	}
	
	// 1차 구현. 완전 탐색하는 함수. 타임아웃발생함.
	static int comb(int n, int s, List<Integer> picked) {
		// 기저조건 : 양자화종류의 수가 더 많다면 1:1 매칭이되므로 자연스럽게 최저값은 0이 된다.
		if(n <= s) {
			return 0;
		}
		int si = (picked.size() == 1) ? 1 : picked.get(picked.size()-1) + 1;
		if(picked.size() == s+1) {
			int totres = 0;
			for(int i=0;i<s+1;i++) {
				// [st, et]
				int st = picked.get(i);
				int et = (i==s) ? n : picked.get(i+1);
				
				int avg = 0;
				for(int j=st;j<et;j++) {
					avg += arr[j];
				}
				avg = (int)Math.round(avg / (double)(et-st));
				
				int oneres = 0;
				for(int j=st;j<et;j++) {
					int sub = Math.abs(avg - arr[j]);
					oneres += sub * sub;
				}
				totres += oneres;
				System.out.println("st : " + st + ", et : " + et + ", res : " + oneres);
			}
			//System.out.println("tot = " + totres);
			return totres;
		}
		
		int res = 987654321;
		
		for(int i=si;i<n;i++) {
			picked.add(i);
			res = Math.min(res, comb(n, s, picked));
			picked.remove(picked.size()-1);
		}
		return res;
	}
}

/*
2
10 3
3 3 3 1 2 3 2 2 2 1
9 3
1 744 755 4 897 902 890 6 777

0
651

1
9 5
1 744 755 4 897 902 890 6 777

86
*/