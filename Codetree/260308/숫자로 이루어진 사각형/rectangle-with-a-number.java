import java.util.Scanner;
import java.util.*;


public class Main {

    static StringBuilder sb;



    public static void arrayPrint(int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(count ==9){
                    count=0;
                }
                count++;
                sb.append(count).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sb = new StringBuilder();
        // Please write your code here.
        arrayPrint(n);
    }

}