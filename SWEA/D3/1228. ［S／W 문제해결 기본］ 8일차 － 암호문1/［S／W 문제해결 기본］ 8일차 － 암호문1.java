import java.util.*;
import java.io.*;

public class Solution
{
	static StringBuilder sb;
	static StringTokenizer st;
	static BufferedReader br;
	
	
	
	public static void main(String args[]) throws IOException
	{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{	
			int numberRange = Integer.parseInt(br.readLine());
			List<String> passWordList = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<numberRange;i++) {
				passWordList.add(st.nextToken());
			}
			int number = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int action = 0; action<number; action++) {
				String cmd = st.nextToken();
				
			
				if(cmd.equals("I")) {
					int x = Integer.parseInt(st.nextToken()); // 인덱스 위치
					int y = Integer.parseInt(st.nextToken()); // 가져올 숫자
					
					for(int next=0; next<y;next++) {
						passWordList.add(x+next,st.nextToken());
					}
				}
					
			}
			
			List<String> sub = passWordList.subList(0, 10);
			String result = String.join(" ",sub);
			System.out.println("#"+test_case+" "+result); 
		}
	}
}
