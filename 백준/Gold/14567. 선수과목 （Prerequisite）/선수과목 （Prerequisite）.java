import java.io.*;
import java.util.*;

/**
 * 백준 14567 : 선수과목 (Prerequisite) 골드 5
 *
 * 문제 설명:
 *      1. 선수과목 조건을 만족하도록 해야한다.
 *          - 한 학이에 들을 수 있는 과목 수는 제한 없다.
 *          - 모든 과목은 매 학기 항상 개설된다.
 *      2. 모든 과목에 대해 각 과목 이수하기 위한 최소 몇 학기 걸리는지 계산
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
    static int[] ans;

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        indegree = new int[N+1];
        ans = new int[N+1];

        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>(); // 각각 graph[0] -> [] 이런식으로 만들기

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            indegree[b]++; // a->b 일때 b가 있다는 것을 추가함.
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=N; i++) {
            if(indegree[i] == 0) q.offer(i); // a->b 했을 때 b만 추가한 것이니까 0이라면 본인이 향하는 화살표만 존재한 다는 소리
            ans[i] = 1; // 진입 차수가 0인 경우는 기본 1학기
        }


        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next : graph[cur]) {
                indegree[next]--;
                if(indegree[next]==0) {
                    ans[next] = ans[cur]+1;
                    q.offer(next); // 큐에 추가
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

}
