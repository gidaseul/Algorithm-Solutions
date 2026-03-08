import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int addResult = addResult(n);
        addResult /= 10;
        System.out.println(addResult);
    }
    static int addResult(int n){
        int sum = 0;

        sum = n * (n+1);
        sum/=2;
        return sum;
    }
}