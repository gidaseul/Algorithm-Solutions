import java.io.*;
import java.util.*;

/**
 * SW Expert Academy 1227 : [문제 해결 기본] 7일차 - 미로2
 *
 *  문제 설명:
 *      1. 100 x 100 미로 존재 (공간 배열의 100 x 100) 이라는 점을 잘 생각해봐야 함.
 *      2. 시작점 (1,1) : '2' -> 도착점 (13,13) : '3'
 *      3. 출발점 부터 도착지점까지 갈 수 있는 길이 있는지 여부 파악하기
 *
 *  해결 접근:
 *      1. 미로를 탐색하는 부분에서 BFS로 풀어봄.
 *          1-1. (1,1) 에서 탐색 시작함.
 *          1-2. 탐색 방향은 4가지 방향 존재함.
 *              1-2-1. 방향을 탐색하면서 주의할 점.
 *                  1-2-1-1. 방해물(1), 이미 갔던 곳, 배열을 벗어난 nx,ny 가 아닐 경우에만 탐색
 *              1-2-2. 탐색 하면서 탐색 가능성 있는 것만 큐에 집어넣기
 *
 *
 */
public class Solution {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static boolean[][] check;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            map= new int[100][100];
            check = new boolean[100][100];
            String T = br.readLine();
            for (int i = 0; i < 100; i++) {
                String line = br.readLine().trim();
                for (int j = 0; j < 100; j++) {
                    int a = line.charAt(j) - '0';
                    map[i][j] = a;
                }
            }
            sb.append("#").append(tc).append(" ");
            if(bfs(1,1)) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.println(sb);
        sb.setLength(0);
    }

    static boolean bfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x,y});
        check[x][y] = true;


        while(!q.isEmpty()){
            int [] cur = q.poll();
            int ax = cur[0];
            int ay = cur[1];

            if(map[ax][ay] == 3) return true;

            for(int i=0; i<4;i++){
                int nx = ax+dx[i];
                int ny = ay+dy[i];

                if(nx >=0 && nx < 100 && ny >= 0 && ny < 100 && map[nx][ny] !=1) {
                    if(!check[nx][ny]){
                        check[nx][ny] = true;
                        q.offer(new int[]{nx,ny});
                    }
                }
            }
        }
        return false;
    }
}

