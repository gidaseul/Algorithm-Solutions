import java.util.Scanner;

public class Main {

    static void comb(int idx, int depth){
        if(depth == n){
            for(int i=0; i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i=1; i<=k;i++){
            arr[depth] = i;
            comb(i,depth+1);
        }

    }
    static int k,n;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();
        n = sc.nextInt();
        // Please write your code here.
        arr = new int[n];
        comb(0,0);


    }
}