import java.io.*;
import java.util.*;

/*
 * SW Expert Academy
 *
 * @Author: gidaseul
 * SWEA 5643.[Professional] 키 순서(D4)
 *
 * 문제
 * N명의 학생들 사이에서 키 비교 결과(a < b)가 M번 주어질 때,
 * 자신의 키 순위를 정확히 알 수 있는 학생 수를 구하라.
 *
 * 풀이 전략 : 플로이드-워셜 (전체 쌍 도달 가능성 확인)
 *
 * 1. 입력 처리
 *    - N: 학생 수 (노드)
 *    - M: 비교 횟수 (간선)
 *    - a < b -> arr[a][b] = true (방향 그래프)
 *
 * 2. 플로이드-워셜로 간접 비교 전파
 *    - arr[i][k] && arr[k][j] -> arr[i][j] = true
 *    - ex) 1<3, 3<5 -> 1<5 자동 확정
 *    - 시간복잡도: O(N^3)
 *
 * 3. 순위 확정 조건 판별
 *    - i번 학생 기준, 나머지 N-1명 모두와 비교 가능해야 순위 확정
 *    - arr[i][j] || arr[j][i] -> i와 j 비교 가능
 *    - count == N-1 이면 answer++
 *
 * 4. 출력
 *    - StringBuilder로 누적 후 한 번에 출력하기
 *    - 형식: #TC answer
 */

public class Solution {

    static int N, M, answer,T;
    static boolean[][] arr;

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            // 학생들의 수
            N = Integer.parseInt(br.readLine().trim());
            arr = new boolean[N + 1][N + 1];

            // 비교 횟수로 값을 받을 것
            M = Integer.parseInt(br.readLine().trim());

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine().trim());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a][b] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (arr[i][k] && arr[k][j]) {
                            arr[i][j] = true;
                        }
                    }
                }
            }


            answer = 0;
            for (int i = 1; i <= N; i++) {

                int count = 0;

                for (int j = 1; j <= N; j++) {

                    if (i == j) continue;

                    if (arr[i][j] || arr[j][i]) {
                        count++;
                    }
                }
                if (count == N-1){
                    answer++;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}