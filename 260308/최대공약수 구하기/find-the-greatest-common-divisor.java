import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int result = 0;
        // Please write your code here.
        if(n>m) result = get_gcd(n,m);
        else result = get_gcd(m,n);
        
        System.out.println(result);

    }

    static int get_gcd(int n, int m){
        while(m!=0){
            gcd(m,n%m);
        }
        return n;
    }
}