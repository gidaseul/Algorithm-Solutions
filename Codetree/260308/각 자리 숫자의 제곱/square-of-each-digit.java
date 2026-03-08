import java.util.Scanner;

public class Main {
    static int result;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        result = 0;
        add(n);
        System.out.print(result);
    
    }

    static void add(int n){
        
        if(n<10){
            result+=(n*n);
            return;
        }
        int r = n%10;
        result+=(r*r);
        add(n/10);
    
    }
}