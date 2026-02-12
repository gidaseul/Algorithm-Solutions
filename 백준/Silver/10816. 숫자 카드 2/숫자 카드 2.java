import java.io.*;
import java.util.*;

/**
 * 백준 10816번 숫자 카드 2 (실버4)
 *
 * 문제 설명:
 *  1. N개의 숫자 카드가 주어짐.
 *  2. M개의 숫자에 대해 각각 몇 개 있는지 출력.
 *
 * 해결 접근:
 *  1. HashMap을 이용하여 카드 숫자의 빈도수를 저장.
 *  2. 질의가 들어올 때마다 map에서 바로 조회. -> map.getOrDefault(num, 0) + 1); 이게 젤 중요
 *
 * 시간복잡도:
 *  - 카드 입력: O(N)
 *  - 질의 처리: O(M)
 *  => 전체 O(N + M)
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 숫자 카드 빈도 저장용 HashMap
        Map<Integer, Integer> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 기존 값이 있으면 +1, 없으면 1로 저장
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int query = Integer.parseInt(st.nextToken());

            // 해당 숫자의 개수를 출력 (없으면 0)
            sb.append(map.getOrDefault(query, 0)).append(" ");
        }

        System.out.println(sb);
    }
}
