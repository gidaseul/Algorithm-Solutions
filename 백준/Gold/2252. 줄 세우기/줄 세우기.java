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
        
        Queue<Integer> q = new LinkedList<>();
        for (int i=1; i<=N; i++) {
        	if(indegree[i] == 0) q.offer(i); // a->b 했을 때 b만 추가한 것이니까 0이라면 본인이 향하는 화살표만 존재한 다는 소리
        }
		
        StringBuilder sb = new StringBuilder();
        int count =0; // 처리된 노드의 수
        
        while(!q.isEmpty()) {
        	int cur = q.poll();
        	sb.append(cur).append(" ");
        	count++;
        	for(int next : graph[cur]) {
        		indegree[next]--;
        		
        		if(indegree[next]==0) {
        			q.offer(next); // 큐에 추가
        		}
        	}
        }
        if(count != N) {
           System.out.println("Cycle detected or not all nodes were processed.");
        }
        else System.out.println(sb);
	}

}
