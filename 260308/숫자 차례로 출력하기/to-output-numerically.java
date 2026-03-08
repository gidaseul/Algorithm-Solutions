import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        printa(n);
        System.out.print("\n");
        printb(n);
    }

    static void printa(int n){
        if(n==0){
            return;
        }
        printa(n-1);
        System.out.print(n+" ");

    }

    static void printb(int n){

        if(n==0){
            return;
        }

        System.out.print(n+" ");
        printb(n-1);


    }
}