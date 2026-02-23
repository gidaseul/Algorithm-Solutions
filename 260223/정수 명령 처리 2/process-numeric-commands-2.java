import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            String line = sc.next();
            if (line.equals("push")) {
                int a = sc.nextInt();
                queue.add(a);

            } else if (line.equals("front")) {
                System.out.println(queue.peek());
            } else if (line.equals("size")) {
                System.out.println(queue.size());
            } else if (line.equals("empty")) {
                if (queue.isEmpty()) {
                    System.out.println("0");
                } else System.out.println("1");
            } else if(line.equals("pop")){
                System.out.println(queue.poll());
            }
        }
    }
}
