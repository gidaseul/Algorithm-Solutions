import java.io.*;
import java.util.*;

/**
 * 백준 - 1931번 : 회의실 배정 
 *
 *  문제 설명: 
 *  	N개의 회의에 대하여 회의실 사용표 만듦 -> 각 회의실에서 회의 겹치지 않게 사용할 수 있는 회의의 최대 개수 찾기
 *  	
 *  입력 설명:
 *  	1. 첫째 줄에 회의의 수 N이 주어진다. (1<=N<=100,000)
 *  	2. N+1 줄까지 각 회의의 정보주어짐. (시작시간, 끝나는 시간) 
 *
 *  해결 접근:
 *  일단 가장 우선적으로 봐야 할 것은 정렬을 어떻게 할 것인지! (접근법이 중요한 것 같음)
 *  시작 시간이 빠른 것 보다 빨리 끝나는 것이 무엇인지 중요하고 이때 빨리 끝나는 시간 기준으로, 
 *  시작시간이 더 빠른 것으로 정렬하도록 하면 될 듯-> 그리고 선택해서 최대 개수 구하기
 *  
 *  python - Timsort 함수로 받아서 사용이 가능함. 
 *  data = [(10, 2), (5, 1), (5, 2), (7, 1)] # (a, b) 형태
 *
 * # b(x[1])를 1순위, a(x[0])를 2순위로 오름차순 정렬
 * data.sort(key=lambda x: (x[1], x[0]))
 * print(data) 
 * # 결과: [(5, 1), (7, 1), (5, 2), (10, 2)]
 * 
 * ----------------------------------------------
 * 이때 사용되는 내장 함수의 원리는 병합 정렬 + 삽입 정렬을 결합한 형태의 안정 정렬이다.
 * 원리 :
 * 	- 정렬된 구간을 찾고 하나의 Run으로 묶음 (길이가 너무 짧으면 효율이 떨어지기 때문에 특정 최소 길이를 구함) - 분할
 * 	- 이때 길이를 비교해서 이진 삽입 정렬을 통해서 길이를 채움. -> 이때 생성된 Run들을 효율적으로 병합하여 Merge 전략 선택
 * -------------------------------------------------
 * 자바에서 활용 -> 하나의 클래스를 만들어서 Sort를 사용 // 이때 ArrayList를 사용해서 배열의 동적을 위해서 생성
 * 1. 정렬(회의 시간 끝나는 기점으로 정렬) + 만약 끝나는 시간이 같다면 시작시간이 더 빠른 쪽이 앞으로 오게
 * 2. 이렇게 정렬된 상태에서 먼저 선택하도록 N번만큼 돌게 함. 최대 N번이여서 시간 복잡도 문제 없음
 * 
 */
public class Main {

	
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int N,count,endTime;
    
    
    static class Node{
    	int a; // 회의 시작 시간
    	int b; // 회의 끝나는 시간
    	
    	Node(int a, int b){
    		this.a = a;
    		this.b = b;
    	}
    }
    
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine().trim());
      
        List<Node> list = new ArrayList<>();
        for(int i=0; i<N;i++) {
        	st = new StringTokenizer(br.readLine().trim());
        	int nodeStart = Integer.parseInt(st.nextToken());
        	int nodeEnd = Integer.parseInt(st.nextToken());
        	list.add(new Node(nodeStart,nodeEnd)); // class를 무조건 static으로 선언해서 사용하도록
        }
        // node1, node2 기준으로 각각 a,b 비교
        list.sort((n1,n2)->{
        	int result = Integer.compare(n1.b, n2.b);
        	if(result != 0) {
        		return result;
        		}
        	
        	return Integer.compare(n1.a, n2.a);
        	});
        
        count = 0;
        endTime=0;
        
        for(int i =0; i<list.size();i++) {
        	Node currentNode = list.get(i);
        	if(currentNode.a >= endTime) {
        		count++;
        		endTime = currentNode.b;
        	}
        }
        System.out.println(count);
        
        

    }
}
