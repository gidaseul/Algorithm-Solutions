import java.io.*;
import java.util.*;

/**
 *
 * @author gidasuel
 * SWEA 3289. 서로소 집합
 *
 * 1. 테스트 케이스 입력받기
 * 2. N,M 입력받기
 * 3. 처음 parent 배열 초기화
 * 4. 입력 받으면서 union 진행
 *  4-1. find 함수로 parent 배열 갱신
 * 5. 이때 모두 갱신한 경우일 때,
 *  5-1. HashSet에 넣어서 중복없이 대표만 남도록 진행한다.
 *  5-2. HashSet의 Size가 곧 집합의 개수이다.
 *
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T;          // 테스트 케이스 수
    static int N, M;       // N: 사람 수, M: 관계 수
    static int[] parent;   // 부모 배열

    // 각 대표를 찾는 과정을 진행하고 본인 인덱스와 같은 경우 root로 연결
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // 두 원소의 대표를 찾고 다를 경우 한 쪽으로 합친다.(같은 경우면 이미 합집합)
    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa != pb){
            parent[pb] = pa;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());


            // 1. 부모 배열 초기화
            parent = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }
            // 2. 관계 입력받으면서 union 수행
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            // 3. 각 사람의 대표를 HashSet에 넣어서 무리 개수 세기
            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                set.add(find(i));
            }

            // 4. 출력 형식 맞추기
            sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
        }

        System.out.print(sb);

    }
}
