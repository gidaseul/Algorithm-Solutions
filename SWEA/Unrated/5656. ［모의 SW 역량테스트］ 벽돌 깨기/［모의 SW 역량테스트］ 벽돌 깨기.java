import java.io.*;
import java.util.*;

/*
 * SWEA 벽돌 깨기
 *
 * 개선된 풀이 방식
 * 1. bead[] 중복순열을 따로 만들지 않음
 * 2. dfs(depth, map) 형태로 현재 맵 상태를 직접 넘김
 * 3. 각 depth에서 열 하나를 선택하고, 그 결과 map으로 다음 depth 진행
 * 4. 공통 prefix 상태를 재사용하므로 기존보다 효율적
 */

public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int T, N, W, H;
    static int minResult;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Node {
        int x, y, range;

        Node(int x, int y, int range) {
            this.x = x;
            this.y = y;
            this.range = range;
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int[][] originMap = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < W; j++) {
                    originMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            minResult = Integer.MAX_VALUE;

            dfs(0, originMap);

            sb.append("#").append(tc).append(" ").append(minResult).append("\n");
        }

        System.out.print(sb);
    }

    /**
     * depth번째 구슬을 쏘는 단계
     * curMap: 현재까지 구슬을 쏜 결과 상태
     */
    static void dfs(int depth, int[][] curMap) {
        if (minResult == 0) return;

        int remain = countBricks(curMap);

        if (remain == 0) {
            minResult = 0;
            return;
        }

        if (depth == N) {
            minResult = Math.min(minResult, remain);
            return;
        }

        boolean skippedEmpty = false;

        for (int col = 0; col < W; col++) {
            int topRow = findTopBrick(curMap, col);

            // 빈 열인 경우
            // 빈 열 여러 개는 결과가 모두 동일하므로 한 번만 탐색
            if (topRow == -1) {
                if (skippedEmpty) continue;
                skippedEmpty = true;

                dfs(depth + 1, curMap);
                continue;
            }

            int[][] nextMap = copyMap(curMap);
            shot(nextMap, col);
            dfs(depth + 1, nextMap);
        }
    }

    /**
     * 해당 열에서 가장 위에 있는 벽돌 찾기
     */
    static int findTopBrick(int[][] map, int col) {
        for (int i = 0; i < H; i++) {
            if (map[i][col] > 0) return i;
        }
        return -1;
    }

    /**
     * 구슬 한 번 쏘기
     * 1. 해당 열의 가장 위 벽돌 찾기
     * 2. BFS로 연쇄 폭발
     * 3. 중력 적용
     */
    static void shot(int[][] map, int col) {
        int startX = findTopBrick(map, col);

        if (startX == -1) return;

        Queue<Node> q = new ArrayDeque<>();

        q.offer(new Node(startX, col, map[startX][col]));
        map[startX][col] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.range == 1) continue;

            for (int dir = 0; dir < 4; dir++) {
                for (int dist = 1; dist < cur.range; dist++) {
                    int nx = cur.x + dx[dir] * dist;
                    int ny = cur.y + dy[dir] * dist;

                    if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
                    if (map[nx][ny] == 0) continue;

                    q.offer(new Node(nx, ny, map[nx][ny]));
                    map[nx][ny] = 0;
                }
            }
        }

        blockSetting(map);
    }

    /**
     * 중력 처리
     * 각 열마다 아래로 벽돌을 떨어뜨림
     */
    static void blockSetting(int[][] map) {
        for (int col = 0; col < W; col++) {
            int writeRow = H - 1;

            for (int row = H - 1; row >= 0; row--) {
                if (map[row][col] > 0) {
                    int value = map[row][col];
                    map[row][col] = 0;
                    map[writeRow][col] = value;
                    writeRow--;
                }
            }

            while (writeRow >= 0) {
                map[writeRow][col] = 0;
                writeRow--;
            }
        }
    }

    /**
     * 현재 남은 벽돌 수 세기
     */
    static int countBricks(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0) cnt++;
            }
        }
        return cnt;
    }

    /**
     * 2차원 배열 깊은 복사
     */
    static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            newMap[i] = map[i].clone();
        }
        return newMap;
    }
}