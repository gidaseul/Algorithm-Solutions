import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int result = F(n);
        System.out.print(result);

    }

    static int F(int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 1;
        }

        return F(n-1) + F(n-2);

    }
}