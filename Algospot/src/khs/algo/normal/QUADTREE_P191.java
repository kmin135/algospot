package khs.algo.normal;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/QUADTREE
 * P191
 * 시작 : 160410 12:20
 * 종료 : 160410 16:10
 */
public class QUADTREE_P191 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			String input = sc.next();
			long st = System.nanoTime();
			Node n = new Node(input.toCharArray(), new MutableInt(-1));
			System.out.println("#구현 1#1 : " + (System.nanoTime() - st) + " ns ...");
			n.turnTree();
			System.out.println("#구현 1#2 : " + (System.nanoTime() - st) + " ns ...");
			n.printNode();
			System.out.println();
			System.out.println("#구현 1#End : " + (System.nanoTime() - st) + " ns ...");
			
			st = System.nanoTime();
			System.out.println(reverse(input.toCharArray(), new MutableInt(0)));
			System.out.println("#구현 2 : " + (System.nanoTime() - st) + " ns ...");
		}
		
		sc.close();
	}
	
	
	/**
	 *  분할정복을 이용한 구현방법(P194). 
	 *  훨씬 빠르고 입력이 아무리 커져도 별 문제 없음.
	 */
	static String reverse(char[] input, MutableInt initCnt) {
		char head = input[initCnt.value];
		initCnt.value++;
		
		if(head == 'b' || head == 'w')
			return new String(new char[]{head});
		
		String upperLeft = reverse(input, initCnt);
		String upperRight = reverse(input, initCnt);
		String lowerLeft = reverse(input, initCnt);
		String lowerRight = reverse(input, initCnt);
		
		return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
	}
	
	/**
	 *  내가 구현한 방식. (16-04-10)
	 *  Tree 구조 만드느라 시간 다 씀. 
	 *  입력이 커질수록 메모리 full을 걱정해야함.
	 */
	static class Node {		
		char ch;
		Node[] childs;
		public Node(char[] input, MutableInt initCnt) {
			initCnt.value++;
			ch = input[initCnt.value];
			
			if(input[initCnt.value] == 'x') {
				childs = new Node[4];
				for(int j=0;j < childs.length;j++) {
					childs[j] = new Node(input, initCnt);
				}
			}
		}
		
		public void turnTree() {
			if(childs != null) {
				Node[] tmp = new Node[1];
				tmp[0] = childs[0];
				childs[0] = childs[2];
				childs[2] = tmp[0];
				
				tmp[0] = childs[1];
				childs[1] = childs[3];
				childs[3] = tmp[0];
				
				for(int j=0;j < childs.length;j++) {
					if(childs[j].ch == 'x') {
						childs[j].turnTree();
					}
				}
			}
		}
		
		public void printNode() {
			System.out.print(ch);
			if(childs != null) {
				for(int j=0;j < childs.length;j++) {
					childs[j].printNode();
				}
			}
		}
	}
	
	static class MutableInt {
		public int value = 0;
		public MutableInt(int value) {
			this.value = value;
		}
	}
}
