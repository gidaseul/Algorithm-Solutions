import java.io.BufferedReader;
import java.io.InputStreamReader;
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
 * 			2-1. 카드 9장을 입력 받는다.
 * 			2-2. 1~18에서 입력 받은 카드를 제외한 인영이의 카드 9장을 추출한다. 
 *  
 * 		@see #executeCommand()
 * 		3. 다음 기능들을 수행한다.
 * 			3-1. 인영이 카드 9장의 모든 순열 조합을 구한다. permCard 함수 사용
 * 			3-2. 각 순열 조합에서 승패 여부를 체크한다. vsKyu 함수 사용
 * 
 * 			@see #permCard(int cnt)
 * 			3-1. 인영이 카드 9장의 모든 순열 조합을 구한다.
 * 
 * 			@see #vsKyu()
 * 			3-2. 각 순열 조합에서 승패 여부를 체크한다.
 * 				3-2-1. -1이면 패배
 * 				3-2-2. 0이면 무승부
 * 				3-2-3. 1이면 승리
 */
public class Solution {
	static final int CARDS_NUM = 9;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int [] kyuCards;
	static int [] yinCards;
	private static void inputTestCase() throws Exception {
		kyuCards = new int[CARDS_NUM];
		yinCards = new int[CARDS_NUM];
		st = new StringTokenizer(br.readLine().trim());
		
		boolean [] selectedCards = new boolean[CARDS_NUM * 2 + 1]; 
		for (int cardIdx = 0; cardIdx < CARDS_NUM; cardIdx++) {
			kyuCards[cardIdx] = Integer.parseInt(st.nextToken());
			selectedCards[kyuCards[cardIdx]] = true;
		}
		
		for (int allCardIdx = 1, yinCardIdx = 0; allCardIdx <= CARDS_NUM * 2; allCardIdx++) {
			if (!selectedCards[allCardIdx]) yinCards[yinCardIdx++] = allCardIdx;
		}
	}
	
	static boolean [] isSelected;
	static int [] permCards;
	static int victory, defeat;
	public static void executeCommand() {
		int cnt = 0;
		victory = defeat = 0;
		isSelected = new boolean[CARDS_NUM];
		permCards = new int[CARDS_NUM];
		
		permCard(cnt);
	}
	
	public static void permCard(int cnt) {
		if (cnt == CARDS_NUM) {
			int vsResult = vsKyu();
			if (vsResult > 0) victory++;
			else if (vsResult < 0) defeat++;
		} else {
			for (int cardIdx = 0; cardIdx < CARDS_NUM; cardIdx++) {
				if (isSelected[cardIdx]) continue;
				isSelected[cardIdx] = true;
				permCards[cnt] = yinCards[cardIdx];
				permCard(cnt + 1);
				isSelected[cardIdx] = false;
			}
		}
	}
	
	public static int vsKyu() {
		int kyuScore = 0;
		int yinScore = 0;
		for (int cardIdx = 0; cardIdx < CARDS_NUM; cardIdx++) {
			if (kyuCards[cardIdx] > permCards[cardIdx]) kyuScore += kyuCards[cardIdx] + permCards[cardIdx];
			else if (kyuCards[cardIdx] < permCards[cardIdx]) yinScore += kyuCards[cardIdx] + permCards[cardIdx];
		}
		
		if (kyuScore > yinScore) return 1;
		else if (kyuScore < yinScore) return -1;
		else return 0;
	}
	
	static int testCaseNum;
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		testCaseNum = Integer.parseInt(br.readLine().trim());
		for (int testCaseIdx = 1; testCaseIdx <= testCaseNum; testCaseIdx++) {
			inputTestCase();
			executeCommand();
			
			sb.append("#" + testCaseIdx + " " + victory + " " + defeat + "\n");
		}
		
		
		System.out.println(sb.toString());
	}
}
