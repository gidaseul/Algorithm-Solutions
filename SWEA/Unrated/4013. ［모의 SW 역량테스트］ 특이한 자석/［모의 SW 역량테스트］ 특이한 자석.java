import java.io.*;
import java.util.*;

public class Solution {

    static int[][] magnet = new int[4][8];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int K = Integer.parseInt(br.readLine());

            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnet[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < K; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int num = Integer.parseInt(st.nextToken()) - 1;
                int dir = Integer.parseInt(st.nextToken());

                int[] rotate = new int[4];
                rotate[num] = dir;

                // 오른쪽 전파
                for (int i = num; i < 3; i++) {
                    if (magnet[i][2] != magnet[i + 1][6]) {
                        rotate[i + 1] = -rotate[i];
                    } else break;
                }

                // 왼쪽 전파
                for (int i = num; i > 0; i--) {
                    if (magnet[i][6] != magnet[i - 1][2]) {
                        rotate[i - 1] = -rotate[i];
                    } else break;
                }

                // 회전 실행
                for (int i = 0; i < 4; i++) {
                    if (rotate[i] != 0) rotateMagnet(i, rotate[i]);
                }
            }

            int score = 0;
            if (magnet[0][0] == 1) score += 1;
            if (magnet[1][0] == 1) score += 2;
            if (magnet[2][0] == 1) score += 4;
            if (magnet[3][0] == 1) score += 8;

            sb.append("#").append(tc).append(" ").append(score).append("\n");
        }

        System.out.print(sb);
    }

    static void rotateMagnet(int idx, int dir) {
        if (dir == 1) { // 시계
            int temp = magnet[idx][7];
            for (int i = 7; i > 0; i--) {
                magnet[idx][i] = magnet[idx][i - 1];
            }
            magnet[idx][0] = temp;
        } else { // 반시계
            int temp = magnet[idx][0];
            for (int i = 0; i < 7; i++) {
                magnet[idx][i] = magnet[idx][i + 1];
            }
            magnet[idx][7] = temp;
        }
    }
}
