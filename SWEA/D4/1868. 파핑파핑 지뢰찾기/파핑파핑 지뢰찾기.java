import java.util.*;
import java.io.*;

/*
 * 문제 
 *  표가 존재함. 표의 각 칸에는 지뢰가 있을 수도 있고, 없을 수도 있음
 *  표의 각 칸 클릭 할 때 
 *  	1. 지뢰 일 경우 -> 게임 끝
 *  	2. 지뢰가 아닐 경우 -> 변이 맞닿아 있거나 꼭지점이 맞닿아 있는 최대 8칸에 대해 몇 개의 지뢰가 있는지가 0~8 사이의 숫자로 클릭한 칸에 표시
 *  		2-1. 이 숫자가 0일 경우 근처 8방향에 지뢰가 없다는 의미 = 그 8방향의 칸도 자동으로 숫자 표시하기
 *  	지뢰 : *
 *  	지뢰 없는 칸 : .
 *  	클릭한 지뢰가 없는 칸 : c
 *  
 *  	지뢰가 있는 칸을 제외한 다른 모든 칸의 숫자들이 표시됙 위해서 몇 번의 클릭이 필요한 가
 *  
 *  1. 테스트 케이스  T 입력
 *	  1-1. 각 테스트 케이스 마다 N을 입력받는다. (1<= N <= 300) 
 *		1-1-1. N개의 줄 만큼 입력받기 위해 Map[N][N]을 생성한다.
 *		1-1-2. 방문 처리하기 위한 visited[N][N]을 만든다.
 *		1-1-3. N개의 줄 만큼 반복하여 문자열을 입력받는다. 
 *			1-1-3-1. N개의 입력만큼 문자열 형태가 주어진다. ('.' : 지뢰 없음 , '*' : 지리 있음)
 *			1-1-3-2. '.'은 0, '*'은 -1로 표시한다.
 *			1-1-3-3. -1인 지점은 방문처리를 사전에 처리한다.
 *
 *	2. 각 칸에 8개의 방향기준으로 지뢰가 몇개 있는지 파악하도록 map을 갱신한다.
 *	3. map[i][j] == 0이면서 방문하지 않은 영역을 클릭한다.
 *		3-1. 클릭함과 동시에 근처 영역을 방문처리 할 수 있도록 bfs를 진행한다.
 *		3-2. 8가지 방향을 돌면서 0인 지점을 다시 큐에 넣는다.
 *
 *	4. 나머지 방문하지 않은 후보들을 클릭하여 값을 더한다.
 *	
 *
 *
 *		주의할 점 : 0인 지점을 먼저 하면 좋겠다고 생각했지만 먼저 큐에 미리 넣고 진행할 경우 확장의 여부에 따라 제대로 된 계산이 어려움.
 *			ex)  	0 0 0 x
 *					0 0 0 x
 *					0 0 0 x
 *					x x x x 여기서 최소 클릭은 1,1을 누르는 1이지만 
 *							미리 0인 지점을 큐에 넣어버리면 확장이 제대로 이루어지지 않고
 *							다른 칸들이 개개인 클릭으로 이루어짐.
 *		
 * 
 * 
 * 
 * 
 * 
 */

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T,N;
	static int[][] map; // 받은 문자열을 지뢰의 숫자 및 표현하기 위한 맵
	static boolean[][] visited;
	// 8개의 방향 찾기 12시 방향 기준으로 시계방향
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,1,1,1,0,-1,-1,-1};
	
	
	
	// 2. 각 칸에 8개의 방향기준으로 지뢰가 몇개 있는지 파악하도록 map을 갱신한다.
	public static int search(int x,int y) {
		
		int result = 0;
		
		if (map[x][y] == -1) return -1;
		
		
		for(int n=0;n<8;n++) {
			int nx = x+dx[n];
			int ny = y+dy[n];
			
			if(nx <0 || ny <0 || nx >=N || ny >= N) continue;
			
			if(map[nx][ny]==-1) {
				result += 1;
			}
		}
		return result;
		
	}
	
	// 3-1. 클릭함과 동시에 근처 영역을 방문처리 할 수 있도록 bfs를 진행한다.
	public static void bfs(int x, int y) {
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		visited[x][y] = true;
		q.add(new int[] {x,y});
		
		while(!q.isEmpty()) {
			
			int[] n = q.poll();
			int ax = n[0];
			int ay = n[1];
			
			
			// 3-2. 8가지 방향을 돌면서 0인 지점을 다시 큐에 넣는다.
			for(int i=0;i<8;i++) {
				int nx = ax+dx[i];
				int ny = ay+dy[i];
				
				
				if(nx<0 || ny<0 || nx>=N || ny>=N )continue;
				if(visited[nx][ny]) continue;
				if(map[nx][ny]==-1) continue;
				
				visited[nx][ny] = true;
				
				if(map[nx][ny]==0) {
					q.add(new int[] {nx,ny});
				}
			}
			
			
		}
		
	} 
	
	public static void main(String[] args) throws Exception {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 1. 테스트 케이스  T 입력
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc<= T; tc++) {
		
			
			// 1-1. 각 테스트 케이스 마다 N을 입력받는다. (1<= N <= 300) 
			N = Integer.parseInt(br.readLine().trim());
			
			
			// 1-1-1. N개의 줄 만큼 입력받기 위해 Map[N][N]을 생성한다.
			map = new int[N][N];
			// 1-1-2. 방문 처리하기 위한 visited[N][N]을 만든다.
			visited = new boolean[N][N];
			
			
			int count = 0;

			
			for(int i =0;i<N;i++) {
				
				// 1-1-3. N개의 줄 만큼 반복하여 문자열을 입력받는다. 
				String line = br.readLine().trim();
				
				// 1-1-3-1. N개의 입력만큼 문자열 형태가 주어진다. ('.' : 지뢰 없음 , '*' : 지리 있음)
				for(int j=0;j<N;j++) {
					
					// 1-1-3-2. '.'은 0, '*'은 -1로 표시한다.
					if(line.charAt(j) == '.') {
						map[i][j] = 0;
					}
					else if(line.charAt(j) == '*'){
						map[i][j] = -1;
						// 1-1-3-3. -1인 지점은 방문처리를 사전에 처리한다.
						visited[i][j] = true;
					}
				}
			}
			// 2. 각 칸에 8개의 방향기준으로 지뢰가 몇개 있는지 파악하도록 map을 갱신한다.
			for(int i=0;i<N;i++) {
				for (int j=0; j<N;j++) {
					map[i][j] = search(i,j);		
				}
			}
			
			// 3. map[i][j] == 0이면서 방문하지 않은 영역을 클릭한다.
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 0 && !visited[i][j]) {
                        // 3-1. 클릭함과 동시에 근처 영역을 방문처리 할 수 있도록 bfs를 진행한다.
                    	bfs(i, j);
                        count++;
                    }
                }
            }
			
			// 4. 나머지 방문하지 않은 후보들을 클릭하여 값을 더한다.
			for(int i=0;i<N;i++) {
				for(int j=0; j<N;j++) {
					if(!visited[i][j] && map[i][j] > 0) {
						count++;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(count).append("\n");
			
		}
		System.out.print(sb);
	}

}
