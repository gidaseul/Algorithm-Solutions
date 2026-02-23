import java.util.*;

public class Main {
    static StringBuilder sb;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<Integer> dq = new ArrayDeque<>();

        int n = sc.nextInt();
        for(int i =0; i<n; i++){
            String line = sc.next();
            if(line.equals("push_back")){
                int a = sc.nextInt();
                dq.addLast(a);
            } else if(line.equals("push_front")){
                int a = sc.nextInt();
                dq.addFirst(a);
            }else if(line.equals("pop_front")){
                System.out.println(dq.pollFirst());
            }else if(line.equals("pop_back")){
                System.out.println(dq.pollLast());
            }else if(line.equals("size")){
                System.out.println(dq.size());
            }else if(line.equals("empty")){
                if(dq.isEmpty()){
                    System.out.println("1");
                }else System.out.println("0");
            }else if(line.equals("front")){
                System.out.println(dq.peekFirst());
            }else if(line.equals("back")){
                System.out.println(dq.peekLast());
            }
        }
    }
}

