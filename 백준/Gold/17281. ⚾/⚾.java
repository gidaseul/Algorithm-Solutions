import java.util.*;
import java.io.*;

/*
 * @author Da_seul
 * 
 * 	@see #main(String[])
 *  1. N 이닝의 수 만큼 9명 자리 입력,
 *  2. N만큼의 이닝마다 9명에  결과 값 입력
 *  	안타 : 1 
 *  	2루타 : 2
 *  	3루타 : 3
 *  	홈런 : 4
 *  	아웃 : 0
 *  3. 1번 타자는 무조건 4번 타자로 고정 (9! ->8!)
 *  4. 여기서 1,2,3,4,5,6,7,8의 경우로 순열을 돌리면서 반복함. 이때 0이 3번 나온 경우 다음 이닝으로 넘기기
 *  5. 점수는 누적으로 합이 진행 될 예정
 *  
 *  알고리즘 풀이
 *  1. 순열 생성(1번 선수는 4번 타자로 고정)
 *  2. 이때 이닝별로 점수 계산 / 아웃 3개 count 하면 next_inning 넘기기
 * 		
 */
public class Main
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
    static int N, score;
    static boolean[] visited;
    static int[][] game; // [inning][player] = 0~4까지의 값
    static int[] order; // [타순 자리] = 선수번호 // 예시 : [2,5,4,1,3,9,8,7,6] (1번 타자는 : 2번 선수)
	
	
	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine().trim());
		
		game = new int[N][9];
		order = new int[9];
		score = Integer.MIN_VALUE;
		visited = new boolean[9];
		
		for(int inning = 0; inning<N; inning++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<9;i++) {
				game[inning][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		order[3] = 0;
		visited[0] = true;
		perm(0);
		
		System.out.println(score);
    }	
	
	// perm 순열 만들기 - 순열 만들면서 조건 맞출 경우 넘기기
	static void perm(int depth) {
		if(depth == 9) {
			simulate();
			return;
		}
		
		// 0,1,2,3 -> 4번째는 이미 우리가 고정을 한 상태로 
		// order = [ ?, ?, ?, 0, ?, ?, ?, ?, ? ] 이러한 상황
		if(depth == 3) {
			perm(depth+1);
			return;
		}
		
		for(int i=1; i<9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				order[depth] = i; // depth는 선택한 순열의 index -> index 위치에선 i 값을 부여함. (고정되는 자리도 존재해서)
				perm(depth+1);
				visited[i] = false;
			}
		}
	}
	
	// 시뮬레이션 - 각 게임 진행
	static void simulate() {
		int scoreSimulate = 0;
		int batterIdx = 0; // 경기 전체에서 유지
		
		for (int inning =0; inning < N; inning++) {
			int outCount = 0;
			boolean[] base = new boolean[3]; // 1,2,3루의 유무 판단
			
			while(outCount < 3) {
				int player = order[batterIdx]; // 현재 타자
				int result = game[inning][player]; // 이번 이닝에서 해당하는 플레이어의 결과값
				
				if(result == 0) {
					outCount++;
				}else {
					scoreSimulate+= move(base, result); // 안타/홈런 처리하기 위한 메서드 
				}
				
				batterIdx = (batterIdx+1) % 9; // 다음 타자
			}
		}
		score = Math.max(scoreSimulate, score);
	}
	
	// 이때 게임당 base 확인 후 점수 판별 - 현재 전체 상태를 보기 위해선 3-2-1루 형태를 먼저 봐야 함.
	static int move(boolean[] base, int result) {
		int run = 0;
		// 이게 호출한 시점은 0이 아닌 지점이고, result = 1,2,3,4 가능
		for(int i =2;i>=0;i--) {
			if(base[i]) {
				int next = i+result;
				
				if(next>= 3) {
					run++; // 3을 넘어선 지점이라면 home 도착
				}else {
					base[next] = true; // 다음루로 이동
				}
				
				base[i] = false; // 기존에 있던 true는 사라짐.
			}
		}
		
		// 홈런일 경우
		if(result == 4) {
			run++;
		}else {
			base[result -1] = true;
		}
		return run;
	}
	
}
