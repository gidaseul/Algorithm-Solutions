import java.util.*;
import java.io.*;

/*
 * 1은 벽
 * 0은 길
 * 2는 출발점 
 * 3은 도착점
 * 2->3까지 도달할 수 있는지 여부를 판단해야 함.
 * 
 */

public class Solution
{
	static StringBuilder sb;
	static StringTokenizer st;
	static BufferedReader br;
	static int miro[][];
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[][] isVisited;
	static int nextX = 0;
	static int nextY = 0;
	static int result; // 결과 값 리턴
	
	
	public static void main(String args[]) throws IOException
	{
		
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int numberTestCase = Integer.parseInt(br.readLine().trim());
			miro = new int[16][16];
			isVisited = new boolean[16][16];
			result = 0;
			for(int i =0; i<16; i++) {
				String str = br.readLine();
				for(int j =0; j<16; j++) {
					miro[i][j] = str.charAt(j)-'0';
					if(miro[i][j] == 2) {
						nextX = i;
						nextY = j;
						isVisited[i][j] = true;
					}
				}
			}
			
			dfs(nextX, nextY);
			System.out.println("#"+numberTestCase+" "+ result); // 결과값 
		}
	}
	
	static void dfs(int x, int y) {
		if (miro[x][y] == 3) {
			result = 1; // static 변수니까
			return;
		}
		// DFS 시작하게 
		for (int i=0; i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			
			if (nx<16 && nx>=0 && ny<16 && ny>=0 && miro[nx][ny] != 1 && isVisited[nx][ny]==false) {
				isVisited[nx][ny] = true;
				dfs(nx,ny);
				if(result == 1) return;
				}
			}
		}
	}
