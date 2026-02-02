import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SW Expert Academy
 * @author triplenieun
 * 
 * 	@see #main(String[])
 * 	1. 10개의 테스트 케이스를 처리한다.
 * 	5. 각 테스트 케이스마다 맨 앞 10개의 숫자들만 출력한다. 
 * 
 * 	@see #inputTestCase()
 * 	2. 각 테스트 케이스마다,
 * 		2-1. 원본 암호문의 길이를 입력 받는다.
 * 		2-2. 연결리스트에 원본 암호문을 입력 받는다.
 * 		2-3. 명령어의 개수를 입력 받는다.
 * 		2-4. 명령어들을 입력 받는다.
 * 	
 * 		@see #insertCommand()
 * 		3. x 위치에서 y개의 숫자를 삽입한다.
 * 		
 * 		@see #deleteCommand()
 * 		4. x 위치 다음 y개의 숫자를 삭제한다.
 * */
public class Solution {	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static List<Integer> codeList;
	public static void inputTestCase() throws Exception {
		codeList = new LinkedList<>();
		int codeLen = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		for (int codeListIdx = 0; codeListIdx < codeLen; codeListIdx++) {
			codeList.add(Integer.parseInt(st.nextToken()));
		}
		
		int commandNum = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		for (int commandIdx = 0; commandIdx < commandNum; commandIdx++) {
			String commandType = st.nextToken();
			if (commandType.equals("I")) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int [] s = new int [y];
				for (int sIdx = 0; sIdx < y; sIdx++) 
					s[sIdx] = Integer.parseInt(st.nextToken());
				insertCommand(x, y, s);
			}
		}
	}
	
	public static void insertCommand(int x, int y, int [] s) {
		for (int sIdx = y - 1; sIdx >= 0; sIdx--) {
			codeList.add(x, s[sIdx]);
		}
	}
	
	static int testCaseNum;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCaseNum = 10;
		for (int testCaseIdx = 1; testCaseIdx <= testCaseNum; testCaseIdx++) {
			inputTestCase();
			sb.append("#" + testCaseIdx);
			for (int codeListIdx = 0; codeListIdx < 10; codeListIdx++) {
				sb.append(" " + codeList.get(codeListIdx));
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
