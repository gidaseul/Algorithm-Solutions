import java.io.*;
import java.util.*;

/**
 * 백준 1766 : 문제집 골드 2 (위상정렬)
 *
 * 문제 설명:
 *      1. 먼저 푸는 것이 좋은 문제
 *          - N개의 문제는 모두 풀어야 함.
 *          - 먼저 푸는 것이 있는 좋은 문제 우선 풀 것.
 *          - 가능하면 쉬운 문제부터 풀어야 함.
 *
 *          <일반적인 순서에서 개별적인 조건이 따로 추가가 되어 있는 경우 - PriorityQueue<Integer>를 통하여 진행 가능함.)
 *          - N개 순서는 1,2,3,4 순서대로 어려운 난이도로 되어 있음
 *
 *
 * 해결 접근:
 *      1. 위상정렬을 통하여 A->B라는 순서가 정해져 있음.
 *      2. Graph와 inDegree를 통하여 진입을 확인한다.
 *      3. 이때 진입할 때 마다 +1을 하여 학기의 수를 파악해야 한다.
 *
 *
 *
 *
 */

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static int[] indegree;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        indegree = new int[N+1];

        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>(); // 각각 graph[0] -> [] 이런식으로 만들기

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            indegree[b]++; // a->b 일때 b가 있다는 것을 추가함.
        }

        // 핵심: 일반 Queue 대신 PriorityQueue 사용 (기본: 오름차순 정렬)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=1; i<=N; i++) {
            if(indegree[i] == 0) pq.offer(i); // a->b 했을 때 b만 추가한 것이니까 0이라면 본인이 향하는 화살표만 존재한 다는 소리
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            // pq.poll()을 하면 현재 진입 차수가 0인 문제 중 번호가 가장 작은 것이 나옵니다.
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for(int next : graph[cur]) {
                indegree[next]--;

                if(indegree[next]==0) {
                    pq.offer(next); // 큐에 추가
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}
