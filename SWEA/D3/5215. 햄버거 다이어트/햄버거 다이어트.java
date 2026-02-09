import java.util.*;
import java.io.*;

/*
 * NextPermutationInputTest -> nPr은 안 됨.
 * @author Da_seul
 *
 *     @see #main(String[])
 *  1. 뒤쪽부터 탐색하며 교환 위치 찾기 (i-1), i = 꼭대기 지점
 *      뒤쪽부터 점점 커지는 지점이라면 두지만 큰 지점에서 작은 지점으로 떨어지면 그 지점이 i-1, i의 지점
 *  2. 뒤쪽부터 탐색하며 교환위치 (i-1)와 교환할 큰 값 위치 (j) 찾기
 *  3. 두 위치 값(i-1,j) 교환
 *  4. 꼭대기 위치(i)부터 맨 뒤까지 오름차순 정렬
 *  5. 이때 select[]을 통해서 조합이 가능한 r을 선택하여 진행하도록 함.
 *
 */
public class Solution
{

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int testCase, N,L;
    static int[] score, calorie;
    static int minNum = Integer.MAX_VALUE;


    public static void main(String args[]) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        testCase = Integer.parseInt(br.readLine().trim());


        for(int t=1; t<=testCase; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            score = new int[N];
            calorie = new int[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                score[i] = Integer.parseInt(st.nextToken());
                calorie[i] = Integer.parseInt(st.nextToken());
            }

            int maxScore = 0;

            // 여기서 r = 선택할 개수
            for (int r = 1; r <= N; r++) {
                int[] select = new int[N];

                // 뒤에서 r개를 1로 먼저 세팅
                // 사전 순으로 다음 배열을 만들어 줘야 하기 때문
                for (int i = N - r; i < N; i++) {
                    select[i] = 1;
                }

                do {
                    int sumScore = 0;
                    int sumCal = 0;

                    for (int i = 0; i < N; i++) {
                        if (select[i] == 1) {
                            sumScore += score[i];
                            sumCal += calorie[i];
                        }
                    }
                    if (sumCal <= L) {
                        maxScore = Math.max(maxScore, sumScore);
                    }

                } while (np(select));
            }
            sb.append("#").append(t).append(" ").append(maxScore).append("\n");
        }
        System.out.println(sb);
    }

    static boolean np(int[] arr) { // 현재 순열 기반으로 다음 단계 순열 생성 : 가능하면 true, 불가능하면(가장 큰 순열이면) false
        // 1. 꼭대기를 찾기 (i) => i-1 위치인 교환 위치 찾기

        int i = arr.length-1;
        while(i>0 && arr[i-1] >= arr[i]) i--; // 이때 (arr[i-1] < arr[i]) 가 되는 지점을 찾을 때 까지 i--;

        if(i==0) return false; // 가장 큰 순열인지 확인 i==0이라면 이미 가장 큰 상태로 더 이상 다음 조합이 없는 상태

        int j = arr.length-1; // arr[i-1]보다 큰 값 중 가장 오른쪽 값을 찾는다.
        while(arr[i-1] >= arr[j]) j--;

        // 작은 값을 조금 큰 값으로 교체
        swap(arr,i-1,j);
        
        // 뒤쪽 정렬하기 위해서 정렬
        int k = arr.length-1;
        while(i<k){
            swap(arr,i++,k--);
        }

        return true;

    }

    private static void swap(int[] arr,int a, int b) { // a,b 두 인덱스의 해당하는 수를 교환
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}