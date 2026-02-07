import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스의 수 T
 * 2. 각 테스트 케이스마다,
 * 2-1. 햄스터 우리 N, 햄스터 마리 X, M개의 기록(기록의 내용)
 *      2-1-1. M개 만큼의 햄스터 기록 - I~R(우리 번호), S(햄스터 수)
 * 3. 문제 풀이
 *  3.1. 1~N번 우리까지 각각ㄱ 0~X 마리를 배치하는 모든 조합
 *  3.2 배치가 완료된 시점에서만 M개의 조건을 검사하기!
 *  3.3 이 조건을 만족하는 배치 중 전체 합이 최대인 것 확인하기
 *      - 전체에사 이떄 빈 우리랑  햄스터 최대 마리 X 곱하기
 *
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N,X,M;
    static int[] cages;      // 현재 탐색 중인 햄스터 배치
    static int[] result;     // 최적의 배치 저장
    static int[][] conditions; // [M][3] 크기의 조건 저장 (l, r, s)
    static int maxHamsters;  // 최대 햄스터 합계


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            cages = new int[N + 1];
            result = new int[N + 1];
            conditions = new int[M][3];
            maxHamsters = -1; // 초기값 -1 (가능한 경우가 없을 때 출력용)

            // M 개의 명령어를 입력 받음
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                conditions[i][0] = Integer.parseInt(st.nextToken()); // l
                conditions[i][1] = Integer.parseInt(st.nextToken()); // r
                conditions[i][2] = Integer.parseInt(st.nextToken());// s
            }

            dfs(1,0);


            sb.append("#").append(tc);
            if (maxHamsters == -1) {
                // 1. 예외 케이스 처리: 조건을 만족하는 배치가 없는 경우
                sb.append(" -1");
            } else {
                // 2. 최적해 출력: 저장된 result 배열의 값을 하나씩 추가
                for (int i = 1; i <= N; i++) {
                    sb.append(" ").append(result[i]);
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int index, int currentSum){
        // 항상 조건 언제 끝나는지 - 모든 우리에 배치 끝나는 경우
        if (index > N){
            // 조건 검증
            if (isValid()) {
                if (currentSum > maxHamsters){
                    maxHamsters = currentSum;
                    for(int i = 1; i<=N; i++) {
                        result[i] = cages[i];
                    }
                }
            }
            return;
        }

        // 사전 순 출력을 위해 0부터 X까지 탐색
        // 이렇게 하면 합계가 '큰' 경우만 갱신해도 자동으로 사전 순 최소가 유지됨
        for (int i = 0; i <= X; i++) {
            cages[index] = i;
            dfs(index + 1, currentSum + i);
        }
    }
    // 조건 검증 로직
    static boolean isValid() {
        for (int i = 0; i < M; i++) {
            int l = conditions[i][0];
            int r = conditions[i][1];
            int s = conditions[i][2];

            int sum = 0;
            for (int k = l; k <= r; k++) {
                sum += cages[k];
            }
            if (sum != s) return false;
        }
        return true;
    }
}