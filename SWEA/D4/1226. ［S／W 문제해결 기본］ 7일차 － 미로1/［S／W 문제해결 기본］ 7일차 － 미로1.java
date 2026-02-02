import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * SW Expert Academy
 * @author triplenieun
 * 
 * 	@see #main(String[])
 * 	1. 10개의 테스트 케이스를 처리한다.
 * 
 * 	@see #inputTestCase()
 * 	2. 각 테스트 케이스마다,
 * 		2-1. 미로 2차원 배열을 입력 받는다.
 * 		2-2. 입력 받을 때, 2와 3인 좌표는 각각 출발점, 도착점으로 저장한다.  
 * 		2-3. 미로의 각 좌표를 담을 스택을 생성하고 출발점 좌표를 넣어둔다.
 * 	
 * 	@see #executeCommand()
 * 	3. 각 기능을 수행한다.
 * 		3-1. 스택의 peak로 현재 위치에서 사방에 0이 있는지 확인한다.
 * 			3-1-1. 0이 있으면 현재 좌표는 -1로, 해당 좌표를 스택에 push한다.
 * 			3-1-2. 스택의 top의 값 말고 0이 없으면 해당 좌표를 0에서 1로 수정한다.
 * 				3-1-2-1. 스택의 top으로 이동하고 3-1을 반복한다.
 * 		3-2. 도착점을 push하게 되면 도달이 가능함을 의미하고,
 * 		3-3. 스택이 결국 다 비워질 때까지 길을 찾지 못하면 도달이 불가능하다. 
 * */
public class Solution {
	static final int MAZE_SIZE = 16;
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static int [][] maze;
	static int [] startPoint;
	static int [] endPoint;
	static Stack<int[]> mazeRunner;
	public static void inputTestCase() throws Exception {
		maze = new int[MAZE_SIZE][MAZE_SIZE];
		mazeRunner = new Stack<>();
		for (int inputIdx1 = 0; inputIdx1 < MAZE_SIZE; inputIdx1++) {
			String inputLine = br.readLine().trim();
			for (int inputIdx2 = 0; inputIdx2 < MAZE_SIZE; inputIdx2++) {
				maze[inputIdx1][inputIdx2] = inputLine.charAt(inputIdx2) - '0';
				if (inputLine.charAt(inputIdx2) - '0' == 2) 
					startPoint = new int[] {inputIdx1, inputIdx2};
				if (inputLine.charAt(inputIdx2) - '0' == 3) 
					endPoint = new int[] {inputIdx1, inputIdx2};
			}
		}
		mazeRunner.push(startPoint);
	}
	
	static final int [] dr = {-1, 1, 0, 0};
	static final int [] dc = {0, 0, -1, 1};
	static boolean isPossible;
	public static void executeCommand() {
		isPossible = false;
		while (!mazeRunner.isEmpty() && !isPossible) {
			int r = mazeRunner.peek()[0];
			int c = mazeRunner.peek()[1];
			boolean flag = false;
			for (int deltaIdx = 0; deltaIdx < 4 && !flag; deltaIdx++) {
				int nr = r + dr[deltaIdx];
				int nc = c + dc[deltaIdx];
				
				if (nr < 0 || nr >= MAZE_SIZE || nc < 0 || nc >= MAZE_SIZE) {
					continue;
				}
				
				// 3-2. 도착점을 push하게 되면 도달이 가능함을 의미하고,
				if (maze[nr][nc] == 3) {
					isPossible = true;
					break;
				}
				
				// 3-1-1. 0이 있으면 현재 좌표는 -1로, 해당 좌표를 스택에 push한다.
				if (maze[nr][nc] == 0) {
					maze[r][c] = -1;
					mazeRunner.push(new int [] {nr, nc});
					break;
				}
				
				// 3-1-2. 스택의 top의 값 말고 0이 없으면 해당 좌표를 0에서 1로 수정한다.
				if (deltaIdx == 3) {
					maze[mazeRunner.peek()[0]][mazeRunner.peek()[1]] = 1;
					mazeRunner.pop();
				}
			}
		}
	}
	
	static int testCaseNum;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCaseNum = 10;
		for (int testCaseIdx = 1; testCaseIdx <= testCaseNum; testCaseIdx++) {
			br.readLine().trim();
			inputTestCase();
			executeCommand();
			sb.append("#" + testCaseIdx + " ");
			if (isPossible)
				sb.append("1\n");
			else
				sb.append("0\n");
		}
		System.out.println(sb.toString());
	}
}
