import java.io.*;
import java.util.*;

/**
 * 백준 1074. Z(골드 5)
 *
 *  문제 설명:
 *  	2^N x 2^N 배열 모양을 Z로 탐색하려고 함. 
 *  	N > 1인 경우 4 등분 해서 재귀적으로 순서대로 방문함.
 *  	N이 주어질 때 r행 c열을 몇번째로 방문하는지 파악하기
 *  	
 *
 *	입력 
 *	1. 첫번째 줄에 N,r,c 입력 받음.
 *		- N : 2^N의 배열 크기 (1<= N <= 15)
 *		- r : 행의 번호 (0<=r)
 *		- c : 열의 번호 (c<2^N)
 *	
 *	
 *  해결 접근:
 *  	1. 분할할 구역을 정해야 한다. r,c가 속한 행이 될 때 까지 계속해서 분할한다.(size가 1이 될 때 까지)
 *  	2. cut 함수로 구역을 계속해서 나누고, 속하지 않은 부분은 탐색하지 않도록 한다.
 *  	3. 속한 값을 찾아서 size=1이 될 때 r,c로 배열의 값을 호출한다.
 *  	
 *  -------> 메모리 문제로 수정
 *  	1. 모든 배열에 값을 저장할 수 없고 오히려 구역을 나눠서 분할하여 탐색하기
 *  		1-1. 이때 1~4분면에 속한 지점이 어딘지 탐색하도록 나누기
 *  		1-2. 탐색을 한 지점에 속한 부분에 영역이라면 현재 해당하는 영역에 size가 1인 지점이 아니기 때문에 그 영역값을 누적하기
 *  		1-3. size==1인 경우 속한 부분을 찾은 거기 때문에 그때 값을 찾아서 확인하기
 *  
 */

public class Main {

	
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int N,r,c,mapSize,mapR,mapC;

    static int count;
    
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        mapSize = 1<<N;
        mapR = 1<<(N-1);
        mapC = 1<<(N-1);
        count = 0;
        move(0,0,mapSize);
        
   
    }
    
    static void move(int x, int y, int size) {  	
    	if(size == 1) {
    		System.out.println(count);
    		return;
    	}
    	int half = size /2;
    	int area = half*half;
    	
    
    	// 제 1사분면 -  r,c 모두 경계선 보다 작은 구역
    	if(r< half+x && c< half+y) {
    		move(x,y,half);
    	}
    
    	// 제 2사분면 - r은 작고, c는 경계선 이상의 구역
    	else if(r < x+half && c >=y+half) {
    		count += area;
    		move(x,y+half,half);
    	}
    	
    	// 제 3사분면 - r은 경계선 이상이고, r은 작은 구역 
    	else if(r >= x+half && c<y+half) {
    		count+=area*2;
    		move(x+half,y,half);
    	}
    	
    	// 제 4사분면 - r,c 모두 경계선 이상 구역
    	else {
    		count+=area*3;
    		move(x+half,y+half,half);
    	}
    }
}
