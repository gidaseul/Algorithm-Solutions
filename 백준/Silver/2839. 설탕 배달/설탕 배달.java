import java.io.*;
import java.util.*;

/**
 * 백준 - 2839번 : 설탕 배달 
 *
 *  문제 설명: 
 *  	설탕 N킬로그램을 정확하게 배달해야 함. 봉지는 3kg, 5kg 정해져 있음.
 *  	최대한 적은 수의 봉지로 할 수 있도록 구하는 수
 *
 *  해결 접근:
 *  3,5로 만들 수 있는 최소의 개수를 구하고 이때 N으로 구할 수 없다면 -1값 반환
 */
public class Main {


    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,T,B;
    static int[] nHeights; 
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine().trim());
        int count = 0;
        while(true) {
        	if(N%5 == 0) {
        		count += N/5;
        		break;
        	}
        	N-=3;
        	count++;
        	
        	if(N<0) {
        		count = -1;
        		break;
        	}
        }
       
        System.out.println(count);


    }
}
