import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *	SW Expert Academy
 * 	@author triplenieun
 *		
 *		@see #main(String[])
 * 		1. 테스트 케이스 개수를 입력 받는다.
 * 		4. 3-2에서의 배열 중 가격이 가장 높은 두 요소의 값을 더해 출력한다. 
 * 
 * 		@see #inputTestCase()
 * 		2. 각 테스트 케이스마다,
 * 			2-1. 벝통 배열의 크기 N, 선택할 수 있는 벌통 수 M, 최대 양 C를 입력 받는다.
 * 			2-2. N*N 개의 벌통들을 입력 받는다.
 * 			2-3. 연산 속도를 위해 각 칸의 수를 제곱한 수를 저장할 가격 배열을 생성한다.
 *  
 * 		@see #executeCommand()
 * 		3. 다음 기능들을 수행한다.
 * 			3-1. 각 행에서 첫 열부터 M칸씩 잡아 해당 칸에서 C를 넘지 않는 조합을 만든다. 
 * 			3-2. 각 조합에서 구한 총 가격 중 가장 큰 가격을 N * (N - M + 1) 배열에 넣는다. 
 * 
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int totalBeeBoxSize;
	static int beeBoxLen;
	static int maxHoneyAmount;
	static int[][] beeBox;
	static int[][] priceBox;
	private static void inputTestCase() throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		totalBeeBoxSize = Integer.parseInt(st.nextToken());
		beeBoxLen = Integer.parseInt(st.nextToken());
		maxHoneyAmount = Integer.parseInt(st.nextToken());
		
		beeBox = new int[totalBeeBoxSize][totalBeeBoxSize];
		priceBox = new int[totalBeeBoxSize][totalBeeBoxSize];
		for (int rowIdx = 0; rowIdx < totalBeeBoxSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIdx = 0; colIdx < totalBeeBoxSize; colIdx++) {
				beeBox[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		for (int rowIdx = 0; rowIdx < totalBeeBoxSize; rowIdx++) {
			for (int colIdx = 0; colIdx < totalBeeBoxSize; colIdx++) {
				priceBox[rowIdx][colIdx] = beeBox[rowIdx][colIdx] * beeBox[rowIdx][colIdx];
			}
		}
	}

	static int[][] maxPriceBox;
	static int[] selectedBox;
	static int[] combinationBox;
	static List<int[]> combIdxListList;
	static int[] combIdxList;
	public static void executeCommand() {
		maxPriceBox = new int[totalBeeBoxSize][totalBeeBoxSize - beeBoxLen + 1];
		for (int rowIdx = 0; rowIdx < totalBeeBoxSize; rowIdx++) {
			for (int colIdx = 0; colIdx < totalBeeBoxSize - beeBoxLen + 1; colIdx++) {
				
				int honeySum = 0;
				int honeyTotalPrice = 0;
				for (int honeyIdx = 0; honeyIdx < beeBoxLen; honeyIdx++) {
					honeySum += beeBox[rowIdx][colIdx + honeyIdx];
					honeyTotalPrice += priceBox[rowIdx][colIdx + honeyIdx];
				}
				
				if (honeySum <= maxHoneyAmount) {	// 가격 바로 계산
					maxPriceBox[rowIdx][colIdx] = Math.max(maxPriceBox[rowIdx][colIdx], honeyTotalPrice);
				} else {	// 조합(부분집합) 실행
					combIdxListList = new ArrayList<int[]>();
					for (int cnt = 1; cnt < beeBoxLen; cnt++) {
						combIdxList = new int[cnt];
						combination(cnt, 0);
					}
					
					for (int[] idxList : combIdxListList) {
						honeySum = 0;
						honeyTotalPrice = 0;
						for (int idx : idxList) {
							honeySum += beeBox[rowIdx][colIdx + idx];
							honeyTotalPrice += priceBox[rowIdx][colIdx + idx];
						}
						if (honeySum <= maxHoneyAmount) {
							maxPriceBox[rowIdx][colIdx] = Math.max(maxPriceBox[rowIdx][colIdx], honeyTotalPrice);
						}
					}
				}
								
			}
		}
	}
	
	public static void combination(int cnt, int start) {
		if (cnt == 0) {
			combIdxListList.add(combIdxList.clone());	// 그냥은 안 들어가니 주의...
			return;
		}
		for (int combIdx = start; combIdx < beeBoxLen; combIdx++) {
			combIdxList[cnt - 1] =  combIdx;
			combination(cnt - 1, combIdx + 1);
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
			
			int firstMax = 0, secondMax = 0;
			int fobbidR = 0, fobbidC = 0;
			for (int rowIdx = 0; rowIdx < totalBeeBoxSize; rowIdx++) {
				for (int colIdx = 0; colIdx < totalBeeBoxSize - beeBoxLen + 1; colIdx++) {
					if (maxPriceBox[rowIdx][colIdx] > firstMax) {
						firstMax = maxPriceBox[rowIdx][colIdx];
						fobbidR = rowIdx;
						fobbidC = colIdx;
					}
				}
			}
			
			for (int rowIdx = 0; rowIdx < totalBeeBoxSize; rowIdx++) {
				if (rowIdx == fobbidR) continue;
				for (int colIdx = 0; colIdx < totalBeeBoxSize - beeBoxLen + 1; colIdx++) {
//					if (colIdx + beeBoxLen > fobbidC || fobbidC + beeBoxLen > colIdx) continue;
					if (maxPriceBox[rowIdx][colIdx] > secondMax) {
						secondMax = maxPriceBox[rowIdx][colIdx];
					}
				}
			}
			int ans = firstMax + secondMax;
			sb.append("#").append(testCaseIdx).append(" ").append(ans).append("\n");
			
		}
		System.out.println(sb.toString());
	}
}
 