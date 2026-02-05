import java.util.*;
import java.io.*;

/*
 * SW Expert Academy 
 * @author Da_seul
 * 
 * 	@see #main(String[])
 * 		
 */
public class Main
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static boolean[] visited;
	static void perm(int n, int r, List<Integer> path) {

		
		// 종료 조건
		if(path.size() == r) {
			sb = new StringBuilder();
			for (int x : path) {
				sb.append(x).append(" ");
			}
			System.out.println(sb.toString().trim());
			return;
		}
		
		// 다음 숫자 선택
		for(int i =1; i<=n; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			path.add(i);
			perm(n,r,path);
			path.remove(path.size()-1);
			visited[i] = false;
		}
	}
	public static void main(String args[]) throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		
		perm(N,M,new ArrayList<>());
	}
}