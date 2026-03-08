import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1;i<=n;i++){
            dq.addLast(i);
        }

        while(dq.size()>1){
            dq.pollFirst();
            int x = dq.pollFirst();
            dq.addLast(x);
        }

        System.out.println(dq.pollFirst());
    }
}