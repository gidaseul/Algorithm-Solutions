import java.util.*;
import java.io.*;

/*
 * SW Expert Academy 
 * @author Da_seul
 * 
 * 	@see #main(String[])
 * 		
 */
public class Solution
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int N, L, maxScore;
    static int[] scoreRecord, kcalRecord;
    
	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int t=1; t <= testCase; t++) {
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 수 N
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리 L
			scoreRecord = new int[N];
			kcalRecord = new int[N];
			maxScore = 0;
			
				for (int i=0; i<N;i++) {		
					st = new StringTokenizer(br.readLine());
					scoreRecord[i] = Integer.parseInt(st.nextToken());
					kcalRecord[i] = Integer.parseInt(st.nextToken());
				}
				
				generateSubset(0,0,0);
				
				sb.append("#").append(t).append(" ").append(maxScore).append("\n");
				System.out.print(sb);
		}
		
	}
	// 매번 visited 배열로 선택할 상태들을 이야기 함.
	static void generateSubset(int idx, int cuurentScore, int currentKcal) {		
		
		if (currentKcal > L) return;
		
		// 전체 판단 진행할 경우
		if (idx == N) {
			maxScore = Math.max(maxScore, cuurentScore);
			return;
		}
		// 현재 재료를 선택할 경우
		generateSubset(idx+1,cuurentScore+scoreRecord[idx], currentKcal + kcalRecord[idx]);
		// 현재 재료를 선택하지 않는 경우
		generateSubset(idx+1,cuurentScore, currentKcal);

	}
}