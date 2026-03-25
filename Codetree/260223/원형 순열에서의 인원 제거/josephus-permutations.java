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
        int count = 0;
        while(!queue.isEmpty()){
            count++;
            if(count%k == 0){
                int b = queue.poll();
                sb.append(b).append(" ");
            }else {
                int a = queue.poll();
                queue.add(a);
            }
        }
        System.out.println(sb);
    }
}
