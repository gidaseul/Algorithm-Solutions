import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *	SW Expert Academy
 * 	@author triplenieun
 *		
 *		@see #main(String[])
 * 		1. 테스트 케이스 개수를 입력 받는다.
 * 
 * 		@see #inputTestCase()
 * 		2. 각 테스트 케이스마다,
 * 			2-1. 격자판의 높이H, 너비W를 입력 받는다.
 * 				2-1-1. 입력 받을 때 ^ v < > 이 오면 방향을 저장한다.
 * 				2-1-2. H * W 격자판을 생성한다.
 * 			2-2. 명령 개수 N을 입력 받는다.
 * 			2-3. N 길이의 명령 문자열을 입력 받는다.
 *  
 * 		@see #executeCommand()
 * 		3. 다음 기능들을 수행한다.
 * 			3-1. U D L R 명령
 * 				3-1-1. 해당 방향으로 먼저 설정한다.
 * 				3-1-2. 앞으로 가기 전 장애물 혹은 격자판 밖인지 확인한다.
 * 				3-1-3. 이상이 없다면 앞으로 이동한다.
 * 			3-2. S 명령
 * 				3-2-1. 전차 방향으로 포탄이 이동할 때, (반복문)
 * 					3-2-1-1. * 벽돌 벽이 있다면 *를 .으로 변경하고 break.
 * 					3-2-1-2. # 강철 벽이 있다면 바로 break.
 * 
 */
public class Solution {
	static final int MAX_LEN = 100;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static char[][] battleField;
	static int height, width, commandNum;
	static String commandStr;
	static int directionIdx;	// 0:^ 1:v 2:< 3:>
	static Map<Character, Integer> dirToIdx = new HashMap<Character, Integer>() {{
	    put('^', 0);
	    put('v', 1);
	    put('<', 2);
	    put('>', 3);
	}};
	static char[] idxToDir = {'^', 'v', '<', '>'};
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int currentR, currentC;
	private static void inputTestCase() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		
		battleField = new char[height][width];
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String rowLine = br.readLine().trim();
			for (int colIdx = 0; colIdx < width; colIdx++) {
				battleField[rowIdx][colIdx] = rowLine.charAt(colIdx);
				if (battleField[rowIdx][colIdx] == '^' 
						|| battleField[rowIdx][colIdx] == 'v' 
						|| battleField[rowIdx][colIdx] == '<'
						|| battleField[rowIdx][colIdx] == '>') {
					directionIdx = dirToIdx.get(battleField[rowIdx][colIdx]);
					currentR = rowIdx;
					currentC = colIdx;
				} 
			}
		}
		
		commandNum = Integer.parseInt(br.readLine().trim());
		commandStr = br.readLine().trim();
	}
	
	public static void executeCommand() {
		for (int commandIdx = 0; commandIdx < commandNum; commandIdx++) {
			if (commandStr.charAt(commandIdx) == 'S') {
				shoot();
			} else if (commandStr.charAt(commandIdx) == 'U') {
				directionIdx = 0;
				move();
			} else if (commandStr.charAt(commandIdx) == 'D') {
				directionIdx = 1;
				move();
			} else if (commandStr.charAt(commandIdx) == 'L') {
				directionIdx = 2;
				move();
			} else if (commandStr.charAt(commandIdx) == 'R') {
				directionIdx = 3;
				move();
			}
		}
	}
	
	public static void shoot() {
		int cannonBallR = currentR;
		int cannonBallC = currentC;
		
		while (true) {
			cannonBallR += dr[directionIdx];	// 이동한 대포알의 행 위치
			cannonBallC += dc[directionIdx];	// 이동한 대포알의 열 위치
			if (cannonBallR < 0 || cannonBallR > height - 1 || cannonBallC < 0 || cannonBallC > width - 1) break;	// 격자판 바깥
			
			if (battleField[cannonBallR][cannonBallC] == '*') {
				battleField[cannonBallR][cannonBallC] = '.';
				break;
			} else if (battleField[cannonBallR][cannonBallC] == '#') {
				break;
			}
		}
	}
	
	public static void move() {
		battleField[currentR][currentC] = idxToDir[directionIdx]; // 방향 전환
		
		int moveR = currentR + dr[directionIdx];
		int moveC = currentC + dc[directionIdx];
		if (moveR < 0 || moveR > height - 1 || moveC < 0 || moveC > width - 1) return;	// 격자판 바깥
		if (battleField[moveR][moveC] == '.') {	// 물 또는 벽에 들어갈 수 없음
			battleField[currentR][currentC] = '.';

			currentR = moveR;
			currentC = moveC;
			
			battleField[currentR][currentC] = idxToDir[directionIdx];
		}
	}
	
	static int testCaseNum;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCaseNum = Integer.parseInt(br.readLine().trim());
		for (int testCaseIdx = 1; testCaseIdx <= testCaseNum; testCaseIdx++) {
			inputTestCase();
			executeCommand();
			
			sb.append("#" + testCaseIdx + " ");
			for (int rowIdx = 0; rowIdx < height; rowIdx++) {
				for (int colIdx = 0; colIdx < width; colIdx++) {
					sb.append(battleField[rowIdx][colIdx]);
				} 
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
