import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        
        if ((A % 13) == 0 || (A%19) ==0){
            System.out.println("True");
        }else {System.out.println("Flase");
                }

    }
}
