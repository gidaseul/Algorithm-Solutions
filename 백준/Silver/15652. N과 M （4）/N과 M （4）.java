import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,M;
    static int[] result;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[M];
        perm(1,0);
        System.out.print(sb);

    }
    static void perm(int idx, int depth){

        if(depth == M){
            for(int x : result){
                sb.append(x).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=idx;i<=N;i++){
            result[depth] = i;
            perm(i,depth+1);
        }
    }

}