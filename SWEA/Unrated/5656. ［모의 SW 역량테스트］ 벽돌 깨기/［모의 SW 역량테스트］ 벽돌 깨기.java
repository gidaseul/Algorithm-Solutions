import java.io.*;
import java.util.*;

/*
 * 
 * 1. 입력받기
 * 	1-1. 테스트 케이스 수를 입력받는다.
 * 	1-2. 테스트 케이스 마다, N,W,H를 입력받는다. 
 * 			- 1<=N<=4 (구슬을 쏠 수 있는 경우의 수)
 * 			- 2<=W<=12 (가로 길이)
 * 			- 2<=H<=15 (세로길이)
 * 	1-3. H 줄 마다 W 개수 만큼 벽돌의 상태를 입력받는다.
 * 		1-3-1. 입력받은 숫자 중에서 먼저 0보다 큰 값이 나온 경우를 기준으로 열마다 높이를 저장한다.
 * 	
 * 	2. W의 위치 만큼 N번 탐색을 해야함. 1111, 1112 등 중복순열 가능함. 12^4
 * 		2-1. 중복순열을 통하여 3,4를 진행하여 나오는 값을 min 값으로 업데이트 한다.
 * 
 *  // 3. 구슬을 쏘는 경우
 * 	3-1. 구슬의 위치를 정할 경우 현재 같은 열에 속한 블록의 높이를 기준으로 H-높이를 하여 해당 칸의 값을 확인
 * 	3-2. 확인한 이후 해당하는 값을 기준으로 범위를 파악한다.
 * 		3-2-1. 1이라면 해당 블록을 -1 처리하고 넘어간다.
 * 	3-3. 2이상이라면 현재 값 -1 만큼의 4가지 방향을 탐색하여 범위에 해당하는 1이상인 값들만 모두 큐(x,y,값)에 넣는다.
 * 	3-4. 해당 범위에 값을 넣은 상태라면 이후 그 범위에 해당하는 값을 -1 처리한다. (큐에 넣고 -1을 해야함)
 * 	3-5. 이후 큐가 빌 때 까지 좌표를 기준으로 범위를 산정하여 2-4~2-5를 반복한다.
 * 	
 *	// 4. 구슬로 인하여 벽돌의 상태가 변한 것을 -> 위치에 맞게 조정하는 역할
 * 	4-1. 열마다 반복한다.
 * 		4-1-1. 0이 아닌 것들만 값을 큐에 넣는다.
 * 		4-1-2. 큐의 사이즈를 통하여 벽돌의 높이를 계산하여 열에 입력한다
 * 		4-1-3. 열에 뒤에서부터 큐에 있는 값을 넣어서 등록시켜준다.
 * 		
 *  5-1. 맵을 순회하며 벽돌의 총합을 구한다.
 *  	5-1-1. 최소 값으로 갱신한다.
 *  
 *  5-2. 다시 맵을 초기화 한다. 
 *  
 *  
 *  
 * */

