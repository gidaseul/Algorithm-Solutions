import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();
        sb = new StringBuilder();
        int n = sc.nextInt();
        int k = sc.nextInt();

        // 1차적인 원형 만들기
        for(int i =1;i<=n;i++){
            queue.add(i);
        }

        while(queue.isEmpty()){
            for(int i=1; i<=k; i++){
                queue.add(queue.poll());
                if(i==k){
                    sb.append(queue.poll()).append(" ");
                }
            }
        }
        System.out.println(sb);
    }
}
