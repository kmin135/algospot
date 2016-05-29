package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/PICNIC
 *
 */
public class PICNIC {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int[][] friends = new int[n][n];
			for(int i=0;i<m;i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				friends[x][y] = friends[y][x] = 1; 
			}
			
			System.out.println(pairing(friends, new int[n]));
		}
		sc.close();
	}
	
	static int pairing(int[][] friends, int[] paired) {
		/*
		 * 이 기저조건은 쓸모가 없다! startIdx만 체크해도 충분.
		 * if(left == 0) {
			boolean isAllPaired = true;
			for(int i=0;i<paired.length && isAllPaired;i++) {
				if(paired[i] != 1) {
					isAllPaired = false;
				}
			}
			return (isAllPaired) ? 1 : 0;
		}*/
		
		int startIdx = -1;
		for(int i=0;i<paired.length;i++) {
			if(paired[i] == 0) {
				startIdx = i;
				break;
			}
		}
		/*
		 * 기저조건:
		 * startIdx가 -1이라는 것은 모두가 쌍을 찾은 경우이므로 1 리턴.
		 */
		if(startIdx == -1) {
			return 1;
		}
		
		int res = 0;
		for(int i=startIdx+1;i<paired.length;i++) {
			/*
			 * 친구가 아니라면 이 route는 더 이상 탐색할 필요가 없다!
			 */
			if(paired[i] == 0 && friends[i][startIdx] == 1) {
				paired[i] = paired[startIdx] = 1;
				
				res += pairing(friends, paired);
				
				paired[i] = paired[startIdx] = 0;
			}
		}
		return res;
	}
}

/*
3 
2 1 
0 1 
4 6 
0 1 1 2 2 3 3 0 0 2 1 3 
6 10 
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5

1
3
4
*/