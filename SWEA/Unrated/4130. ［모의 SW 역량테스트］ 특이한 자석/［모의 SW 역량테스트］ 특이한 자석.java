import java.util.*;
import java.io.*;
import java.lang.management.ManagementFactory;

/*
 * SW Expert Academy 
 * @author Da_seul
 * 
 * 	@see #main(String[])
 * 		input
 * 		1. T 테스트 케이스 개수
 * 			1-1. K : 회전시킬 톱니바퀴 개수
 * 			1-2. 4개의 톱니바퀴 자석 입력 (N=0, S=1)
 * 			1-3. a b : a(톱니바퀴 번호), b(회전 방향 1=시계, -1=반시계)
 *			
 *		
 *		2. 문제 풀이 방법 
 *			특정 톱니바퀴를 회전할 때 배열 0~7번의 해당하는 값 중에 각 톱니바퀴가 만나는 지점은 2,6
 *				(중요! 일반적인 톱니바퀴 처럼 연쇄적으로 방향이 움직인다고 생각하면 안됨.)
 *				(톱니바퀴가 회전한 이후 변화되는 것을 한번에 확인하고 전체 갱신)

 *				특정 a(톱니바퀴 번호)에 b를 회전시킬 경우 확인
 *				2-1. 회전 할 경우 오른쪽 전파 -> 회전 해야 할 톱니바퀴 저장
 *				2-2. 회전 할 경우 왼쪽 전파 -> 회전 해야 할 톱니바퀴 저장
 *				2-3. 회전 실행 rotateMagnet(int idx, int dir) 함수 실행 (회전 해야할 톱니바퀴, 회전하는 방향)
 *					2-3-1. dir = 1 : 시계 방향 회전일 경우 
 *						2-3-1-1. 이때 배열의 7번째가 0번째로 가야하기 때문에 temp 변수 활용
 *					2-3-2. dir = -1 : 반시계 방향 회전일 경우
 * 						2-3-2-1. 이때 배열의 0번째가 7번째로 가야하기 때문에 temp 변수 활용
 * 
 *		output		
 *		3. 계산 로직 
 *			3-1. score는 해당 톱니바퀴 번호에 따라 값이 다름. (1,2,4,8)이며, 12시 방향인 0번째 배열의 index 확인 
 *			3-2. sb를 통하여 출력
 *
 */
public class Solution
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int[][] magnet = new int[4][8];
	
	public static void main(String args[]) throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. T 테스트 케이스 개수
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<=T; t++) {
			// 1-1. K : 회전시킬 톱니바퀴 개수
			int K = Integer.parseInt(br.readLine());
			sb = new StringBuilder(); 
			// 1-2. 4개의 톱니바퀴 자석 입력 (N=0, S=1)
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1-3. a b : a(톱니바퀴 번호), b(회전 방향 1=시계, -1=반시계)
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken())-1; // 회전할 톱니바퀴 위치 (배열이여서 -1)
				int dir = Integer.parseInt(st.nextToken()); 
				
				int[] rotate = new int[4];
				rotate[num] = dir; //회전할 부분을 명시
			
			
				
				// 2-1. 오른쪽 전파 확인
				for(int i=num; i<3;i++) {
					
					if(magnet[i][2]!=magnet[i+1][6]) {
						rotate[i+1] = -rotate[i];
					}else break;
				}
				
				// 2-2. 왼쪽 전파 확인
				for(int i=num; i>0;i--) {
					if(magnet[i][6] != magnet[i-1][2]) {
						rotate[i-1] = -rotate[i];
					}
				}
				
				//  2-3. 회전 실행 rotateMagnet(int idx, int dir) 함수 실행 (회전 해야할 톱니바퀴, 회전하는 방향)
				for(int i=0; i<4; i++) {
					if(rotate[i]!=0) {
						rotateMagnet(i,rotate[i]);
					}
				}
			}
			
			// 3-1. score는 해당 톱니바퀴 번호에 따라 값이 다름. (1,2,4,8)이며, 12시 방향인 0번째 배열의 index 확인 
			int score = 0;

			if(magnet[0][0] == 1) score +=1;
			if(magnet[1][0] == 1) score +=2;
			if(magnet[2][0] == 1) score +=4;
			if(magnet[3][0] == 1) score +=8;
			
			// 3-2. sb를 통하여 출력
			sb.append("#").append(t).append(" ").append(score).append("\n");
			System.out.print(sb);
		}
	}
	
	static void rotateMagnet(int idx, int dir) {
		
		// 2-3-1. dir = 1 : 시계 방향 회전일 경우
		if (dir == 1) { 
			// 2-3-1-1. 이때 배열의 7번째가 0번째로 가야하기 때문에 temp 변수 활용
			int temp = magnet[idx][7]; 
			for(int i=7; i>0; i--) {
				magnet[idx][i] = magnet[idx][i-1]; 
			}
			magnet[idx][0] = temp;
		}
		// 2-3-2. dir = -1 : 반시계 방향 회전일 경우
		else { 
			// 2-3-2-1. 이때 배열의 0번째가 7번째로 가야하기 때문에 temp 변수 활용
			int temp = magnet[idx][0];
			for(int i=0; i<7; i++) {
				magnet[idx][i] = magnet[idx][i+1];
			}
			magnet[idx][7]=temp;
		}
	}
}