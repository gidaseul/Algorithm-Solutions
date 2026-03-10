import java.io.*;
import java.util.*;

/**
 *
 * @author gidasuel
 * 백준 15686. 치킨 배달 (골드 5)
 *
 *	문제 설명 : 
 *		- 0,1,2를 바탕으로 빈 칸, 집, 치킨 집 순서로 주어진다.
 *		- 이때 치킨집과 집 사이의 거리의 최소가 되는 값으로 집 마다 거리를 구한다.
 *		- 각 집에 가장 작은 값을 더하여 출력한다.
 *		- 이때 치킨 집은 M개만큼 있어서 
 *
 *
 * 1. 입력 받는다.
 * 		1-1. N,M이 주어진다.
 * 			1-1-1. NxN 배열을 생성한다.
 * 			1-1-2. 1의 위치 저장할 list, 2의 위치를 저장할 list를 생성한다.
 * 
 * 		1-2. NxN 만큼의 배열에 넣을 값들을 입력 받는다. (이때 전체 2의 값 중에서 M만큼만 선택해서 진행하도록 조합 사용)
 * 			1-2-1. 1인 위치를 저장하는 list, 2의 위치를 저장하는 list를 따로 만들어서 진행한다.
 * 			1-2-2. 1인 경우 home에 위치 값 저장(x,y)
 * 			1-2-3. 2인 경우 chicken에 위치 값 저장(x,y)
 * 	
 * 2. comb 조합 진행한다.
 * 		2-1. 조합의 경우 idx를 넘기면서 전체를 탐색한다.
 * 			2-1-1. 이때 방문한 것이 M과 동일하면 cal()함수를 통해서 계산한다.
 * 		2-2. cal() 함수
 *			2-2-1. 각 집마다 허용되는 치킨집의 거리의 최소를 구하고 이를 누적하여 더한다.
 *			2-2-2. int로 반환하여 값을 전달하고 이때 최소가 되는 M개의 치킨 선택 값을 탐색한다.
 *
 */
public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N,M; 
    
    static int[][] map;
    static ArrayList<int[]> home;
    static ArrayList<int[]> chicken;
    static boolean[] check;
    static int minResult;
    
    // 좌표를 주면 길이를 출력하는 함수
    static int len(int x1,int y1, int x2, int y2) {
    	return Math.abs(x1-x2) + Math.abs(y1-y2);
    	
    }
    
    
    static void comb(int idx, int cnt) {
    	
    	// 2-1-1. 이때 방문한 것이 M과 동일하면 cal()함수를 통해서 계산한다.
    	if(cnt == M) {
    		// 2-2-2. int로 반환하여 값을 전달하고 이때 최소가 되는 M개의 치킨 선택 값을 탐색한다.
    		int result = cal();
    		minResult = Math.min(result, minResult);
    		return;
    	}
    	// 2-1. 조합의 경우 idx를 넘기면서 전체를 탐색한다.
    	for(int i = idx; i<chicken.size(); i++) {
    		if(!check[i]) {
    			check[i] = true;
    			comb(i+1,cnt+1);
    			check[i] = false;
    		}
    	}
    }
    
    // 2-2. cal() 함수
    static int cal() {
    	int result = 0;
    	int sum = 0;
    	
    	// 2-2-1. 각 집마다 허용되는 치킨집의 거리의 최소를 구하고 이를 누적하여 더한다.
    	for(int i =0; i<home.size(); i++) {
        	int minResult = Integer.MAX_VALUE;

    		for(int j=0; j<chicken.size(); j++) {
    			if(!check[j]) continue;
    			
    			// 집 하나 당 여러 치킨 집 거리 값 갱신하기
    			int[] curChicken = chicken.get(j);
    			int cx = curChicken[0];
    			int cy = curChicken[1];
    					
    			int[] curHome = home.get(i);
    			int hx = curHome[0];
    			int hy = curHome[1];
    			
    			result = len(cx,cy,hx,hy);
    			minResult = Math.min(result, minResult);
    		}
    		sum += minResult;

    	}
    	return sum;

    }
   
    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 입력 받는다.
        st = new StringTokenizer(br.readLine().trim());
        
        // 1-1. N,M이 주어진다
        N  = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 1-1-1. NxN 배열을 생성한다.
        map = new int[N][N];
        		
        // 1-1-2. 1의 위치 저장할 list, 2의 위치를 저장할 list를 생성한다.
        home = new ArrayList<>();
        chicken = new ArrayList<>();
        
        // 1-2. NxN 만큼의 배열에 넣을 값들을 입력 받는다. (이때 전체 2의 값 중에서 M만큼만 선택해서 진행하도록 조합 사용)
        for(int i=0; i<N;i++) {
        	st = new StringTokenizer(br.readLine().trim());
        	for (int j=0; j<N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		
        		// 1-2-2. 1인 경우 home에 위치 값 저장(x,y)
        		if(map[i][j]==1) {
        			home.add(new int[] {i,j});
        		}
        		// 1-2-3. 2인 경우 chicken에 위치 값 저장(x,y)
        		else if(map[i][j] == 2) {
        			chicken.add(new int[] {i,j});
        		}
        	}
        }
        // 방문 여부 체크하기 위한 것
        check = new boolean[chicken.size()];

        minResult = Integer.MAX_VALUE;
        
        // 2. comb 조합 진행한다
        comb(0,0);

        System.out.print(minResult);

    }
}
