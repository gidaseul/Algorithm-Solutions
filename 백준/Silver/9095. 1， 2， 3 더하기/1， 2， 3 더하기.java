import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,result;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        for(int i=0;i<N;i++){
            result = Integer.parseInt(br.readLine().trim());
            int answer = search(0,result);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);


    }
    static int search(int sum ,int result) {

        if(sum==result){
            return 1;
        }

        if(sum>result) return 0;

        int count = 0;
        for(int i=1;i<=3;i++){
            count+=search(sum+i,result);
        }

        return count;

    }

}