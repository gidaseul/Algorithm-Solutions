import java.util.Scanner;

public class sample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A_math = sc.nextInt();
        int A_English = sc.nextInt();

        int B_math = sc.nextInt();
        int B_English = sc.nextInt();

        // Please write your code here.

        if (A_math > B_math && A_English >B_English ){
            System.out.println("1");
        }else  System.out.println("0");

    }
}
