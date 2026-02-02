import java.util.*;
import java.io.*;

public class Solution
{
	static StringBuilder sb;
	static StringTokenizer st;
	static BufferedReader br;
	static int currentSum;
	static int finalMax;
	
	
	
	public static void main(String args[]) throws IOException
	{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		for(int test_case = 1; test_case <= T; test_case++)
		{	
			currentSum =0;
			finalMax =0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 선택한 자신의 지점으로부터 M만큼 확장해서 더하고 새롭게 넣으면 됨.
			for(int startX = 0; startX<N-M+1;startX++) {
				
				for(int startY =0; startY<N-M+1;startY++) {
				
					
					for(int MX=0; MX<M;MX++) {
						for(int MY=0; MY<M;MY++) {
						currentSum += map[startX+MX][startY+MY];
						}
					}
					finalMax = Math.max(currentSum, finalMax);
					currentSum=0;
				}
			} 
			 

			System.out.println("#"+test_case+" "+ finalMax); // 결과값 
		}
	}
}
