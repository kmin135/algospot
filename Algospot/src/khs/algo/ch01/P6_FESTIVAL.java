package khs.algo.ch01;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * https://algospot.com/judge/problem/read/FESTIVAL
 */
public class P6_FESTIVAL {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0) {
			String[] NL = br.readLine().split(" ");
			// 일 수
			int N = Integer.parseInt(NL[0]);
			// 팀의 수, L <= N
			int L = Integer.parseInt(NL[1]);
			int[] costs = getCosts(br.readLine().split(" "));
			
			double minCost = Double.MAX_VALUE;
			for(int s=0;s<=N-L;s++) {
				for(int e=s+L-1;e<N;e++) {
					minCost = Math.min(minCost, (double)sum(costs, s, e)/(e-s+1));
				}
			}
			System.out.printf("%.12f\n", minCost);
		}
	}
	
	public static int sum(int[] costs, int s, int e) {
		int sum = 0;
		for(;s<=e;s++) {
			sum += costs[s];
		}
		return sum;
	}
	
	public static int[] getCosts(String[] costsStr) {
		int[] costs = new int[costsStr.length];
		for(int i=0;i<costs.length;i++) {
			costs[i] = Integer.parseInt(costsStr[i]);
		}
		return costs;
	}
}
