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
				
				boolean[] isVisted = new boolean[N];
				for(int r=1; r<=N; r++) {
					combination(isVisted, 0, r);
				}
				sb.append("#").append(t).append(" ").append(maxScore).append("\n");
				System.out.print(sb);
		}
		
	}
	// 매번 visited 배열로 선택할 상태들을 이야기 함.
	static void combination(boolean[] visited, int start, int r) {		
		
		if (r==0) {
			// 계산 로직 - 함수 호출
			calculate(visited);
			return;
		}
		
		for (int i=start; i<N; i++) {
			visited[i] = true;
			combination(visited,i+1,r-1);
			visited[i] = false;
		}
	}
	
	
	static void calculate(boolean[] visited) {
		int totalScore = 0;
        int totalKcal = 0;
        
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                totalScore += scoreRecord[i];
                totalKcal += kcalRecord[i];
            }
        }
        
        // 칼로리 제한 조건을 만족할 때만 최대 점수 갱신
        if (totalKcal <= L) {
            maxScore = Math.max(maxScore, totalScore);
        }
	}
}