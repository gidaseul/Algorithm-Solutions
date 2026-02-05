import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 각 테스트 케이스마다,
 * 2-1. 맵의 크기, 채취할 수 있는 벌집의 개수, 채취할 수 있는 최대량을 입력받는다.
 * 2-2. 벌꿀 정보를 입력받는다. (1차원 배열로 활용)
 * 3. 작업자가 채취할 수 있는 경우를 살펴본다. (조합)
 * 3-1. 모든 작업자가 채취할 위치를 선정했으면 -> 계산
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int mapSize, collectHoneyCombCount, maxHoneyCount;
    static int[] honeyMap;
    static int[] startCollectIndexArray; // 작업자별 시작 인덱스
    static final int WORKER_COUNT = 2;
    static int totalMaxProfit;
    static int maxProfitByWorker; // 부분집합 계산용 임시 변수

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++) {
            totalMaxProfit = 0;
            inputTestCase();
            
            startCollectIndexArray = new int[WORKER_COUNT];
            // 3. 조합 시작
            collectHoney(0, 0);
            
            sb.append("#").append(tc).append(" ").append(totalMaxProfit).append("\n");
        }
        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        collectHoneyCombCount = Integer.parseInt(st.nextToken());
        maxHoneyCount = Integer.parseInt(st.nextToken());

        honeyMap = new int[mapSize * mapSize];
        for (int i = 0; i < mapSize; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < mapSize; j++) {
                honeyMap[i * mapSize + j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 3-1-1-1. 채취할 때 인덱스를 벗어나거나 행이 바뀌면 안 됨
    public static boolean isInvalidRange(int startIdx) {
        int currentRow = startIdx / mapSize;
        int lastRow = (startIdx + collectHoneyCombCount - 1) / mapSize;
        
        // 범위를 벗어나거나 가로로 연속되지 않고 다음 줄로 넘어가는 경우
        return (startIdx + collectHoneyCombCount > mapSize * mapSize) || (currentRow != lastRow);
    }

    // 3-1-1-2. 작업자 간 영역이 겹치면 안 됨 (상품성 저하 방지)
    public static boolean isDuplicated() {
        int start1 = startCollectIndexArray[0];
        int end1 = start1 + collectHoneyCombCount - 1;
        int start2 = startCollectIndexArray[1];
        int end2 = start2 + collectHoneyCombCount - 1;

        // 두 선분이 겹치는지 확인하는 조건
        return (start1 <= end2 && start2 <= end1);
    }

    // 3-1-1-3. 최대 채취량을 조절하여 최대 이익 계산 (부분집합)
    public static void calculateBestProfit(int startIdx, int count, int sum, int profit) {
        if (sum > maxHoneyCount) return; // 최대량 초과 시 탈락
        
        if (count == collectHoneyCombCount) {
            maxProfitByWorker = Math.max(maxProfitByWorker, profit);
            return;
        }

        // 현재 벌통 선택
        int honey = honeyMap[startIdx + count];
        calculateBestProfit(startIdx, count + 1, sum + honey, profit + (honey * honey));
        // 현재 벌통 선택 안 함
        calculateBestProfit(startIdx, count + 1, sum, profit);
    }

    public static void collectHoney(int workerIndex, int honeyCombIndex) {
        // 3-1. 모든 작업자의 위치 선정이 끝난 경우
        if (workerIndex == WORKER_COUNT) {
            if (isDuplicated()) return;

            int currentTotal = 0;
            for (int i = 0; i < WORKER_COUNT; i++) {
                maxProfitByWorker = 0;
                // 각 작업자 구역에서 얻을 수 있는 최적의 이익 계산
                calculateBestProfit(startCollectIndexArray[i], 0, 0, 0);
                currentTotal += maxProfitByWorker;
            }
            totalMaxProfit = Math.max(totalMaxProfit, currentTotal);
            return;
        }

        // 3-2. 모든 위치 확인 종료
        if (honeyCombIndex >= mapSize * mapSize) return;

        // 3-3. 조합 진행
        // 현재 위치를 해당 작업자의 시작점으로 선택 (유효할 경우에만)
        if (!isInvalidRange(honeyCombIndex)) {
            startCollectIndexArray[workerIndex] = honeyCombIndex;
            collectHoney(workerIndex + 1, honeyCombIndex + 1);
        }

        // 현재 위치를 선택하지 않고 다음 칸 확인
        collectHoney(workerIndex, honeyCombIndex + 1);
    }
}