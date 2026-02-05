import java.util.*;
import java.io.*;

/*
 * SW Expert Academy 
 * @author Da_seul
 * 
 * 	@see #main(String[])
 * 		
 * 
 * 		문자		의미
		.	평지(전차가 들어갈 수 있다.)
		*	벽돌로 만들어진 벽
		#	강철로 만들어진 벽
		-	물(전차는 들어갈 수 없다.)
		^	위쪽을 바라보는 전차(아래는 평지이다.)
		v	아래쪽을 바라보는 전차(아래는 평지이다.)
		<	왼쪽을 바라보는 전차(아래는 평지이다.)
		>	오른쪽을 바라보는 전차(아래는 평지이다.)
 *	
 *		<문자 동작>
 *		Up : 방향 up / if(한칸 위 == 평지) -> 이동
 * 		Down : 방향 아래 / if(한칸 아래 == 평지) -> 이동
 * 		Left : 방향 왼쪽 / if(한칸 왼쪽 == 평지) -> 이동
 * 		Right : 방향 오른쪽 / if(한칸 오른쪽 == 평지) -> 이동
 *		Shoot : 방향으로 포탄 발사
 *		
 *
 *		규칙
 *		전차 이동: 이동 할 때 맵 밖이라면 전차는 움직일 수 없음.
 *		포탄 발사 : 강철 벽 or 게임 맵 밖 아무일도 일어나지 않음 / 벽돌 벽은 삭제되고 평지로 개선됨.이후 강철 벽 or 맵 밖까지 멈추기)
 *
 *
 *	문제 해결 접근 방식
 *	1. 첫번째 테스트 케이스의 수 T
 *	2. H(맵의 높이),W(너비) 공백으로 구분되어 주어짐.
 *	3. map의 명령어에 따라 진행함.
 *	4. 명령어 기준으로 현재 위취의 전차 방향 기준으로 명령 진행함. -> commands 
 *	5. commands 함수를 통해 진행할 때  S 명령어 진행 시 shoot 함수 진행.
 *
 */
public class Solution
{
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb; 
	static char[][] map;
	static char vector;
	static int targetI, targetJ;	
	
	public static void main(String args[]) throws IOException{
	
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
					
			map = new char[H][W];
						
			// 문자열 배열 값 저장
			for(int i =0; i<H;i++) {
				String s = br.readLine(); 
				for(int j=0; j<W; j++) {
					if(s.charAt(j) == '>' || s.charAt(j) == '<' ||s.charAt(j) == '^' ||s.charAt(j) == 'v') {
						targetI = i;
						targetJ = j;
					}
					map[i][j] = s.charAt(j);
				}
			}
			
			// 사용자 문자 동작 인식 공격
			int N = Integer.parseInt(br.readLine());
			String s = br.readLine();
			for (int i=0; i<N; i++) {
				commands(s.charAt(i));
			}
			sb = new StringBuilder();
			sb.append('#').append(t).append(" ");
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.print(sb);
		}
	}
	
	static void commands(char cmd) {
		int r = targetI;
		int c = targetJ;
		
		if (cmd == 'U') {
			if (r-1 >= 0 && map[r-1][c] == '.') {
				map[r][c] = '.';
				map[r-1][c] = '^';
				targetI = r-1;
			}else map[r][c] = '^';
		}
		
		else if (cmd == 'D') {
			if (r+1 < map.length && map[r+1][c] == '.') {
				map[r][c] = '.';
				map[r+1][c] = 'v';  
				targetI = r+1;
			}else map[r][c] = 'v';
		}
		
		else if (cmd == 'L') {
			if(c-1 >= 0 && map[r][c-1] == '.') {
				map[r][c] = '.';
				map[r][c-1] ='<';
				targetJ = c-1;
			}else map[r][c] = '<';
		}
		else if (cmd == 'R'){
			if(c+1 <map[0].length && map[r][c+1] == '.') {
				map[r][c] = '.';
				map[r][c+1] ='>';
				targetJ = c+1;
			}else map[r][c] = '>';
		}
		else if (cmd == 'S'){
			vector = map[r][c];
			shoot(r,c);
			}
		}

	static void shoot(int r, int c) {
	    int dr = 0, dc = 0;
	    int H = map.length;
	    int W = map[0].length;
	    
	    if (map[r][c] == '^') dr = -1;
	    else if (map[r][c] == 'v') dr = 1;
	    else if (map[r][c] == '<') dc = -1;
	    else if (map[r][c] == '>') dc = 1;

	    int nr = r + dr;
	    int nc = c + dc;

	    while (nr >= 0 && nr < H && nc >= 0 && nc < W) {
	        if (map[nr][nc] == '*') {
	            map[nr][nc] = '.';
	            break;
	        }
	        if (map[nr][nc] == '#') break;

	        nr += dr;
	        nc += dc;
	    }
	}
}