import java.io.*;
import java.util.*;

/**
 * SW Expert Academy 7206 숫자 게임
 *
 *
 *  문제 설명:
 *  1. 하나의 숫자가 주어진다.
 *  2. 숫자를 2개 이상으로 나눈 뒤 곱한다.
 *  3. 그 결과 숫자에 대해 같은 과정을 반복한다.
 *  4. 이때 한 자리 수가 되면 종료한다.
 *  5. 만들 수 있는 최대 단계 수를 구한다.
 *
 *
 *  해결 접근:
 *  1. 햔재 숫자를 문자열로 변환.
 *  2. 자를 수 있는 모든 위치를 탐색.
 *  3. 각 분할에 대해 곱을 계산하고 재귀 호출(이때 분할에 대한 곱은 중복 가능성 있음).
 *  4. 같은 숫자가 다시 나오면 memod에서 결과를 재사용함.
 *  5. 최대 깊이를 저장하고 반환.
 *
 */
public class Solution {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // memo[num] = num에서 시작했을 때 얻을 수 있는 최대 단계 수
    static Map<Integer, Integer> memo;

    static int N, T;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            N = Integer.parseInt(st.nextToken());

            memo = new HashMap<>();

            int anser = dfs(N);

            sb.append("#").append(tc).append(" ").append(anser).append("\n");
        }
        System.out.print(sb);

    }

    static int dfs(int num) {

        // 한 자리 수이면 더 이상 분할 불가 -> 종료
        if (num < 10) return 0;

        // 이미 계산한 적이 있다면 재사용
        if (memo.containsKey(num)) {
            return memo.get(num);
        }
        int maxDepth = 0;

        String s = String.valueOf(num);
        int len = s.length();


        // 자를 수 있는 위치는 1 ~ len-1까지
        // 선택 - 비선택 재귀 대신, 비트 마스크 사용 없이 직접 분할 생성
        // 분할 조합 생성을 위해 2**(len-1) 경우 탐색
        int totalCase = 1 << (len - 1);

        // 나올 수 있는 자르는 분할의 총 개수 (분할해서 나올 수 있는 총 조합의 수)
        for (int mask = 1; mask < totalCase; mask++) {

            int product = 1; // 곱의 값을 기억하는 변수 위치
            int prev = 0; // 자른 위치를 기억하는 포인터

            // 자르는 위치의 기준
            for (int i = 0; i < len - 1; i++) {

                // mask는 나올 수 있는 조합으로 이진수로 하면 선택한 경우의 조합이 나올 것
                // 이때 mask = 5 = 0101 형식이라고 할 때 i로 0001,0010 -- 이런식으로 and 연산처리
                // mask로 나올 수 있는 경우의 수를 바탕으로 실제로 잘라서 확인하도록 일치 여부 파악
                if ((mask & 1 << i) != 0) {
                    // 이때 위치에서 잘라서 나눠야 하니까 숫자를 문자열로 바꿔서 자른다.
                    // prev ~ i+1까지 자르면 실제로는 prev+i 까지 잘라진다.
                    int part = Integer.parseInt(s.substring(prev, i + 1));
                    product *= part;
                    prev = i + 1;
                }
            }
            // 마지막 남은 부분 곱하기
            // prev를 통해서 마지막까지 자르면 자른 부분에 마지막 부분이 남아서 for문 돌고
            // 마지막에 곱을 해줘서 결론 짓도록 한다.

            // index:   0   1   2   3
            // digits:  1   2   3   4
            // cuts:    |       |

            // [0~1) = 1
            // [1~3) = 23
            // [3~4) = 4

            int part = Integer.parseInt(s.substring(prev));
            product *= part;

            maxDepth = Math.max(maxDepth, 1 + dfs(product));
        }
        // 계산 결과 저장
        memo.put(num, maxDepth);
        return maxDepth;
    }
}