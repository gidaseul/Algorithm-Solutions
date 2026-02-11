import java.util.*;
import java.io.*;

/*
 * 문제 - SWEA : 14510. 나무 높이 D2
 * @author Da_seul
 * 
 * 	@see #main(String[])
 * 	입력
 * 	1. TestCase의 값이 주어짐
 * 	2. N개의 나무 개수가 주어짐.
 * 	3. 나무들의 높이가 N개로 키워짐.
 * 	
 * 	알고리즘
 * 	홀수 날짜 키 +1
 * 	짝수 날짜 키 +2
 * 	날짜에 관계 없이 +0 (가능)
 * 	
 * 모든 나무의 키가 처음에 가장 키가 컸던 나무와 같아지도록 할 수 있는 최소 날짜 구하기
 * 
 * 알고리즘 풀이
 * 1. 배열 중 가장 높은 값을 구한다 -> target(목표 지점)
 * 2. 배열 중에서 target이 될 수를 구한다. 
 * 3. 각 배열에서 target-arr[i]을 하여서 2로 나누어 몫과 나머지를 통해서 one,two를 구분짓도록 날짜를 담는다.
 * 	3-1. 날짜 기준으로 계산을 하는데 홀수는 2일씩 쓰지만 홀수먼저 나오기 때문에 -1을 해야 하고, 짝수는 본인 날짜 x2를 하면 된다.
 * 	3-2. 이때 기준으로 두개는 결국 각자 계산되지만 가장 max값을 고르면 홀수,짝수 모두 x 2를 했기 때문에 하나를 선택해도 다른 쪽이 포함된다.
 * 	3.3. 짝수만 너무 많은 경우라면 쉬는 날 없이 1로 대체할 수 있도록 경계가 둘이 근접한 게 가장 좋기 때문에 빼서 max값을 만들어서 설정해준다.
 * 
 */
public class Solution
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int T,N,target,countDays, oneNum, twoNum;
	static int[] arr; 
	
	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		sb = new StringBuilder();

		for (int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine().trim());

			arr = new int[N];
			oneNum = 0;
			twoNum = 0; 
			
			target = 0;
			countDays = 0;
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0; i<N;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] > target) {
					target = arr[i];
				}
			}
			for(int j=0; j<N;j++) {
				oneNum += (target - arr[j]) %2;
				twoNum += (target - arr[j]) /2;
			}
			// 여기서 짝수가 혼자 너무 많은 경우라면 홀수에 쉬는 것이 아니라 홀수도 날짜를 이용하는 방안도 고려해야 함.
			// 홀수와 짝수의 균형을 맞추기 위해서 진행함. 홀수가 많은 경우엔 짝수가 대체 어려움.
			while(twoNum > oneNum+1) {
				twoNum--;
				oneNum+=2;
			}
			
			// 홀수가 가장 큰 지점이라면 홀-짝 순서로 진행되므로 마지막 날이 홀수이기 때문에 
			// 홀수 작업 3번 (1,3,5일차에 수행) -> 3*2-1 해야 함.
			int D = Math.max(oneNum*2-1, twoNum*2);
		
			sb.append("#").append(tc).append(" ").append(D).append("\n");
		}
		System.out.println(sb);

	}
}
	
