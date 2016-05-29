package khs.algo.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** https://algospot.com/judge/problem/read/WEIRD
 * 2016-04-16 언젠가 풀어봐야지
 *
 */
public class WEIRD_ONGOING {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			int num = sc.nextInt();
			int sum = 1;
			List<Integer> divisors = new ArrayList<Integer>();
			divisors.add(1);
			
			for(int i=2;i<=num/2;i++) {
				if(num % i == 0) {
					divisors.add(i);
					sum += i;
				}
			}
			
			if(sum > num) {
				// 조합으로 divisors.size() C 1 부터  divisors.size () C divisors.size 까지 모든 합을 테스트 해볼까
				
				int subsum = 0;
				for(int i=0,size=divisors.size()-1;i<size;i++) {
					subsum += divisors.get(i);
					
					
				}
			} else {
				System.out.println("not weird");
			}
			
			for(int i=0;i<divisors.size();i++) {
				System.out.print(divisors.get(i) + ", ");
			}
			System.out.println();
		}
	}
}
