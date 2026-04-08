
import java.util.*;

import javax.swing.InputMap;

import java.io.*;


public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	
    public static void main(String[] args) throws Exception {
    	
    	br = new BufferedReader(new InputStreamReader(System.in));
    	int T = Integer.parseInt(br.readLine().trim());
    	
    	for (int i=0; i<T; i++) {
    		sb = new StringBuilder();
    		st = new StringTokenizer(br.readLine().trim());
    		int N = Integer.parseInt(st.nextToken());
    		int M = Integer.parseInt(st.nextToken());
    		
    		long result = 0;
    		result = comb(M,N);
    		sb.append(result).append("\n");
        	System.out.print(sb);

    	}
    
    }
    static long comb(int a, int b) {
    	
    	int n = a;
    	int r = b;
    	
    	long up = 1;
    	long dw = 1;
    	
    	if(a-b < b) {
    		r = a-b;
    	}
    	
    	for(int i=0; i<r;i++) {
    		up *= n;
    		n--;
    	}
    	for (int j=r; j>=1; j--) {
    		dw *= j;
    	}
    	return up/dw;
    }

}

