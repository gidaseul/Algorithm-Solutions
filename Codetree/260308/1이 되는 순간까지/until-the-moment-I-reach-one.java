import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        int result = devide(n);
        System.out.print(result);

    }

    static int devide(int n){
        if(n==1){
            return 0;
        }
        if(n %2 ==0){
            return devide(n/2)+1;
        }
        else {
            return devide(n/3)+1;
        }
    }
}