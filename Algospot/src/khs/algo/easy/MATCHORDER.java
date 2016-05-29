package khs.algo.easy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/MATCHORDER
 * 2016-04-21 약 30분 소요. 탐욕법 사용. 성공
 * 책은 이진트리를 써서 풀었던데 나도 해봐야겠다. 첫번째 푼건 배열 초기화 다시 안 할려고 9999라는 임의의 값을 사용했기 때문에 순회를 계속 해야한다는 단점이 있다.
 */
public class MATCHORDER {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();
			// 모든 레이팅은 [1, 4000] 정수
			int[] oArr = new int[n];
			for(int i=0;i<n;i++) {
				oArr[i] = sc.nextInt();
			}
			int[] myArr = new int[n];
			for(int i=0;i<n;i++) {
				myArr[i] = sc.nextInt();
			}
			
			// 1. 내 레이팅값을 오름차순 정렬
			Arrays.sort(myArr);
			
			int maxIdx = -1;
			int canWinIdx = -1;
			int cnt = 0;
			/*
			 * 2. 내 레이팅을 순회.
			 */
			for(int mi=0;mi<n;mi++) {
				maxIdx = -1;
				canWinIdx = -1;
				/*
				 * 3. 상대방 레이팅 중 이길 수 있는 것을 찾는다.
				 * 이 때 남은 상대 배열값중 가장 레이팅이 큰 idx(maxIdx)와
				 * 이길 수 있는 상대 배열값중 가장 레이팅이 작은 idx(canWinIdx)를 저장한다.
				 */
				for(int oi=0;oi<n;oi++) {
					if(oArr[oi] != 9999) {
						if(oArr[oi] > myArr[mi]) {
							if(maxIdx == -1 || oArr[maxIdx] < oArr[oi]) {
								maxIdx = oi;
							}
						} else {
							if(canWinIdx == -1 || oArr[canWinIdx] > oArr[oi]) {
								canWinIdx = oi;
							}
						}
					}
				}
				/*
				 * 4. 이번 순회중인 내 레이팅이 상대방을 이길 수 있으면 미리 찾아둔 상대의 가장 레이팅이 작은 idx에게 붙인다.
				 * 반면, 이길 수 있는 idx가 없으면 미리 찾아둔 상대 레이팅중 가장 큰 idx에 붙인다.
				 * 결과를 확정한 상대의 배열값은 9999로 바꾸고 더 이상 보지 않는다.
				 */
				if(canWinIdx != -1) {
					cnt++;
					oArr[canWinIdx] = 9999;
				} else {
					oArr[maxIdx] = 9999;
				}
			}
			System.out.println(cnt);
		}
		sc.close();
	}
}

/*
3
6
3000 2700 2800 2200 2500 1900
2800 2750 2995 1800 2600 2000
3
1 2 3
3 2 1
4
2 3 4 5
1 2 3 4


5
3
3
*/