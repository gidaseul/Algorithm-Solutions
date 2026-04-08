
import java.util.*;
import java.io.*;



// 1. 테스트 케이스 입력
// 2. N,K (물건, 최대 크기)
// 3. 선택한 부피의 합이 K이하가 되도록

public class Solution {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	
	static int[][] dp;
	static int[] weights;
	static int[] values;
	
	static int N,K;
	
    public static void main(String[] args) throws Exception {
    	
    	br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	for (int tc=1; tc<=T; tc++) {
    		
    		sb = new StringBuilder();
    		st = new StringTokenizer(br.readLine().trim());
    		
    		N = Integer.parseInt(st.nextToken());
    		K = Integer.parseInt(st.nextToken());
    		
    		dp = new int[N+1][K+1];
    		weights = new int[N+1];
    		values = new int[N+1];
    		
    		for (int i=1;i<=N; i++) {
    			st = new StringTokenizer(br.readLine().trim());
    			weights[i] = Integer.parseInt(st.nextToken());
				values[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		for (int i=1;i<=N;i++) {
    			
    			for (int j=1;j<=K;j++) {
    				dp[i][j] = dp[i-1][j];
    				if (weights[i] <= j) {
    					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i]]+values[i]);
    				}
    				
    			}
    		}
			sb.append("#").append(tc).append(" ").append(dp[N][K]).append("\n");
			System.out.print(sb);
    	}
    	
    }
   
}

