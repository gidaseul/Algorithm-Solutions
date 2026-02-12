import java.io.*;
import java.util.*;

/**
 * SWEA 1861. 정사각형 방(D4)
 *
 *  문제 설명: 
 *  	N^2개의 방이 NxN 형태로 늘어서 있다.
 *  	i번째 줄의 왼쪽에서 j번째 방에 1~N^2까지의 수가 적혀 있음. (모든 방에 대해 수가 다름)
 *  	상하좌우로 움직임이 가능함.
 *  		조건 1 : 이동 하려는 방이 존재해야 함. -> nx,ny로 더한 다음에 범위에 벗어나지 않도록
 *  		조건 2 : 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확하게 1 더 커야 함. -> 기존 값과 비교해서 +1 과 동일한지
 *  	목표 : 처음 어떤 방에서 시작해야 가장 많은 개수의 방을 이동할 수 있는지 확인하기! 
 *
 *	입력 
 *	1. TestCase 수 주어짐
 *	2. N의 값이 주어짐 (1<=N<=10^3)
 *		2-1. N*N 개 만큼의 숫자로 각각 값이 주어짐 -> map에 값을 저장해야 함.
 *	
 *  해결 접근:
 *  3. 탐색을 진행할 때  모든 방마다 탐색을 해야 하기 때문에 N^2 만큼 반복해서 탐색  Move 진행
 *  	3-1. Move : 본인 좌표에서 nx,ny 만큼 반복해서 탐색 시작 (조건에 해당되면 다음으로 재귀 호출 하도록)
 *  	3-2. Move 함수 진행 결과 int로 움직힌 횟수 return
 *  
 *  		3-2-1. move 함수로 진행한 1. map[x][y], 2. move(x,y)의 값으로 비교 시작
 *  		  	3-2-1-1. 현재의 값이 이전의 저장한 움직임 보다 클 경우 업데이트 -> 1.방의 번호, 2. 움직인 횟수
 *  			3-2-1-2. 만약 움직임이 동일한 경우는 방의 번호가 작은 것으로 저장
 *  
 */
public class Solution {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb; // sb.setLength(0)을 사용해서 재사용 하도록

    static int N,T;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int mapNumber, mapCount;
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        // 1. TestCase 수 입력받기
        T = Integer.parseInt(br.readLine().trim());
        for(int tc = 1; tc<=T; tc++) {
        	
        	// 방의 번호, 방의 개수
        	mapNumber =0;
        	mapCount =0;
        	
        	// 2. N 입력받기
        	N = Integer.parseInt(br.readLine().trim());
        	map = new int[N][N];
        	// 2-1. N*N 개 만큼의 숫자로 각각 값이 주어짐 -> map에 값을 저장해야 함.
        	for(int r=0;r<N;r++) {
        		st = new StringTokenizer(br.readLine().trim());
        		for(int c=0; c<N; c++) {
        			map[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}
        	// 3-1. Move : 본인 좌표에서 nx,ny 만큼 반복해서 탐색 시작 (조건에 해당되면 다음으로 재귀 호출 하도록)
        	for(int row =0; row<N; row++) {
        		for(int col =0; col<N; col++) {
        			
        			int currentMapNumber = map[row][col];
        			
        			int currentCount = move(row,col); // 3-2. Move 함수 진행 결과 int로 움직힌 횟수 return

        			// 3-2-1. move 함수로 진행한 1. map[x][y], 2. move(x,y)의 값으로 비교 시작
        			if(currentCount>mapCount) {
        				
        				// 3-2-1-1. 현재의 값이 이전의 저장한 움직임 보다 클 경우 업데이트 -> 1.방의 번호, 2. 움직인 횟수
        				mapNumber = currentMapNumber;
        				mapCount = currentCount;
        			}
        				// 3-2-1-2. 만약 움직임이 동일한 경우는 방의 번호가 작은 것으로 저장
        				else if(currentCount == mapCount) {
        					mapNumber = Math.min(mapNumber, currentMapNumber);        				}
        		}
        	}
        	
        	
        	
        	sb.append("#").append(tc).append(" ").append(mapNumber).append(" ").append(mapCount); // 추가
        	System.out.println(sb);
        	sb.setLength(0); // StringBuilder를 초기화 하여 재사용.
        }
    }
    
    public static int move(int x, int y) {
    	 
    	// 탐색 안 했던 것 
    	for(int i=0; i<4;i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		
    		if(nx<N && nx>=0 && ny<N &&ny >=0) {
    			if(map[x][y]+1 == map[nx][ny]) {
    				return 1+move(nx,ny);
    				}
    			}
    		}
		return 1;
    }
   
}