import java.util.*;
import java.io.*;

public class Main {

    static BufferedReader br;
    static StringBuilder sb; // 선언

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder(); // 초기화 필수 (이 부분이 빠져서 에러 발생)

        while (true) {
            String line = br.readLine();

            // 종료 조건: 입력의 마지막에 온점 하나만 들어옴
            if (line == null || line.equals(".")) {
                break;
            }

            Stack<Character> stack = new Stack<>();
            boolean isBalanced = true;

            for (char c : line.toCharArray()) {
                if (c == '(' || c == '[') {
                    stack.push(c);
                } 
                else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        isBalanced = false;
                        break;
                    }
                    stack.pop();
                } 
                else if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        isBalanced = false;
                        break;
                    }
                    stack.pop();
                }
            }

            if (isBalanced && stack.isEmpty()) {
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }
        }
        System.out.print(sb.toString());
    } // main 종료
} // 클래스 종료