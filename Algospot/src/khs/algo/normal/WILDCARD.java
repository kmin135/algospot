package khs.algo.normal;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 2016-04-14 완전탐색으로 350ms 나온다.
 * 동적계획법을 적용해보자
 * https://algospot.com/judge/problem/read/WILDCARD
 *
 */
public class WILDCARD {
	private static final char A = '*';
	private static final char Q = '?';
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-- > 0) {
			char[] expr = sc.next().toCharArray();
			int n = sc.nextInt();
			List<String> list = new LinkedList<>();
			while(n-- > 0) {
				char[] str = sc.next().toCharArray();
				
				boolean isMatch = match(expr, str, 0, 0);
				if(isMatch) {
					if(list.size() == 0) {
						list.add(new String(str));
					} else {
						String newStr = new String(str);
						boolean isInserted = false;
						for(int i=0;i<list.size();i++) {
							if(list.get(i).compareTo(newStr) > 0) {
								list.add(i, newStr);
								isInserted = true;
								break;
							}
						}
						if(!isInserted) {
							list.add(newStr);
						}
					}
				}
			}
			for(String str : list) {
				System.out.println(str);
			}
		}
		
		sc.close();
	}
	
	static boolean match(char[] expr, char[] str, int ei, int si) {
		if(ei == expr.length && expr[ei-1] == A && si == str.length-1) {
			return true;
		} else if(ei >= expr.length || si >= str.length) {
			return false;
		} else if(ei == expr.length-1 && si == str.length-1) {
			if(expr[ei] == str[si] || expr[ei] == A || expr[ei] == Q) {
				return true;
			}
		} else if(expr[ei] != A && expr[ei] != Q && expr[ei] != str[si]) {
			return false;
		}

		if(expr[ei] == A) {
			for(int i=0;i<str.length-si;i++) {
				if(match(expr, str, ei+1, si+i)) {
					return true;
				}
			}
		}
		return match(expr, str, ei+1, si+(si == str.length-1 ? 0 : 1));
	}
}

/*
2
he?p
3
help
heap
helpp
*p*
3
help
papa
hello


heap
help
help
papa
*/




