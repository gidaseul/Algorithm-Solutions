import java.io.*;
import java.util.*;

/**
 * 백준 : 28107 회전초밥
 *
 *	문제 설명 :
 *	 - N명의 사람이 존재한다.
 *   - M개의 초밥이 순서대로 등장한다.
 *   - 각 사람은 먹고 싶은 초밥 목록을 가진다.
 *   - 초밥 등장 시 해당 초밥을 원하는 사람 중 번호가 가장 작은 사람이 먹는다.
 *
 *	해결 접근 : 
 *		1. 사람 마다 초밥 번호를 -> 초밥 번호 마다 사람 번호 구조로 변경함.
 *		2. 초밥 번호를 Key로 잡고, 사람 번호를 PriorityQueue를 Value 저장한다.
 *		3. PriorityQueue를 통해 가장 번호 작은 사람을 선택한다.
 **/

public class Main {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,M;
    
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    	
        for(int person=1; person<=N; person++) {
        	st = new StringTokenizer(br.readLine().trim());

            int k = Integer.parseInt(st.nextToken()); // 초밥의 개수
            for(int i=0; i<k;i++) {
            	
            	// 초밥의 번호를 각각 Person 순서에 맞게 하나씩 입력 시킴
	        	int number = Integer.parseInt(st.nextToken()); 
	        	
	        	//(초밥 번호) 1 → pq[1,3] (사람 후보)
	        	//(초밥 번호) 2 → pq[2] (사람 후보)
	        	
	        	if(!map.containsKey(number)) {
	        		map.put(number,  new PriorityQueue<>());
	        	}
	        	map.get(number).offer(person);
	        }
           
        }
        
        st = new StringTokenizer(br.readLine());

        int[] answer = new int[N+1];

        for (int i = 0; i < M; i++) {

        	// 목표 초밥 확인하기
            int sushi = Integer.parseInt(st.nextToken());

            // 초밥을 원하는 사람을 찾기 위해서 초밥 번호의 값을 가져와서 pq에 넣음
            PriorityQueue<Integer> pq = map.get(sushi);

            if (pq == null || pq.isEmpty()) continue;

            int person = pq.poll();
            answer[person]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(answer[i]).append(" ");
        System.out.println(sb);

        }
    }
