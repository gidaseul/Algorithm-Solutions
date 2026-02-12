import java.io.*;
import java.util.*;

/**
 * 백준 (문제번호). 문제이름(문제등급)
 *
 *  문제 설명: 
 *  	1. 가지고 있는 K개의 랜선은 길이가 모두 다름.
 *  	2. 랜선을 모두 N개의 같은 길이로 만들고 싶어함.
 *  	3. K개의 랜선을 잘라서 만들어야 하고 이때 이미 자른 랜선을 붙일 수 없음.
 *  	4. N개를 만들 때 최대 랜선 길이를 구해야 함.
 *		
 *		(주의사항) 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우도 있으며, N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
 *	입력 
 *		1. 가지고 있는 랜선의 개수 K, 필요한 랜선의 개수 N 
 *			- K : 1 ~ 10,000
 *			- N : 1 ~ 1,000,000 
 *		2. K개 만큼의 길이를 받음.
 *			
 *  해결 접근:
 *  1. 가지고 있는 K를 잘 잘라서 공평한 N개 이상 만들어야 함.(이때 각 값의 범위가 2^31이 되기 때문에 long 자료형을 사용해야 함.)
 *  2. Max 값을 구하고 이때 Max 값 기준으로 나눌 것을 찾기 위해 탐색을 시작함.
 *  3. 이때 1~max 값의 길이의 범위의 mid 값 기준으로 적절한 길이 찾기
 *  4. count를 통해서 N이상이라면 저장하고 아니라면 탐색 범위를 낮춰서 이진탐색 반복 
 *  
 */

public class Main {

	
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int N,K;
    static long[] arr;
    
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine().trim());
        
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
       
        
        arr = new long[K];
        
        long max = 0;
        
        for(int i=0;i<K;i++) {
        	arr[i] = Long.parseLong(br.readLine().trim());
        	max = Math.max(max, arr[i]);
        }
        
        // 인덱스가 올 수 있는 범위
        long left =1;
        long right = max;
        long answer = 0;
        
  
        while (left <= right) {
        	
        	long mid = (left+right) / 2; // 전체 인덱스 기준으로 절반
        	long count =0;
        
        	for(int i=0;i<K;i++) {
        		count += arr[i] /mid;
        	}
        	
        	if(count >=N) {
        		answer = mid;
        		left = mid+1;
        	}
        	else {
        		right = mid-1;
        	}
        }
        
        System.out.println(answer);
  
   
    }
}
