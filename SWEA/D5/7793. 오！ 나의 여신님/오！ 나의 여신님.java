import java.io.*;
import java.util.*;

/**
 *
 * @author gidaseul
 * SWEA 7793. 오! 나의 여신님
 *
 *	1. 입력받는다.
 *		1-1. 테스트 케이스 수 T를 입력받는다.
 *		1-2. 맵의 세로 크기 N, 가로 크기 M을 입력받는다.
 *		1-3. N개의 줄에 걸쳐 맵 정보를 입력받는다.
 *			1-3-1. 수연이의 시작 위치 S를 저장한다.
 *			1-3-2. 악마의 시작 위치 * 를 큐에 저장한다.
 *
 *	2. 악마가 각 칸에 도달하는 최소 시간을 구한다.
 *		2-1. 악마는 여러 위치에서 동시에 시작하므로 멀티소스 BFS를 사용한다.
 *		2-2. 돌 X와 여신의 공간 D는 악마가 확장할 수 없다.
 *		2-3. devilTime 배열에 각 칸에 대한 악마 도착 시간을 기록한다.
 *
 *	3. 수연이가 여신의 공간 D에 도달하는 최소 시간을 구한다.
 *		3-1. 수연이는 상하좌우로 한 칸씩 이동할 수 있다.
 *		3-2. 돌 X는 이동할 수 없다.
 *		3-3. 악마가 먼저 도착했거나 같은 시간에 도착하는 칸은 이동할 수 없다.
 *		3-4. suyeonTime 배열에 각 칸에 대한 수연의 도착 시간을 기록한다.
 *
 *	4. 여신의 공간 D에 도달하면 최소 시간을 출력한다.
 *		4-1. 도달할 수 없으면 GAME OVER를 출력한다.
 */
public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, N, M;
    static char[][] map;
    static int[][] devilTime;
    static int[][] suyeonTime;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 범위 안에 있는지 확인한다.
    static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    // 악마가 각 칸에 도달하는 최소 시간을 BFS로 구한다.
    static void bfsDevil(Queue<Node> q) {
        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!isIn(nx, ny)) continue;
                if (devilTime[nx][ny] != -1) continue;
                if (map[nx][ny] == 'X') continue;
                if (map[nx][ny] == 'D') continue;

                devilTime[nx][ny] = devilTime[cur.x][cur.y] + 1;
                q.offer(new Node(nx, ny));
            }
        }
    }

    // 수연이가 여신의 공간에 도달하는 최소 시간을 BFS로 구한다.
    static int bfsSuyeon(Node start) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(start);
        suyeonTime[start.x][start.y] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (map[cur.x][cur.y] == 'D') {
                return suyeonTime[cur.x][cur.y];
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (!isIn(nx, ny)) continue;
                if (suyeonTime[nx][ny] != -1) continue;
                if (map[nx][ny] == 'X') continue;

                int nextTime = suyeonTime[cur.x][cur.y] + 1;

                if (map[nx][ny] != 'D') {
                    if (devilTime[nx][ny] != -1 && devilTime[nx][ny] <= nextTime) continue;
                }

                suyeonTime[nx][ny] = nextTime;
                q.offer(new Node(nx, ny));
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 1-1. 테스트 케이스 수를 입력받는다.
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {

            // 1-2. 맵의 크기 N, M을 입력받는다.
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 악마와 수연의 도착 시간을 저장할 배열을 생성한다.
            map = new char[N][M];
            devilTime = new int[N][M];
            suyeonTime = new int[N][M];

            // 각 칸을 아직 방문하지 않은 상태인 -1로 초기화한다.
            for (int i = 0; i < N; i++) {
                Arrays.fill(devilTime[i], -1);
                Arrays.fill(suyeonTime[i], -1);
            }

            Queue<Node> devilQ = new ArrayDeque<>();
            Node start = null;

            // 1-3. 맵 정보를 입력받는다.
            for (int i = 0; i < N; i++) {
                String line = br.readLine();

                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);

                    // 1-3-1. 수연이의 시작 위치를 저장한다.
                    if (map[i][j] == 'S') {
                        start = new Node(i, j);
                    }

                    // 1-3-2. 악마의 시작 위치를 큐에 저장하고 시간을 0으로 설정한다.
                    else if (map[i][j] == '*') {
                        devilQ.offer(new Node(i, j));
                        devilTime[i][j] = 0;
                    }
                }
            }

            // 2. 악마가 각 칸에 도달하는 최소 시간을 구한다.
            bfsDevil(devilQ);

            // 3. 수연이가 여신의 공간에 도달하는 최소 시간을 구한다.
            int answer = bfsSuyeon(start);

            // 4. 결과를 출력 형식에 맞게 저장한다.
            sb.append("#").append(tc).append(" ");

            if (answer == -1) {
                sb.append("GAME OVER");
            } else {
                sb.append(answer);
            }

            sb.append("\n");
        }

        System.out.print(sb);
    }
}