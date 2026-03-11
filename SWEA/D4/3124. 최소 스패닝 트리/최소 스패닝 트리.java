import java.io.*;
import java.util.*;

/**
 *
 * @author gidaseul
 *		
 * 1. 시작 정점을 기준으로 연결된 후보 중 방문하지 않은 것을 pq에 넣는다.
 * 2. pq는 간선의 길이를 기준으로 오름차순 정렬이 되어 있다.
 * 3. 이미 연결되어 있는 정점이라면 continue를 진행한다.
 * 4. 연결된 상태라면 count++, result+= 를 한다.
 * 5. count가 간선의 개수와 동일하다면 중단한다.
 * 
 *
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Node{
    	int to , weight;
    	
    	Node(int to, int weight){
    		this.to = to;
    		this.weight = weight;
    	}
    }
    
    static int T,V,E;
    static List<Node>[] graph;
    static boolean[] visited;
    
    static long prim(int start) {
    	PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(n->n.weight));
    	
    	visited = new boolean[V+1];
    	
    	long result = 0;
    	int count = 0;
    	
    	pq.offer(new Node(start,0));
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		int now = cur.to;
    		int cost = cur.weight;
    		
    		if(visited[now]) continue;
    		
    		visited[now] = true;
    		result += cost;
    		count++;
    		
    		if(count == V) break; 
    		
    		for(Node next : graph[now]) {
    			if(!visited[next.to]) {
    				pq.add(new Node(next.to, next.weight));
    			}
    		}
    	}
    	return result;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine().trim());
        
        for(int tc= 1; tc<=T; tc++) {
        	st = new StringTokenizer(br.readLine().trim());
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	
        	graph = new ArrayList[V+1];
        	for(int i=1; i<=V; i++) {
        		graph[i] = new ArrayList<>();
        	}
        	
        	for(int e=0; e<E;e++) {
        		st = new StringTokenizer(br.readLine().trim());
        		int from = Integer.parseInt(st.nextToken());
        		int to = Integer.parseInt(st.nextToken());
        		int weight = Integer.parseInt(st.nextToken());
        		graph[from].add(new Node(to, weight));
        		graph[to].add(new Node(from, weight));
        	}
        	
        	long result = prim(1);
        	
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);

    }
}
