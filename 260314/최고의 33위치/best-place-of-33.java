import java.awt.*;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Main {

    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxResult = Integer.MIN_VALUE;
        n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        for(int i=0; i<n-2; i++){
            for(int j =0; j<n-2; j++){
                int result = search(i,j, grid);
                maxResult = Math.max(result,maxResult);
            }
        }
        System.out.println(maxResult);
    }

    static int search(int x, int y,int[][] arr){
        int count = 0;
        for(int i=0; i<3;i++) {
            for(int j=0; j<3; j++){
                int nx = x + i;
                int ny = y + j;

                if(nx >=0 && ny >= 0 && nx <n && ny <n && arr[nx][ny] == 1){
                    count++;
                }
            }
        }

        return count;
    }
}