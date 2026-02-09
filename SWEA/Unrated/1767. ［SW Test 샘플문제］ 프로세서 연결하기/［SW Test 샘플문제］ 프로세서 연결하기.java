import java.io.*;
import java.util.*;

/*
 * 1767. 프로세서 연결하기
 *
 * [문제 핵심]
 * 1. 내부 코어를 최대한 많이 가장자리와 연결해야 한다.
 * 2. 연결한 코어 개수가 같다면, 전선 길이의 합을 최소로 한다.
 *
 * [해결 전략]
 * - 가장자리에 위치한 코어는 이미 연결된 상태이므로 제외한다.
 * - 내부 코어만 리스트에 저장하여 백트래킹 탐색을 진행한다.
 * - 각 코어마다 5가지 선택을 고려한다.
 *     1) 상
 *     2) 하
 *     3) 좌
 *     4) 우
 *     5) 연결하지 않음
 *
 * [탐색 방법]
 * - DFS(코어 인덱스, 연결 개수, 전선 길이)
 * - 모든 코어를 처리하면 결과 갱신
 *
 * [가지치기]
 * - 남은 코어를 전부 연결해도 현재 최대 연결 개수를 넘지 못하면 탐색 중단
 *
 * [백트래킹 핵심]
 * - 전선 설치 후 반드시 원상복구
 *
 * [시간 복잡도]
 * - 최악의 경우 4^12 수준
 * - 가지치기를 통해 실제 탐색 수는 크게 감소
 */


public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;                     // 격자 크기
    static int[][] map;               // 원본 맵 (0: 빈칸, 1: 코어, 2: 전선)
    static List<int[]> cores;         // 가장자리가 아닌 내부 코어만 저장
    static int maxCore;               // 현재까지 연결한 코어의 최대 개수
    static int minLength;             // maxCore일 때의 최소 전선 길이

    // 상, 하, 좌, 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            // 맵 입력
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    // 가장자리가 아닌 코어만 리스트에 저장
                    if (map[i][j] == 1) {
                        if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                            cores.add(new int[]{i, j});
                        }
                    }
                }
            }

            maxCore = 0;
            minLength = Integer.MAX_VALUE;

            // 백트래킹 시작
            dfs(0, 0, 0);

            sb.append("#").append(t).append(" ").append(minLength).append("\n");
        }
        System.out.print(sb);
    }

    /**
     * @param idx        현재 처리할 코어의 인덱스
     * @param connected  지금까지 연결한 코어 개수
     * @param length     지금까지 사용한 전선 길이
     */

    static void dfs(int idx, int connected, int length){

        // 모든 코어를 다 처리했다면
        if(idx == cores.size()){
            // 1순위 : 더 많이 연결한 경우
            if(connected > maxCore){
                maxCore = connected;
                minLength = length;
            }
            else if (connected == maxCore){
                minLength = Math.min(minLength, length);
            }
            return;
        }

        // 가지치기
        // 남은 코어를 전부 연결해도 maxCore보다 작으면 더 볼 필요 없음
        if(connected + (cores.size() - idx) < maxCore) return;

        int x = cores.get(idx)[0];
        int y = cores.get(idx)[1];


        // 4방향 연결 시도
        for(int d = 0; d < 4; d++) {

            int nx = x;
            int ny = y;
            int count = 0;          // 이 방향으로 연결할 때 전선 길이
            boolean possible = true;

            // 해당 방향으로 끝까지 갈 수 있는지 확인
            while(true) {
                nx += dx[d];
                ny += dy[d];

                // 가장자리에 도달하면 성공
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) break;

                // 다른 코어나 전선을 만나면 실패
                if(map[nx][ny] != 0) {
                    possible = false;
                    break;
                }
            }

            if(!possible) continue;

            // 실제로 전선 설치
            nx = x;
            ny = y;

            while(true) {
                nx += dx[d];
                ny += dy[d];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) break;

                map[nx][ny] = 2;   // 전선 표시
                count++;
            }

            // 다음 코어로 진행
            dfs(idx + 1, connected + 1, length + count);

            // 백트래킹: 전선 제거 (원상복구)
            nx = x;
            ny = y;

            while(true) {
                nx += dx[d];
                ny += dy[d];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) break;

                map[nx][ny] = 0;
            }
        }

        // 현재 코어를 연결하지 않는 경우
        dfs(idx + 1, connected, length);
    }
}
