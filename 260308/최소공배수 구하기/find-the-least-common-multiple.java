import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Please write your code here.

        int result = gcd(n,m);
        int nResult = n/result;
        int mResult = m/result;
        int finalResult = result*nResult*mResult;
        System.out.println(finalResult);

    }

    static int gcd(int a, int b){
        while(b!=0){
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}