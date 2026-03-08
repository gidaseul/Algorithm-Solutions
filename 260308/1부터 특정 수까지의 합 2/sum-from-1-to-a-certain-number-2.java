import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int result = add(n);
        System.out.print(result);
    }

    static int add(int n){
        if(n==0){
            return n;
        }
        return n+add(n-1);
    }
}