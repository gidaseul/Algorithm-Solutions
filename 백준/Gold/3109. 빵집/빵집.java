import java.io.*;
import java.util.*;

/**
 * 백준 3109번. 빵집 (골드 2)
 *
 *  문제 설명: 
 *  	1. R * C 격자로 배열이 정해짐.
 *		2. 첫째 열은 빵집의 가스관(시작 지점), 마지막 열은 빵집(도착 지점) -> 즉, 시작지점과 끝점이 정해져 있는 것
 *		3. 가스관과 빵집을 연결할 때 이동 가능 범위(오른쪽,오른쪽 위 대각선, 오른쪽 아래 대각선)
 *		4. 연결하는 통로가 하나 설치되면 그 경로를 지나칠 수 없음.
 *
 *	입력 
 *	- R, C 입력 받음.
 *		R * C에 해당하는 . or x를 받음. (x는 소문자) 
 *		
 *		
 *  해결 접근:
 *  	1. R,C에 대한 배열을 초기화 한다.
 *  	2. 시작점과 도착점은 열이 고정적이다.
 *  	3. 탐색하는 과정 중에서 그리디한 접근법이 필요하다.(탐색 방향은 3가지인데 아래 부터 찾으면 다음 행은 갈 수 있는 경로를 본인 보다 아래에서 밖에 못 찾음.)
 *  	4. boolean을 통해서 탐색하는 부분에서 탐색하지 않아도 되는 부분은 true로 호출하여 다음 행으로 넘어가도록 한다.
 *  	
 *  
 */

public class Main {

	
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int R,C,count;
    static char[][] mapInformation;
    static boolean[][] mapCheck;
    static int[] dx = {-1,0,1};
    static int[] dy = {1,1,1};
    
    public static boolean dfs(int x,int y) {
    	
    	if(y==C-1) {
    		count++;
    		return true;
    		
    	} 
    	
    	for(int i=0;i<3;i++) {
    		int nx = x+dx[i];
    		int ny = y+dy[i];
    		
    		if(nx <R && nx >=0 && ny<C && ny>=0) {
    			if(!mapCheck[nx][ny]) {
    			mapCheck[nx][ny] = true;
    			if (dfs(nx,ny)) return true;
    			}
    		}
    	}

    	return false;
    	
    }
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        count = 0;
        mapInformation = new char[R][C];
        mapCheck = new boolean[R][C];
        for(int r =0; r<R; r++) {
        	String line = br.readLine();
        	for(int c =0; c<C; c++) {
        		mapInformation[r][c] = line.charAt(c);
        		if(mapInformation[r][c] == 'x') {
        			mapCheck[r][c] = true;
        		}
        	}
        }
        for(int i=0;i<R;i++) {
        	if(!mapCheck[i][0]) {
        		mapCheck[i][0] = true;
            	dfs(i,0); 
        	}
        }
        System.out.println(count);
   
    }
}
