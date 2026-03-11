import java.io.*;
import java.util.*;

/**
 *
 * @author gidaseul
 * SWEA 3289. 서로소 집합
 *
 *	1. 입력받는다.
 *		1-1. 테스트 케이스 수 T를 입력받는다.
 *		1-2. 원소의 개수 n, 연산의 개수 m을 입력받는다.
 *
 *	2. 부모 배열을 생성한다.
 *		2-1. 각 원소는 처음에 자기 자신이 대표이므로 parent[i] = i 로 초기화한다.
 *
 *	3. m개의 연산을 입력받아 처리한다.
 *		3-1. 0 a b 이면 a가 속한 집합과 b가 속한 집합을 합친다.
 *		3-2. 1 a b 이면 a와 b가 같은 집합인지 확인한다.
 *			3-2-1. 같은 집합이면 1 출력
 *			3-2-2. 다른 집합이면 0 출력
 *
 *	4. 각 테스트케이스마다 결과를 한 줄에 이어서 출력한다.
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T, n, m;
    static int[] parent;

    // x가 속한 집합의 대표를 찾는다.
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // a가 속한 집합과 b가 속한 집합을 합친다.
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        // 이미 같은 집합이면 합칠 필요 없음
        if (aRoot == bRoot) return;

        // 한 쪽 루트를 다른 쪽 밑으로 연결
        parent[bRoot] = aRoot;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 1-1. 테스트 케이스 수 입력
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {

            // 1-2. n, m 입력
            st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // 2. 부모 배열 생성
            parent = new int[n + 1];

            // 2-1. 자기 자신으로 초기화
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            sb.append("#").append(tc).append(" ");

            // 3. m개의 연산 처리
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine().trim());

                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 3-1. 합집합 연산
                if (cmd == 0) {
                    union(a, b);
                }
                // 3-2. 같은 집합인지 확인
                else {
                    if (find(a) == find(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }

            sb.append("\n");
        }

        // 4. 출력
        System.out.print(sb);
    }
}