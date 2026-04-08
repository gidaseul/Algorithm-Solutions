
import java.util.*;
import java.io.*;

public class Solution {


	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	// 상태에 대해서 표현하는 것 -> 이동 중 ,대기 중 ,내려가는 중, 완료
	static final int M=1, W=2, D=3,C=4;
	
	static class Person implements Comparable<Person>{
		int r,c,downCnt,status, arrivalTime;
		
		public Person(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		
		public void init() {
			downCnt = arrivalTime= 0;
			status = M;
		}

		@Override
		public int compareTo(Person o) { // 계단 입구, 도착시간이 빠른 순서
			return Integer.compare(this.arrivalTime, o.arrivalTime);
		}
	}
	
	static int N, cnt,min;
	static ArrayList<Person> pList;
	static int[][] sList; // 계단 리스트
	static int[] selected; // 각 사람마다 받으 계단 리스트
	
	
    public static void main(String[] args) throws Exception {
    	
    	br = new BufferedReader(new InputStreamReader(System.in));
    	int T;
    	
    	T = Integer.parseInt(br.readLine());
    	for (int tc=1; tc<=T; tc++) {
    		
    		N = Integer.parseInt(br.readLine());
    		pList = new ArrayList<>();
    		sList = new int[2][];
    		min = Integer.MAX_VALUE;
    		
    		for (int i=0, k=0; i<N; i++) {
    			st = new StringTokenizer(br.readLine());
    			for (int j =0; j<N; j++) {
    				int c = Integer.parseInt(st.nextToken());
    				if (c==1) pList.add(new Person(i,j)); //  사람이면
    				else if(c>1) sList[k++] = new int[] {i,j,c}; //계단이면
    			}
    		}
    		
    		cnt = pList.size();
    		selected = new int[cnt];
    		
    		
    		divide(0);
    		System.out.println("#"+tc+" "+min);
    	}
    }
    private static void divide(int index) {
    	
    	// 모든 사람이 계단 배정이 끝났으면.. 각 배정 받은 계단에 따른 사람들 내려가기 처리
    	if(index == cnt) {
    		// 각 배정 받은 계단에 따른 사람들 내려가기 처리
    		makeList(); // 각 계단에 배정 받은 사람들 목록 만들기(계단 입구 도착시간 계산-> 바로 내려가도록)
    		return;
    	}
    	
    	
    	// 계단 0 배정
    	selected[index] = 0;
    	divide(index+1);
    	
    	// 계단 1 배정
    	selected[index] = 1;
    	divide(index+1);
    }
 
    private static void makeList() {
    	// 밑에서 반복문 돌면서 전체 사람들 리스트 있을 때, 대응되는 것들도 있을 때 바로 바로 찾아서 확인하기 쉽게 만들기 위해서
    	ArrayList<Person>[] list = new ArrayList[] {new ArrayList<Person>(),new ArrayList<Person>()};
    	
    	
    	for (int i=0; i<cnt; i++) {
    		Person p = pList.get(i);
    		p.init(); // 상태 초기화
    		int no = selected[i]; // 배정받은 계단 idx
    		
    		p.arrivalTime = Math.abs(p.r-sList[no][0]) + Math.abs(p.c-sList[no][1]);
    		list[no].add(p);
    	}
    	
    	// 각 계단 별로 내려가는 시간 고려했을 때 마지막 내려간 사람 시간을 고려해야 함.
    	
    	int timeA = processDown(list[0],sList[0][2]);
    	int timeB = processDown(list[1],sList[1][2]);
    	int res = Math.max(timeA, timeB);
    	min = Math.min(min, res);
    	
    }
    
	private static int processDown(ArrayList<Person> list, int height) {
		if(list.size() == 0) return 0;
		
		Collections.sort(list);
		int time = list.get(0).arrivalTime;
		int size = list.size();
		
		int ingCnt = 0, cCnt = 0; // 계단을 내려가고 있는 사람 수, 계단 내려가기를 완료한 사람 수
		
		while(true) {
			
			for(int i=0; i< size; i++) {
				Person p = list.get(i);
				
				if(p.status == C) continue; // 이미 계단 내려가기를 완료한 사람이면 패스
				if(p.arrivalTime == time) { // 계단 입구에 도착했다면
					p.status = W;
					
				}else if(p.status == W && ingCnt < 3) {
					++ingCnt;
					p.status = D;
					p.downCnt = 1;
				}
				else if(p.status == D) {
					if(p.downCnt < height) {
						++p.downCnt;
					}else {
						p.status = C;
						--ingCnt;
						++cCnt;
					}
				}
				
			}
			
			if(cCnt == size) break;
			++time; // 시간 흐르기
			
		}
		
		return time;
	}
    
}