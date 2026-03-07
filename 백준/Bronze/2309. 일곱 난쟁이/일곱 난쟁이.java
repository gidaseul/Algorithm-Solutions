import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;

    static int[] arr = new int[9];
    static int[] picked = new int[7];
    static boolean found = false;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }

        search(0, 0, 0);
    }

    static void search(int start, int depth, int sum) {
        if (found) return;

        if (depth == 7) {
            if (sum == 100) {
                Arrays.sort(picked);
                for (int x : picked) {
                    System.out.println(x);
                }
                found = true;
            }
            return;
        }

        for (int i = start; i < 9; i++) {
            picked[depth] = arr[i];
            search(i + 1, depth + 1, sum + arr[i]);
        }
    }
}