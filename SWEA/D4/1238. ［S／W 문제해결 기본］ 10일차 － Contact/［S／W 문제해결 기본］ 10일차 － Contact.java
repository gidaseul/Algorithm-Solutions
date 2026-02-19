import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, start;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {

            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            //  노드는 1~100
            graph = new ArrayList[101];
            visited = new boolean[101];

            for (int i = 0; i <= 100; i++) graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < N / 2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }

            int answer = bfs();

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static int bfs() {

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        int result = start;

        while (!q.isEmpty()) {

            int size = q.size();
            int max = 0;
            boolean hasNext = false;   //  다음 레벨 존재 여부

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                        max = Math.max(max, next);  //  next 기준
                        hasNext = true;
                    }
                }
            }

            if (hasNext) result = max;
        }

        return result;
    }
}
