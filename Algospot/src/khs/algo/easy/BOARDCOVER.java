package khs.algo.easy;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/BOARDCOVER
 * 2016-04-24 성공. 약 1시간 소요.
 */
public class BOARDCOVER {
	static int[][][] moveMap = {{{0,0},{0,1},{1,1}},
								{{0,0},{1,0},{1,1}},
								{{0,0},{1,0},{0,1}},
								{{0,0},{0,1},{-1,1}}};
								
	public static void main(String[] args) {		
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			char[][] map = new char[h][w];
			for(int i=0;i<map.length;i++) {
				map[i] = sc.next().toCharArray();
			}
			
			System.out.println(countCover(map));
		}
		sc.close();
	}
	
	/*
	 * 핵심아이디어는 왼쪽 위에서부터 찾는거다.
	 * 이렇게 하면
	 * X
	 * OO
	 * 
	 * XO
	 *  O
	 *  
	 *  X
	 * OO
	 * 
	 * XO
	 * O
	 * 
	 * 이렇게 4개씩만 체크하면 된다.
	 */
	static int countCover(char[][] map) {
		int sy = -1;
		int sx = -1;
		for(int y=0;y<map.length;y++) {
			for(int x=0;x<map[0].length;x++) {
				if(map[y][x] == '.') {
					sx = x;
					break;
				}
			}
			if(sx != -1) {
				sy = y;
				break;
			}
		}
		
		// 전부 채웠으면 하나 찾은 것.
		if(sy == -1 && sx == -1) {
			return 1;
		}
		
		int res = 0;		
		for(int z=0;z<moveMap.length;z++) {
			boolean isCovered = true;
			for(int y=0;y<moveMap[0].length;y++) {
				// 주어진 map의 index를 넘어서거나 '.'이 아니면 cover할 수 없다.
				if((sy+moveMap[z][y][1]) < 0
						|| (sy+moveMap[z][y][1]) >= map.length
						|| (sx+moveMap[z][y][0]) < 0
						|| (sx+moveMap[z][y][0]) >= map[0].length
						|| map[sy+moveMap[z][y][1]][sx+moveMap[z][y][0]] != '.') {
					isCovered = false;
					break;
				}
			}
			// 현재 위치에서 cover가 될 때만 다음 위치를 찾는다.
			if(isCovered) {
				for(int y=0;y<moveMap[0].length;y++) {
					map[sy+moveMap[z][y][1]][sx+moveMap[z][y][0]] = '#';
				}
				res += countCover(map);
				for(int y=0;y<moveMap[0].length;y++) {
					map[sy+moveMap[z][y][1]][sx+moveMap[z][y][0]] = '.';
				}
			}
		}
		return res;
	}
}

/*
3 
3 7 
#.....# 
#.....# 
##...## 
3 7 
#.....# 
#.....# 
##..### 
8 10 
########## 
#........# 
#........# 
#........# 
#........# 
#........# 
#........# 
########## 

0
2
1514
*/