
import java.util.*;
import java.io.*;

/*
 * 백준 14891 톱니바퀴
 * @author Da_seul
 *
 * 	input
 * 		1. 4개의 톱니바퀴 상태 입력
 * 			1-1. 각 톱니바퀴는 8개의 극을 가지며 문자열 형태로 주어진다.
 * 		2. K : 회전시킬 횟수 입력
 * 		3. a b : a(톱니바퀴 번호), b(회전 방향 1=시계, -1=반시계)
 *
 * 	문제 풀이 방법
 * 		특정 톱니바퀴를 회전할 때 배열 0~7번의 해당하는 값 중
 * 		각 톱니바퀴가 만나는 지점은 2, 6 이다.
 *
 * 		(중요! 회전 여부는 현재 상태를 기준으로 먼저 전부 판단한 뒤,
 * 		 실제 회전은 나중에 한 번에 적용해야 한다.)
 *
 * 		특정 a(톱니바퀴 번호)에 b를 회전시킬 경우 확인
 * 			2-1. 오른쪽 전파 -> 회전해야 할 톱니바퀴 저장
 * 			2-2. 왼쪽 전파 -> 회전해야 할 톱니바퀴 저장
 * 			2-3. rotate 배열을 바탕으로 실제 회전 실행
 *
 * 				2-3-1. dir = 1 : 시계 방향 회전일 경우
 * 					2-3-1-1. 배열의 7번째가 0번째로 이동
 * 				2-3-2. dir = -1 : 반시계 방향 회전일 경우
 * 					2-3-2-1. 배열의 0번째가 7번째로 이동
 *
 * 	output
 * 		최종 12시 방향(0번 인덱스)의 값을 기준으로 점수 계산 후 출력
 */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int[][] magnet = new int[4][8];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 4개의 톱니바퀴 상태 입력
        for (int i = 0; i < 4; i++) {
            String str = br.readLine().trim();
            for (int j = 0; j < 8; j++) {
                magnet[i][j] = str.charAt(j) - '0';
            }
        }

        // 2. 회전 횟수 입력
        int K = Integer.parseInt(br.readLine().trim());

        // 3. 회전 명령 처리
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int dir = Integer.parseInt(st.nextToken());

            int[] rotate = new int[4];
            rotate[num] = dir; // 시작 톱니바퀴 회전 방향 저장

            // 2-1. 오른쪽 전파 확인
            for (int i = num; i < 3; i++) {
                if (magnet[i][2] != magnet[i + 1][6]) {
                    rotate[i + 1] = -rotate[i];
                } else {
                    break;
                }
            }

            // 2-2. 왼쪽 전파 확인
            for (int i = num; i > 0; i--) {
                if (magnet[i][6] != magnet[i - 1][2]) {
                    rotate[i - 1] = -rotate[i];
                } else {
                    break;
                }
            }

            // 2-3. 실제 회전 실행
            for (int i = 0; i < 4; i++) {
                if (rotate[i] != 0) {
                    rotateMagnet(i, rotate[i]);
                }
            }
        }

        // 4. 점수 계산
        int score = 0;

        if (magnet[0][0] == 1) score += 1;
        if (magnet[1][0] == 1) score += 2;
        if (magnet[2][0] == 1) score += 4;
        if (magnet[3][0] == 1) score += 8;

        // 5. 출력
        System.out.println(score);
    }

    static void rotateMagnet(int idx, int dir) {

        // 2-3-1. dir = 1 : 시계 방향 회전일 경우
        if (dir == 1) {
            // 배열의 7번째가 0번째로 이동
            int temp = magnet[idx][7];
            for (int i = 7; i > 0; i--) {
                magnet[idx][i] = magnet[idx][i - 1];
            }
            magnet[idx][0] = temp;
        }

        // 2-3-2. dir = -1 : 반시계 방향 회전일 경우
        else {
            // 배열의 0번째가 7번째로 이동
            int temp = magnet[idx][0];
            for (int i = 0; i < 7; i++) {
                magnet[idx][i] = magnet[idx][i + 1];
            }
            magnet[idx][7] = temp;
        }
    }
}