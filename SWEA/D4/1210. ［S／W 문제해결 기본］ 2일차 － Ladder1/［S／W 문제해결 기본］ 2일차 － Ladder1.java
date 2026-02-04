import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *	SW Expert Academy
 * 	@author triplenieun
 *		
 *		@see #main(String[])
 * 		1. 테스트 케이스는 10개다.		
 * 
 * 		@see #inputTestCase()
 * 		2. 각 테스트 케이스마다,
 * 			2-1. 사다리를 99번째 줄까지 2차원 배열로 입력 받는다.
 * 			2-2. 100번째 줄에서도 동일하게 입력 받는 대신, 2인 지점을 저장한다.
 *  
 * 		@see #executeCommand()
 * 		3. 다음 기능들을 수행한다.
 * 			3-1. 도착점에서부터 거꾸로 거슬러 올라간다.
 * 			3-2. 한 칸 올라가기 전에 항상 오른쪽, 왼쪽에 길이 있는지 확인한다.
 * 				3-2-1. 0, 99번째 열에서는 한쪽만 확인한다.
 * 				3-2-2. 만약 꺾여 갔다면 다시 위쪽 방향에 길이 있을 때까지 앞으로 간다.
 * 			3-3. 0번 행에 도착하면 그때의 열 인덱스를 저장한다.
 */
public class Solution {
	static final int MAX_LEN = 100;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int[][] ladder;
	static int[] endPoint;
	private static void inputTestCase() throws Exception {
		ladder = new int[MAX_LEN][MAX_LEN];
		// 2-1. 사다리를 99번째 줄까지 2차원 배열로 입력 받는다.
		for (int rowIdx = 0; rowIdx < MAX_LEN - 1; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIdx = 0; colIdx < MAX_LEN; colIdx++) {
				ladder[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine().trim());
		for (int colIdx = 0; colIdx < MAX_LEN; colIdx++) {
			ladder[MAX_LEN - 1][colIdx] = Integer.parseInt(st.nextToken());
			if (ladder[MAX_LEN - 1][colIdx] == 2) endPoint = new int[] {99, colIdx};
		}
	}
	
	static int startColIdx;
	public static void executeCommand() {
		int currentR, currentC;
		currentR = endPoint[0];
		currentC = endPoint[1];
		
		// 위: 0 / 왼: 2 / 오: 3
		int [] dr = new int[] {-1, 1, 0, 0};
		int [] dc = new int[] {0, 0, -1, 1};
		int direction = 0;
		while(true) {
			if (currentR - 1 < 0) {
				startColIdx = currentC;
				break;
			}
			
			if (direction != 0 && ladder[currentR - 1][currentC] == 1) {
				direction = 0;
			} else if (direction == 0 && currentC < MAX_LEN - 1 && ladder[currentR][currentC + 1] == 1) {
				direction = 3;
			} else if (direction == 0 && currentC > 0 && ladder[currentR][currentC - 1] == 1) {
				direction = 2;
			}
			
			currentR += dr[direction];
			currentC += dc[direction];
		}
	}
	
	
	static int testCaseNum;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCaseNum = 10; // Integer.parseInt(br.readLine().trim());
		for (int testCaseIdx = 1; testCaseIdx <= testCaseNum; testCaseIdx++) {
			br.readLine().trim(); // 테스트 케이스 번호 입력 무시
			inputTestCase();
			executeCommand();
			
			sb.append("#" + testCaseIdx + " " + startColIdx + "\n");
		}
		
		
		System.out.println(sb.toString());
	}
}
