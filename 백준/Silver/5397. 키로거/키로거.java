import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

/*
 * 백준 5397번 키로거
 * @author Da_seul
 * * @see #main(String[])
 * Input
 * 1. 테스트 케이스의 개수 입력 (T)
 * 1-1. T만큼 반복 실행
 * 2. 각 테스트 케이스 별 문자열 입력 (L)
 * 2-1. 문자열의 길이는 1 <= L <= 1,000,000
 * 2-2. 입력 문자: 알파벳 대소문자, 숫자, 백스페이스(-), 화살표(<, >)
 *
 *
 *		Logic (Two Stacks Algorithm)
 *		3. 문제 풀이 방법 : 커서를 기준으로 두 개의 스택 활용하여 O(L) 구현
 *			3-1. Left Stack : 커서 왼쪽에 존재하는 문자 저장
 *			3-2. Right Stack : 커서 오른쪽에 존재하는 문자 저장 (LIFO로 인해 거꾸로 쌓임)
 *
 *			3-3. 문자열 파싱 및 분기 처리 (반복문)
 *				3-3-1. '<' (왼쪽 이동)
 *					- 조건: Left Stack이 비어있지 않아야 함
 *					- 동작: Left Stack.pop() -> Right Stack.push()
 *					- 의미: 커서 왼쪽 문자를 커서 오른쪽으로 넘김
 *
 *				3-3-2. '>' (오른쪽 이동)
 *					- 조건: Right Stack이 비어있지 않아야 함
 *					- 동작: Right Stack.pop() -> Left Stack.push()
 *					- 의미: 커서 오른쪽 문자를 커서 왼쪽으로 넘김
 *
 *				3-3-3. '-' (백스페이스)
 *					- 조건: Left Stack이 비어있지 않아야 함
 *					- 동작: Left Stack.pop()
 *					- 의미: 커서 바로 왼쪽에 있는 문자 삭제
 *
 *				3-3-4. 그 외 (일반 문자)
 *					- 동작: Left Stack.push(문자)
 *					- 의미: 커서 왼쪽에 문자 추가
 *
 *		Output
 *		4. 최종 비밀번호 출력 (StringBuilder 활용)
 *			4-1. Left Stack에 남아있는 모든 문자를 Right Stack으로 이동
 *				- 이유: Left Stack은 입력 순서대로 쌓여있지만, Right Stack으로 옮겨야
 *				        출력 시 올바른 순서(LIFO 역순 활용)로 꺼낼 수 있음.
 *			4-2. Right Stack에서 pop 하며 StringBuilder에 저장
 *			4-3. 최종 결과 문자열 출력
 *
 */

public class Main {

    public static void main(String[] args) throws IOException {
        // 속도 향상을 위해 BufferedReader와 StringBuilder 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1. 테스트 케이스 개수 입력
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 2. 문자열 입력
            String input = br.readLine();

            // 3. 두 개의 스택 초기화
            Stack<Character> leftSt = new Stack<>();
            Stack<Character> rightSt = new Stack<>();

            // 3-3. 문자열 파싱 및 분기 처리
            for (char c : input.toCharArray()) {
                switch (c) {
                    case '<': // 3-3-1. 커서 왼쪽 이동
                        if (!leftSt.isEmpty()) {
                            rightSt.push(leftSt.pop());
                        }
                        break;

                    case '>': // 3-3-2. 커서 오른쪽 이동
                        if (!rightSt.isEmpty()) {
                            leftSt.push(rightSt.pop());
                        }
                        break;

                    case '-': // 3-3-3. 백스페이스
                        if (!leftSt.isEmpty()) {
                            leftSt.pop();
                        }
                        break;

                    default: // 3-3-4. 일반 문자 입력
                        leftSt.push(c);
                        break;
                }
            }

            // 4. 최종 비밀번호 출력 준비

            // 4-1. 왼쪽 스택의 모든 요소를 오른쪽 스택으로 이동
            while (!leftSt.isEmpty()) {
                rightSt.push(leftSt.pop());
            }

            // 4-2. 오른쪽 스택에서 꺼내며 출력 버퍼에 저장
            while (!rightSt.isEmpty()) {
                sb.append(rightSt.pop());
            }

            sb.append('\n');
        }

        // 4-3. 결과 출력
        System.out.print(sb);
    }
}