import java.util.*;
import java.io.*;

/*
 * NextPermutationInputTest -> nPr은 안 됨.
 * @author Da_seul
 * 
 * 	@see #main(String[])
 *  1. 뒤쪽부터 탐색하며 교환 위치 찾기 (i-1), i = 꼭대기 지점
 *  	뒤쪽부터 점점 커지는 지점이라면 두지만 큰 지점에서 작은 지점으로 떨어지면 그 지점이 i-1, i의 지점
 *  2. 뒤쪽부터 탐색하며 교환위치 (i-1)와 교환할 큰 값 위치 (j) 찾기
 *  3. 두 위치 값(i-1,j) 교환
 *  4. 꼭대기 위치(i)부터 맨 뒤까지 오름차순 정렬
 * 		
 */
public class Main
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
    static int input[],N;
	
	
	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		input = new int[N];
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		

		if(np()) {
			for(int i =0; i<input.length; i++) {
			System.out.print(input[i]+ " ");
			}
		}else System.out.println(-1);
    }	
	
	static boolean np() { // 현재 순열 기반으로 다음 단계 순열 생성 : 가능하면 true, 불가능하면(가장 큰 순열이면) false
		// 1. 꼭대기를 찾기 (i) => i-1 위치인 교환 위치 찾기
		
		// 1 3 5 4 2 이 라면
//		4 > 2
//		5 > 4
//		3 < 5 ← 여기 이 지점이 바로 i=2, i-1 = 1인 위치
		
		int i = N-1;
		while(i>0 && input[i-1] >= input[i]) --i;
		
		// 2. 꼭대기 체크 ==> i-1 교환위치 없는지 체크 : 가장 큰 순열인지 체크
		if(i==0) return false;
		
		// 3. i-1 교환위치 값 보다 한 단계 큰 수 뒤쪽부터 찾기
		int j =N-1;
		while(input[i-1] >= input[j]) --j; 
		
		// 1번과 3번을 잘 보면 while 문 비슷함. (대신 3번이 J번 위치부터 계산하는 것의 차이가 있음))
		
		// 4. i-1 위치 값과 j 위치 값 교환
		swap(i-1,j);
		
		// 5. 꼭대기부터 맨 뒤까지 가장 작은 수로 만듦. (오름차순 정렬)
		int k = N-1;
		while(i<k) {
			swap(i++,k--);
		}
		return true;
		
	}
	
	private static void swap(int a, int b) { // a,b 두 인덱스의 해당하는 수를 교환
		int temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
}
