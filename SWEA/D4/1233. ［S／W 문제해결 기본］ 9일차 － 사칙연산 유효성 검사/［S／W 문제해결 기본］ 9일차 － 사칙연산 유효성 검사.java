import java.util.*;
import java.io.*;

/*
 * SW Expert Academy
 * @author Da_seul
 * 
 * 	@see #main(String[])
 * 		input
 * 		1. 총 10개의 테스트 케이스 실행
 * 			1-1. N번 입력값 받기 - (1<=N<=200)
 * 			1-2. 노드번호, 노드번호에 들어갈 값, left 자식 노드 추가, right 자식 노드 추가  
 *			
 *		
 *		2. 문제 풀이 방법 
 *			2-1. 각 노드가 가지고 있는 것이 연산자인지 숫자인지 파악
 *				  연산자와 숫자가 혼합되어 있기 때문에 토큰 별로 확인하고 값을 받으려고 함. 
 *				
 *				2-1-1. 숫자일 경우 : 자식 노드가 없어야 한다. 
 *				2-1-2. 연산자일 경우 : 자식 노드 가능 한 경우 2가지 
 *					2-1-2-1. (자식 노드) : 숫자, 숫자
 *					2-1-2-2. (자식 노드) : 연산자, 숫자
 *					(가장 중요한 것 : 마지막 입력 받는 것이 숫자여야 함. if) leaf 중 연산자가 존재할 경우 -> return 0;)
 *		
 *		output
 *		3. 계산 가능 여부 파악 후 결정
 *			3-1. 계산 가능한 연산 : return 1;
 *			3-2. 계산 가능하지 못한 연산 : return 0;
 *
 */

class Node{
	int idx;
	String data;
	Node left;
	Node right;
	
	public Node(int idx) {
		this.idx = idx;
	}
}


public class Solution
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static Node[] nodes; // 이 부분 추가 -> 객체 참조를 유지할 배열
	public static void main(String args[]) throws IOException
	{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			nodes = new Node[N+1]; // N개의 노드를 생성
			
			for(int i=0; i<N; i++) {
	
				st = new StringTokenizer(br.readLine());
				
				// Node 번호, Node 번호에 들어가는 값, Left_Node, Right_Node
				int curNodeNumber = Integer.parseInt(st.nextToken());
				String data = st.nextToken();
				
				// 노드 생성 및 데이터 채우기  - 미리 만들 것 생성하기
				if(nodes[curNodeNumber]==null) {
					nodes[curNodeNumber] = new Node(curNodeNumber);
				} nodes[curNodeNumber].data = data;
				
				//자식 이제 연결하려고 함. left / right
				if(st.hasMoreTokens()) {
					int leftIdx = Integer.parseInt(st.nextToken());
					if(nodes[leftIdx]==null) {
						nodes[leftIdx] = new Node(leftIdx);
					} nodes[curNodeNumber].left = nodes[leftIdx];
				}
				
				if(st.hasMoreTokens()) {
					int rightIdx = Integer.parseInt(st.nextToken());
					if(nodes[rightIdx]==null) {
						nodes[rightIdx] = new Node(rightIdx);
					} nodes[curNodeNumber].right = nodes[rightIdx];
				}
				
			}
			boolean result = isValid(nodes[1]);
			System.out.println("#"+t+" "+(result ? 1: 0));
		}
	}
	
	static boolean isValid(Node node) {
		if (node == null) return true;
		
		// 숫자 판별 하기-> 음수 같은 경우는 2글자처럼 인식해야 함.
		// 그래서 특정 연산자 인지 확인.
		String d = node.data;
		boolean isOperator = d.equals("+") || d.equals("-") || d.equals("*") || d.equals("/");
		if(isOperator) {
			// 여기서 걸린 것들은 자식이 모두 존재해야 함.
			if(node.left != null && node.right != null) {
				return isValid(node.left) && isValid(node.right);
			}
			return false;
		}
		else {
			if(node.left == null && node.right == null) {
				return true;
			}
			return false;
		}
	}
}