public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    
    static int T,N,W,H;
    static int[] originHeights; // heights[i]는 열을 의미하고 이때 들어있는 값은 행이 들어있는 이야기
    static int[] heights;
    static int[][] originMap;
    static int[][] map;
    static int[] bead;
    static int minResult;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    
    // 큐에 넣을 블럭
    static class Node{
    	int x,  y,  range;
    	
    	Node(int x, int y, int range){
    		this.x = x;
    		this.y = y;
    		this.range =range;
    	}
    }
    //<!--------------------------------!>
    // 	2. W의 위치 만큼 N번 탐색을 해야함. 1111, 1112 등 중복순열 가능함. 12^4
    // 		2-1. 중복순열을 통하여 3,4를 진행하여 나오는 값을 min값으로 업데이트 한다.
    static void search(int depth) {
    	
    	
    	if(depth == N) {
		    // 배열을 복사해서 넘기기
    		map = copyMap(originMap); 
    		// 오리지널 기준으로 맵 다시 변경해서 배열 업데이트 해야함.
    		heights = copyArray(originHeights);
    		
    		for(int i=0;i<N;i++) {
    			// 3.구슬을 쏘는 경우
    			shot(bead[i],i);	
    		}
    		return;
    	}
    	
    	for(int i=0;i<W;i++) {
    		bead[depth] = i;
    		search(depth+1);	
    	}
    }
	
    static int[][]copyMap(int[][] map){
    	int[][] newMap = new int[map.length][map[0].length];
    	for (int i = 0; i < map.length; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }
    
    static int[] copyArray(int []arr) {
    	int[] newMap = new int[arr.length];
    	for (int i = 0; i < arr.length; i++) {
            newMap[i] = arr[i];
        }
        return newMap;
    }
    
    //<!--------------------------------!>
    static void shot(int yBlock, int count) {
        // 	3-1. 구슬의 위치를 정할 경우 현재 같은 열에 속한 처음 나오는 값의 행 번호
        Queue<Node> q = new ArrayDeque<>();

    	int x = heights[yBlock];
    	
    	if (x==-1) {
    		if(count==N-1) calculate();
    		return;
    	}
    	
    	int y = yBlock;
    	int range = map[x][y];
    	
    	q.add(new Node(x,y,range));
    	map[x][y] = 0;
    	
    	while(!q.isEmpty()) {
    		Node cur = q.poll();
    		int cX = cur.x;
    		int cY = cur.y;
    		int cRange = cur.range;
    		
    		// 3-2. 확인한 이후 해당하는 값을 기준으로 범위를 파악한다.
    		
    		// 3-2-1. 1이라면 해당 블록을 처리하고 넘어간다
    		if(cRange == 1) continue;


    		// 3-3. 2이상이라면 현재 값 -1 만큼의 4가지 방향을 탐색하여 범위에 해당하는 1이상인 값들만 모두 큐(x,y,값)에 넣는다.
    		for(int i=0; i<4;i++) {
    			for(int r=1;r<=cRange-1;r++) {
    				int nx = cX + dx[i]*r;
    				int ny = cY + dy[i]*r;
    				
    				if(nx >=H || ny >= W || nx<0 || ny <0) continue;    				
    				if(map[nx][ny]<=0) continue;
    				
    				
    				int nRange = map[nx][ny];
    					
    				
    				// 3-4. 해당 범위에 값을 넣은 상태라면 이후 그 범위에 해당하는 값을 -1 처리한다. (큐에 넣고 -1을 해야함)
    				q.add(new Node(nx,ny,nRange));
    				
    				map[nx][ny] = 0;
    				
    				// 3-5. 이후 큐가 빌 때 까지 좌표를 기준으로 범위를 산정하여 2-4~2-5를 반복한다.
    			}
    		}
    	}
    	
    	// 4.구슬로 인하여 벽돌의 상태가 변한 것을 -> 위치에 맞게 조정하는 역할
    	blockSetting();
    	
    	if(count==N-1) calculate();
    	
    	return;
    }
    
    //<!--------------------------------!>
    // 4-1. 열마다 반복한다.
    static void blockSetting() {
        for (int w = 0; w < W; w++) {
            Deque<Integer> dq = new ArrayDeque<>();

            // 위 -> 아래 순서로 저장
            for (int h = 0; h < H; h++) {
                if (map[h][w] > 0) {
                    dq.offerLast(map[h][w]);
                }
            }

            // 일단 전부 0으로 초기화
            for (int h = 0; h < H; h++) {
                map[h][w] = 0;
            }

            // 아래에서부터 채우기
            int row = H - 1;
            while (!dq.isEmpty()) {
                map[row--][w] = dq.pollLast();
            }
        }

        // 높이 다시 계산
        Arrays.fill(heights, -1);
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                if (map[h][w] > 0 && heights[w] == -1) {
                    heights[w] = h;
                }
            }
        }
    }
    
    static void calculate () {
    	int sum =0;
    
		// 이때는 배열의 총합 계산
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(map[i][j] <=0) continue;
				sum++;
			}
		}
		
		minResult = Math.min(sum, minResult);
		return;
    	
    }
    
	public static void main(String[] args)  throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		// 1. 입력받기
		
		//1-1. 테스트 케이스 수를 입력받는다.
		T = Integer.parseInt(br.readLine().trim());
		
		for(int tc=1; tc<=T; tc++) {
			// 1-2. 테스트 케이스 마다, N,W,H를 입력받는다.

			st = new StringTokenizer(br.readLine().trim());
    		
			N = Integer.parseInt(st.nextToken());
    		W = Integer.parseInt(st.nextToken());
    		H = Integer.parseInt(st.nextToken());
    		
    		originHeights = new int[W];
    		originMap = new int[H][W];
    		bead = new int[N]; // 구슬이 쏠 위치 정하는 거
    		heights = new int[W];
    		minResult = Integer.MAX_VALUE;
    		
        	Arrays.fill(originHeights, -1);
        	Arrays.fill(heights, -1);


			// 1-3. H 줄 마다 W 개수 만큼 벽돌의 상태를 입력받는다.
    		for(int h=0;h<H;h++) {
    			st = new StringTokenizer(br.readLine().trim());
    			for(int w=0;w<W;w++) {
    				originMap[h][w] = Integer.parseInt(st.nextToken());
    				
    				// 1-3-1. 입력받은 숫자 중에서 먼저 0보다 큰 값이 나온 경우를 기준으로 열마다 높이를 저장한다.
    				if(originMap[h][w] > 0 && originHeights[w] == -1  )
    					originHeights[w] = h;
    			}
    		}
    		
    		// 2. W의 위치 만큼 N번 탐색을 해야함. 1111, 1112 등 중복순열 가능함. 12^4
    		// 구슬을 정하여 넘기는 경우
    		search(0);
    		
    		sb.append("#").append(tc).append(" ").append(minResult).append("\n");
		}
		System.out.print(sb);
	}
    		
}