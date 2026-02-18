import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;


    static int[] price;   // 0: 1일권, 1: 1달권, 2: 3달권, 3: 1년권
    static int[] days;    // 각 달 이용일수
    static int min;       // 최소 비용


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for (int t = 1; t <= TC; t++) {

            price = new int[4];
            days = new int[12];
            min = Integer.MAX_VALUE;

            // 이용권 가격 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                price[i] = Integer.parseInt(st.nextToken());
            }

            // 월별 이용 일수 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                days[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            // 1년권과 비교
            min = Math.min(min, price[3]);

            System.out.println("#" + t + " " + min);
        }
    }

    // idx: 현재 월 (0~11)
    // total: 현재까지 누적 비용
    private static void dfs(int idx, int total) {

        // 가지치기
        if (total >= min) return;

        // 12월까지 다 계산했으면 최소값 갱신
        if (idx >= 12) {
            min = Math.min(min, total);
            return;
        }

        // 1일권 선택
        dfs(idx + 1, total + price[0] * days[idx]);

        // 1달권 선택
        dfs(idx + 1, total + price[1]);

        // 3달권 선택
        dfs(idx + 3, total + price[2]);
    }
}
