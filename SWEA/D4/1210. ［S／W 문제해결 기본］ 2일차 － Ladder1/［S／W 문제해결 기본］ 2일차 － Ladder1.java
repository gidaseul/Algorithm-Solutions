import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
    static BufferedReader br;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            st = new StringTokenizer(br.readLine().trim());
            int testCase = Integer.parseInt(st.nextToken());

            int[][] ladder = new int[100][100];
            int curX = -1;
            int curY = 99; // 맨 마지막 줄부터 시작

            // 1. 데이터 입력 및 도착점(2) 찾기
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 99 && ladder[i][j] == 2) {
                        curX = j;
                    }
                }
            }

            // 2. 역방향 탐색 시작
            while (curY > 0) {
                // 좌측 확인 (인덱스 범위 체크 필수)
                if (curX - 1 >= 0 && ladder[curY][curX - 1] == 1) {
                    // 좌측 통로가 끝날 때까지 이동
                    while (curX - 1 >= 0 && ladder[curY][curX - 1] == 1) {
                        curX--;
                    }
                }
                // 우측 확인 (좌측에 길이 없을 때만 확인)
                else if (curX + 1 < 100 && ladder[curY][curX + 1] == 1) {
                    // 우측 통로가 끝날 때까지 이동
                    while (curX + 1 < 100 && ladder[curY][curX + 1] == 1) {
                        curX++;
                    }
                }

                // 좌/우 이동이 끝나면 무조건 위로 한 칸 이동
                curY--;
            }

            System.out.println("#" + testCase + " " + curX);
        }
    }
}