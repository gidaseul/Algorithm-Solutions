import java.util.*;
import java.io.*;
import java.lang.management.ManagementFactory;

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
	static void comb(int start, int n, int r, List<Integer> path) {
		
		
		// 가지치기 - 현재 내가 가지고 있는 숫자와 남은 숫자들을 더해도 r을 만들지 못할 경우 안됨.
		if (path.size() + (n-start+1)<r) {
			return;
		}
		
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
		for(int i =start; i<=n; i++) {
			path.add(i); // i 선택
			comb(i+1,n,r,path); // 다음 단계
			path.remove(path.size()-1); // 선택 취소
		}
	}
	public static void main(String args[]) throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		comb(1,N,M,new ArrayList<>());
	}
}