import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Da_seul (Modified)
 * * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 * 2-1. 맵의 크기(mapSize), 채취할 수 있는 벌통 개수(M), 최대 채취량(C)을 입력받는다.
 * 2-2. 벌꿀 정보를 입력받는다.
 * 3. 두 명의 작업자가 채취할 수 있는 위치를 선정한다. (조합/완전탐색)
 * 3-1. 첫 번째 작업자의 위치를 먼저 결정하고, 겹치지 않게 두 번째 작업자의 위치를 결정한다.
 * 3-2. 각 작업자가 선택한 M개의 벌통 범위 내에서, 최대 채취량(C)을 넘지 않으면서 수익(벌꿀의 제곱 합)이 최대가 되는 부분집합을 구한다.
 * 4. 두 작업자의 수익 합 중 최대값을 갱신한다.
 */
public class Solution {

    static int mapSize, M, maxHoney;
    static int[][] honeyMap;
    static int maxProfitByWorker; // 각 범위 내에서 얻을 수 있는 최대 수익 임시 저장용
    static int totalMaxProfit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mapSize = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            maxHoney = Integer.parseInt(st.nextToken());

            // 2-2. 벌꿀 정보 입력
            honeyMap = new int[mapSize][mapSize];
            for (int i = 0; i < mapSize; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < mapSize; j++) {
                    honeyMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            totalMaxProfit = 0;
            selectWorkers(); // 3. 작업자 위치 선정 시작
            
            // 4. 최대 이익 출력
            System.out.println("#" + tc + " " + totalMaxProfit);
        }
    }

    /**
     * 3. 작업자가 채취할 수 있는 경우를 살펴본다. (완전 탐색)
     */
    public static void selectWorkers() {
        // 첫 번째 작업자의 시작 위치 (r1, c1)
        for (int r1 = 0; r1 < mapSize; r1++) {
            for (int c1 = 0; c1 <= mapSize - M; c1++) {
                
                // 두 번째 작업자의 시작 위치 (r2, c2)
                for (int r2 = 0; r2 < mapSize; r2++) {
                    for (int c2 = 0; c2 <= mapSize - M; c2++) {
                        
                        // 3-1-1-2. 영역이 겹치는지 확인 (상품성이 떨어지면 안됨)
                        // 같은 행일 때만 열 범위를 체크하여 겹침 방지
                        if (r1 == r2 && (c1 + M > c2 && c2 + M > c1)) continue;

                        // 각 작업자가 해당 영역에서 얻을 수 있는 최대 수익 계산
                        int profit1 = calculateMaxProfitForRange(r1, c1);
                        int profit2 = calculateMaxProfitForRange(r2, c2);

                        // 전체 최대 수익 갱신
                        totalMaxProfit = Math.max(totalMaxProfit, profit1 + profit2);
                    }
                }
            }
        }
    }

    /**
     * 3-1-1-3. 최대 채취량을 넘길 수 없으므로, 범위 내에서 최적의 부분집합 계산
     */
    private static int calculateMaxProfitForRange(int r, int c) {
        maxProfitByWorker = 0;
        findBestSubset(r, c, 0, 0, 0);
        return maxProfitByWorker;
    }

    /**
     * 부분집합을 이용한 수익 계산
     * @param count 현재 고려 중인 벌통 인덱스 (0 ~ M-1)
     * @param sum 현재까지 채취한 벌꿀의 합계
     * @param profit 현재까지 계산된 수익(각 벌꿀의 제곱 합)
     */
    private static void findBestSubset(int r, int c, int count, int sum, int profit) {
        // 3-1-1-3. 최대 채취량을 넘기면 중단
        if (sum > maxHoney) return;

        // M개의 벌통을 모두 고려했을 때 수익 갱신
        if (count == M) {
            maxProfitByWorker = Math.max(maxProfitByWorker, profit);
            return;
        }

        // 현재 벌통을 선택하는 경우
        int currentHoney = honeyMap[r][c + count];
        findBestSubset(r, c, count + 1, sum + currentHoney, profit + (currentHoney * currentHoney));
        
        // 현재 벌통을 선택하지 않는 경우
        findBestSubset(r, c, count + 1, sum, profit);
    }
}