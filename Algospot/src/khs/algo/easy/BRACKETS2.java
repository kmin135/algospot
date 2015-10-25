package khs.algo.easy;

import java.util.Scanner;

// https://algospot.com/judge/problem/read/BRACKETS2
public class BRACKETS2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		while(tc-- > 0) {
			char[] inputs = sc.next().toCharArray();
			
			for(int i=0;i<inputs.length;i++) {
				if(inputs[i] == ')') {
					if(i-1 >= 0 && inputs[i-1] == '(') {
						inputs = newInputs(inputs, i, i-1);
						i -= 2;
					} else {
						break;
					}
				} else if(inputs[i] == '}') {
					if(i-1 >= 0 && inputs[i-1] == '{') {
						inputs = newInputs(inputs, i, i-1);
						i -= 2;
					} else {
						break;
					}
				} else if(inputs[i] == ']') {
					if(i-1 >= 0 && inputs[i-1] == '[') {
						inputs = newInputs(inputs, i, i-1);
						i -= 2;
					} else {
						break;
					}
				}
			}
			
			if(inputs.length == 0) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}
	
	private static char[] newInputs(char[] originInputs, int a, int b) {
		char[] newInputs = new char[originInputs.length-2];
		int ni = 0;
		for(int i=0;i<originInputs.length;i++) {
			if(i == a || i == b)
				continue;
			newInputs[ni++] = originInputs[i];
		}
		return newInputs;
	}
}
