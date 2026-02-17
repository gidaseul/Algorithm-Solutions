import java.io.*;
import java.util.*;

/**
 * SW Expert Academy 4008 [모의 SW 역량테스트] 숫자 만들기
 *
 * 문제 설명:
 *  1. N개의 숫자가 적혀 있는 게임 판이 있고, +,-,x,/ 의 연산자 카드 숫자 사이에 넣기
 *  2. 수식 계산할 때 연산자의 우선 순위 고려하지 않고 왼쪽에서 오른쪽으로 차례대로 계산
 *  3. 주어진 연산자 카드를 사용해서 최대의 값과 최소의 값의 차이를 구하기.
 *
 * 해결 접근:
 *  1. 연산자의 개수를 저장하는 배열을 만듦(opCount)
 *      - 연산자의 남은 개수를 관리함.
 *  2. DFS + 백트래킹을 이용하여 연산자 배치의 모든 경우 완전 탐색
 *      - dfs(idx, currentVlaue) 형태로 구현하며,
 *      - currentValue: 현재까지 계산된 값
 *
 *  3. 매 단계에서 사용 가능한 연산자 선택 (opCount[i] > 0),
 *     해당 연산자를 1개 사용한 뒤 (opCount[i]--)
 *     다음 숫자와 연산을 수행하여 재귀 호출한다.
 *
 *  4. 재귀 호출이 끝나면 연산자 개수를 복구(opCount[i]++)하여
 *      다음 경우의 탐색이 가능하도록 한다.
 *
 *  5. 모든 숫자를 사용했을 때 (idx == N), 현재 계산 값으로 최댓값, 최솟값을 갱신
 */

public class Solution {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int T,N;
    static int [] numbers;
    static int [] opCount; // < '+' '_'  '*'  '/' >
    static int maxAns, minAns;

    static void dfs(int idx, int currentValue){


        // 종료 조건 : 모든 숫자 사용
        if(idx == N){
            maxAns = Math.max(maxAns, currentValue);
            minAns = Math.min(minAns, currentValue);
            return;
        }

        for(int i =0; i<4; i++){
            if (opCount[i] > 0){
                opCount[i]--; // 연산자 사용

                int nextValue = currentValue;

                switch (i){
                    case 0: nextValue += numbers[idx]; break; // +
                    case 1: nextValue -= numbers[idx]; break; // -
                    case 2: nextValue *= numbers[idx]; break; // *
                    case 3: nextValue /= numbers[idx]; break; // /
                }

                dfs(idx+1, nextValue);

                opCount[i]++; // 백트래킹
                
            }
        }
    }


    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            numbers = new int[N];
            opCount = new int[4];

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < 4; i++) {
                opCount[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine().trim());
            for(int i=0; i<N;i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            maxAns = Integer.MIN_VALUE;
            minAns = Integer.MAX_VALUE;

            dfs(1, numbers[0]);

            sb.append("#").append(tc)
                    .append(" ")
                    .append(maxAns - minAns)
                    .append("\n");
        }

        System.out.print(sb);
    }
}
