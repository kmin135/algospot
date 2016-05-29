package khs.algo.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/CLOCKSYNC
 * 16-04-25 20:30 ~ 21:15 1차 아이디어
 * 16-04-27 21:00 ~ 22:15 1차 코딩. swArr이 4개일때는 얼추 돌다가 10개되니까 안 끝남...
 * 16-04-30 10:30 ~ 11:50 정답. turn()에서 turn2()로 아예 풀이 전략을 변경했다. (중복 순열 사용)
 * 중복순열의 형태로 문제를 변형하는게 핵심이었다.
 */
public class CLOCKSYNC {
	// 스위치와 시계 맵핑
	static int[][] swArr = {
		{0, 1, 2},
		{3, 7, 9, 11},
		{4, 10, 14, 15},
		{0, 4, 5, 6, 7},
		{6, 7, 8, 10, 12},
		{0, 2, 14, 15},
		{3, 14, 15},
		{4, 5, 7, 14, 15},
		{1, 2, 3, 4, 5},
		{3, 4, 5, 9, 13}
	};
	
	static int[] times;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		while(tc-->0) {
			times = new int[16];
			for(int i=0;i<16;i++) {
				// 3, 6, 9, 12중 하나
				times[i] = sc.nextInt();
			}
			
			/*
			 * 내 생각들
			 * 1. 각 스위치를 4번 연속 누르면 1번 누른것과 같다.
			 * 2. 1.의 조건을 이용하여 모든 경우의 수를 돌려보면 된다!
			 * 3. 아... 근데  0 / 1 / 0 / 0 / 1 / 0 / 1 / 1 ... 이런 식이면 결국 조건은 거의 무한대 아닌가...?
			 * 4. 아님. 순서에 상관없이 각 스위치는 4번 돌리면 한 번도 안 돌린 것과 같아짐!
			 */
			
			// 제출할 때 이거는 빼야한다.
			long st = System.nanoTime();
			
			int res = turn2(4, swArr.length, new ArrayList<Integer>());
			if(res == 987654321) {
				res = -1;
			}
			
			System.out.println(res);
			System.out.println("# elapsed : " + (System.nanoTime() - st) + " ns...");
		}
		sc.close();
	}
	
	/**
	 * 중복순열 구하는 함수에다가 시계 돌리기를 끼얹은게 핵심 아이디어.
	 * 0~n까지의 정수에 대해 r자리의 중복순열을 구하면서 시계를 돌리는 방식이다.
	 * 
	 * 시간복잡도 : 각 시계는 최대 3번까지 돌리고 스위치는 10개다. 스위치를 한 번도 안 돌린 것도 경우의 수에 포함되니까
	 * 시간복잡도라기 보다는 항상 4^10 번을 수행한다.
	 */
	static int turn2(int n, int r, List<Integer> list) {
		if(list.size() == r) {	
			boolean isEnd = true;
			int result = 987654321;
			for(int i=0;i<times.length&&isEnd;i++) {
				if(times[i] != 12) {
					isEnd = false;
				}
			}
			if(isEnd) {
				int turnCnt = 0;
				for(int i=0;i<list.size();i++) {
					turnCnt += list.get(i);
				}
				result = turnCnt;
			}
			return result;
		}
		
		int result = 987654321;
		for(int i=0;i<n;i++) {
			list.add(i);
			/*
			 * 시계 돌리기.
			 * i가 시계를 돌려야하는 횟수고
			 * list.size()-1이 돌려야하는 스위치의 idx 값이 된다.
			 */
			for(int j=0;j<i;j++)
				turnTimes(times, list.size()-1, true);
			result = Math.min(result, turn2(n, r, list));
			/*
			 * 반대로 시계를 돌려서 times 를 원복한다.
			 */
			for(int j=0;j<i;j++)
				turnTimes(times, list.size()-1, false);
			list.remove(list.size()-1);
		}
		return result;
	}
	
	/**
	 * 
	 * @param times
	 * @param sws 각 인덱스의 스위치를 누른 횟수
	 */
	/*static int turn(int[] times, int[] sws) {
		boolean isEnd = true;
		for(int i=0;i<times.length&&isEnd;i++) {
			if(times[i] != 12) {
				isEnd = false;
			}
		}
		if(isEnd) {
			int turnCnt = 0;
			for(int i=0;i<sws.length;i++) {
				turnCnt += sws[i];
			}
			return turnCnt;
		}
		
		int res = 987654321;
		for(int i=0;i<sws.length;i++) {
			if(sws[i] >= 3) {
				continue;
			}
			turnTimes(times, i, true);
			sws[i] += 1;
			System.out.println(Arrays.toString(sws));
			res = Math.min(res, turn(times, sws));
			turnTimes(times, i, false);
			sws[i] -= 1;
		}
		return res;
	}*/
	
	/**
	 * @param times : 돌릴 시계들 (0~15)
	 * @param swIdx : 누를 스위치 (0~9)
	 * @param wise : true : 시계방향 
	 * 			false : 반시계방향
	 */
	static void turnTimes(int[] times, int swIdx, boolean wise) {
		for(int i=0;i<swArr[swIdx].length;i++) {
			if(wise) {
				times[swArr[swIdx][i]] = (times[swArr[swIdx][i]] < 12) ? times[swArr[swIdx][i]] + 3 : 3;
			} else {
				times[swArr[swIdx][i]] = (times[swArr[swIdx][i]] > 3) ? times[swArr[swIdx][i]] - 3 : 12;
			}
		}
	}
}
/*
각 스위치(0~9)에 연결된 시계
0	0, 1, 2
1	3, 7, 9, 11
2	4, 10, 14, 15
3	0, 4, 5, 6, 7
4	6, 7, 8, 10, 12
5	0, 2, 14, 15
6	3, 14, 15
7	4, 5, 7, 14, 15
8	1, 2, 3, 4, 5
9	3, 4, 5, 9, 13
 */

/*
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12 
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6

2
9
*/