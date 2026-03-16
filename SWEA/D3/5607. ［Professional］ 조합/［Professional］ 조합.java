
import java.io.*;
import java.util.*;

/**
 * SW Expert Academy
 *
 * @Author: gidaseul
 * SWEA 5607.[Professional] 조합 (D3)
 * 1. 테스트 케이스의 수 T를 입력 받는다.
 * 2. T의 수 만큼 반복한다.
 * 3. 조합 공식  : nCr = N! / (R! * (N-R)!) 이다.
 *  3-1. 하지만 mod 연산에서는 일반적인 나눗셈이 불가능 함. (mod는 나머지 값을 의미하는 연산)
 *  3-2. 따라서 분모의 역원을 구해서 곱으로 바꿔야 한다.
 *  3-3. mod P에서 P가 소수일 때,
 *      a^(P-1) = 1 (mod P) [페르마의 소정리]
 *  3-4. 양변에 a^(-1)을 곱하면,
 *      a^(P-2) = a^(-1)(mod P)
 *  3-5. 즉, a의 모듈러 역원은 a ^(P-2) % P을 할 수 있다.
 *  3-6. nCr = N! * (R! * (N-R)!)^(P-2) % P
 *
 *
 * --------------------------------------------------
 * @see #main(String[])
 * 1. 테스트 케이스 수 T를 입력받는다.
 * 2. 각 테스트 케이스마다 N, R을 입력받는다.
 * 3. N!을 구해 분자(numer)로 저장한다.
 * 4. R! * (N-R)! % P를 구해 분모(denom)로 저장한다.
 * 5. 분모의 역원 denom^(P-2) % P를 구한다.
 * 6. numer * denom^(P-2) % P를 계산하여 최종 조합 값을 구한다.
 * --------------------------------------------------
 * @see #factorial(long)
 * 1. N부터 2까지 차례대로 곱한다.
 * 2. 매 곱셈마다 mod P를 적용하여 overflow를 방지한다.
 * 3. N! % P 값을 반환한다.
 * --------------------------------------------------
 * @see #pow(long, long)
 * 분할 정복을 이용한 거듭제곱 계산 함수
 * 1. expo가 1이면 base % P를 반환한다.
 * 2. pow(base, expo / 2)를 재귀 호출하여 절반 값을 구한다.
 * 3. expo가 홀수이면
 *      base^expo = base^(expo/2) * base^(expo/2) * base
 *    형태로 계산한다.
 * 4. expo가 짝수이면
 *      base^expo = base^(expo/2) * base^(expo/2)
 *    형태로 계산한다.
 *
 *
 */
public class Solution {
    final static long P = 1234567891L;

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            // 분자: N! % P
            long numer = factorial(N);

            // 분모: (R! * (N-R)!) % P
            long denom = factorial(R) * factorial(N - R) % P;

            // 최종 계산:
            // N! * (분모의 역원) % P
            // 분모의 역원 = denom^(P-2) % P
            long result = numer * pow(denom, P - 2) % P;


            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    public static long factorial(long N) {
        long fac = 1L;

        while (N > 1) {
            fac = (fac * N) % P;
            N--;
        }

        return fac;
    }


    public static long pow(long base, long expo) {
        // 종료 조건
        if (expo == 1) return base % P;

        long temp = pow(base, expo / 2);

        // 홀수 지수
        if (expo % 2 == 1) {
            return (temp * temp % P) * base % P;
        }

        // 짝수 지수
        return temp * temp % P;
    }
}