import java.io.*;
import java.util.*;

/**
 * SW Expert Academy 6782 현주가 좋아하는 제곱근 놀이 (D5)
 *
 * 문제 설명:
 *  1. 2 이상의 정수 N(2<=N<=10^12)을 2로 만들기 위한 최솟값 구하기
 *  2. N을 바꿀 수 있음.
 *      2-1. N = N + 1
 *      2-2. 루트 변환 시 정수가 되면 -> sqrt(N)으로 바꿀 수 있음.
 *
 * 해결 접근:
 *  1. N을 최대한 빠르게 줄이려면 sqrt를 최대한 많이 해야 함.
 *  2. sqrt를 하기 위해선 완전제곱수가 되어야 함.
 *  3. 따라서 다음 완전제곱수까지 증가시킨 후 sqrt 수행.
 */

public class Solution {

    static BufferedReader br;
    static StringBuilder sb;
    static int T;
    static long N;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Long.parseLong(br.readLine());
            long count = 0;

            while (N > 2) {

                long root = (long) Math.sqrt(N);

                // 완전제곱수인 경우
                if (root * root == N) {
                    N = root;
                    count++;
                }
                // 완전제곱수가 아닌 경우
                else {
                    long next = root + 1;
                    long target = next * next;

                    count += (target - N);  // +1 연산 횟수
                    N = target;
                }
            }

            sb.append("#").append(tc).append(" ").append(count).append("\n");
        }

        System.out.print(sb);
    }
}
