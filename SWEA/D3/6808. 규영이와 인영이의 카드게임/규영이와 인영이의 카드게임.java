import java.io.*;
import java.util.*;

/*
 * SW Expert Academy 6808: 규영이와 인영이의 카드게임
 * @author Da_seul
 * * @see #main(String[])
 *      Input
 *      1. 입력값 받기
 *          1-1. TestCase 입력 받기
 *          1-2. 1st의 9장의 카드 정보 (1~18 사이의 값) x TestCase 수 만큼 카드 정보를 받음.
 *          (참고 : 1~18 정도의 카드는 모두 1장씩 존재)
 *
 *
 *		Logic
 *		2. 문제 설명
 *          2-1. 9라운드 동안 게임을 진행하여 점수 계산함.
 *          2-2. 숫자 비교
 *              2-2-1. 높은 것을 낸 사람 : 높은 사람 = 두 카드의 합만큼 점수를 가져감
 *              2-2-2. 낮은 것을 낸 사람 : 낮은 사람 = 점수 없음
 *          2-3. 연산 이후 각각의 총점 비교
 *              2-3-1. 총점 계산 : 총 점에 따라 승,무,패 정해짐.
 *          2-4. 9가지의 1~18에서 있는 카드를 제외하여 9! 를 통하여 승패가 정해질 때 이기는 경우의 수와 지는 경우의 수 확인하기
 *
 *      Solution
 *      3. 규영의 카드 순서는 고정된 상태에서, 인영이 카드를 낼 수 있는 모든 경우의 수(9!) 탐색
 *          3-1. 카드 분리
 *              - 카드 번호 1~18까지 있을 때 각 1장씩 사용
 *              - 규영이 가진 9장의 카드를 boolean 배열로 체크한 뒤, 사용하지 않은 카드들로 인영의 카드를 구성하게 함.
 *
 *          3-2. DFS 설계
 *              - DFS의 depth는 현재 진행 중인 라운드 번호 의미
 *              - depth는 0부터 시작하여 9가 되면 모든 라운드를 완료한 상태
 *
 *              dfs(depth, kScore, iScore)
 *              - depth : 현재 라운드 번호 (0~8)
 *              - kScore : 규영 누적 점수
 *              - iScore : 인영 누적 점수
 *
 *         3-3. DFS 종료 조건 : depth == 9인 경우 종료하여 총 점수에 따라 경우의 수를 각각 증가한다.
 *
 *         3-4. DFS 진행 로직 : 규영은 카드를 고정하고, 고정된 값에 따라 인영이 가지고 있는 경우의 수대로 DFS로 재귀 호출
 *
 *		Output
 *		4. 최종 결과
 *          - DFS 탐색이 끝나면 규영이 이기는 경우, 지는 경우의 수 출력
 *
 */

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[] kCards = new int[9]; // 규영 카드
    static int[] iCards = new int[9]; // 인영 카드
    static int win, lose;
    static boolean[] visited;   // 인영 카드 사용 여부 확인

    public static void main(String[] args) throws IOException {
        // 속도 향상을 위해 BufferedReader와 StringBuilder 사용
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T;t++) {
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            boolean[] used = new boolean[19]; // 1~18 확인

            for (int i = 0; i < 9; i++) {
                kCards[i] = Integer.parseInt(st.nextToken()); // 규영이 카드 등록
                used[kCards[i]] = true;
            }
            int idx = 0; // 인영이 되는 카드 확인
            for (int i = 1; i <= 18; i++) {
                if (!used[i]) {
                    iCards[idx++] = i;
                }
            }
            win = 0;
            lose = 0;
            visited = new boolean[9];

            dfs(0,0,0); // 확인
            sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");

            // 4-3. 결과 출력
            System.out.print(sb);
        }
    }

    public static void dfs(int depth, int kScore, int iScore){

        if(depth == 9){
            if(kScore > iScore) win++;
            else if (kScore < iScore) lose++;
            return;
        }

        for(int i=0; i<9; i++){
            if(!visited[i]){
                visited[i] = true;

                int k = kCards[depth]; // 규영은 고정
                int in = iCards[i]; // 인영은 선택
                int sum = k + in;

                if (k > in){
                    dfs(depth+1, kScore+sum, iScore);
                }
                else {
                    dfs(depth+1, kScore, iScore+sum);
                }
                visited[i] = false;
            }
        }

    }
}

