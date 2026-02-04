import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *	SW Expert Academy
 * 	@author triplenieun
 *		
 *		@see #main(String[])
 * 		1. N과 M을 입력 받는다.		
 * 
 * 		@see #executeCommand(int cnt)
 * 		2. 다음 기능들을 수행한다.
 * 			2-1. 1~N까지의 수에서 M만큼 선택한다.
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static boolean[] isSelected;
	static int[] numbers;
	public static void executeCommand(int cnt) {
		if (cnt == M) {
			for (int j = 0; j < M; j++) {
				sb.append(numbers[j] + " ");
			}
			sb.append("\n");
		} else {
			for (int i = 1; i <= N; i++) {
				if (isSelected[i]) continue;
				numbers[cnt] = i;
				isSelected[i] = true;
				executeCommand(cnt + 1);
				isSelected[i] = false;
			}
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
		isSelected = new boolean[N + 1];
		numbers = new int[M];
		executeCommand(cnt);
		
		System.out.println(sb.toString());
	}
}
