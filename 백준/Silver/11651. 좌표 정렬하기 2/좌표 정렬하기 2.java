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

    static class Node{
        int a;
        int b;

        Node(int a, int b){
            this.a = a;
            this.b = b;
        }
    }
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        List<Node> list = new ArrayList<>();


        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Node(a,b)); //list에 객체 Node 추간
        }

        list.sort((node1,node2)->{
            int result = Integer.compare(node1.b,node2.b);
            if(result !=0){
                return result;
            }
            return Integer.compare(node1.a, node2.a);
        });

        for(Node node : list){
            System.out.println(node.a+" "+ node.b);
        }

    }
}
