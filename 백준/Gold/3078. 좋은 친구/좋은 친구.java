import java.util.*;
import java.io.*;


public class Main
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	

		
	public static void main(String args[]) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int [] arr = new int [N];
		int count = 0;
		int right = 0;
		long totalFriends = 0;
		int[] lengthCount = new int[21]; // 이름 길이별 저장할 장부
		
		for (int testCase =0; testCase<N; testCase++) {
			String s = br.readLine();
			arr[testCase] = s.length();
		}
		// 꼴찌는 친구 없음.
		// 30만 ^이면 시간초과여서 투포인터로 사용해야 함.
		for (int left = 0; left < N; left++) {
			
			// 본인 기준 K개까지 전체를 보고 K개가 보지 못했던 부분만 추가해서 count 세도록
			while (right < N && left + K >= right) {
		        lengthCount[arr[right]]++; // 여기엔 인덱스 번호 = 문자열의 길이, 그에 해당하는 빈도수 저장
		        right++;
		    }
			
			totalFriends += (lengthCount[arr[left]] - 1); // 본인도 계산되어 있어서 이를 제외하고
			
			lengthCount[arr[left]]--; // 계산되었으니까 그에 해당하는 인덱스 제거
		}
		System.out.println(totalFriends);
		
	}
}