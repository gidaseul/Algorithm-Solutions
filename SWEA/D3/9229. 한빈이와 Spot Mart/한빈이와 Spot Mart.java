import java.util.*;
import java.io.*;

/*
 * SW Expert Academy
 * @author Da_seul
 * 
 * 	@see #main(String[])
 * 	input
 * 		1. 테스트 케이스 수 입력 받기 (TC) (1-1~1-2) * TC
 * 			1-1. 첫번째 테스트 케이스의 N,M 
 * 			1-2. N개의 과자 봉지 무게  
 *		
 *		2. 문제 풀이 방법 (정렬 후 pointer 사용 예정)
 *			2-1. 입력 받은 배열을 정렬
 *			2-2. e,s를 가지고 조건에 맞게 움직이도록 함.
 *
 *		3. 2개 선택 후 최대 무게 선택 후 결과 값
 *			3-1. 최대 무게 있을 경우 : return 최대 무게;
 *			3-2. 없는 경우 : return -1;
 *
 */

public class Solution
{
	
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String args[]) throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=TC; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()); // 최대값
			
			// 과자 봉지 무게 값 넣기
			st = new StringTokenizer(br.readLine());
			int[] weight = new int[N];
			for(int i=0; i<N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(weight);
			
			int s=0, e=N-1, sum=0, maxSum=0;
			while(s<e) {
				sum = weight[s] + weight[e];
				if(sum > M) {
					--e;
				}
				else{
					maxSum = Math.max(maxSum, sum);
					++s;
				}
			}
			if(maxSum ==0) {
				maxSum = -1;
			}
			
			System.out.println("#"+t+" "+maxSum);
		}
		
		
	}
}