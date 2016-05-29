package khs.algo.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/BOGGLE
 * 교재 p150
 * 
 * 16-04-03 15:40 시작
 * 16-04-03 17:00 종료
 * 
 * 완전탐색을 이용한 재귀이며 Timeout이 발생한다.
 * 시간복잡도 : O(8^n)
 * 교재 8장 동적계획법을 배우고 다시 도전해보자.
 * 
 * 16-04-11 동적계획법을 적용해서  pass함.
 * cache배열 추가한 것만으로 어마어마한 차이가 난다.
 * @author Administrator
 *
 */
public class BOGGLE {
	static final int WIDTH = 5;
	static final int HEIGHT = 5;
	
	static int[][][] cache = new int[WIDTH][HEIGHT][10];
	static final List<int[]> vectorList = new ArrayList<>();
	static {
		vectorList.add(new int[]{-1, -1});
		vectorList.add(new int[]{-1, 0});
		vectorList.add(new int[]{-1, 1});
		vectorList.add(new int[]{0, -1});
		vectorList.add(new int[]{0, 1});
		vectorList.add(new int[]{1, -1});
		vectorList.add(new int[]{1, 0});
		vectorList.add(new int[]{1, 1});
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		while(tc-- > 0) {
			char[][] map = new char[HEIGHT][WIDTH];
			for(int i=0;i<HEIGHT;i++) {
				String next = sc.next();
				map[i] = next.toCharArray();
			}
			
			int tc2 = sc.nextInt();
			String[] tcArr = new String[tc2];
			for(int i=0;i<tc2;i++) {
				tcArr[i] = sc.next();
			}
			
			long startTime = System.nanoTime();
			
			// 알고리즘 시작
			
			for(String tar : tcArr) {
				char[] tarArr = tar.toCharArray();
				
				boolean isExist = false;
				
				for(int i = 0;i<map.length && !isExist;i++) {
					for(int j=0;j<map[i].length && !isExist;j++) {
						isExist = searchStr(map, tarArr, new Point(j, i), 0, false);
					}
				}
				
				cache = new int[WIDTH][HEIGHT][10];
				
				if(isExist) {
					System.out.println(tar + " YES");
				} else {
					System.out.println(tar + " NO");
				}
			}
			
			System.out.println("# Elpased : " + (System.nanoTime() - startTime ) + " ns...");
		}
		sc.close();
	}
	
	static boolean searchStr(char[][] map, char[] tar, Point p, int nextIdx, boolean isFinal) {		
		if(chkArrOut(p) || (map[p.y][p.x] != tar[nextIdx])) {
			return false;
		} else if(isFinal && (map[p.y][p.x] == tar[nextIdx])) {
			return true;
		}
		nextIdx++;
		
		if(cache[p.x][p.y][nextIdx] == 1) {
			return false;
		}
		
		boolean isExist = false;			
		for(int[] vector : vectorList) {
			isExist = searchStr(map, tar, new Point(p.x + vector[0], p.y + vector[1]), nextIdx, nextIdx == tar.length -1);
			if(isExist) {
				return true;
			}
		}
		
		cache[p.x][p.y][nextIdx] = 1;
		
		return false;
	}
	
	/*static Point searchPoint(char[][] map, char c) {
		for(int i = 0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				if(c == map[i][j]) {
					return new Point(j, i);
				}
			}
		}
		return new Point(-1, -1);
	}*/
	
	static boolean chkArrOut(Point p) {
		return (p.x < 0 || p.x >= WIDTH || p.y < 0 || p.y >= HEIGHT) ? true : false;
	}
	
	private static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
