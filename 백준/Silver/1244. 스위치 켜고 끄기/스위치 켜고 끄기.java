import java.io.*;
import java.util.*;

/*
 *	문제 설명
 *		- LED 상태
 *			0: 꺼져있음.
 *			1: 켜져있음.
 *		- 학생 몇 명 뽑아서, 학생들에게 1~LED 개수 이하를 하나씩 나눠 줌
 *		- 남학생 : led 번호가 자기가 받은 수의 배수일 경우 상태변화
 *		- 여학생 : 자기가 받은 수와 같은 번호가 붙은 led 중심으로 좌우 대칭, 가장 많은 led 포함하는 구간 찾아서 바꾸기
 *				- 구간에 속하는 led 개수는 항상 홀수(two - point)
 *
 *
 *	입력
 *	 - led 개수
 *	 - led 상태 표시(0,1)
 *	 - 학생 수
 *	 - 한 학생의 성별, 받은 수 (남 : 1, 여: 2)
 *
 * 문제 해결
 * 	- 상태에 따른 분기 조건 확인
 */


public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;


    static int N, number;
    static int[] led;
    static int[][] input; // 학생 성별, 번호

    public static void main(String[] args) throws IOException{
        //---------솔루션 코드를 작성하세요.

        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        led = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i =0; i<N;i++) {
            led[i] = Integer.parseInt(st.nextToken());
        }

        number = Integer.parseInt(br.readLine().trim()); // 학생 수
        input = new int[number][2];

        for(int i =0; i<number; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken()); // 학생 성별
            input[i][1] = Integer.parseInt(st.nextToken()); // 학생 받은 번호
        }

        for(int i=0; i<number; i++) {
            change(input[i][0],input[i][1]);
        }

        sb = new StringBuilder();

        for(int i=0; i<led.length;i++) {
            sb.append(led[i]).append(' ');
            if((i+1)%20==0) {
                System.out.println(sb);
                sb.setLength(0);
            }
        }
        System.out.println(sb);


    }

    static void change(int stundet, int studentNum) {

        // 남자일 경우
        if(stundet == 1) {
            for(int i=0; i<N;i++) {
                if((i+1) % studentNum == 0) {
                    // 배수일 때 마다
                    if(led[i] == 0) {
                        led[i] = 1;
                    }

                    else if(led[i] == 1){
                        led[i] = 0;
                    }
                }
            }
        }

        // 여자일 경우
        if(stundet == 2) {
            int cuurent = led[studentNum-1]; // 현재 상태
            int count = 0;
            // 자신 기준 왼쪽
            int left = studentNum-2; // 실제 배열 번호
            // 자신 기준 오른쪽
            int right = studentNum;

            while (left>=0 && right < N) {
                if (led[left] == led[right]) {
                    count++;
                    left--;
                    right++;
                }
                else {
                    break;
                }
            }

            if(count==0) {
                if(cuurent == 1) {
                    led[studentNum-1] = 0;
                }
                else if(cuurent == 0) {
                    led[studentNum-1] = 1;
                }
            }
            else {
                // 전체 N 만큼 배열 변화
                for(int i=studentNum-count-1;i<=studentNum+count-1;i++) {
                    // 상태변화

                    if(led[i] == 1) {
                        led[i] = 0;
                    }
                    else if(led[i] == 0) {
                        led[i] = 1;
                    }

                }

            }
        }
    }
}
