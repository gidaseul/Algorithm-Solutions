import java.io.*;
import java.util.*;

/**
 * 백준 : 감시 (15683번) 감시
 *
 *  1. 사무실은 N * M 으로 나누어져 있다.
 *  2. K개의 CCTV가 설치되어 있고 5가지의 종류가 존재한다.
 *      2-1. 1번 : 한 방향
 *      2-2. 2번 : 서로 반대 방향
 *      2-3. 3번 : 직각 방향
 *      2-4. 4번 : 3방향(이어져 있음)
 *      2-5. 5번 : 4방향
 *
 * 문제 풀이
 *     1. N,M을 입력받는다.
 *     2. N개의 줄 마다 총 M 개의 0~6까지의 값을 입력 받아 저장한다.
 *     3. 각 CCTV는 종류별로 가능한 뱡향 경우의 수가 다르므로,
 *          cctv의 상태를 담은 리스트로 배열을 담는다.
 *     4. DFS(백트래킹)을 통해서 CCTV를 하나씩 선택하면서,
 *          가능한 모든 방향 경우를 시도한다.
 *     5. 감시 영역은 cover[][] 배열을 통하여 누적 카운트 방식으로 관리한다.
 *          - 감시 추가 : +1
 *          - 복구 : -1
 *
 *      여러 CCTV가 같은 칸을 동시에 감시할 수 있기 때문에 누적하여 처리함.
 *
 *      ex) 어떤 칸을 A CCTV가 감시 -> cover = 1
 *          같은 칸을 B CCTV도 감시 -> cover = 2
 *          B를 복구 -> cover = 1
 *          => 여전히 A가 감시 중이라는 정보가 유지되어서 판단하기 편함.
 *          !(단순 복구를 하면 재귀를 하면서 확인하기 어려움)
 *
 *
 *
 *
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,M,countzero;
    static int[][] map;

    static int answer = Integer.MAX_VALUE;

    static ArrayList<Node> list = new ArrayList<>();


    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static int[][][] cctvDir = {
            {},                                           // 0번 사용 안 함
            {{0}, {1}, {2}, {3}},                        // 1번
            {{0, 2}, {1, 3}},                            // 2번
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},           // 3번
            {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}}, // 4번
            {{0, 1, 2, 3}}                               // 5번
    };


    static class Node{
        int x;
        int y;
        int type;

        Node(int x,int y,int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }


    /**
    * 한 방향(dir)으로 벽(6)을 만날 때까지 감시 처리하는 함수
    * @param x : 현재 CCTV의 행 좌표
    * @param y : 현재 CCTV의 열 좌표
    * @param dir : 감시할 방향 (상 : 0 ,우 : 1,하 : 2,좌 : 3)
    * @param cover : 현재 감시 상태를 기록하는 배열 (cover[nx][ny] > 0) 이면 감시 중
    * @param delta : 감시 상태 변화 값(+1은 다음 감시 , -1은 감시 복구)
    *
    */
    static void watch(int x, int y, int dir, int[][] cover, int delta) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        while (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != 6) {
            if (map[nx][ny] == 0) {
                cover[nx][ny] += delta;
            }

            nx += dx[dir];
            ny += dy[dir];
        }
    }

    /**
    * 하나의 CCTV에 대해 특정 방향 조합을 한 번에 적용하는 함수
    * @param cctv  : 현재 처리할 CCTV 정보
    * @param dirs  : 이번 경우에 감시할 방향 목록
    *               예) {0,1}이면 상, 우 방향 감시
    * @param cover : 현재 감시 상태 배열
    * @param delta : 감시 추가(+1) 또는 복구(-1)
    * */
    static void apply(Node cctv, int[] dirs, int[][] cover, int delta) {
        for (int dir : dirs) {
            watch(cctv.x, cctv.y, dir, cover, delta);
        }
    }

    /**
     * DFS(백트래킹) 함수
     *
     * @param depth  현재 몇 번째 CCTV를 처리 중인지 나타내는 인덱스
     *               depth == list.size() 이면 모든 CCTV의 방향 선택 완료
     * @param cover  현재까지의 감시 상태 배열
     *
     *
     * [동작 순서]
     *  1. 현재 depth 번째 CCTV를 가져온다.
     *  2. 해당 CCTV가 가능한 모든 방향 경우를 반복한다.
     *  3. 각 방향 경우를 적용한다.
     *  4. 다음 CCTV를 재귀적으로 처리한다.
     *  5. 재귀가 끝나면 원상복구한다.
     *
     * [종료 조건]
     *  모든 CCTV를 다 처리했으면,
     *  현재 map에서 감시되지 않은 0의 개수를 세어 answer를 갱신한다.
     */


    static void dfs(int depth, int[][] cover) {

        if(depth == list.size()){
            int blindSpot = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 빈 칸인데 감시되지 않은 경우
                    if (map[i][j] == 0 && cover[i][j] == 0) {
                        blindSpot++;
                    }
                }
            }
            answer = Math.min(answer, blindSpot);
            return;
        }

        Node cur = list.get(depth);

        // 가지고 있는 방향 전부 진행하기
        for(int[] dirs : cctvDir[cur.type]){
            // 감시 적용
            apply(cur,dirs,cover,+1);

            dfs(depth+1,cover);

            apply(cur,dirs,cover,-1);
        }

    }


    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        // 1. N, M 입력
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 2. 사무실 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // CCTV만 저장, 벽(6)은 제외
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    list.add(new Node(i, j, map[i][j]));
                }
            }
        }

        // 3.감시 상태 배열 생성
        int[][] cover = new int[N][M];


        // 4.DFS 시작
        dfs(0, cover);

        // 5. 정답 출력
        System.out.println(answer);

    }

}