package khs.algo.easy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://algospot.com/judge/problem/read/CSBASEBALL
public class CSBASEBALL {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-- > 0) {
			String[] scores = br.readLine().split(" ");
			int aScore = Integer.parseInt(scores[0]);
			int bScore = Integer.parseInt(scores[1]);
			
			if(aScore > bScore) {
				System.out.println("0");
			} else {
				System.out.println(3 + bScore - aScore + 1);
			}
		}
	}
}
