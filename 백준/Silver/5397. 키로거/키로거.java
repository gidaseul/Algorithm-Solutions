import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            String input = br.readLine();
            
            // 1. LinkedList 생성
            LinkedList<Character> list = new LinkedList<>();
            
            // 2. 리스트의 커서(Iterator) 생성 (초기 위치: 맨 앞)
            ListIterator<Character> iter = list.listIterator();
            
            for (char c : input.toCharArray()) {
                switch (c) {
                case '<': 
                    // 커서 왼쪽 이동: 이전 노드가 있다면 뒤로 한 칸(previous) 이동
                    if (iter.hasPrevious()) {
                        iter.previous();
                    }
                    break;
                    
                case '>': 
                    // 커서 오른쪽 이동: 다음 노드가 있다면 앞으로 한 칸(next) 이동
                    if (iter.hasNext()) {
                        iter.next();
                    }
                    break;
                    
                case '-': 
                    // 백스페이스: 커서 왼쪽 문자 삭제
                    // 로직: 왼쪽으로 한 칸 넘어가서(previous), 그 넘겨진 문자를 삭제(remove)
                    if (iter.hasPrevious()) {
                        iter.previous(); // 삭제할 문자 위로 커서를 이동 (반환값은 삭제 대상)
                        iter.remove();   // 방금 previous()로 지나친 문자를 삭제함
                    }
                    break;
                    
                default: 
                    // 일반 문자: 현재 커서 위치에 즉시 삽입 (O(1))
                    iter.add(c);
                    break;
                }
            }
            
            // 출력
            for (char c : list) {
                sb.append(c);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}