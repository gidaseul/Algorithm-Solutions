import java.io.*;
import java.util.*;

/**
 * 백준 - 10851번 : 숫자 카드 
 *
 *  문제 설명: 
 *  	
 *  입력 설명:
 *
 *  해결 접근:
 *  
 * 
 */
public class Main {

	
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int N,M;
    static int[] arrN, arrM;
    
    
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine().trim());
        arrN = new int[N];
    	st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<N;i++) {
        	arrN[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arrN); // 정렬
        
        M = Integer.parseInt(br.readLine().trim());
        arrM = new int[M];
    	st = new StringTokenizer(br.readLine().trim());

        for(int j=0; j<M;j++) {
        	arrM[j] = Integer.parseInt(st.nextToken());
        }
        
        
        for(int k=0; k<M; k++) {
        	int index = Arrays.binarySearch(arrN,arrM[k]);
        	if(index >= 0) {
        		sb.append(1).append(" ");
        	}else sb.append(0).append(" ");
        }
        
        System.out.println(sb);
  
   
    }
}
