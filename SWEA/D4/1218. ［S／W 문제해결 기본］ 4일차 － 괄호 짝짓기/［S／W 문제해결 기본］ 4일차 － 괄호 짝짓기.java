import java.awt.image.ImageProducer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스의 수 T
 * 2. 각 테스트 케이스마다,
 *
 *
 */
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, M, count;
    static boolean[][] isBad; // 인접 행렬 만들자.
    static boolean[] selected; // 현재 버거에 담긴 재료들

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            String input = br.readLine().trim();


            System.out.println("#" + tc + " " + solve(input));
        }
    }

    static int solve(String input) {
        Map<Character, Character> parenthesisMap = new HashMap<>();

        // 닫힌 괄호를 key로 해야 value로 있는지 찾을 수 있어서
        parenthesisMap.put(')', '(');
        parenthesisMap.put(']', '[');
        parenthesisMap.put('}', '{');
        parenthesisMap.put('>', '<');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (parenthesisMap.containsKey(current)) {
                // 닫힌 괄호 중에 하나라면
                if (stack.isEmpty() || stack.pop() != parenthesisMap.get(current)) {
                    return 0;
                }
            } else {
                stack.push(current);
            }
        }
        return stack.isEmpty() ? 1: 0;
    }
}