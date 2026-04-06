import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N,M;
    static boolean[] arrN;
    static int[] arrM,arrNResult;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arrN = new boolean[N];
        arrNResult = new int[N];
        for(int i=0;i<N;i++){
            arrNResult[i] = i+1;
        }
        arrM = new int[M];

        // 순열이니까 N의 숫자를 M만큼
        perm(0);

    }
    static void perm(int depth){

        if(depth == M){
            for(int a : arrM){
                sb.append(a).append(" ");
            }
            System.out.println(sb);
            sb.setLength(0);
            return;
        }

        for(int i=0;i<N;i++){
            if(!arrN[i]){
                arrN[i] = true;
                arrM[depth] = arrNResult[i];
                perm(depth+1);
                arrN[i] = false;
            }
        }
    }

}