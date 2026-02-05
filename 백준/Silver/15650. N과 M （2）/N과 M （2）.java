import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *	BOJ
 * 	@author triplenieun
 *		
 *	1. N과 M을 입력 받는다.
 *	
 *	2. 조합 함수를 구성하여 nCm의 모든 경우를 출력한다.
 *
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[] numbers;
	public static void combination(int cnt, int start) {
		if (cnt == M) {
			for (int number : numbers) {
				sb.append(number).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int combIdx = start; combIdx <= N; combIdx++) {
			numbers[cnt] = combIdx;
			combination(cnt + 1, combIdx + 1);
		}
	}
	
	static int N;
	static int M;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		numbers = new int[M];
		combination(cnt, 1);
		
		System.out.println(sb.toString());
	}
}
