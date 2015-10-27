package khs.algo.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://algospot.com/judge/problem/read/BRACKETS2
public class BRACKETS2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		while(tc-- > 0) {
			char[] inputs = br.readLine().toCharArray();
			int[] opens = new int[inputs.length];
			
			int opensIdx = 0;
			boolean valid = true;
			for(int i=0;i<inputs.length && valid;i++) {
				switch(inputs[i]) {
				case '(':
					opens[opensIdx++] = 1;
					break;
				case '{':
					opens[opensIdx++] = 2;
					break;
				case '[':
					opens[opensIdx++] = 3;
					break;
				case ')':
					opensIdx = (opensIdx - 1) < 0 ? 0 : opensIdx - 1;
					valid = (opens[opensIdx] -= 1) == 0 ? true : false;
					break;
				case '}':
					opensIdx = (opensIdx - 1) < 0 ? 0 : opensIdx - 1;
					valid = (opens[opensIdx] -= 2) == 0 ? true : false;
					break;
				case ']':
					opensIdx = (opensIdx - 1) < 0 ? 0 : opensIdx - 1;
					valid = (opens[opensIdx] -= 3) == 0 ? true : false;
					break;
				}
			}
			if(opens[0] == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
}
