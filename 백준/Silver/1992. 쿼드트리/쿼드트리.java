import java.io.*;
import java.util.*;

/**
 * 백준 1992번 : 쿼드트리 (실버 1)
 *
 * 문제 설명:
 *  1. N * N 배열에는 1 or 0으로 이루어짐.
 *  2. 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순서로 4개 영역 분할
 *  3. 4개의 영역을 통해서 압축하여 1 or 0의 결과를 반환함.
 *  4. 4개 영역에서 모두 1 or 0 이라면 압축하여 하나의 숫자로 반환
 *  5. 섞여 있다면 영역별로 쪼갤 수 있을 만큼 쪼개기
 *
 * 해결 접근:
 *  1. N * N 에서 half 기준으로 4등분 하여 탐색한다.
 *  2. 탐색하여 결과를 반환한다.
 *      2-1. 2 * 2 일 때 까지 반복한다.
 *          2-1-1. 4개의 영역 값이 0 or 1로만 이루어져 있으면 1을 반환.
 *          2-1-2. 섞여 있다면
 *              2-1-2-1. 괄호 '(' + ')' 반환하게 끔 포함
 *              2-1-2-2. 순서대로 (a,b,c,d)로 반환.
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;
    static int[][] map;


    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i=0; i<N;i++){
            String line = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        // 전체 영역 시작
        solve(0,0,N);

        System.out.print(sb);
    }

    static void solve(int row, int col, int size){

       // 1. 현재 영역이 단색인지 검사
        if(isSame(row,col,size)) {
            sb.append(map[row][col]);
            return;
        }
        // 2. 단색이 아닐 경우
        int half = size / 2;

        sb.append("(");

        solve(row,col,half);

        solve(row,col+half,half);

        solve(row + half, col, half);

        solve(row + half, col + half, half);

        sb.append(")");
    }

    static boolean isSame(int row, int col, int size){
        int first = map[row][col];

        for(int i=row; i<row+size;i++){
            for(int j = col; j<col+size; j++){
                if(map[i][j] != first){
                    return false;
                }
            }
        }
        return true;
    }
}
